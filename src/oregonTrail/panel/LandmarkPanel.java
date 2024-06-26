package oregonTrail.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import oregonTrail.OregonTrail;
import oregonTrail.landmark.Landmark;

/**
 * Panel class representing options available when located at a landmark in the Oregon Trail game.
 * This panel displays buttons for various actions such as opening the map, changing the rate of travel,
 * changing rations, attempting to trade, and continuing the trail.
 * It also includes action listeners for each button to handle the corresponding actions.
 * 
 * @author Corbin Hibler
 * @date 2024-04-17
 * @filename LandmarkPanel.java
 */
public class LandmarkPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private OregonTrail oregonTrail;
    private String landmarkName;
    private ImageIcon landmarkImageIcon;
    
    /**
     * Constructs a new FortPanel with the specified OregonTrail instance and fort image icon
     * which are obtained from a passed Fort object.
     * 
     * @param pOregonTrail The OregonTrail instance associated with the game.
     * @param fort The Fort object used for this panel.
     */
    public LandmarkPanel(OregonTrail pOregonTrail, Landmark landmark) {
        this.oregonTrail = pOregonTrail;
        this.landmarkName = landmark.getName();
        
        // Scale up image
        ImageIcon originalIcon = landmark.getPicture();
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        this.landmarkImageIcon = new ImageIcon(scaledImage);
        
        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("WELCOME TO " + landmarkName +"!!!");
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(Color.BLACK);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Impact", Font.BOLD, 30)); // Set font size to 24
        add(welcomeLabel, BorderLayout.NORTH);

        // Create image label and add it to the top half
        JLabel imageLabel = new JLabel(landmarkImageIcon);
        add(imageLabel, BorderLayout.CENTER);

        // Create panel for buttons on the bottom half
        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        // Create buttons
        JButton openMapButton = new JButton("Open Map");
        openMapButton.setBackground(Color.BLACK);
        openMapButton.setForeground(Color.WHITE);
        openMapButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton changeRateButton = new JButton("Change Pace");
        changeRateButton.setBackground(Color.BLACK);
        changeRateButton.setForeground(Color.WHITE);
        changeRateButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton changeRationsButton = new JButton("Change Rations");
        changeRationsButton.setBackground(Color.BLACK);
        changeRationsButton.setForeground(Color.WHITE);
        changeRationsButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton attemptTradeButton = new JButton("Attempt to Trade");
        attemptTradeButton.setBackground(Color.BLACK);
        attemptTradeButton.setForeground(Color.WHITE);
        attemptTradeButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton continueButton = new JButton("Continue Trail");
        continueButton.setBackground(Color.BLACK);
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton talkToLocals = new JButton("Talk to Locals");
        talkToLocals.setBackground(Color.BLACK);
        talkToLocals.setForeground(Color.WHITE);
        talkToLocals.setFont(new Font("Impact", Font.PLAIN, 20));

        // Add buttons to button panel
        buttonPanel.add(openMapButton);
        buttonPanel.add(changeRateButton);
        buttonPanel.add(changeRationsButton);
        buttonPanel.add(attemptTradeButton);
        buttonPanel.add(continueButton);
        buttonPanel.add(talkToLocals);

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
        
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            }
        });
        talkToLocals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	oregonTrail.getDialogueState().talkToLandmarkLocals();
            }
        });
    } 
}
