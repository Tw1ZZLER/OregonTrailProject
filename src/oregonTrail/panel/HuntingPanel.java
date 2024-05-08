package oregonTrail.panel;

import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import oregonTrail.HuntingKeyEventDispatcher;
import oregonTrail.OregonTrail;

/**
 * class containing all game logic for the Hunting activity and instantiating enemy and player sprites.
 *
 * @authors Ray Otto
 * @date 2024-04-09
 * @filename HuntingPanel.Java
 */

public class HuntingPanel extends JPanel {
   
    private JLabel sprite; // Player's sprite
    private JLabel enemyLabel; // Label displaying enemy's health
    private JLabel winnerLabel; // Label for displaying game outcome
    private JLabel enemySprite; // Enemy's sprite
    private int enemyHealth; // Enemy's health
    private String enemyType; // Type of enemy
    private int playerHealth; // Player's health
    private Timer punchTimer; // Timer for player's punch cooldown
    private Timer enemyAttackTimer; // Timer for enemy's attack
    private boolean gameRunning; // Bool to check if game is running
    private int countdown; // Countdown timer for the game

    // ImageIcons for player's sprite and enemies
    private ImageIcon standingIcon; // Player's standing sprite
    private ImageIcon blockingIcon; // Player's blocking sprite
    private ImageIcon rightPunchIcon; // Player's right punch sprite
    private ImageIcon leftPunchIcon; // Player's left punch sprite
    private ImageIcon bisonImage; // Bison enemy image
    private ImageIcon squirrelImage; // Squirrel enemy image
    private ImageIcon coyoteImage; // Coyote enemy image
    private ImageIcon deerImage; // Deer enemy image
    private ImageIcon laurieMooImage; // LaurieMoo enemy image

    private boolean punchCooldown; // Flag to check if player's punch is on cooldown
    private boolean blocking; // Flag to check if player is blocking
    private OregonTrail oregonTrail; // Reference to the main game class

    // Method to scale ImageIcon
    private ImageIcon scaleImageIcon1(ImageIcon icon, int scale) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(img.getWidth(null) * scale, img.getHeight(null) * scale, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
    
    /**
     * Constructor for the HuntingPanel class
     * @param oregonTrail Reference to the main game class
     */
    public HuntingPanel(OregonTrail oregonTrail) {
        this.oregonTrail = oregonTrail;

        // Display warning messages
        JOptionPane.showMessageDialog(this,
                "Warning: You are about to play the hunting minigame.",
                "Inane warning",
                JOptionPane.WARNING_MESSAGE);

        JOptionPane.showMessageDialog(this,
                "Please note that if you proceed, the fun you will experience as well as the events in the game will not be historically accurate.",
                "Inane warning",
                JOptionPane.WARNING_MESSAGE);

        int decision = JOptionPane.showConfirmDialog(this,
                "Would you like to proceed?",
                "A Silly Question",
                JOptionPane.YES_NO_OPTION);

        // If user chooses not to proceed, display message and return
        if (decision == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "Your husband went hunting and found nothing. You could have done better, not only in hunting but also in marriage.",
                    "Result",
                    JOptionPane.INFORMATION_MESSAGE);
            gameRunning = false;
            oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            return;
        }

        initializeGame(); // Initialize the hunting game
    }
    
    /**
     * Initializes the game by setting up components and starting the game loop
     */
    private void initializeGame() {
        // Set layout to null and initialize components
        setLayout(null);
        initialize();
        gameRunning = true;
        punchCooldown = false;
        blocking = false;
        countdown = 60;
        playerHealth = 5;
        Random rand = new Random();
        int random = rand.nextInt(5) + 1;

        // Randomly choose an enemy type
        switch (random) {
            case 1:
                // Initialize squirrel enemy
                enemyHealth = 1;
                enemyType = "Squirrel";
                enemyLabel.setText(enemyType + " Health: " + enemyHealth);
                rotateSprite(squirrelImage);
                enemyAttackTimer.setDelay(2000);
                break;
            case 2:
                // Initialize deer enemy
                enemyHealth = 8;
                enemyType = "Deer";
                enemyLabel.setText(enemyType + " Health: " + enemyHealth);
                rotateSprite(deerImage);
                enemyAttackTimer.setDelay(5000);
                break;
            case 3:
                // Initialize coyote enemy
                enemyHealth = 12;
                enemyType = "Coyote";
                enemyLabel.setText(enemyType + " Health: " + enemyHealth);
                rotateSprite(coyoteImage);
                break;
            case 4:
                // Initialize bison enemy
                enemyHealth = 20;
                enemyType = "Bison";
                enemyLabel.setText(enemyType + " Health: " + enemyHealth);
                rotateSprite(bisonImage);
                enemyAttackTimer.setDelay(10000);
                break;
            case 5:
                // Initialize LaurieMoo enemy
                enemyHealth = 1;
                enemyType = "LaurieMoo";
                enemyLabel.setText(enemyType + " Health: " + enemyHealth);
                rotateSprite(laurieMooImage);
                enemyAttackTimer.setDelay(20000);
                break;
            default:
                break;
        }

        // Start game loop
        if (gameRunning) {
            enemyAttackTimer.start();
            Timer countdownTimer = new Timer(1000, e -> {
                if (gameRunning) {
                    if (playerHealth > 0) {
                        winnerLabel.setText("Time Left: " + (--countdown) + " seconds");
                    } else {
                        gameRunning = false;
                        sprite.setVisible(false);
                        JOptionPane.showMessageDialog(this, "You've been defeated! The enemy " + enemyType + " ran away.");
                    }
                    if (countdown == 0) {
                        gameRunning = false;
                        sprite.setVisible(false);
                        enemySprite.setVisible(false);
                        winnerLabel.setText("Time's up! The enemy " + enemyType + " ran away.");
                    }
                }
            });
            countdownTimer.setInitialDelay(0);
            countdownTimer.start();
        }

        this.requestFocusInWindow();
    }

    // Method to initialize components
    private void initialize() {
        // Initialize ImageIcons for sprites
        standingIcon = new ImageIcon("src/images/standing1.png");
        blockingIcon = new ImageIcon("src/images/blocking1.png");
        rightPunchIcon = new ImageIcon("src/images/rightPunch1.png");
        leftPunchIcon = new ImageIcon("src/images/leftPunch1.png");
        bisonImage = new ImageIcon("src/images/Bison1.png");
        squirrelImage = new ImageIcon("src/images/Squirell1.png");
        coyoteImage = new ImageIcon("src/images/coyote.png");
        deerImage = new ImageIcon("src/images/deer.png");
        laurieMooImage = new ImageIcon("src/images/LaurieMoo.png");

        // Scale the ImageIcons
        standingIcon = scaleImageIcon1(standingIcon, 10);
        blockingIcon = scaleImageIcon1(blockingIcon, 10);
        rightPunchIcon = scaleImageIcon1(rightPunchIcon, 10);
        leftPunchIcon = scaleImageIcon1(leftPunchIcon, 10);
        bisonImage = scaleImageIcon1(bisonImage, 1);
        squirrelImage = scaleImageIcon1(squirrelImage, 1);
        coyoteImage = scaleImageIcon1(coyoteImage, 1);

        // Initialize player's sprite
        sprite = new JLabel(standingIcon);
        sprite.setBounds(823, 334, standingIcon.getIconWidth(), standingIcon.getIconHeight());
        add(sprite);

        // Initialize enemy's sprite and labels
        enemySprite = new JLabel();
        enemySprite.setHorizontalAlignment(SwingConstants.CENTER);
        enemySprite.setBounds(823, 193, 320, 300);
        enemySprite.setIcon(laurieMooImage);
        add(enemySprite);

        enemyLabel = new JLabel("Enemy");
        enemyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        enemyLabel.setBounds(873, 275, 198, 14);
        add(enemyLabel);

        winnerLabel = new JLabel();
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setBounds(873, 696, 198, 20);
        add(winnerLabel);

        JLabel playerHealthLabel = new JLabel("Player Health: ");
        playerHealthLabel.setBounds(937, 665, 120, 20);
        add(playerHealthLabel);

        JLabel playerHealthValueLabel = new JLabel("5");
        playerHealthValueLabel.setBounds(1031, 665, 40, 20);
        add(playerHealthValueLabel);

        // Add KeyListener for handling player's actions
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_S) {
                    block();
                } else if (keyCode == KeyEvent.VK_A) {
                    leftPunch();
                } else if (keyCode == KeyEvent.VK_D) {
                    rightPunch();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                unblock();
            }
        });

        // Initialize timers
        punchTimer = new Timer(400, e -> {
            punchCooldown = false;
            punchTimer.stop();
        });

        enemyAttackTimer = new Timer(1000, e -> {
            if (enemyHealth <= 0 || !gameRunning) {
                enemyAttackTimer.stop();
                return;
            }

            enemySprite.setLocation(enemySprite.getX(), enemySprite.getY() - 20);

            Timer raiseTimer = new Timer(500, evt -> {
                enemySprite.setLocation(enemySprite.getX(), enemySprite.getY() + 20);

                enemySprite.setLocation(enemySprite.getX(), enemySprite.getY() + 20);

                Timer attackTimer = new Timer(200, attackEvt -> {
                    enemySprite.setLocation(enemySprite.getX(), enemySprite.getY() - 20);

                    switch (enemyType) {
                        case "Squirrel":
                            break;
                        case "Deer":
                            if (!blocking) {
                                playerHealth -= 2;
                                playerHealthValueLabel.setText(Integer.toString(playerHealth));
                            }
                        case "Coyote":
                            if (!blocking) {
                                playerHealth -= 1;
                                playerHealthValueLabel.setText(Integer.toString(playerHealth));
                            }
                            break;
                        case "Bison":
                            if (!blocking) {
                                playerHealth -= 4;
                                playerHealthValueLabel.setText(Integer.toString(playerHealth));
                            }
                            break;
                        case "LaurieMoo":
                            if (!blocking) {
                                playerHealth -= 1;
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
                        System.exit(0);
                    }
                });
                attackTimer.setRepeats(false);
                attackTimer.start();
            });
            raiseTimer.setRepeats(false);
            raiseTimer.start();
        });

        enemyAttackTimer.setInitialDelay(2000);

        // Add KeyEventDispatcher for handling player's actions
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new HuntingKeyEventDispatcher(this));
    }

    // Method for handling player's blocking action
    public void block() {
        sprite.setIcon(blockingIcon);
        blocking = true;
    }

    // Method for handling player's unblocking action
    public void unblock() {
        sprite.setIcon(standingIcon);
        blocking = false;
    }

    // Method for handling player's left punch action
    public void leftPunch() {
        if (!punchCooldown) {
            sprite.setIcon(leftPunchIcon);
            if (enemyHealth > 0 && !blocking) {
                enemyHealth -= 1;
                updateEnemyLabel();
            }
            startPunchTimer();
            punchCooldown = true;
        }
    }

    // Method for handling player's right punch action
    public void rightPunch() {
        if (!punchCooldown) {
            sprite.setIcon(rightPunchIcon);
            if (enemyHealth > 0 && !blocking) {
                enemyHealth -= 1;
                updateEnemyLabel();
            }
            startPunchTimer();
            punchCooldown = true;
        }
    }

    // Method to update enemy's health label
    private void updateEnemyLabel() {
        if (enemyHealth > 0) {
            enemyLabel.setText(enemyType + " Health: " + enemyHealth);
        } else {
            enemyLabel.setText(enemyType + " Defeated!");
            winnerLabel.setText(" Winner!");
            gameRunning = false;
        }
    }

    // Method to start punch cooldown timer
    private void startPunchTimer() {
        punchTimer.restart();
    }

    // Method to rotate enemy's sprite
    private void rotateSprite(ImageIcon enemyImage) {
        enemySprite.setIcon(enemyImage);
    }

    // Method to scale ImageIcon
    private ImageIcon scaleImageIcon(ImageIcon icon, int scale) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(img.getWidth(null) / scale, img.getHeight(null) / scale, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    // Main method for testing the panel
    public static void main(String[] args) {
        OregonTrail oregonTrail = new OregonTrail();
        oregonTrail.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HuntingPanel huntingPanel = new HuntingPanel(oregonTrail);
        oregonTrail.frame.getContentPane().add(huntingPanel);
        oregonTrail.frame.setVisible(true);
    }
}
