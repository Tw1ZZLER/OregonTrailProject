package oregonTrail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import oregonTrail.landmark.Fort;
import oregonTrail.landmark.Landmark;
import oregonTrail.landmark.River;

/**
 * Handles all logic related to traveling. Contains Swing timer for traveling
 * and updates TravelPanel accordingly.
 */
public class Travel {
	private OregonTrail oregonTrail;
	private int milesTraveled;
	private int milesNextLandmark;
	private Landmark currentLandmark;
	private Landmark nextLandmark;
	private static Random rand = new Random();
	private Calendar date = new GregorianCalendar(1848, 8, 11); // Set to August 11, 1848
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
	 * Function that gets called every time the Swing timer runs
	 * Moves date forward, generates miles traveled, updates miles until next landmark,
	 * and calculates food weight.
	 * @author Corbin Hibler
	 * @date 2024-04-09
	 */
	private void travelCycle() {
		// Use GregorianCalendar library as way of keeping track of time, and update panel accordingly
		date.add(GregorianCalendar.DAY_OF_MONTH, 1);
	    DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
	    String formattedDate = dateFormat.format(date.getTime());
	    oregonTrail.TRAVEL_PANEL.setDateText(formattedDate);
	    oregonTrail.TRAIL_MENU_PANEL.setDateText(formattedDate);
	    
	    // Generate miles generated and update label
	    int milesTraveledCycle = rand.nextInt(oregonTrail.WAGON.getTravelSpeed());
	    milesTraveled += milesTraveledCycle;
	    oregonTrail.TRAVEL_PANEL.setDistanceTraveledText(milesTraveled);
	    
	   
	    // Update miles until next landmark
	    milesNextLandmark -= milesTraveledCycle;
	    oregonTrail.TRAVEL_PANEL.setNextLandmarkMilesText(milesNextLandmark);
	    
	    // Calculate new food weight and set accordingly based on the mathematical models used in the original game [1]
	    // [1] R. P. Bouchard, “Chapter 16: Building the Mathematical Models,” in  R. Philip Bouchard; 1st edition (January 28, 2016), 
	    int totalFoodWeight = oregonTrail.WAGON.getTotalFoodWeight();
	    int newFoodWeight = (int) (totalFoodWeight-(oregonTrail.WAGON.getFoodConsumptionRate()*5)); 
	    oregonTrail.TRAVEL_PANEL.setFoodText(newFoodWeight);
	    oregonTrail.WAGON.setTotalFoodWeight(newFoodWeight);
	    
	    // Check if we have reached next landmark
	    checkLandmarks();
	}
	
	/**
	 * Function to handle starting and stopping of the travel timer
	 * @author Corbin Hibler
	 * @date 2024-04-09
	 */
	public void travelToggle() {
		this.oregonTrail.TRAVEL_PANEL.setNextLandmarkMilesText(milesNextLandmark);

		if (!timer.isRunning()) {
			timer.start();
			oregonTrail.TRAVEL_PANEL.btnContinue.setText("Stop Traveling!");
		}
		else {
			timer.stop();
			oregonTrail.TRAVEL_PANEL.btnContinue.setText("Continue on Trail!");
		}
	}
	
	/**
	 * Method to check if a landmark has been reached
	 */
	public void checkLandmarks() {
	    for (Landmark landmark : Landmark.landmarkList) {
	        if (milesNextLandmark <= 0 && milesTraveled >= landmark.getDistanceFromStart()) {
	            System.out.println("Landmark reached: " + landmark.getName());
	            Landmark nextLandmark = getNextLandmark(landmark);
	            System.out.println("Next landmark: " + (nextLandmark != null ? nextLandmark.getName() : "None"));
	            JPanel panel = oregonTrail.getPanelForLandmark(landmark);
	            System.out.println("Panel for landmark: " + (panel != null ? panel.getName() : "None"));
	            updateLandmarkReached(landmark, panel, nextLandmark);
	            break;
	        }
	    }
	}


	/**
	 * Retrieve the next landmark in the list given the current landmark
	 * @param currentLandmark 
	 * @return Landmark object of the next Landmark
	 */
	private Landmark getNextLandmark(Landmark currentLandmark) {
	    int currentIndex = Landmark.landmarkList.indexOf(currentLandmark);
	    
	    if (currentIndex < Landmark.landmarkList.size() - 1) {
	        // Return the next landmark in the list
	        return Landmark.landmarkList.get(currentIndex + 1);
	    } else
	        // If the current landmark is the last one, return null
	        return null;
	}

	
	/**
	 *  Method to update the game state when a landmark is reached
	 * @param currentLandmark
	 * @param currentLandmarkPanel
	 * @param nextLandmark
	 */
	private void updateLandmarkReached(Landmark currentLandmark, JPanel currentLandmarkPanel, Landmark nextLandmark) {
	    // Update the current landmark to the landmark that was just reached
	    this.currentLandmark = currentLandmark;
	    this.nextLandmark = nextLandmark;
	    milesNextLandmark = nextLandmark.getDistanceFromPrevious();
	    
	    // Update the UI
	    oregonTrail.openPanel(currentLandmarkPanel);
	    oregonTrail.TRAVEL_PANEL.setNextLandmarkNameText(nextLandmark.getName());
	    travelToggle();
	}


	/**
	 * @return the milesNextLandmark
	 */
	public int getMilesNextLandmark() {
		return milesNextLandmark;
	}

	/**
	 * @param milesNextLandmark the milesNextLandmark to set
	 */
	public void setMilesNextLandmark(int milesNextLandmark) {
		this.milesNextLandmark = milesNextLandmark;
	}
}
