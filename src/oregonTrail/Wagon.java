package oregonTrail;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Wagon {
	private double foodConsumptionRate;
	private int travelSpeed;
	private ArrayList<Item> itemContents;
	private ArrayList<Food> foodContents;
	private double totalWeight;
	private double totalFoodWeight;
	public static final String[] FOOD_CONSUME_LEVELS = { "Sumo Wrestler", "Regular Joe", "Starving Homeless person"};
	public static final int DEFAULT_TRAVEL_SPEED = 12;

	/**
	 * Constructor creates contents ArrayList and sets defaults
	 */
	public Wagon() {
		itemContents = new ArrayList<>();
		foodContents = new ArrayList<>();
		// Default values
		foodConsumptionRate = 1.0;
		travelSpeed = DEFAULT_TRAVEL_SPEED;
	}

	/**
	 * Adds item to wagon
	 * @param item Item object being added
	 */
	public void addItem(Item item) {
		itemContents.add(item);
		totalWeight += item.getWeight();
	}

	/**
	 * Removes item from wagon
	 * @param item Item object being removed
	 */
	public void removeItem(Item item) {
		itemContents.remove(item);
		totalWeight -= item.getWeight();
	}

	/**
	 * @return totalWeight Weight of all items in the wagon
	 */
	public double getTotalWeight() {
		return totalWeight;
	}
	
	/**
	 * Searches through all items in Wagon and adds all food items to foodContents
	 */
	public void foodContentsGenerator() {
		for (Item item : itemContents) {
			boolean isFood = Food.isFood(item);
			if (isFood) {
				foodContents.add((Food) item);
				totalFoodWeight += item.getWeight();
			}
		}
	}
	
	/**
	 * Setter for foodConsumptionRate in the form of a JOptionPane dialog.
	 * @param frame JFrame in use by program (LoadWagon, main game, etc.)
	 * @return foodConsumptionRate The selected food consumption rate.
	 */
	public double foodConsumptionDialog(JFrame frame) {
		String foodConsumptionOption = (String) JOptionPane.showInputDialog(frame, "Select food consumption level:", 
                "Food Consumption", JOptionPane.PLAIN_MESSAGE, 
                null, Wagon.FOOD_CONSUME_LEVELS, Wagon.FOOD_CONSUME_LEVELS[0]);
		
		// Determine food consumption rate based on user input
		double foodConsumptionRate;
		switch (foodConsumptionOption) {
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
		
		this.foodConsumptionRate = foodConsumptionRate;
		return foodConsumptionRate;
	}

	/**
	 * @return double foodConsumptionRate
	 */
	public double getFoodConsumptionRate() {
		return foodConsumptionRate;
	}
	
	/**
	 * Setter for travelSpeed in the form of a JOptionPane message dialog.
	 * @param frame JFrame in use by program (LoadWagon, main game, etc.)
	 * @return int travelSpeed The selected travel speed.
	 */
	public int travelSpeedDialog(JFrame frame) {
		int travelSpeed = DEFAULT_TRAVEL_SPEED;
		String speedInput = JOptionPane.showInputDialog(frame, "Enter travel speed (miles per day):");
		try {
			travelSpeed = Integer.parseInt(speedInput);
			if (travelSpeed < 12 || travelSpeed > 20) {
				JOptionPane.showMessageDialog(frame, "Input must be between 12-20.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				travelSpeedDialog(frame);
			}
			else {
				this.travelSpeed = travelSpeed;
				return travelSpeed;
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
			travelSpeedDialog(frame);
		}
		return DEFAULT_TRAVEL_SPEED;
	}
	
	/**
	 * @return int travelSpeed
	 */
	public int getTravelSpeed() {
		return travelSpeed;
	}

	/**
	 * @return the totalFoodWeight
	 */
	public double getTotalFoodWeight() {
		return totalFoodWeight;
	}
}
