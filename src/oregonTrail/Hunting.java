package oregonTrail.panel;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.util.Random;

public class Hunting extends JPanel {

    private JLabel sprite;
    private JLabel enemyLabel;
    private JLabel winnerLabel;
    private JLabel enemySprite;
    private int enemyHealth;
    private String enemyType;
    private int playerHealth;
    private Timer punchTimer;
    private Timer enemyAttackTimer;
    private boolean gameRunning;
    private int countdown;

    private ImageIcon standingIcon;
    private ImageIcon blockingIcon;
    private ImageIcon rightPunchIcon;
    private ImageIcon leftPunchIcon;
    private ImageIcon bisonImage;
    private ImageIcon squirrelImage;
    private ImageIcon coyoteImage;
    private ImageIcon deerImage;
    private ImageIcon laurieMooImage;

    private boolean punchCooldown;
    private boolean blocking;

    public HuntingPanel() {
        setLayout(null); // For absolute positioning
        initialize();
    }

    private void initialize() {
        standingIcon = new ImageIcon("src/images/standing1.png");
        blockingIcon = new ImageIcon("src/images/blocking1.png");
        rightPunchIcon = new ImageIcon("src/images/rightPunch1.png");
        leftPunchIcon = new ImageIcon("src/images/leftPunch1.png");
        bisonImage = new ImageIcon("src/images/Bison1.png");
        squirrelImage = new ImageIcon("src/images/Squirell1.png");
        coyoteImage = new ImageIcon("src/images/coyote.png");
        deerImage = new ImageIcon("src/images/deer.png");
        laurieMooImage = new ImageIcon("src/images/LaurieMoo.png");

        standingIcon = scaleImageIcon(standingIcon, 10);
        blockingIcon = scaleImageIcon(blockingIcon, 10);
        rightPunchIcon = scaleImageIcon(rightPunchIcon, 10);
        leftPunchIcon = scaleImageIcon(leftPunchIcon, 10);
        bisonImage = scaleImageIcon(bisonImage, 1);
        squirrelImage = scaleImageIcon(squirrelImage, 1);
        coyoteImage = scaleImageIcon(coyoteImage, 1);

        sprite = new JLabel(standingIcon);
        sprite.setBounds(200, 145, standingIcon.getIconWidth(), standingIcon.getIconHeight());
        add(sprite);

        enemySprite = new JLabel();
        enemySprite.setHorizontalAlignment(SwingConstants.CENTER);
        enemySprite.setBounds(200, 4, 320, 300); // Adjusted bounds
        add(enemySprite);

        enemyLabel = new JLabel("Enemy");
        enemyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        enemyLabel.setBounds(250, 86, 198, 14);
        add(enemyLabel);

        winnerLabel = new JLabel();
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setBounds(250, 507, 198, 20);
        add(winnerLabel);

        JLabel playerHealthLabel = new JLabel("Player Health: ");
        playerHealthLabel.setBounds(314, 476, 120, 20);
        add(playerHealthLabel);

        JLabel playerHealthValueLabel = new JLabel("5");
        playerHealthValueLabel.setBounds(408, 476, 40, 20);
        add(playerHealthValueLabel);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!gameRunning) return;
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_DOWN) {
                    sprite.setIcon(blockingIcon);
                    blocking = true;
                } else if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) && !punchCooldown) {
                    if (key == KeyEvent.VK_LEFT) {
                        sprite.setIcon(leftPunchIcon);
                    } else {
                        sprite.setIcon(rightPunchIcon);
                    }
                    if (enemyHealth > 0 && !blocking) {
                        enemyHealth -= 1;
                        updateEnemyLabel();
                    }
                    startPunchTimer();
                    punchCooldown = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                sprite.setIcon(standingIcon);
                blocking = false;
            }
        });

        punchTimer = new Timer(400, e -> {
            punchCooldown = false;
            punchTimer.stop();
        });

        enemyAttackTimer = new Timer(1000, e -> {
            if (enemyHealth <= 0 || !gameRunning) {
                enemyAttackTimer.stop();
                return;
            }

            // Raise enemy sprite for signaling the attack
            enemySprite.setLocation(enemySprite.getX(), enemySprite.getY() - 20);

            Timer raiseTimer = new Timer(500, evt -> {
                // Hold for half a second
                // Move enemy sprite back to original position after holding
                enemySprite.setLocation(enemySprite.getX(), enemySprite.getY() + 20);

                // Move enemy sprite down for attacking animation
                enemySprite.setLocation(enemySprite.getX(), enemySprite.getY() + 20);

                // Move enemy sprite back to original position after attacking animation
                Timer attackTimer = new Timer(200, attackEvt -> {
                    enemySprite.setLocation(enemySprite.getX(), enemySprite.getY() - 20);

                    // Enemy attacking logic
                    Random rand = new Random();
                    switch (enemyType) {
                        case "Squirrel":
                            // Squirrel attacks every 2 seconds but deals zero damage
                            break;
                        case "Deer":
                            // Deer attacks every 5 seconds and deals 2 damage
                            if (!blocking) {
                                playerHealth -= 2;
                                playerHealthValueLabel.setText(Integer.toString(playerHealth));
                            }
                            break;
                        case "Bison":
                            // Bison attacks every 10 seconds and deals 4 damage
                            if (!blocking) {
                                playerHealth -= 4;
                                playerHealthValueLabel.setText(Integer.toString(playerHealth));
                            }
                            break;
                        case "LaurieMoo":
                            // LaurieMoo attacks every 20 seconds and deals 999 damage
                            if (!blocking) {
                                playerHealth -= 999;
                                playerHealthValueLabel.setText(Integer.toString(playerHealth));
                            }
                            break;
                        default:
                            break;
                    }

                    if (playerHealth <= 0) {
                        gameRunning = false;
                        sprite.setVisible(false);
                        JOptionPane.showMessageDialog(getParent(), "You've been defeated! The enemy " + enemyType + " ran away.");
                    }
                });
                attackTimer.setRepeats(false);
                attackTimer.start();
            });
            raiseTimer.setRepeats(false);
            raiseTimer.start();
        });

        enemyAttackTimer.setInitialDelay(2000); // Delay before enemy starts attacking
    }

    private ImageIcon scaleImageIcon(ImageIcon icon, int scale) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(icon.getIconWidth() * scale, icon.getIconHeight() * scale, Image.SCALE_DEFAULT);
        return new ImageIcon(scaledImg);
    }

    private void updateEnemyLabel() {
        if (enemyHealth > 0) {
            enemyLabel.setText(enemyType + " Health: " + enemyHealth);
        } else {
            enemyLabel.setText(enemyType + " Defeated!");
            winnerLabel.setText(" Winner!");
            gameRunning = false;
        }
    }

    private void startPunchTimer() {
        punchTimer.restart();
    }

    private void rotateSprite(ImageIcon enemyImage) {
        enemySprite.setIcon(enemyImage);
    }
}
