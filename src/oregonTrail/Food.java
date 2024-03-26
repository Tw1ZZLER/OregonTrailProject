package oregonTrail;

public class Food extends Item {
	private String name;
	private double nutritionWeight;
	
	public Food(String name, double nutritionWeight) {
		super(name, nutritionWeight);
	}

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
