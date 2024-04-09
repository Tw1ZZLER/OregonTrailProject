package oregonTrail;

/**
 * Class contains methods needed for Food objects.
 * Subclass of Item
 * @author Lukas Dunbar, Corbin Hibler Ray Otto and Ethan Vaughn
 */
public class Food extends Item {
	/**
	 * Constructor creates Food item
	 * @param name Name of Food item
	 * @param weight Weight of Food item
	 */
	public Food(String name) {
		super(name);
	}

	/**
	 * Checks an Item for being a Food item. 
	 * @param item Item being checked for being a Food item.
	 * @return true if item is a Food item, false otherwise
	 */
	public static boolean isFood(Item item) {
		switch (item.getName()) {
		case "Apple Vinegar", "Bacon", "Beans", "Coffee", "Dried Apples", "Flour", 
		     "Hardtack", "Lard", "Salt", "Sugar", "Rice", "Water", "Whiskey":
			return true;
		default:
			return false; // Not food
		}
	}
}
