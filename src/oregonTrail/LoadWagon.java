package oregonTrail;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Java Swing window to load wagon, with functionality for 
 * adding wagon weight and calculating food for the journey.
 * @author Lukas Dunbar, Corbin Hibler, Ray Otto and Ethan Vaughn
 */
public class LoadWagon {

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
	private final int MILES_TO_OREGON = 2200;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadWagon window = new LoadWagon();
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
	public LoadWagon() {
		wagon = new Wagon();
		checkBoxes = new ArrayList<>();
		initialize();
	}
	
	/**
	 * Travel button click handler
	 * Prompts user for food consumption and travel speed
	 * Calculates required food and notifies user if food is sufficient.
	 */
	private void travel() {
		wagon.foodConsumptionDialog(frame);
		wagon.travelSpeedDialog(frame);
		
		// Calculate journey duration
		int journeyDays = MILES_TO_OREGON / wagon.getTravelSpeed();

		// Calculate required food amount
		double requiredFood = journeyDays * wagon.getFoodConsumptionRate();

		// Check if there's enough food
		if (requiredFood > wagon.getTotalFoodWeight()) {
			warningTextField.setText("Insufficient food for the journey.");
		} else {
			warningTextField.setText("Food is sufficient for the journey.");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Load Wagon");
		frame.setSize(650, 500);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(0, 2));

		// Adding checkboxes for items iteratively
		for (int i = 0; i < itemNames.length; i++) {
			JCheckBox checkBox = new JCheckBox(itemNames[i]);
			checkBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String itemName = checkBox.getText();
					double itemWeight = Item.getItemWeight(itemName);
					
					// Add item to wagon
					if (checkBox.isSelected()) {
						Item item = new Item(itemName, itemWeight);
						wagon.addItem(item);
					// Remove item from wagon
					} else {
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
		ImageIcon chimneyRockImage = new ImageIcon("src/images/chimney_rock_1.jpg");
		// Resize image to fit into frame
        Image image = chimneyRockImage.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(image);
        
		JLabel imageLabel = new JLabel(scaledImage);
		frame.getContentPane().add(imageLabel, BorderLayout.NORTH);
	}

}