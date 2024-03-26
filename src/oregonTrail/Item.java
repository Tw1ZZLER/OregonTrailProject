package oregonTrail;

/**
 * Class for each item in the Oregon Trail game.
 * Superclass of Food.
 * @author Lukas Dunbar, Corbin Hibler
 */
public class Item {
	protected String name;
	protected double weight;

	public Item(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}

	/**
	 * @return String Name of item
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return double weight of item
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Switch statement containing all weights for all items
	 * @param itemName Name of item as String
	 * @return double Weight of item
	 */
	public static double getItemWeight(String itemName) {
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
}