package oregonTrail.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import net.miginfocom.swing.MigLayout;
import oregonTrail.Health;
import oregonTrail.OregonTrail;
import oregonTrail.landmark.Landmark;

/**
 * Main traveling panel, shows most pertinent information to wagon
 * This panel displays information such as date, weather, health, food, distance traveled, and the next landmark.
 * It also provides buttons for continuing the travel or sizing up the situation.
 * @author Corbin Hibler
 * @date 2024-04-08
 * @filename TravelPanel.java
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
    private JLabel lblImage;
    private Health health;
    
    private ImageIcon[] wagonImages;
    private int currentImageIndex;
    private Timer animationTimer;
    private static final String[] IMAGE_PATHS = {
        "src/images/wagon.png",
        "src/images/wagonframe2.png",
        "src/images/wagonframe3.png"
    };
    
    /**
     * Sets text of date label
     * @param string Descriptive string date in long format
     */
    public void setDateText(String string) {
        lblDate.setText(string);
    }
    
    /**
     * Sets text of weather label
     * @param string Descriptive string of weather type
     */
    public void setWeatherText(String string) {
        lblWeather.setText(string);
    }

    /**
     * Sets text of health label
     * @param string Descriptive string of health condition
     */
    public void setHealthText(String string) {
        lblHealth.setText(string);
    }

    /**
     * Sets text of pounds of food label
     * @param poundsFood Pounds of food after travel
     */
    public void setFoodText(int poundsFood) {
        String string = poundsFood + " pounds";
        lblFood.setText(string);
    }

    /**
     * Sets text of the distance traveled label
     * @param milesDistanceTraveled Miles of distance traveled after travel
     */
    public void setDistanceTraveledText(int milesDistanceTraveled) {
        String string = milesDistanceTraveled + " miles";
        lblDistanceTraveled.setText(string);
    }

    /**
     * Sets text of the next landmark in miles label
     * @param milesNextLandmark Miles of distance until next landmark after travel
     */
    public void setNextLandmarkMilesText(int milesNextLandmark) {
        String string = milesNextLandmark + " miles";
        lblNextLandmarkMiles.setText(string);
    }
    public int currentDistanceTraveled(int milesDistanceTraveled) {
        return milesDistanceTraveled;
    }
    
    /**
     * Sets text of the next landmark's name label
     * @param nameNextLandmark Name of next landmark
     */
    public void setNextLandmarkNameText(String nameNextLandmark) {
        lblNextLandmarkName.setText(nameNextLandmark);
    }

    /**
     * Create the panel.
     * @param oregonTrail oregonTrail object required for the game
     */
    public TravelPanel(OregonTrail oregonTrail) {
        health = new Health(); // Initialize the Health instance
        setOpaque(false);
        setLayout(new MigLayout("", "[50.00%:50.00%:50.00%,grow][50.00%:50.00%:50.00%,grow,right]", "[15.00%:50.00%:50.00%,grow,top][27px][][grow 25][grow 25][grow 25][grow 25][grow 25][grow 25][27px]"));
        
        btnContinue = new JButton("GO!!!!!!!!");
        btnContinue.setFont(new Font("Impact", Font.PLAIN, 36));
        btnContinue.setPreferredSize(new Dimension(77, 50));
        btnContinue.setForeground(Color.WHITE);
        btnContinue.setBackground(Color.BLACK);
        btnContinue.addActionListener(e -> oregonTrail.getTravelState().travelToggle());
        
        btnSizeUpThe = new JButton("Size Up the Situation");
        btnSizeUpThe.setFont(new Font("Impact", Font.PLAIN, 36));
        btnSizeUpThe.setForeground(Color.WHITE);
        btnSizeUpThe.setMinimumSize(new Dimension(159, 45));
        btnSizeUpThe.setPreferredSize(new Dimension(159, 50));
        btnSizeUpThe.addActionListener(e -> oregonTrail.openPanel(oregonTrail.TRAIL_MENU_PANEL));
        
        lblImage = new JLabel();
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setFont(new Font("Impact", Font.PLAIN, 12));
        add(lblImage, "cell 0 0 2 1,alignx center,aligny center");
        
        // Scale wagon image appropriately
        wagonImages = new ImageIcon[IMAGE_PATHS.length];
        for (int i = 0; i < IMAGE_PATHS.length; i++) {
            wagonImages[i] = new ImageIcon(IMAGE_PATHS[i]);
        }
        animationTimer = new Timer(1000, e -> {
            currentImageIndex = (currentImageIndex + 1) % wagonImages.length;
            lblImage.setIcon(wagonImages[currentImageIndex]);
        });
        animationTimer.start(); // Start the timer
        
        btnSizeUpThe.setBackground(Color.BLACK);
        add(btnSizeUpThe, "cell 0 1 2 1,growx,aligny center");
        
        lblDateLabel = new JLabel("Date:");
        lblDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDateLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        add(lblDateLabel, "cell 0 2,alignx right");
        
        lblDate = new JLabel("August 11, 1848");
        lblDate.setFont(new Font("Impact", Font.PLAIN, 24));
        add(lblDate, "cell 1 2,alignx left");
        
        lblWeather = new JLabel("Warm");
        lblWeather.setFont(new Font("Impact", Font.PLAIN, 24));
        add(lblWeather, "cell 1 3,alignx left");
        
        lblHealth = new JLabel("Fair");
        lblHealth.setFont(new Font("Impact", Font.PLAIN, 24));
        add(lblHealth, "cell 1 4,alignx left");
        
        lblFood = new JLabel(oregonTrail.WAGON.getTotalFoodWeight() + " pounds");
        lblFood.setFont(new Font("Impact", Font.PLAIN, 24));
        add(lblFood, "cell 1 5,alignx left");
        
        lblNameOfNext = new JLabel("Name of Next Landmark:");
        lblNameOfNext.setHorizontalAlignment(SwingConstants.TRAILING);
        lblNameOfNext.setFont(new Font("Impact", Font.PLAIN, 24));
        add(lblNameOfNext, "cell 0 7,alignx right");
        
        lblNextLandmarkName = new JLabel(Landmark.KANSAS_RIVER.getName());
        lblNextLandmarkName.setHorizontalAlignment(SwingConstants.LEFT);
        lblNextLandmarkName.setFont(new Font("Impact", Font.PLAIN, 24));
        add(lblNextLandmarkName, "cell 1 7,alignx left");
        
        lblDistanceTraveledLabel = new JLabel("Distance Traveled:");
        lblDistanceTraveledLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        lblDistanceTraveledLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        add(lblDistanceTraveledLabel, "cell 0 8,alignx right");
        
        lblDistanceTraveled = new JLabel("0 miles");
        lblDistanceTraveled.setFont(new Font("Impact", Font.PLAIN, 24));
        add(lblDistanceTraveled, "cell 1 8,alignx left");
        
        lblNextLandmarkLabel = new JLabel("Next Landmark:");
        lblNextLandmarkLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        lblNextLandmarkLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        add(lblNextLandmarkLabel, "cell 0 6,alignx right,aligny center");
        
        lblNextLandmarkMiles = new JLabel("0 miles");
        lblNextLandmarkMiles.setFont(new Font("Impact", Font.PLAIN, 24));
        add(lblNextLandmarkMiles, "cell 1 6,alignx left");
        
        lblHealthLabel = new JLabel("Health:");
        lblHealthLabel.setOpaque(true);
        lblHealthLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        lblHealthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblHealthLabel, "cell 0 4,alignx right,aligny center");
        
        lblFoodLabel = new JLabel("Food:");
        lblFoodLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        lblFoodLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblFoodLabel, "cell 0 5,alignx right,aligny center");
        
        lblWeatherLabel = new JLabel("Weather:");
        lblWeatherLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        lblWeatherLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblWeatherLabel, "cell 0 3,alignx right,aligny center");
        add(btnContinue, "cell 0 9 2 1,growx,aligny bottom");
    }
    
    // Method to set the health label
    public void updateHealthLabel() {
        lblHealth.setText(health.getGeneralHealthAsString());
    }

    // Stop the timer when the panel is removed
    @Override
    public void removeNotify() {
        super.removeNotify();
        animationTimer.stop();
    }
}
