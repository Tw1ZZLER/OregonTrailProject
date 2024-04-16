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
	        if (milesNextLandmark <= 0 && 
	        	milesTraveled >= landmark.getDistanceFromStart() && 
	        	!landmark.isVisited()) {
	            // Correctly update the current and next landmarks
	            this.currentLandmark = landmark;
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
	 *  Method to update the game state when a landmark is reached
	 * @param currentLandmark
	 * @param currentLandmarkPanel
	 * @param nextLandmark
	 */
	private void updateLandmarkReached(Landmark currentLandmark, Landmark nextLandmark) {
	    // Update the UI
		System.out.println("landmark passed to updatelandmarkreached: " + currentLandmark.getName());
        JPanel panel = oregonTrail.getPanelForLandmark(currentLandmark);
	    oregonTrail.openPanel(panel);
	    
	    // Make sure landmark cannot be visited again
        currentLandmark.setVisited();
	    
	    try {
		    // Next Landmark and associated variables
		    oregonTrail.TRAVEL_PANEL.setNextLandmarkNameText(nextLandmark.getName());
		    this.currentLandmark = nextLandmark;
		    this.nextLandmark = nextLandmark.getNextLandmark();
		    milesNextLandmark = nextLandmark.getDistanceFromPrevious();
		} catch (NullPointerException e) {
			// If at Fort Oregon, no next landmark is available
			System.out.println("Last Fort has been reached, no next landmark available.");
		    oregonTrail.TRAVEL_PANEL.setNextLandmarkNameText("END OF GAME");
		}
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
