package oregonTrail.panel;

import javax.swing.*;

import oregonTrail.OregonTrail;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    /**
     * Constructs a TradePanel.
     * @param oregonTrail The main game instance.
     */
    public TradePanel(OregonTrail oregonTrail) {
        setLayout(new BorderLayout());
        
        // Set game object
        this.oregonTrail = oregonTrail;

        // Initialize trade offer label
        tradeOfferLabel = new JLabel();
        tradeOfferLabel.setOpaque(true);
        tradeOfferLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        tradeOfferLabel.setBackground(Color.BLACK);
        tradeOfferLabel.setForeground(Color.WHITE);
        tradeOfferLabel.setText("really long placeholder text");
        tradeOfferLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(tradeOfferLabel, BorderLayout.CENTER);

        // Initialize buttons
        JPanel buttonPanel = new JPanel();
        yesButton = new JButton("Yes");
        yesButton.setBackground(Color.BLACK);
        yesButton.setForeground(Color.WHITE);
        yesButton.setFont(new Font("Impact", Font.BOLD, 48));
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
        noButton.setBackground(Color.BLACK);
        noButton.setForeground(Color.WHITE);
        noButton.setFont(new Font("Impact", Font.BOLD, 48));
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
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Impact", Font.BOLD, 48));
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


    /**
     * Updates the trade offer based on the current game state.
     */
    private void updateTradeOffer() {
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


    /**
     * Generates a random trade offer.
     * @return The generated trade offer string.
     */
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
