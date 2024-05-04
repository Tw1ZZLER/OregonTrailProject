package oregonTrail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import oregonTrail.landmark.Landmark;
import oregonTrail.weather.WeatherZone;

/**
 * Handles all logic related to traveling. Contains Swing timer for traveling
 * and updates TravelPanel accordingly.
 * 
 * @author Corbin Hibler
 * @date 2024-04-30
 * @filename Travel.java
 */
public class Travel {
	private OregonTrail oregonTrail;
	private int milesTraveled;
	private int milesNextLandmark;
	private Landmark nextLandmark;
	private static Random rand = new Random();
	private Calendar date = new GregorianCalendar(1848, 7, 11); // Set to August 11, 1848
	private Timer timer = new Timer(50, new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			travelCycle();
		}
	});	
	
	public Travel(OregonTrail oregonTrail) {
		this.oregonTrail = oregonTrail;
		this.nextLandmark = Landmark.KANSAS_RIVER; // default to first landmark!
		this.milesNextLandmark = Landmark.KANSAS_RIVER.getDistanceFromPrevious();
	}
	
	/**
	 * Increments the day by passed amount sets the labels accordingly
	 * @param days The number of days to increment by
	 */
	private void incrementDate(int days) {
		// Use GregorianCalendar library as way of keeping track of time, and update panel accordingly
		date.add(GregorianCalendar.DAY_OF_MONTH, days);
	    DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
	    String formattedDate = dateFormat.format(date.getTime());
	    oregonTrail.TRAVEL_PANEL.setDateText(formattedDate);
	    oregonTrail.TRAIL_MENU_PANEL.setDateText(formattedDate);
	}
	
	/**
	 * Function that gets called every time the Swing timer runs
	 * Moves date forward, generates miles traveled, updates miles until next landmark,
	 * and calculates food weight.
	 * @author Corbin Hibler
	 * @date 2024-04-09
	 */
	private void travelCycle() {
		incrementDate(1);
	    
	    // Generate miles generated and update label
	    int milesTraveledCycle = rand.nextInt(oregonTrail.WAGON.getTravelSpeed());
	    milesTraveled += milesTraveledCycle;
	    oregonTrail.TRAVEL_PANEL.setDistanceTraveledText(milesTraveled);
	    
	    // Update miles until next landmark
	    milesNextLandmark -= milesTraveledCycle;
	    oregonTrail.TRAVEL_PANEL.setNextLandmarkMilesText(milesNextLandmark);
	    
	    // Consumes food daily
	    Food.consumeFood(oregonTrail);
	    
	    // Update WeatherZone based on miles traveled
	    for (WeatherZone zone : WeatherZone.values()) {
			if (milesTraveled > zone.getMileMarker()) {
				oregonTrail.getWeatherState().setWeatherZone(zone);
			}
	    }
	    
	    // Update WeatherType
	    oregonTrail.getWeatherState().calcWeather();
	    
	    // Update weather label for current weather
	    String weatherString = oregonTrail.getWeatherState().getWeatherString();
	    oregonTrail.TRAVEL_PANEL.setWeatherText(weatherString);
	    
	    // Update health
	    // TODO
	    
	    // Check if we have reached next landmark
	    checkLandmarks();
	}
	


    /**
     * Function to handle starting and stopping of the travel timer.
     * @author Corbin Hibler
     * @date 2024-04-23
     */
    public void travelToggle() {
        this.oregonTrail.TRAVEL_PANEL.setNextLandmarkMilesText(milesNextLandmark);

        if (!timer.isRunning()) {
            timer.start();
            oregonTrail.TRAVEL_PANEL.btnContinue.setText("Stop Traveling!");
        } else {
            timer.stop();
            oregonTrail.TRAVEL_PANEL.btnContinue.setText("Continue on Trail!");
        }
    }

    /**
     * Function to rest (days go by without moving, increases health)
     * @author Corbin Hibler
     * @date 2024-03-05
     */
    public void rest(JComponent comp) {
    	String restDaysString = JOptionPane.showInputDialog(comp, "Enter the amount of days you'd like to rest:");
    	int restDays = Integer.parseInt(restDaysString);
    	incrementDate(restDays);
    	for (int i = 0; i < restDays; i++) {
    		Food.consumeFood(oregonTrail);
    	}
    }
    
    /**
     * Method to check if a landmark has been reached.
     * @author Corbin Hibler
     * @date 2024-04-23
     */
    public void checkLandmarks() {
        for (Landmark landmark : Landmark.landmarkList) {
            if (milesNextLandmark <= 0 && milesTraveled >= landmark.getDistanceFromStart() && !landmark.isVisited()) {
                this.nextLandmark = landmark.getNextLandmark();
                // Ensure milesNextLandmark is updated correctly for the next landmark
                try {
                    milesNextLandmark = this.nextLandmark.getDistanceFromPrevious();
                } catch (NullPointerException e) {
                    // If at the end of the trail, there is no next Landmark:
                    milesNextLandmark = 0;
                }
                updateLandmarkReached(landmark, nextLandmark);
                break;
            }
        }
    }

    /**
     * Method to update the game state when a landmark is reached.
     * 
     * @param currentLandmark The current landmark reached.
     * @param nextLandmark    The next landmark to be reached.
     * @author Corbin Hibler
     * @date 2024-04-22
     */
    private void updateLandmarkReached(Landmark currentLandmark, Landmark nextLandmark) {
        // Update the UI
        JPanel panel = oregonTrail.getPanelForLandmark(currentLandmark);
        oregonTrail.openPanel(panel);

        // Make sure landmark cannot be visited again
        currentLandmark.setVisited();

        try {
            // Next Landmark and associated variables
            oregonTrail.TRAVEL_PANEL.setNextLandmarkNameText(nextLandmark.getName());
            this.nextLandmark = nextLandmark.getNextLandmark();
            milesNextLandmark = nextLandmark.getDistanceFromPrevious();
        } catch (NullPointerException e) {
            // If at Fort Oregon, no next landmark is available
            oregonTrail.TRAVEL_PANEL.setNextLandmarkNameText("END OF GAME");
        }
        travelToggle();
    }

    /**
     * Gets the number of miles remaining until the next landmark.
     * 
     * @return The number of miles remaining until the next landmark.
     */
    public int getMilesNextLandmark() {
        return milesNextLandmark;
    }

    /**
     * Sets the number of miles remaining until the next landmark.
     * 
     * @param milesNextLandmark The number of miles remaining until the next landmark.
     */
    public void setMilesNextLandmark(int milesNextLandmark) {
        this.milesNextLandmark = milesNextLandmark;
    }

    /**
     * Gets the total miles traveled.
     * 
     * @return The total miles traveled.
     */
    public int getMilesTraveled() {
        return milesTraveled;
    }
    
    /**
     * Gets the current month from the GregorianCalendar object.
     * @return The current month
     */
    public int getMonth() {
    	return date.get(Calendar.MONTH);
    }

    /**
     * Moves the player back a specified number of miles.
     * 
     * @param milesBack The number of miles to move back.
     * @author Ray Otto
     * @date 2024-04-23
     */
    public void moveBack(int milesBack) {
        if (milesTraveled >= milesBack) {
            milesTraveled -= milesBack;
            oregonTrail.TRAVEL_PANEL.setDistanceTraveledText(milesTraveled);
            // Recalculate miles until next landmark
            milesNextLandmark += milesBack;
            oregonTrail.TRAVEL_PANEL.setNextLandmarkMilesText(milesNextLandmark);
            // Update the next landmark based on the new position
            updateNextLandmark();
            System.out.println("Moved back " + milesBack + " miles.");
        } else {
            System.out.println("Cannot move back " + milesBack + " miles. Already at the starting point.");
        }
    }

    /**
     * Updates the information about the next landmark based on the current position.
     * @author Corbin Hibler
     * @date 2024-04-25
     */
    public void updateNextLandmark() {
        for (Landmark landmark : Landmark.landmarkList) {
            if (milesNextLandmark <= 0 && milesTraveled >= landmark.getDistanceFromStart() && !landmark.isVisited()) {
                this.nextLandmark = landmark.getNextLandmark();
                // Ensure milesNextLandmark is updated correctly for the next landmark
                try {
                    milesNextLandmark = this.nextLandmark.getDistanceFromPrevious();
                } catch (NullPointerException e) {
                    // If at the end of the trail, there is no next Landmark:
                    milesNextLandmark = 0;
                }
                updateLandmarkReached(landmark, nextLandmark);
                break;
            }
        }
    }
}
