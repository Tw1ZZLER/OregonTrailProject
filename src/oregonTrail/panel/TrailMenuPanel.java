package oregonTrail.panel;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import oregonTrail.Health;
import oregonTrail.OregonTrail;
import java.awt.Font;
import java.awt.Color;


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
    private JLabel foodRationingValueLabel;
    private Health health;
    private JLabel lblSituation;

    /**
     * Constructs a TrailMenuPanel with the specified OregonTrail instance.
     * @param pOregonTrail The main OregonTrail instance
     */
    public TrailMenuPanel(OregonTrail pOregonTrail) {
        this.oregonTrail = pOregonTrail;
        
        setLayout(new MigLayout("wrap 2", "[grow]10[grow]", "[grow]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]"));
        
		lblSituation = new JLabel("THE SITUATION");
		lblSituation.setFont(new Font("Impact", Font.BOLD, 72));
		lblSituation.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSituation, "cell 0 0 2 1,alignx center");

		// Display date
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(dateLabel, "cell 0 1,alignx right");
		dateValueLabel = new JLabel("August 11, 1848");
		dateValueLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		add(dateValueLabel, "cell 1 1");

		// Display weather
		JLabel weatherLabel = new JLabel("Weather:");
		weatherLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		weatherLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(weatherLabel, "cell 0 2,alignx right");
		weatherValueLabel = new JLabel("Insane Thunderstorm!");
		weatherValueLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		add(weatherValueLabel, "cell 1 2");

		// Display health
		JLabel healthLabel = new JLabel("Health:");
		healthLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		healthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(healthLabel, "cell 0 3,alignx right");
		healthValueLabel = new JLabel("Fair");
		healthValueLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		add(healthValueLabel, "cell 1 3");

		// Display pace
		JLabel paceLabel = new JLabel("Pace:");
		paceLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		paceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(paceLabel, "cell 0 4,alignx right");
		paceValueLabel = new JLabel("12");
		paceValueLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		add(paceValueLabel, "cell 1 4");

		// Add buttons
		continueButton = new JButton("Continue on Trail");
		continueButton.setBackground(Color.BLACK);
		continueButton.setForeground(Color.WHITE);
		continueButton.setFont(new Font("Impact", Font.PLAIN, 24));
		add(continueButton, "cell 0 5 2 1,growx");
		
		// Add action listeners to buttons
		continueButton.addActionListener(e -> oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL));
        checkSuppliesButton = new JButton("Check Supplies");
        checkSuppliesButton.setBackground(Color.BLACK);
        checkSuppliesButton.setForeground(Color.WHITE);
        checkSuppliesButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(checkSuppliesButton, "cell 0 6 2 1,growx");
        checkSuppliesButton.addActionListener(e -> oregonTrail.openPanel(oregonTrail.SUPPLIES_PANEL));
        lookMapButton = new JButton("Look at Map");
        lookMapButton.setBackground(Color.BLACK);
        lookMapButton.setForeground(Color.WHITE);
        lookMapButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(lookMapButton, "cell 0 7 2 1,growx");
        lookMapButton.addActionListener(e -> System.out.println("Look at Map button clicked"));
        changeRationsButton = new JButton("Change Food Rations");
        changeRationsButton.setBackground(Color.BLACK);
        changeRationsButton.setForeground(Color.WHITE);
        changeRationsButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(changeRationsButton, "cell 0 8 2 1,growx");
        changeRationsButton.addActionListener(e -> oregonTrail.WAGON.foodConsumptionDialog(TrailMenuPanel.this));
        restButton = new JButton("Stop to Rest");
        restButton.setBackground(Color.BLACK);
        restButton.setForeground(Color.WHITE);
        restButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(restButton, "cell 0 9 2 1,growx");
        restButton.addActionListener(e -> System.out.println("Stop to Rest button clicked"));
        tradeButton = new JButton("Attempt to Trade");
        tradeButton.setBackground(Color.BLACK);
        tradeButton.setForeground(Color.WHITE);
        tradeButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(tradeButton, "cell 0 10 2 1,growx");
        tradeButton.addActionListener(e -> oregonTrail.openPanel(oregonTrail.TRADE_PANEL));
        huntButton = new JButton("Hunt for Food");
        huntButton.setBackground(Color.BLACK);
        huntButton.setForeground(Color.WHITE);
        huntButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(huntButton, "cell 0 11 2 1,growx");
        huntButton.addActionListener(e -> oregonTrail.openPanel(oregonTrail.huntingPanel));
    }

    /**
     * Sets text of date label
     * @param string Descriptive string date in long format
     */
    public void setDateText(String string) {
        dateValueLabel.setText(string);
    }
    
    /**
     * Sets text of health label
     * based on health class
     */
    public void updateHealthLabel() {
	    healthValueLabel.setText(health.getGeneralHealthAsString());
	}
}
