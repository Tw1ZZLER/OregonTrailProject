package oregonTrail;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Contains all information related to the wagon
 * Instance variables: foodConsumptionRate, travelSpeed, itemContents, 
 * 				       foodContents, totalWeight, totalFoodWeight.
 * Static constants: FOOD_CONSUME_LEVELS, DEFAULT_TRAVEL_SPEED
 * @author Lukas Dunbar, Corbin Hibler Ray Otto and Ethan Vaughn
 */
public class Wagon {
	private double foodConsumptionRate;
	private int travelSpeed;
	private ArrayList<Item> itemContents;
	private ArrayList<Food> foodContents;
	private int totalWeight;
	private int totalFoodWeight;
	public static final String[] FOOD_CONSUME_LEVELS = { "Sumo Wrestler", "Regular Joe", "Starving Homeless person"};
	public static final int DEFAULT_TRAVEL_SPEED = 12;
	public static final int MAXIMUM_WEIGHT = 2400;
	public static final String[] itemNames = { "Apple Vinegar", "Bedroll", "Bacon", "Blacksmithing Tools", "Beans", "Books", "Coffee",
			"Medicine", "Dried Apples", "Cast Iron Stove", "Flour", "Chair", "Hardtack",
			"Cookware & Eating Utensils", "Lard", "Granny’s Clock", "Salt", "Gun Making Tools", "Sugar",
			"Keepsakes", "Rice", "Lead Shot", "Water", "Mirror", "Whiskey", "Gunpowder", "Tent & Gear", "Tools",
			"Toys" };
	private double money = 400; //placeholder money amount for now

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
	 * @param panel JPanel in use by program (LoadWagon, main game, etc.)
	 * @return String The selected food consumption rate as string.
	 */
	public String foodConsumptionDialog(JPanel panel) {
		String foodConsumptionOption = (String) JOptionPane.showInputDialog(panel, "Select food consumption level:", 
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
		return foodConsumptionOption;
	}

	/**
	 * @return double foodConsumptionRate
	 */
	public double getFoodConsumptionRate() {
		return foodConsumptionRate;
	}
	
	/**
	 * Setter for travelSpeed in the form of a JOptionPane message dialog.
	 * @param panel JPanel in use by program (LoadWagon, main game, etc.)
	 * @return int travelSpeed The selected travel speed.
	 */
	public String travelSpeedDialog(JPanel panel) {
		int travelSpeed = DEFAULT_TRAVEL_SPEED;
		String speedInput = JOptionPane.showInputDialog(panel, "Enter travel speed (miles per day):");
		try {
			travelSpeed = Integer.parseInt(speedInput);
			if (travelSpeed < 12 || travelSpeed > 20) {
				JOptionPane.showMessageDialog(panel, "Input must be between 12-20.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				travelSpeedDialog(panel);
			}
			else {
				this.travelSpeed = travelSpeed;
				return speedInput;
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(panel, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
			travelSpeedDialog(panel);
		}
		return speedInput;
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
	public int getTotalFoodWeight() {
		return totalFoodWeight;
	}
	
	/**
	 * @param totalFoodWeight the totalFoodWeight to set
	 */
	public void setTotalFoodWeight(int totalFoodWeight) {
		this.totalFoodWeight = totalFoodWeight;
	}
	
	/**
	 * Gets the amount of money that the wagon party has, used for shopping and ferries
	 * @return the remaining amount of money that the wagon party has
	 */
	public double getMoney() {
		return this.money;
	}
	
	/**
	 * Changes the amount of money that the wagon party has
	 * @param amount amount that the wagon's money is being changed by (make negative if subtracting)
	 */
	public void changeMoney(double amount) {
		this.money += amount;
	}
	
}
