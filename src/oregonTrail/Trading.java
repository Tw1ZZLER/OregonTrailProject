package oregonTrail;

import java.util.Random;

public class Trading {
    private final static String[] items = {"Food", "Water", "Clothing", "Ammunition", "Oxen", "Scrap", "Wheels"};

    public Trading() {}

    public static String generateTradeOffer() {
        Random random = new Random();
        int index1 = random.nextInt(items.length);
        int index2 = random.nextInt(items.length);
        while (index2 == index1) { // Ensure different items are selected for trade
            index2 = random.nextInt(items.length);
        }

        return "Would you like to trade " + items[index1] +
                " for " + items[index2] + "?";
    }
}


