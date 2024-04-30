package oregonTrail;

/**
 * Class for each item in the Oregon Trail game.
 * Superclass of Food.
 *
 * @author Lukas Dunbar, Corbin Hibler, Ray Otto, and Ethan Vaughn
 * @version 1.0
 */
public class Item {
    protected String name; // Name of the item
    protected double weight; // Weight of the item

    /**
     * Constructor for creating an Item object.
     *
     * @param name Name of the item
     */
    public Item(String name) {
        this.name = name;
        this.weight = getItemWeight(name);
    }

    /**
     * Returns the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the weight of the item.
     *
     * @return The weight of the item.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Retrieves the weight of a specific item.
     *
     * @param itemName The name of the item as a String.
     * @return The weight of the item.
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
            case "Food":
                return 1;
            default:
                return 0; // Unknown item
        }
    }
}
