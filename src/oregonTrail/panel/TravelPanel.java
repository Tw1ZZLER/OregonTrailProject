package oregonTrail.panel;

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
import oregonTrail.OregonTrail;
import oregonTrail.Wagon;

/**
 * Main traveling panel, shows most pertinent information to wagon
 * @author Corbin Hibler
 * @date 2024-04-08
 */
public class TravelPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblWeather;
	private JLabel lblHealth;
	private JLabel lblFood;
	private JLabel lblDistanceTraveledLabel;
	private JLabel lblDistanceTraveled;
	private JLabel lblNextLandmarkLabel;
	private JLabel lblNextLandmarkMiles;
	private JLabel lblHealthLabel;
	private JLabel lblFoodLabel;
	private JLabel lblWeatherLabel;
	private JLabel lblDateLabel;
	private JLabel lblDate;
	public JButton btnContinue;
	public JButton btnSizeUpThe;
	private JLabel lblNameOfNext;
	private JLabel lblNextLandmarkName;
	
	/**
	 * Sets text of date label
	 * @param String Descriptive string date in long format
	 */
	public void setDateText(String string) {
		lblDate.setText(string);
	}
	
	/**
	 * Sets text of weather label
	 * @param String Descriptive string of weather type
	 */
	public void setWeatherText(String string) {
		lblWeather.setText(string);
	}

	/**
	 * Sets text of health label
	 * @param String Descriptive string of health condition
	 */
	public void setHealthText(String string) {
		lblHealth.setText(string);
	}

	/**
	 * Sets text of pounds of food label
	 * @param int Pounds of food after travel
	 */
	public void setFoodText(int poundsFood) {
		String string = poundsFood + " pounds";
		lblFood.setText(string);
	}

	/**
	 * Sets text of the distance traveled label
	 * @param int Miles of distance traveled after travel
	 */
	public void setDistanceTraveledText(int milesDistanceTraveled) {
		String string = milesDistanceTraveled + " miles";
		lblDistanceTraveled.setText(string);
	}

	/**
	 * Sets text of the next landmark in miles label
	 * @param int Miles of distance until next landmark after travel
	 */
	public void setNextLandmarkMilesText(int milesNextLandmark) {
		String string = milesNextLandmark + " miles";
		lblNextLandmarkMiles.setText(string);
	}
	
	/**
	 * Sets text of the next landmark's name label
	 * @param String name of next landmark
	 */
	public void setNextLandmarkNameText(String nameNextLandmark) {
		lblNextLandmarkMiles.setText(nameNextLandmark);
	}

	/**
	 * Create the panel.
	 */
	public TravelPanel(OregonTrail oregonTrail) {
		setOpaque(false);
		setLayout(new MigLayout("", "[50.00%:50.00%:50.00%,grow][50.00%:50.00%:50.00%,grow,right]", "[50.00%:50.00%:50.00%,grow,top][27px][][grow 25][grow 25][grow 25][grow 25][grow 25][grow 25][]"));
		
		btnContinue = new JButton("GO!!!!!!!!");
		btnContinue.setPreferredSize(new Dimension(77, 50));
		btnContinue.setForeground(Color.WHITE);
		btnContinue.setBackground(Color.BLACK);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				oregonTrail.getTravelState().travelToggle();
			}
		});
		
		btnSizeUpThe = new JButton("Size Up the Situation");
		btnSizeUpThe.setMinimumSize(new Dimension(159, 45));
		btnSizeUpThe.setPreferredSize(new Dimension(159, 50));
		btnSizeUpThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				oregonTrail.openPanel(oregonTrail.TRAIL_MENU_PANEL, TravelPanel.this);
			}
		});
		btnSizeUpThe.setBackground(Color.BLACK);
		btnSizeUpThe.setForeground(Color.WHITE);
		add(btnSizeUpThe, "cell 0 1 2 1,growx,aligny center");
		
		lblDateLabel = new JLabel("Date:");
		lblDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		add(lblDateLabel, "cell 0 2,alignx right");
		
		lblDate = new JLabel("August 11, 1848");
		lblDate.setFont(new Font("Dialog", Font.BOLD, 24));
		add(lblDate, "cell 1 2,alignx left");
		
		lblWeather = new JLabel("Severe Thunderstorm");
		lblWeather.setFont(new Font("Dialog", Font.BOLD, 24));
		add(lblWeather, "cell 1 3,alignx left");
		
		lblHealth = new JLabel("Fair");
		lblHealth.setFont(new Font("Dialog", Font.BOLD, 24));
		add(lblHealth, "cell 1 4,alignx left");
		
		lblFood = new JLabel(oregonTrail.WAGON.getTotalFoodWeight() + " pounds");
		lblFood.setFont(new Font("Dialog", Font.BOLD, 24));
		add(lblFood, "cell 1 5,alignx left");
		
		lblNameOfNext = new JLabel("Name of Next Landmark:");
		lblNameOfNext.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNameOfNext.setFont(new Font("Dialog", Font.BOLD, 24));
		add(lblNameOfNext, "cell 0 7,alignx right");
		
		lblNextLandmarkName = new JLabel("Fort Strong");
		lblNextLandmarkName.setHorizontalAlignment(SwingConstants.LEFT);
		lblNextLandmarkName.setFont(new Font("Dialog", Font.BOLD, 24));
		add(lblNextLandmarkName, "cell 1 7,alignx left");
		
		lblDistanceTraveledLabel = new JLabel("Distance Traveled:");
		lblDistanceTraveledLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblDistanceTraveledLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblDistanceTraveledLabel, "cell 0 8,alignx right");
		
		lblDistanceTraveled = new JLabel("0 miles");
		lblDistanceTraveled.setFont(new Font("Dialog", Font.BOLD, 24));
		add(lblDistanceTraveled, "cell 1 8,alignx left");
		
		lblNextLandmarkLabel = new JLabel("Next Landmark:");
		lblNextLandmarkLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNextLandmarkLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblNextLandmarkLabel, "cell 0 6,alignx right,aligny center");
		
		lblNextLandmarkMiles = new JLabel("0 miles");
		lblNextLandmarkMiles.setFont(new Font("Dialog", Font.BOLD, 24));
		add(lblNextLandmarkMiles, "cell 1 6,alignx left");
		
		lblHealthLabel = new JLabel("Health:");
		lblHealthLabel.setOpaque(true);
		lblHealthLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblHealthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblHealthLabel, "cell 0 4,alignx right,aligny center");
		
		lblFoodLabel = new JLabel("Food:");
		lblFoodLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblFoodLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFoodLabel, "cell 0 5,alignx right,aligny center");
		
		lblWeatherLabel = new JLabel("Weather:");
		lblWeatherLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblWeatherLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblWeatherLabel, "cell 0 3,alignx right,aligny center");
		add(btnContinue, "cell 0 9 2 1,growx,aligny bottom");

	}
}
