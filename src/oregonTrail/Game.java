package oregonTrail;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game {

	private JFrame frame;
	private Wagon wagon;
	private JLabel totalWeightLabel;
	private JTextField warningTextField;
	private ArrayList<JCheckBox> checkBoxes;
	String[] itemNames = { "Apple Vinegar", "Bedroll", "Bacon", "Blacksmithing Tools", "Beans", "Books", "Coffee",
			"Medicine", "Dried Apples", "Cast Iron Stove", "Flour", "Chair", "Hardtack",
			"Cookware & Eating Utensils", "Lard", "Granny’s Clock", "Salt", "Gun Making Tools", "Sugar",
			"Keepsakes", "Rice", "Lead Shot", "Water", "Mirror", "Whiskey", "Gunpowder", "Tent & Gear", "Tools",
			"Toys" };
	private JButton travelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Game() {
		wagon = new Wagon();
		checkBoxes = new ArrayList<>();
		initialize();
	}
	
	/**
	 * Travel button click handler
	 */
	private void travel() {
		int travelSpeed = 12; // Default travel speed
		String[] options = { "Sumo Wrestler", "Regular Joe", "Starving Homeless person" };
		String foodConsumption = (String) JOptionPane.showInputDialog(frame, "Select food consumption level:", 
				                                                      "Food Consumption", JOptionPane.PLAIN_MESSAGE, 
				                                                      null, options, options[0]);
		if (foodConsumption != null) {
			String speedInput = JOptionPane.showInputDialog(frame, "Enter travel speed (miles per day):");
			if (speedInput != null) {
				try {
					travelSpeed = Integer.parseInt(speedInput);
					if (travelSpeed < 12 || travelSpeed > 20) {
						JOptionPane.showMessageDialog(frame, JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} else {
				return; // User canceled speed input
			}

			// Calculate journey duration
			int journeyDays = 2200 / travelSpeed;

			// Determine food consumption rate based on user input
			double foodConsumptionRate;
			switch (foodConsumption) {
				case "Sumo Wrestler":
					foodConsumptionRate = 1.5;
					break;
				case "Regular Joe":
					foodConsumptionRate = 1.0;
					break;
				case "Starving Homeless person":
					foodConsumptionRate = 0.5;
					break;
				default:
					foodConsumptionRate = 1.0; // Default value
			}

			// Calculate required food amount
			double requiredFood = journeyDays * foodConsumptionRate;

			// Check if there's enough food
			if (requiredFood > wagon.getTotalWeight()) {
				warningTextField.setText("Insufficient food for the journey.");
			} else {
				warningTextField.setText("Food is sufficient for the journey.");
			}
		}
	}

	// Dummy implementation, you may want to fetch weights from a data source or a
	// predefined map
	private double getItemWeight(String itemName) {
		// For simplicity, using a simple mapping here
			switch (itemName) {
			case "Apple Vinegar":
				return 25;
			case "Bedroll":
				return 15;
			case "Bacon":
				return 400;
			case "Blacksmithing Tools":
				return 200;
			case "Beans":
				return 200;
			case "Books":
				return 75;
			case "Coffee":
				return 80;
			case "Medicine":
				return 10;
			case "Dried Apples":
				return 80;
			case "Cast Iron Stove":
				return 300;
			case "Flour":
				return 500;
			case "Chair":
				return 20;
			case "Hardtack":
				return 200;
			case "Cookware & Eating Utensils":
				return 75;
			case "Lard":
				return 200;
			case "Granny’s Clock":
				return 15;
			case "Salt":
				return 50;
			case "Gun Making Tools":
				return 200;
			case "Sugar":
				return 40;
			case "Keepsakes":
				return 40;
			case "Rice":
				return 200;
			case "Lead Shot":
				return 25;
			case "Water":
				return 100;
			case "Mirror":
				return 15;
			case "Whiskey":
				return 40;
			case "Gunpowder":
				return 80;
			case "Tent & Gear":
				return 150;
			case "Tools":
				return 50;
			case "Toys":
				return 15;
			default:
				return 0; // Unknown item
			}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Oregon Trail");
		frame.setSize(400, 400);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(0, 2));

		// Adding checkboxes for items iteratively
		for (int i = 0; i < itemNames.length; i++) {
			JCheckBox checkBox = new JCheckBox(itemNames[i]);
			checkBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String itemName = checkBox.getText();
					double itemWeight = getItemWeight(itemName);
					if (checkBox.isSelected()) {
						// Add item to wagon
						Item item = new Item(itemName, itemWeight);
						wagon.addItem(item);
					} else {
						// Remove item from wagon
						Item item = new Item(itemName, itemWeight);
						wagon.removeItem(item);
					}
					totalWeightLabel.setText("Total Weight: " + wagon.getTotalWeight());
					if (wagon.getTotalWeight() > 2400) {
						warningTextField.setText("You may not travel with more than 2400lbs in your wagon");
					} else {
						warningTextField.setText("");
					}
				}
			});
			
			controlPanel.add(checkBox);
			checkBoxes.add(checkBox);
		}

		// Display total weight
		totalWeightLabel = new JLabel("Total Weight: ");
		controlPanel.add(totalWeightLabel);

		// Warning text field
		warningTextField = new JTextField();
		warningTextField.setEditable(false);
		controlPanel.add(warningTextField);

		// Travel button
		travelButton = new JButton("Travel");
		travelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				travel();
			}
		});
		controlPanel.add(travelButton);

		// Add control panel to frame
		frame.getContentPane().add(controlPanel, BorderLayout.CENTER);

		// Add Oregon Trail image
		ImageIcon oregonTrailImage = new ImageIcon("oregon_trail_image.jpg");
		JLabel imageLabel = new JLabel(oregonTrailImage);
		frame.getContentPane().add(imageLabel, BorderLayout.NORTH);
	}

}
