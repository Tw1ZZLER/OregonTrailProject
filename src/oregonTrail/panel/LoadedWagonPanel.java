package oregonTrail.panel;

import javax.swing.*;

import oregonTrail.OregonTrail;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Shows information about the loaded wagon
 * @author Lukas Dunbar and Ethan Vaughn
 * @date 2024-04-09
 * @filename LoadedWagonPanel.java
 */
public class LoadedWagonPanel extends JPanel {
    // Set initial values for food, water, ammo, and clothing
    private int foodAmount = 200;
    private int waterAmount = 50;
    private int ammoAmount = 200;
    private int clothingAmount = 10;
    private int oxenAmount = 2;
    private OregonTrail oregonTrail;
    private JButton continueButton;
    private JButton refreshButton;
    private JLabel wagonImageLabel;
    private JLabel oxenLabel;
    private JLabel oxenAmountLabel;
    
    // Constructor accepting OregonTrail instance
    public LoadedWagonPanel(OregonTrail oregonTrail) {
    	
    	this.oregonTrail = oregonTrail;
  
    	foodAmount = oregonTrail.WAGON.getTotalFoodWeight();   	
    	ammoAmount = oregonTrail.WAGON.getAmmoAmount();
    	clothingAmount = oregonTrail.WAGON.getClothingAmount();
    	oxenAmount = oregonTrail.WAGON.getOxenAmount();
        setLayout(null);

        // Create panel for displaying inventory details
        JPanel inventoryDetailsPanel = new JPanel(new GridLayout(5, 2));
        inventoryDetailsPanel.setBounds(0, 233, 450, 67);
        add(inventoryDetailsPanel);
        
        // Add labels and amounts for food, water, ammo, and clothing
        JLabel foodLabel = new JLabel("Food:");
        JLabel foodAmountLabel = new JLabel(Integer.toString(foodAmount));

        JLabel waterLabel = new JLabel("Water:");
        JLabel waterAmountLabel = new JLabel(Integer.toString(waterAmount));

        JLabel ammoLabel = new JLabel("Ammo:");
        JLabel ammoAmountLabel = new JLabel(Integer.toString(ammoAmount));

        JLabel clothingLabel = new JLabel("Clothing:");
        JLabel clothingAmountLabel = new JLabel(Integer.toString(clothingAmount));

        JLabel oxenLabel = new JLabel("Oxen:");
        JLabel oxenAmountLabel = new JLabel(Integer.toString(oxenAmount));

        
        inventoryDetailsPanel.add(foodLabel);
        inventoryDetailsPanel.add(foodAmountLabel);
        inventoryDetailsPanel.add(waterLabel);
        inventoryDetailsPanel.add(waterAmountLabel);
        inventoryDetailsPanel.add(ammoLabel);
        inventoryDetailsPanel.add(ammoAmountLabel);
        inventoryDetailsPanel.add(clothingLabel);
        inventoryDetailsPanel.add(clothingAmountLabel);
        inventoryDetailsPanel.add(oxenLabel);
        inventoryDetailsPanel.add(oxenAmountLabel);

        
        
        continueButton = new JButton("Continue On Trail");
        continueButton.setBounds(0, 0, 450, 23);
        continueButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
        	}
        });
        add(continueButton);
        
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	foodAmount = oregonTrail.WAGON.getTotalFoodWeight();   	
            	ammoAmount = oregonTrail.WAGON.getAmmoAmount();
            	clothingAmount = oregonTrail.WAGON.getClothingAmount();
            	oxenAmount = oregonTrail.WAGON.getOxenAmount();
            	foodAmountLabel.setText(""+foodAmount);
            	ammoAmountLabel.setText(""+ammoAmount);
            	clothingAmountLabel.setText(""+clothingAmount);
            	oxenAmountLabel.setText(""+oxenAmount);
        	}
        });
        refreshButton.setBounds(0, 209, 450, 23);
        add(refreshButton);
        
        wagonImageLabel = new JLabel("");
        wagonImageLabel.setBounds(0, 22, 450, 188);
        add(wagonImageLabel);
		ImageIcon icon = new ImageIcon("src/images/wagon.png");
		wagonImageLabel.setIcon(icon);
    }
}

