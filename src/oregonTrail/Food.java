package oregonTrail;

/**
 * Class contains methods needed for Food objects.
 * Subclass of Item
 *
 * @author Lukas Dunbar, Corbin Hibler, Ray Otto, and Ethan Vaughn
 * @version 1.0
 */
public class Food extends Item {
    /**
     * Constructor creates Food item
     *
     * @param name Name of Food item
     */
    public Food(String name) {
        super(name);
    }

    /**
     * Checks an Item for being a Food item.
     *
     * @param item Item being checked for being a Food item.
     * @return true if item is a Food item, false otherwise
     */
    public static boolean isFood(Item item) {
        switch (item.getName()) {
            case "Apple Vinegar", "Bacon", "Beans", "Coffee", "Dried Apples", "Flour",
                    "Hardtack", "Lard", "Salt", "Sugar", "Rice", "Water", "Whiskey", "Food":
                return true;
            default:
                return false; // Not food
        }
    }
    
    public static void consumeFood(OregonTrail oregonTrail) {
	    // Calculate new food weight and set accordingly based on the mathematical models used in the original game [1]
	    // [1] R. P. Bouchard, “Chapter 16: Building the Mathematical Models,” in  R. Philip Bouchard; 1st edition (January 28, 2016), 
	    int totalFoodWeight = oregonTrail.WAGON.getTotalFoodWeight();
	    int newFoodWeight = (int) (totalFoodWeight - (oregonTrail.WAGON.getFoodConsumptionRate() * 5)); 
	    oregonTrail.TRAVEL_PANEL.setFoodText(newFoodWeight);
	    oregonTrail.TRAIL_MENU_PANEL.setFoodText(newFoodWeight);
	    oregonTrail.WAGON.setTotalFoodWeight(newFoodWeight);
    }
}
