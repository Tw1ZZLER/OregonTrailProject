package oregonTrail.panel;

import javax.swing.*;
import oregonTrail.OregonTrail;
import oregonTrail.Travel;
import oregonTrail.landmark.Fort;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private Travel travelState;
    private Fort fort;
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
        this.travelState = pOregonTrail.getTravelState();
        this.fort = fort;
        this.fortName = fort.getName();
        
        // Scale up image
    	// Assuming river.getPicture() returns an ImageIcon
        ImageIcon originalIcon = fort.getPicture();
        Image originalImage = originalIcon.getImage();

        // Calculate the new dimensions (double the original size)
        int newWidth = originalImage.getWidth(null) * 2;
        int newHeight = originalImage.getHeight(null) * 2;

        // Scale the image using getScaledInstance
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Convert the scaled Image back to ImageIcon
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

        // Add buttons to button panel
        buttonPanel.add(openMapButton);
        buttonPanel.add(changeRateButton);
        buttonPanel.add(changeRationsButton);
        buttonPanel.add(attemptTradeButton);
        buttonPanel.add(openShopButton);
        buttonPanel.add(continueButton);

        // Add action listeners to buttons
        openMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open Map button clicked");
            }
        });

        changeRateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Rate of Travel button clicked");
            }
        });

        changeRationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Rations button clicked");
            }
        });

        attemptTradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openPanel(oregonTrail.TRADE_PANEL);
            }
        });

        openShopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open Shop button clicked");
            }
        });
        
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            }
        });
    } 
}
