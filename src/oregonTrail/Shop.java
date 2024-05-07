package oregonTrail;

import java.util.ArrayList;

/**
 * Class containing methods and constructor used for a Shop object.
 * The Shop class manages prices and items available for purchase at forts.
 * 
 * @author Ethan Vaughn
 * @version 1.0
 * @date 2024-04-15
 * @filename Shop.java
 */
public class Shop {

    double orderPrice = 0;
    ArrayList<Item> orderItems;

    private double oxenPrice = 40;
    private double foodPrice = 0.20;
    private double clothingPrice = 10;
    private double ammoPrice = 20;
    private double partsPrice = 10;
    private double priceMultiplier;

    /**
     * Initializes a Shop object with prices based on the fort number where the player is.
     *
     * @param fortNum The number of the fort that the player is at, which contributes to the price multiplier.
     */
    public Shop(int fortNum) {
        priceMultiplier = 0.25 * fortNum;
        oxenPrice += oxenPrice * priceMultiplier;
        foodPrice += foodPrice * priceMultiplier;
        clothingPrice += foodPrice * priceMultiplier;
        ammoPrice += ammoPrice * priceMultiplier;
        partsPrice += partsPrice * priceMultiplier;
    }

    /**
     * Gets the price of ordering a certain number of one item from the store.
     *
     * @param item        The number of the item being purchased (1 for oxen, 2 for food, 3 for clothing, 4 for ammunition, 5 for wagon parts).
     * @param numPurchased The quantity of the item being purchased.
     * @return The total price of the purchased items.
     */
    public double getPrice(int item, int numPurchased) {
        // Allows for selection of what is being purchased
        switch (item) {
            case 1:
                return this.oxenPrice * numPurchased;

            case 2:
                return this.foodPrice * numPurchased;

            case 3:
                return this.clothingPrice * numPurchased;

            case 4:
                return this.ammoPrice * numPurchased;

            case 5:
                return this.partsPrice * numPurchased;
        }

        // Failsafe to ensure no unnecessary charges are added
        return 0;
    }

    /**
     * Returns the item purchased, to be added to an ArrayList and then to a Wagon.
     *
     * @param item The identity of the purchased item (1 for oxen, 2 for food, same as getPrice).
     * @return The item that was purchased.
     */
    public Item itemPurchased(int item) {
        switch (item) {

            case 1:
                return new Item("Oxen");

            case 2:
                return new Item("Food");

            case 3:
                return new Item("Clothing");

            case 4:
                return new Item("Ammunition");

            case 5:
                return new Item("SpareParts");

        }

        // Failsafe to ensure a blank item is added to the wagon if something goes wrong
        return new Item("nothing");
    }

}
