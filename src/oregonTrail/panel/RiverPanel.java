package oregonTrail.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import oregonTrail.OregonTrail;
import oregonTrail.Travel;

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

    /**
     * Constructs a new RiverPanel with the specified OregonTrail instance and river image icon.
     * 
     * @param pOregonTrail The OregonTrail instance associated with the game.
     * @param riverImageIcon The ImageIcon representing the river.
     */
    public RiverPanel(OregonTrail pOregonTrail, ImageIcon riverImageIcon) {
        this.oregonTrail = pOregonTrail;
        this.travelState = pOregonTrail.getTravelState();

        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Kansas River");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size to 24
        add(welcomeLabel, BorderLayout.NORTH);

        // Create image label and add it to the top half
        Image scaledImage = riverImageIcon.getImage().getScaledInstance(450, 249, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        add(imageLabel, BorderLayout.CENTER);

        // Create panel for buttons on the bottom half
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        // Create buttons
        JButton attemptToCrossButton = new JButton("Attempt to Cross"); // Cross river, add distance, chance to lose items if river is too deep
        JButton CaulkWagonButton = new JButton("Caulk the Wagon"); // Takes a day and allows you to cross river if light enough
        JButton WaitButton = new JButton("Wait"); // Should add a day to current day

        // Add buttons to button panel
        buttonPanel.add(attemptToCrossButton);
        buttonPanel.add(CaulkWagonButton);
        buttonPanel.add(WaitButton);

        // Add action listeners to buttons
        attemptToCrossButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crossRiver();
            }
        });

        CaulkWagonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                caulkWagon();
            }
        });

        WaitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                waitDay();
            }
        });
    }

    // Method to handle crossing the river
    private void crossRiver() {
       
        int milesCrossed = 1; // Example value
        travelState.setMilesNextLandmark(travelState.getMilesNextLandmark() - milesCrossed);
        oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL, this);
    }

    // Method to handle caulking the wagon
    private void caulkWagon() {
      //add
        travelState.travelToggle();
    }

    // Method to handle waiting
    private void waitDay() {
    	travelState.travelToggle();
        oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL, this);
    }
}

