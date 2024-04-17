package oregonTrail.panel;

import javax.swing.*;
import oregonTrail.OregonTrail;
import oregonTrail.Travel;
import oregonTrail.landmark.Fort;
import oregonTrail.landmark.River;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel class representing options available when located at a river in the Oregon Trail game.
 * This panel displays buttons for various actions such as opening the map, changing the rate of travel,
 * changing rations, attempting to cross the river, caulking the wagon, and waiting a day.
 * It also includes action listeners for each button to handle the corresponding actions.
 * 
 * @author Ray Otto
 * @date 2024-14-04
 * @filename RiverPanel.java
 */
public class RiverPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private OregonTrail oregonTrail;
    private Travel travelState;
    private River river;
    private String riverName;
    private ImageIcon riverImageIcon;
    
    /**
     * Constructs a new RiverPanel with the specified OregonTrail instance and river image icon.
     * 
     * @param pOregonTrail The OregonTrail instance associated with the game.
     * @param river The river object used for this panel.
     */
    public RiverPanel(OregonTrail pOregonTrail, River river) {
        this.oregonTrail = pOregonTrail;
        this.travelState = pOregonTrail.getTravelState();
        this.river = river;
        this.riverName = river.getName();
        
        // Scale up image
        // Assuming river.getPicture() returns an ImageIcon
        ImageIcon originalIcon = river.getPicture();
        Image originalImage = originalIcon.getImage();

        // Scale the image using getScaledInstance
        Image scaledImage = originalImage.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);

        // Convert the scaled Image back to ImageIcon
        this.riverImageIcon = new ImageIcon(scaledImage);
        

        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the " + riverName);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size to 24
        add(welcomeLabel, BorderLayout.NORTH);

        // Create image label and add it to the top half
        JLabel imageLabel = new JLabel(riverImageIcon);
        add(imageLabel, BorderLayout.CENTER);

        // Create panel for buttons on the bottom half
        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        // Create buttons
        JButton openMapButton = new JButton("Open Map");
        JButton viewRiverDataButton = new JButton("River Stats");
        JButton changeRateButton = new JButton("Change Rate of Travel");
        JButton changeRationsButton = new JButton("Change Rations");
        JButton attemptToCrossButton = new JButton("Attempt to Cross");
        JButton caulkWagonButton = new JButton("Caulk the Wagon");
        JButton waitButton = new JButton("Wait");

        // Add buttons to button panel
        buttonPanel.add(openMapButton);
        buttonPanel.add(viewRiverDataButton);
        buttonPanel.add(changeRateButton);
        buttonPanel.add(changeRationsButton);
        buttonPanel.add(attemptToCrossButton);
        buttonPanel.add(caulkWagonButton);
        buttonPanel.add(waitButton);

        // Add action listeners to buttons
        openMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open Map button clicked");
            }
        });
        
        viewRiverDataButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		river.viewRiverData(RiverPanel.this);
        	}
        });

        changeRateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oregonTrail.WAGON.travelSpeedDialog(RiverPanel.this);
            }
        });

        changeRationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oregonTrail.WAGON.foodConsumptionDialog(RiverPanel.this);
            }
        });

        attemptToCrossButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	river.attemptToCross(RiverPanel.this);
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            }
        });
      
        caulkWagonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				river.caulkWagon(RiverPanel.this);
	        	oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            }
        });

        waitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            }
        });
    } 
}
