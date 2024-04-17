package oregonTrail;

import java.util.ArrayList;

/**
 * Class containing the methods & constructor used for a Shop object
 * @author Ethan Vaughn
 * @date 2024-04-15
 */

public class Shop {

	double orderPrice = 0;
	ArrayList<Item> orderItems;
	
	private double oxenPrice = 40;
	private double foodPrice = .20;
	private double clothingPrice = 10;
	private double ammoPrice = 20;
	private double partsPrice = 10;
	private double priceMultiplier;
	
	/**
	 * Initializes a Shop object with prices based on which fort the player is at
	 * @param fortNum the number of the fort that the player is at, contributes to the price multiplier
	 */
	public Shop(int fortNum) {
		priceMultiplier = .25*fortNum;
		oxenPrice += oxenPrice* priceMultiplier;
		foodPrice += foodPrice* priceMultiplier;
		clothingPrice += foodPrice * priceMultiplier;
		ammoPrice += ammoPrice * priceMultiplier;
		partsPrice += partsPrice * priceMultiplier;
	}
	
	
	/**
	 * Gets the price of ordering a certain number of one item from the store.
	 * @param item number of item being purchased (1 for oxen, 2 for food, 3 for clothing, 4 for ammunition, 5 for wagon parts)
	 * @param numPurchased amount of item being purchased
	 * @return
	 */
	public double getPrice(int item, int numPurchased) {
		//allows for selection of what is being purchased
		switch(item) {
		case 1: return this.oxenPrice * numPurchased;
			
		case 2: return this.foodPrice * numPurchased;
			
		case 3: return this.clothingPrice * numPurchased;
			
		case 4: return this.ammoPrice * numPurchased;
			
		case 5: return this.partsPrice * numPurchased;
		}
		
		//failsafe so that if anything goes wrong, no unnecessary charges will be added
		return 0;
	}
	
	/**
	 * Returns the item purchased, to be added to an arrayList to add to a Wagon.
	 * @param item the identity of the purchased item (1 for oxe, 2 for food, same as getPrice)
	 * @return the item that was purchased
	 */
	public Item itemPurchased(int item) {
		switch(item) {
		
		case 1: 
			Item oxen = new Item("Oxen");
			return oxen;
			
		case 2:
			Item food = new Item("Food");
			return food;
			
		case 3:
			Item clothing = new Item("Clothing");
			return clothing;
		
		case 4:
			Item ammunition = new Item("Ammunition");
			return ammunition;
			
		case 5:
			Item spareParts = new Item("SpareParts");
			return spareParts;
			
		}
		
		//failsafe so that if something goes wrong, a blank item gets added to the wagon
		Item nothing = new Item("nothing");
		return nothing;
	}

	
	
	
}
