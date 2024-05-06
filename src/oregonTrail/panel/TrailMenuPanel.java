package oregonTrail.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import oregonTrail.Health;
import oregonTrail.OregonTrail;


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
    private JButton changePaceButton;
    private JButton restButton;
    private JButton tradeButton;
    private JButton huntButton;
    private JLabel foodRationingValueLabel;
    private Health health;
    private JLabel lblSituation;
    private JLabel rationingValueLabel;
    private JLabel rationingLabel;
    private JLabel foodLabel;
    private JLabel foodValueLabel;

    /**
     * Constructs a TrailMenuPanel with the specified OregonTrail instance.
     * @param pOregonTrail The main OregonTrail instance
     */
    public TrailMenuPanel(OregonTrail pOregonTrail) {
        this.oregonTrail = pOregonTrail;
        
        setLayout(new MigLayout("wrap 2", "[grow]10[grow][grow][grow]", "[grow]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]"));
        
		lblSituation = new JLabel("THE SITUATION");
		lblSituation.setFont(new Font("Impact", Font.BOLD, 72));
		lblSituation.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSituation, "cell 0 0 4 1,alignx center");

		// Display date
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(dateLabel, "cell 0 1,alignx right");
		dateValueLabel = new JLabel("August 11, 1848");
		dateValueLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		add(dateValueLabel, "cell 1 1");
		
		foodLabel = new JLabel("Food:");
		foodLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		foodLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		add(foodLabel, "cell 2 1,alignx right");
		
		foodValueLabel = new JLabel("1400 pounds");
		foodValueLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		add(foodValueLabel, "cell 3 1");

		// Display weather
		JLabel weatherLabel = new JLabel("Weather:");
		weatherLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		weatherLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(weatherLabel, "cell 0 2,alignx right");
		weatherValueLabel = new JLabel("Warm");
		weatherValueLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		add(weatherValueLabel, "cell 1 2");
		
		rationingLabel = new JLabel("Rationing:");
		rationingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		rationingLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		add(rationingLabel, "cell 2 2,alignx right");
		
		rationingValueLabel = new JLabel("Regular Jane");
		rationingValueLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		add(rationingValueLabel, "cell 3 2");

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
		add(paceLabel, "cell 2 3,alignx right");
		paceValueLabel = new JLabel("12");
		paceValueLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		add(paceValueLabel, "cell 3 3");

		// Add buttons
		continueButton = new JButton("Continue on Trail");
		continueButton.setBackground(Color.BLACK);
		continueButton.setForeground(Color.WHITE);
		continueButton.setFont(new Font("Impact", Font.PLAIN, 24));
		add(continueButton, "cell 0 4 4 1,growx");
		
		// Add action listeners to buttons
		continueButton.addActionListener(e -> oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL));
        changePaceButton = new JButton("Change Pace");
        changePaceButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
				String option = oregonTrail.WAGON.travelSpeedDialog(TrailMenuPanel.this);
        		paceValueLabel.setText(option);
        	}
        });
        changePaceButton.setBackground(Color.BLACK);
        changePaceButton.setForeground(Color.WHITE);
        changePaceButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(changePaceButton, "cell 0 5 4 1,growx");
        checkSuppliesButton = new JButton("Check Supplies");
        checkSuppliesButton.setBackground(Color.BLACK);
        checkSuppliesButton.setForeground(Color.WHITE);
        checkSuppliesButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(checkSuppliesButton, "cell 0 6 4 1,growx");
        checkSuppliesButton.addActionListener(e -> oregonTrail.openPanel(oregonTrail.SUPPLIES_PANEL));
        lookMapButton = new JButton("Look at Map");
        lookMapButton.setBackground(Color.BLACK);
        lookMapButton.setForeground(Color.WHITE);
        lookMapButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(lookMapButton, "cell 0 7 4 1,growx");
        lookMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create and configure popup window
                JDialog popup = new JDialog();
                
                // Add your map content here
                JLabel mapLabel = new JLabel(new ImageIcon("src/images/mapagain.gif"));
                mapLabel.setFont(new Font("Impact", Font.PLAIN, 24));
                popup.add(mapLabel);
                
                // Pack and display the popup window
                popup.pack();
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });
       
        changeRationsButton = new JButton("Change Food Rations");
        changeRationsButton.setBackground(Color.BLACK);
        changeRationsButton.setForeground(Color.WHITE);
        changeRationsButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(changeRationsButton, "cell 0 8 4 1,growx");
        changeRationsButton.addActionListener(e -> oregonTrail.WAGON.foodConsumptionDialog(TrailMenuPanel.this));
        restButton = new JButton("Stop to Rest");
        restButton.setBackground(Color.BLACK);
        restButton.setForeground(Color.WHITE);
        restButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(restButton, "cell 0 9 4 1,growx");
        restButton.addActionListener(e -> oregonTrail.getTravelState().rest(this));
        tradeButton = new JButton("Attempt to Trade");
        tradeButton.setBackground(Color.BLACK);
        tradeButton.setForeground(Color.WHITE);
        tradeButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(tradeButton, "cell 0 10 4 1,growx");
        tradeButton.addActionListener(e -> oregonTrail.openPanel(oregonTrail.TRADE_PANEL));
        huntButton = new JButton("Hunt for Food");
        huntButton.setBackground(Color.BLACK);
        huntButton.setForeground(Color.WHITE);
        huntButton.setFont(new Font("Impact", Font.PLAIN, 24));
        add(huntButton, "cell 0 11 4 1,growx");
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
     * Sets text of weather label
     * @param string Descriptive string of WeatherType
     */
    public void setWeatherText(String string) {
    	weatherValueLabel.setText(string);
    }
    
    /**
     * Sets text of health label
     * based on health class
     */
    public void updateHealthLabel() {
	    healthValueLabel.setText(health.getGeneralHealthAsString());
	}

    /**
     * Sets text of food label
     * @param newFoodWeight Value of food weight after consumption
     */
	public void setFoodText(int newFoodWeight) {
		foodValueLabel.setText(newFoodWeight + " pounds");
	}
}
