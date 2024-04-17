package oregonTrail.panel;

import javax.swing.*;
import oregonTrail.OregonTrail;
import java.awt.event.*;

/**
 * Panel that shows when player chooses "size up the situation." Contains
 * most game changing options and buttons.
 * This panel displays information such as date, weather, health, pace, and food rationing.
 * It also provides buttons for various actions such as continuing on the trail, checking supplies, looking at the map, changing food rations, resting, attempting to trade, hunting for food, and changing pace.
 * @author Lukas Dunbar
 * @date 2024-04-09
 * @filename TrailMenuPanel.java
 */
public class TrailMenuPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private OregonTrail oregonTrail;
    private JLabel dateValueLabel;
    private JLabel weatherLabel;
    private JLabel weatherValueLabel;
    private JLabel healthLabel;
    private JLabel healthValueLabel;
    private JLabel paceLabel;
    private JLabel paceValueLabel;
    private JButton continueButton;
    private JButton checkSuppliesButton;
    private JButton lookMapButton;
    private JButton changeRationsButton;
    private JButton restButton;
    private JButton tradeButton;
    private JButton huntButton;
    private JButton paceButton;
    private JLabel foodRationingLabel;
    private JLabel foodRationingValueLabel;

    /**
     * Constructs a TrailMenuPanel with the specified OregonTrail instance.
     * @param pOregonTrail The main OregonTrail instance
     */
    public TrailMenuPanel(OregonTrail pOregonTrail) {
        this.oregonTrail = pOregonTrail;
        
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        // Display date
        JLabel dateLabel = new JLabel("Date:");
        dateValueLabel = new JLabel("August 11, 1848");
        add(dateLabel);
        add(dateValueLabel);

        // Display weather
        weatherLabel = new JLabel("Weather:");
        weatherValueLabel = new JLabel("Insane Thunderstorm!");
        add(weatherLabel);
        add(weatherValueLabel);

        // Display health
        healthLabel = new JLabel("Health:");
        healthValueLabel = new JLabel("Fair");
        add(healthLabel);
        add(healthValueLabel);

        // Display pace
        paceLabel = new JLabel("Pace:");
        paceValueLabel = new JLabel("12");
        add(paceLabel);
        add(paceValueLabel);

        // Add buttons
        continueButton = new JButton("Continue on Trail");
        layout.putConstraint(SpringLayout.NORTH, continueButton, 26, SpringLayout.SOUTH, paceLabel);
        layout.putConstraint(SpringLayout.WEST, continueButton, 5, SpringLayout.WEST, this);
        checkSuppliesButton = new JButton("Check Supplies");
        layout.putConstraint(SpringLayout.NORTH, checkSuppliesButton, 26, SpringLayout.SOUTH, paceLabel);
        lookMapButton = new JButton("Look at Map");
        layout.putConstraint(SpringLayout.WEST, lookMapButton, 5, SpringLayout.WEST, this);
        changeRationsButton = new JButton("Change Food Rations");
        layout.putConstraint(SpringLayout.WEST, changeRationsButton, 0, SpringLayout.WEST, checkSuppliesButton);
        layout.putConstraint(SpringLayout.EAST, changeRationsButton, 160, SpringLayout.WEST, checkSuppliesButton);
        restButton = new JButton("Stop to Rest");
        layout.putConstraint(SpringLayout.WEST, restButton, 5, SpringLayout.WEST, this);
        tradeButton = new JButton("Attempt to Trade");
        layout.putConstraint(SpringLayout.WEST, tradeButton, 0, SpringLayout.WEST, checkSuppliesButton);
        huntButton = new JButton("Hunt for Food");
        layout.putConstraint(SpringLayout.WEST, huntButton, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, tradeButton, 0, SpringLayout.NORTH, huntButton);
        paceButton = new JButton("Change Pace");
        layout.putConstraint(SpringLayout.NORTH, paceButton, 100, SpringLayout.SOUTH, paceLabel);
        layout.putConstraint(SpringLayout.SOUTH, paceButton, 8, SpringLayout.SOUTH, restButton);

        // Add action listeners to buttons
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            }
        });

        checkSuppliesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Check Supplies button clicked");
            }
        });

        lookMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Look at Map button clicked");
            }
        });

        changeRationsButton.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
                oregonTrail.WAGON.foodConsumptionDialog(TrailMenuPanel.this);
            }
        });

        restButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Stop to Rest button clicked");
            }
        });

        tradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openPanel(oregonTrail.TRADE_PANEL);
            }
        });

        huntButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openPanel(oregonTrail.huntingPanel);
            }
        });
        
        paceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = oregonTrail.WAGON.travelSpeedDialog(TrailMenuPanel.this);
                paceValueLabel.setText(text);
            }
        });

        // Add components to the panel with SpringLayout constraints
        add(dateLabel);
        add(dateValueLabel);
        add(weatherLabel);
        add(weatherValueLabel);
        add(healthLabel);
        add(healthValueLabel);
        add(paceLabel);
        add(paceValueLabel);
        
        foodRationingLabel = new JLabel("Food Rationing:");
        layout.putConstraint(SpringLayout.WEST, foodRationingLabel, 0, SpringLayout.WEST, dateLabel);
        layout.putConstraint(SpringLayout.SOUTH, foodRationingLabel, -6, SpringLayout.NORTH, continueButton);
        add(foodRationingLabel);
        
        foodRationingValueLabel = new JLabel("12");
        layout.putConstraint(SpringLayout.WEST, foodRationingValueLabel, 6, SpringLayout.EAST, foodRationingLabel);
        layout.putConstraint(SpringLayout.SOUTH, foodRationingValueLabel, -6, SpringLayout.NORTH, continueButton);
        add(foodRationingValueLabel);
        add(continueButton);
        add(checkSuppliesButton);
        add(lookMapButton);
        add(changeRationsButton);
        add(restButton);
        add(tradeButton);
        add(huntButton);
        add(paceButton);

        // Set SpringLayout constraints
        layout.putConstraint(SpringLayout.WEST, dateLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, dateLabel, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, dateValueLabel, 5, SpringLayout.EAST, dateLabel);
        layout.putConstraint(SpringLayout.NORTH, dateValueLabel, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, weatherLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, weatherLabel, 5, SpringLayout.SOUTH, dateLabel);
        layout.putConstraint(SpringLayout.WEST, weatherValueLabel, 5, SpringLayout.EAST, weatherLabel);
        layout.putConstraint(SpringLayout.NORTH, weatherValueLabel, 5, SpringLayout.SOUTH, dateValueLabel);
        layout.putConstraint(SpringLayout.WEST, healthLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, healthLabel, 5, SpringLayout.SOUTH, weatherLabel);
        layout.putConstraint(SpringLayout.WEST, healthValueLabel, 5, SpringLayout.EAST, healthLabel);
        layout.putConstraint(SpringLayout.NORTH, healthValueLabel, 5, SpringLayout.SOUTH, weatherValueLabel);
        layout.putConstraint(SpringLayout.WEST, paceLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, paceLabel, 5, SpringLayout.SOUTH, healthLabel);
        layout.putConstraint(SpringLayout.WEST, paceValueLabel, 5, SpringLayout.EAST, paceLabel);
        layout.putConstraint(SpringLayout.NORTH, paceValueLabel, 5, SpringLayout.SOUTH, healthValueLabel);
        layout.putConstraint(SpringLayout.WEST, checkSuppliesButton, 5, SpringLayout.EAST, continueButton);
        layout.putConstraint(SpringLayout.NORTH, lookMapButton, 10, SpringLayout.SOUTH, continueButton);
        layout.putConstraint(SpringLayout.NORTH, changeRationsButton, 10, SpringLayout.SOUTH, checkSuppliesButton);
        layout.putConstraint(SpringLayout.NORTH, restButton, 10, SpringLayout.SOUTH, lookMapButton);
        layout.putConstraint(SpringLayout.NORTH, huntButton, 10, SpringLayout.SOUTH, restButton);
        layout.putConstraint(SpringLayout.WEST, paceButton, 0, SpringLayout.WEST, checkSuppliesButton);
    }

    /**
     * Sets text of date label
     * @param String Descriptive string date in long format
     */
    public void setDateText(String string) {
        dateValueLabel.setText(string);
    }
}
