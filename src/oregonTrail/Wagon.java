package oregonTrail;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Contains all information related to the wagon.
 * Instance variables: foodConsumptionRate, travelSpeed, itemContents,
 * foodContents, totalWeight, totalFoodWeight.
 * Static constants: FOOD_CONSUME_LEVELS, DEFAULT_TRAVEL_SPEED
 * 
 * @author Lukas Dunbar, Corbin Hibler Ray Otto and Ethan Vaughn
 * @version 1.0
 */
public class Wagon {
    private double foodConsumptionRate;
    private int travelSpeed;
    private ArrayList<Item> itemContents;
    private ArrayList<Food> foodContents;
    private int totalWeight;
    private int totalFoodWeight;
    public static final String[] FOOD_CONSUME_LEVELS = { "Female Bodybuilder", "Regular Jane", "Starving Homeless Woman" };
    public static final int DEFAULT_TRAVEL_SPEED = 12;
    public static final int MAXIMUM_WEIGHT = 2400;
    public static final String[] itemNames = { "Apple Vinegar", "Bedroll", "Bacon", "Blacksmithing Tools", "Beans",
            "Books", "Coffee", "Medicine", "Dried Apples", "Cast Iron Stove", "Flour", "Chair", "Hardtack",
            "Cookware & Eating Utensils", "Lard", "Granny’s Clock", "Salt", "Gun Making Tools", "Sugar", "Keepsakes",
            "Rice", "Lead Shot", "Water", "Mirror", "Whiskey", "Gunpowder", "Tent & Gear", "Tools", "Toys" };
    private double money = 400; // placeholder money amount for now

    /**
     * Constructor creates contents ArrayList and sets defaults.
     */
    public Wagon() {
        itemContents = new ArrayList<>();
        foodContents = new ArrayList<>();
        // Default values
        foodConsumptionRate = 1.0;
        travelSpeed = DEFAULT_TRAVEL_SPEED;
    }

    /**
     * Adds item to wagon.
     * 
     * @param item Item object being added.
     */
    public void addItem(Item item) {
        itemContents.add(item);
        totalWeight += item.getWeight();
    }

    /**
     * Removes item from wagon.
     * 
     * @param item Item object being removed.
     */
    public void removeItem(Item item) {
        itemContents.remove(item);
        totalWeight -= item.getWeight();
    }

    /**
     * Gets the total weight of all items in the wagon.
     * 
     * @return The total weight of all items in the wagon.
     */
    public double getTotalWeight() {
        return totalWeight;
    }

    /**
     * Searches through all items in Wagon and adds all food items to foodContents.
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
     * Displays a JOptionPane dialog to set the food consumption rate.
     * 
     * @param panel JPanel in use by the program (LoadWagon, main game, etc.).
     * @return The selected food consumption rate as a string.
     */
    public String foodConsumptionDialog(JPanel panel) {
        String foodConsumptionOption = (String) JOptionPane.showInputDialog(panel, "Select food consumption level:",
                "Food Consumption", JOptionPane.PLAIN_MESSAGE, null, FOOD_CONSUME_LEVELS, FOOD_CONSUME_LEVELS[0]);

        // Determine food consumption rate based on user input
        double foodConsumptionRate;
        switch (foodConsumptionOption) {
            case "Female Bodybuilder":
                foodConsumptionRate = 1.5;
                break;
            case "Regular Jane":
                foodConsumptionRate = 1.0;
                break;
            case "Starving Homeless Woman":
                foodConsumptionRate = 0.5;
                break;
            default:
                foodConsumptionRate = 1.0; // Default value
        }

        this.foodConsumptionRate = foodConsumptionRate;
        return foodConsumptionOption;
    }

    /**
     * Gets the food consumption rate.
     * 
     * @return The food consumption rate.
     */
    public double getFoodConsumptionRate() {
        return foodConsumptionRate;
    }

    /**
     * Displays a JOptionPane message dialog to set the travel speed.
     * 
     * @param panel JPanel in use by the program (LoadWagon, main game, etc.).
     * @return The selected travel speed as a string.
     */
    public String travelSpeedDialog(JPanel panel) {
        int travelSpeed = DEFAULT_TRAVEL_SPEED;
        String speedInput = JOptionPane.showInputDialog(panel, "Enter travel speed (miles per day, 12-20):");
        try {
            travelSpeed = Integer.parseInt(speedInput);
            if (travelSpeed < 12 || travelSpeed > 20) {
                JOptionPane.showMessageDialog(panel, "Input must be between 12-20.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                travelSpeedDialog(panel);
            } else {
                this.travelSpeed = travelSpeed;
                return speedInput;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel, "Invalid input. Please enter a valid integer.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            travelSpeedDialog(panel);
        }
        return speedInput;
    }

    /**
     * Gets the travel speed.
     * 
     * @return The travel speed.
     */
    public int getTravelSpeed() {
        return travelSpeed;
    }

    /**
     * Gets the total food weight.
     * 
     * @return The total food weight.
     */
    public int getTotalFoodWeight() {
        return totalFoodWeight;
    }

    /**
     * Sets the total food weight.
     * 
     * @param totalFoodWeight The total food weight to set.
     */
    public void setTotalFoodWeight(int totalFoodWeight) {
        this.totalFoodWeight = totalFoodWeight;
    }

    /**
     * Gets the amount of money that the wagon party has.
     * 
     * @return The remaining amount of money that the wagon party has.
     */
    public double getMoney() {
        return this.money;
    }

    /**
     * Changes the amount of money that the wagon party has.
     * 
     * @param amount The amount that the wagon's money is being changed by (make negative if subtracting).
     */
    public void changeMoney(double amount) {
        this.money += amount;
    }

    /**
     * Gets the amount of ammunition in the party's possession.
     * 
     * @return ammo The amount of ammunition stored in the wagon's itemContents array
     */
    public int getAmmoAmount() {
    	int ammo = 0;
    	
    	for(Item i : itemContents) {
    		if(i.name.equals("Ammunition")) {
    			ammo++;
    		}
    	}    	
    	return ammo;
    }
    
    /**
     * Gets the amount of clothes in the party's possession.
     * 
     * @return clothes The number of Items named "Clothing" in itemContents.
     */
    public int getClothingAmount() {
    	int clothes = 0;
    	
    	for(Item i : itemContents) {
    		if(i.name.equals("Clothing")) {
    			clothes++;
    		}
    	}
		return clothes;
    }
    
    /**
     * Gets the amount of oxen at the party's disposal.
     * 
     * @return oxen The amount of items named "Oxen" in itemContents
     */
    public int getOxenAmount() {
    	int oxen = 0;
    	
    	for(Item i : itemContents) {
    		if(i.name.equals("Oxen")) {
    			oxen++;
    		}
    	}
    	return oxen;
    }
    
}
