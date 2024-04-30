package oregonTrail;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Class to generate trade offers and contains information related to trading.
 * This class generates trade offers with random items and quantities,
 * ensuring that each generated trade offer is unique.
 * @author Lukas Dunbar Corbin Hibler
 * @date 2024-04-12
 * @version 1.0
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

    private Set<String> generatedTrades = new HashSet<>();

    /**
     * Generates a trade offer.
     * @return The generated trade offer string.
     */
    public String generateTradeOffer() {
        Random random = new Random();

        String tradeOffer;
        do {
            // Generate Items
            int index1 = random.nextInt(itemChoices.length);
            int index2 = random.nextInt(itemChoices.length);
            while (index2 == index1) { // Ensure different items are selected for trade
                index2 = random.nextInt(itemChoices.length);
            }

            // Generate random quantities for each item
            int quantity1 = random.nextInt(10) + 1; // Random quantity between 1 and 10
            int quantity2 = random.nextInt(10) + 1; // Random quantity between 1 and 10

            tradeOffer = "Would you like to trade " + quantity1 + " " + itemChoices[index1].getName() +
                    " for " + quantity2 + " " + itemChoices[index2].getName() + "?";
        } while (!generatedTrades.add(tradeOffer)); // Ensure the trade offer is unique

        return tradeOffer;
    }
}
