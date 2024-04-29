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
import oregonTrail.landmark.Landmark;
import oregonTrail.landmark.LandmarkType;

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
        pOregonTrail.getTravelState();
        this.landmarkName = landmark.getName();
        
        // Scale up image
    	// Assuming river.getPicture() returns an ImageIcon
        ImageIcon originalIcon = landmark.getPicture();
        Image originalImage = originalIcon.getImage();

        // Scale the image using getScaledInstance
        Image scaledImage = originalImage.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);

        // Convert the scaled Image back to ImageIcon
        this.landmarkImageIcon = new ImageIcon(scaledImage);
        

        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to " + landmarkName +"!!!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size to 24
        add(welcomeLabel, BorderLayout.NORTH);

        // Create image label and add it to the top half
        JLabel imageLabel = new JLabel(landmarkImageIcon);
        add(imageLabel, BorderLayout.CENTER);

        // Create panel for buttons on the bottom half
        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        // Create buttons
        JButton openMapButton = new JButton("Open Map");
        JButton changeRateButton = new JButton("Change Rate of Travel");
        JButton changeRationsButton = new JButton("Change Rations");
        JButton attemptTradeButton = new JButton("Attempt to Trade");
        JButton continueButton = new JButton("Continue Trail");
        JButton talkToLocals = new JButton("Talk to Locals");

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
            	 // Create an instance of Random class
                Random random = new Random();
                // Generate a random number between 1 and 20
                int randomNumber = random.nextInt(20) + 1;
                if(randomNumber>=2) {
            	if(LandmarkType.ASH_HOLLOW.getLandmark().isVisited()) {
            		JOptionPane.showMessageDialog(null, "(You're met by Uncle Hugh) Look at all those reeds it almost makes me want to set up camp here, its a shame its so wide open though. I wouldnt make it a week!");
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "No one wanted to talk with you (Probably cause you stink)");
            	}
                }
            	else {
            		JOptionPane.showMessageDialog(null, "No one wanted to talk with you (Probably cause you stink)");
            	}
            }
        });
    } 
}
