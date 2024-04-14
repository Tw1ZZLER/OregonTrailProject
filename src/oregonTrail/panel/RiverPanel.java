package oregonTrail.panel;

import javax.swing.*;
import oregonTrail.OregonTrail;
import oregonTrail.Travel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel class representing options available when located at a river in the Oregon Trail game.
 * This panel displays buttons for various actions such as caulking the wagon, waiting at the river,
 * and driving through the river.
 * It also includes action listeners for each button to handle the corresponding actions.
 * 
 * @author Ray Otto
 * @date 4/14/2024
 * @filename RiverPanel.java
 */
public class RiverPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private RiverPanel oregonTrail;
    private Travel travelState;
    private TravelPanel travelPanel;
    
    /**
     * Constructs a new RiverPanel with the specified OregonTrail instance and river.
     * 
     * @param currentRiver The OregonTrail instance associated with the game.
     * @param travelPanel A label to display the status of the river.
     */
    public RiverPanel(RiverPanel currentRiver, TravelPanel travelPanel) {
        this.oregonTrail = currentRiver;
        this.setTravelState(currentRiver.getTravelState());
        this.setTravelPanel(travelPanel);

        setLayout(new BorderLayout());
        JLabel promptLabel = new JLabel("You have reached a river. Choose how to cross:");
        promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        promptLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size to 24
        add(promptLabel, BorderLayout.NORTH);

        // Create panel for buttons on the bottom half
        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        // Create buttons
        JButton caulkButton = new JButton("Caulk the Wagon");
        JButton waitButton = new JButton("Wait at River");
        JButton driveButton = new JButton("Drive Through River");

        // Add buttons to button panel
        buttonPanel.add(caulkButton);
        buttonPanel.add(waitButton);
        buttonPanel.add(driveButton);

        // Add action listeners to buttons
        caulkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Caulk the Wagon button clicked");
                travelPanel.setText("Attempting to caulk the wagon...");
                oregonTrail.getTravelState().handleRiverCrossingDecision("caulk");
            }
        });

        waitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Wait at River button clicked");
                travelPanel.setText("Waiting at the river...");
                oregonTrail.getTravelState().handleRiverCrossingDecision("wait");
            }
        });

        driveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Drive Through River button clicked");
                travelPanel.setText("Attempting to drive through the river...");
                oregonTrail.getTravelState().handleRiverCrossingDecision("drive");
            }
        });
    }

	public Travel getTravelState() {
		return travelState;
	}

	public void setTravelState(Travel travelState) {
		this.travelState = travelState;
	}

	public TravelPanel getTravelPanel() {
		return travelPanel;
	}

	public void setTravelPanel(TravelPanel travelPanel) {
		this.travelPanel = travelPanel;
	} 
}
