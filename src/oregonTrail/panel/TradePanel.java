package oregonTrail.panel;

import javax.swing.*;

import oregonTrail.panel.TravelPanel;
import oregonTrail.OregonTrail;
import oregonTrail.Travel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import oregonTrail.landmark.Landmark;
import java.awt.*;
import java.util.Random;

/**
 * Java Swing panel when trading is activated.
 * Can open from Forts, Rivers, or anywhere when traveling.
 * @author Lukas Dunbar
 * @date 2024-04-16
 * @filename TradePanel.java
 */
public class TradePanel extends JPanel {
    private JLabel tradeOfferLabel;
    private JButton yesButton;
    private JButton noButton;
    private JButton closeButton;
    private boolean tradeHappenedToday = false;
    private OregonTrail oregonTrail;
    private OregonTrail TravelPanel;
    private Travel travelState;
    private int firstDistance;
    private int secondDistance;

    public TradePanel(OregonTrail oregonTrail) {
        setLayout(new BorderLayout());
        
        // Set game object
        this.oregonTrail = oregonTrail;

        // Initialize trade offer label
        tradeOfferLabel = new JLabel();
        tradeOfferLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(tradeOfferLabel, BorderLayout.CENTER);

        // Initialize buttons
        JPanel buttonPanel = new JPanel();
        yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Trade Confirmed!");
                tradeHappenedToday = true;
                updateTradeOffer();
            }
        });
        buttonPanel.add(yesButton);

        noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Trade Denied!");
                tradeHappenedToday = true;
                updateTradeOffer();
            }
        });
        buttonPanel.add(noButton);

        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            }
        });
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Update trade offer
        updateTradeOffer();
    }

    // Method to close the trade panel
    private void closeTradePanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    // Method to generate and update trade offer
    private void updateTradeOffer() {
    	firstDistance=oregonTrail.getTravelState().getMilesTraveled();
    	if(firstDistance!=secondDistance) {
    		tradeHappenedToday=false;
    	}
    	secondDistance=firstDistance;
    
        if (!tradeHappenedToday) {
            double tradeChance = Math.random(); // Random number between 0 and 1
            if (tradeChance <= 0.2) {
                tradeOfferLabel.setText("No one wants to trade today.");
                yesButton.setEnabled(false);
                noButton.setEnabled(false);
            } else {
                String tradeOffer = generateTradeOffer();
                tradeOfferLabel.setText(tradeOffer);
                yesButton.setEnabled(true);
                noButton.setEnabled(true);
            }
        } else {
            tradeOfferLabel.setText("You've already made a trade today.");
            yesButton.setEnabled(false);
            noButton.setEnabled(false);
        }
    }

    private int getTravelState() {
		// TODO Auto-generated method stub
		return 0;
	}

	// Method to generate trade offer
    private String generateTradeOffer() {
        Random random = new Random();

        String[] items = {"Food", "Water", "Clothing", "Ammunition", "Oxen", "Scrap", "Wheels"};

        // Generate random quantities for each item
        int index1 = random.nextInt(items.length);
        int index2;
        do {
            index2 = random.nextInt(items.length);
        } while (index2 == index1);

        int quantity1 = random.nextInt(10) + 1; // Random quantity between 1 and 10
        int quantity2 = random.nextInt(10) + 1; // Random quantity between 1 and 10

        return "Would you like to trade " + quantity1 + " " + items[index1] +
                " for " + quantity2 + " " + items[index2] + "?";
    }
}
