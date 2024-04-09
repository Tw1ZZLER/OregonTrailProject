package oregonTrail.panel;

import javax.swing.*;
import java.awt.*;

public class LoadedWagonPanel extends JPanel {
    // Set initial values for food, water, ammo, and clothing
    private int foodAmount = 100;
    private int waterAmount = 50;
    private int ammoAmount = 200;
    private int clothingAmount = 10;

    public LoadedWagonPanel(Image wagonImage) {
        setLayout(new BorderLayout());

        // Create image label and add it to the top half
        ImageIcon imageIcon = new ImageIcon(wagonImage); // Replace path_to_your_image
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.CENTER);

        // Create panel for displaying inventory details
        JPanel inventoryDetailsPanel = new JPanel(new GridLayout(4, 2));
        add(inventoryDetailsPanel, BorderLayout.SOUTH);

        // Add labels and amounts for food, water, ammo, and clothing
        JLabel foodLabel = new JLabel("Food:");
        JLabel foodAmountLabel = new JLabel(Integer.toString(foodAmount));

        JLabel waterLabel = new JLabel("Water:");
        JLabel waterAmountLabel = new JLabel(Integer.toString(waterAmount));

        JLabel ammoLabel = new JLabel("Ammo:");
        JLabel ammoAmountLabel = new JLabel(Integer.toString(ammoAmount));

        JLabel clothingLabel = new JLabel("Clothing:");
        JLabel clothingAmountLabel = new JLabel(Integer.toString(clothingAmount));

        inventoryDetailsPanel.add(foodLabel);
        inventoryDetailsPanel.add(foodAmountLabel);
        inventoryDetailsPanel.add(waterLabel);
        inventoryDetailsPanel.add(waterAmountLabel);
        inventoryDetailsPanel.add(ammoLabel);
        inventoryDetailsPanel.add(ammoAmountLabel);
        inventoryDetailsPanel.add(clothingLabel);
        inventoryDetailsPanel.add(clothingAmountLabel);
    }
}
