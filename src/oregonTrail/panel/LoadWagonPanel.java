package oregonTrail.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oregonTrail.Item;
import oregonTrail.Wagon;

/**
 * Java Swing panel to load wagon, with functionality for 
 * adding wagon weight and calculating food for the journey.
 * @author Lukas Dunbar, Corbin Hibler, Ray Otto and Ethan Vaughn
 */
public class LoadWagonPanel extends JPanel {

	private Wagon wagon;
	private JLabel totalWeightLabel;
	private JTextField warningTextField;
	private ArrayList<JCheckBox> checkBoxes;
	String[] itemNames = { "Apple Vinegar", "Bedroll", "Bacon", "Blacksmithing Tools", "Beans", "Books", "Coffee",
			"Medicine", "Dried Apples", "Cast Iron Stove", "Flour", "Chair", "Hardtack",
			"Cookware & Eating Utensils", "Lard", "Granny’s Clock", "Salt", "Gun Making Tools", "Sugar",
			"Keepsakes", "Rice", "Lead Shot", "Water", "Mirror", "Whiskey", "Gunpowder", "Tent & Gear", "Tools",
			"Toys" };

	/**
	 * Create the panel components.
	 */
	public LoadWagonPanel() {
		wagon = new Wagon();
		checkBoxes = new ArrayList<>();
		
		this.setLayout(new GridLayout(0, 2));

		// Adding checkboxes for items iteratively
        for (String itemName : itemNames) {
            JCheckBox checkBox = new JCheckBox(itemName);
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
                    if (wagon.getTotalWeight() > Wagon.MAXIMUM_WEIGHT) {
                        warningTextField.setText("You may not travel with more than" + 
                        					     Wagon.MAXIMUM_WEIGHT + "lbs in your wagon");
                    } else {
                        warningTextField.setText("");
                    }
                }
            });

            this.add(checkBox);
            checkBoxes.add(checkBox);
        }

		// Display total weight
		totalWeightLabel = new JLabel("Total Weight: ");
		this.add(totalWeightLabel);

		// Warning text field
		warningTextField = new JTextField();
		warningTextField.setEditable(false);
		this.add(warningTextField);

		// Travel button
        JButton travelButton = new JButton("Travel");
		travelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				travel();
			}
		});
		this.add(travelButton);
	}
	
	/**
	 * Travel button click handler
	 * Prompts user for food consumption and travel speed
	 * Calculates required food and notifies user if food is sufficient.
	 */
	private void travel() {
		wagon.foodConsumptionDialog(this);
		wagon.travelSpeedDialog(this);
		
		// Calculate journey duration
        int MILES_TO_OREGON = 2200;
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
}
