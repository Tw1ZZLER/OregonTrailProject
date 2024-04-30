package oregonTrail.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import oregonTrail.OregonTrail;
import oregonTrail.landmark.Fort;
import oregonTrail.landmark.LandmarkType;

/**
 * Panel class representing options available when located at a fort in the Oregon Trail game.
 * This panel displays buttons for various actions such as opening the map, changing the rate of travel,
 * changing rations, attempting to trade, opening the shop, and continuing the trail.
 * It also includes action listeners for each button to handle the corresponding actions.
 * 
 * @author Lukas Dunbar
 * @date 2024-04-09
 * @filename FortPanel.java
 */
public class FortPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private OregonTrail oregonTrail;
    private String fortName;
    private ImageIcon fortImageIcon;
    
    /**
     * Constructs a new FortPanel with the specified OregonTrail instance and fort image icon
     * which are obtained from a passed Fort object.
     * 
     * @param pOregonTrail The OregonTrail instance associated with the game.
     * @param fort The Fort object used for this panel.
     */
    public FortPanel(OregonTrail pOregonTrail, Fort fort) {
        this.oregonTrail = pOregonTrail;
        this.fortName = fort.getName();
        
        // Scale up image
        ImageIcon originalIcon = fort.getPicture();
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        this.fortImageIcon = new ImageIcon(scaledImage);
        

        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to " + fortName +"!!!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size to 24
        add(welcomeLabel, BorderLayout.NORTH);

        // Create image label and add it to the top half
        JLabel imageLabel = new JLabel(fortImageIcon);
        add(imageLabel, BorderLayout.CENTER);

        // Create panel for buttons on the bottom half
        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        // Create buttons
        JButton openMapButton = new JButton("Open Map");
        JButton changeRateButton = new JButton("Change Rate of Travel");
        JButton changeRationsButton = new JButton("Change Rations");
        JButton attemptTradeButton = new JButton("Attempt to Trade");
        JButton openShopButton = new JButton("Open Shop");
        JButton continueButton = new JButton("Continue Trail");
        JButton talkToLocals = new JButton("Talk to Locals");

        // Add buttons to button panel
        buttonPanel.add(openMapButton);
        buttonPanel.add(changeRateButton);
        buttonPanel.add(changeRationsButton);
        buttonPanel.add(attemptTradeButton);
        buttonPanel.add(openShopButton);
        buttonPanel.add(continueButton);
        buttonPanel.add(talkToLocals);

        // Add action listeners to buttons
        openMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openMap(fortName);
            }
        });

        changeRateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //oregonTrail.getTravelState().changeRateOfTravel(this);
            	  System.out.println("Change Rate of Travel button clicked");
            }
        });

        changeRationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // oregonTrail.getTravelState().changeRations(this);
            	  System.out.println("Change Rattions button clicked");
            }
        });

        attemptTradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openPanel(oregonTrail.TRADE_PANEL);
            }
        });

        openShopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openPanel(oregonTrail.SHOP_PANEL);
            }
        });
        
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            }
        });
        
        talkToLocals.addActionListener(new ActionListener() {
            private boolean eventOccurred = false; // Track if the special event has occurred

            public void actionPerformed(ActionEvent arg0) {
                if (!eventOccurred) {
                    // Create an instance of Random class
                    Random random = new Random();

                    // Generate a random number between 1 and 10
                    int randomNumber = random.nextInt(10) + 1;
                    if (randomNumber == 1) {
                        // Show special event dialogue box
                        JOptionPane.showMessageDialog(null, "As you were making breakfast, Dick Delay accidentally steps on his shotgun shooting Mr. Ridgel, the shop owner. You decide it's probably best to leave town.");
                        // Continue the game
                        oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
                        eventOccurred = true; // Set to true to indicate the special event has occurred
                    } else {
                        // Normal dialogue
                        if (LandmarkType.FORT_LARAMIE.getLandmark().isVisited()) {
                            JOptionPane.showMessageDialog(null, "(You're met by a short young girl with blonde hair, you think her name might be Alice from locals chatting) Did you see all those indians last night? I swear there must have been hundreds of em");
                        } else if (LandmarkType.FORT_OREGON.getLandmark().isVisited()) {
                            JOptionPane.showMessageDialog(null, "(You're met by god?) So you finally made it, congratulations, we lost nearly all of our family and it looks like you almost shared the same fate. Now are you gonna play again?");
                        } else {
                            JOptionPane.showMessageDialog(null, "No one wanted to talk with you (Probably cause you stink)");
                        }
                    }
                } else {
                    // All further conversations after the special event
                    JOptionPane.showMessageDialog(null, "You've already had your conversation. No one wants to talk with you again.");
                }
            }
        });
    }
}

