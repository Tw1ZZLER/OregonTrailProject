package oregonTrail.panel;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import oregonTrail.OregonTrail;
import java.awt.Font;


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
    private JLabel weatherValueLabel;
    private JLabel healthValueLabel;
    private JLabel paceValueLabel;
    private JButton continueButton;
    private JButton checkSuppliesButton;
    private JButton lookMapButton;
    private JButton changeRationsButton;
    private JButton restButton;
    private JButton tradeButton;
    private JButton huntButton;
    private JButton paceButton;
    private JLabel foodRationingValueLabel;

    /**
     * Constructs a TrailMenuPanel with the specified OregonTrail instance.
     * @param pOregonTrail The main OregonTrail instance
     */
    public TrailMenuPanel(OregonTrail pOregonTrail) {
        this.oregonTrail = pOregonTrail;
        
        setLayout(new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]10[]10[]10[]10[]10[]"));
        
        Font font = new Font("Monospaced", Font.BOLD, 14);

        // Display date
        JLabel dateLabel = new JLabel("Date:");
        dateValueLabel = new JLabel("August 11, 1848");
        dateValueLabel.setFont(font);
        add(dateLabel);
        add(dateValueLabel);

        // Display weather
        JLabel weatherLabel = new JLabel("Weather:");
        weatherValueLabel = new JLabel("Insane Thunderstorm!");
        weatherValueLabel.setFont(font);
        add(weatherLabel);
        add(weatherValueLabel);

        // Display health
        JLabel healthLabel = new JLabel("Health:");
        healthValueLabel = new JLabel("Fair");
        healthValueLabel.setFont(font);
        add(healthLabel);
        add(healthValueLabel);

        // Display pace
        JLabel paceLabel = new JLabel("Pace:");
        paceValueLabel = new JLabel("12");
        paceValueLabel.setFont(font);
        add(paceLabel);
        add(paceValueLabel);

        // Add buttons
        continueButton = new JButton("Continue on Trail");
        add(continueButton, "span 2, growx, wrap");
        checkSuppliesButton = new JButton("Check Supplies");
        add(checkSuppliesButton, "span 2, growx, wrap");
        lookMapButton = new JButton("Look at Map");
        add(lookMapButton, "span 2, growx, wrap");
        changeRationsButton = new JButton("Change Food Rations");
        add(changeRationsButton, "span 2, growx, wrap");
        restButton = new JButton("Stop to Rest");
        add(restButton, "span 2, growx, wrap");
        tradeButton = new JButton("Attempt to Trade");
        add(tradeButton, "span 2, growx, wrap");
        huntButton = new JButton("Hunt for Food");
        add(huntButton, "span 2, growx, wrap");
        paceButton = new JButton("Change Pace");
        add(paceButton, "span 2, growx");

        // Add action listeners to buttons
        continueButton.addActionListener(e -> oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL));
        checkSuppliesButton.addActionListener(e -> System.out.println("Check Supplies button clicked"));
        lookMapButton.addActionListener(e -> System.out.println("Look at Map button clicked"));
        changeRationsButton.addActionListener(e -> oregonTrail.WAGON.foodConsumptionDialog(TrailMenuPanel.this));
        restButton.addActionListener(e -> System.out.println("Stop to Rest button clicked"));
        tradeButton.addActionListener(e -> oregonTrail.openPanel(oregonTrail.TRADE_PANEL));
        huntButton.addActionListener(e -> oregonTrail.openPanel(oregonTrail.huntingPanel));
        paceButton.addActionListener(e -> paceValueLabel.setText(oregonTrail.WAGON.travelSpeedDialog(TrailMenuPanel.this)));
    }

    /**
     * Sets text of date label
     * @param string Descriptive string date in long format
     */
    public void setDateText(String string) {
        dateValueLabel.setText(string);
    }
}
