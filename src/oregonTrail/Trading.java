package oregonTrail;

import java.util.Random;

/**
 * Class to generate trade offers and contains information related to trading.
 * @author Lukas Dunbar, Corbin Hibler
 * @date 2024-04-12
 */
public class Trading {
    private final static Item[] itemChoices = {
		new Item("Food"), 
		new Item("Water"), 
		new Item("Clothing"), 
		new Item("Ammunition"), 
		new Item("Oxen"), 
		new Item("Scrap"), 
		new Item("Wheels")
	};

    public static String generateTradeOffer() {
        Random random = new Random();
        
        // Generate Items
        int index1 = random.nextInt(itemChoices.length);
        int index2 = random.nextInt(itemChoices.length);
        while (index2 == index1) { // Ensure different items are selected for trade
            index2 = random.nextInt(itemChoices.length);
        }

        return "Would you like to trade " + itemChoices[index1].getName() +
                " for " + itemChoices[index2].getName() + "?";
    }
}


