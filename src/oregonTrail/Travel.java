package oregonTrail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import javax.swing.Timer;

/**
 * Handles all logic related to traveling. Contains Swing timer for traveling
 * and updates TravelPanel accordingly.
 */
public class Travel {
	private OregonTrail oregonTrail;
	private int milesTraveled;
	private int milesNextLandmark;
	private Random rand = new Random();
	private Calendar date = new GregorianCalendar(1848, 8, 11); // Set to August 11, 1848
	private Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			travelCycle();
		}
	});
	public static final int MILES_TO_KANSAS_RIVER = 100;
	public static final int MILES_TO_FORT_STRONG = 150;
	public static final int MILES_TO_FORT_OREGON = 400;
	
	public Travel(OregonTrail oregonTrail) {
		this.oregonTrail = oregonTrail;
		this.milesNextLandmark = MILES_TO_KANSAS_RIVER;
	}
	
	/**
	 * Function that gets called every time the Swing timer runs
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
	    
	   
	    // Check if we have reached fort
	    if (milesNextLandmark <= 0 && milesTraveled < MILES_TO_KANSAS_RIVER + 10) {
	    	oregonTrail.openPanel(oregonTrail.KANSAS_RIVER_PANEL, oregonTrail.TRAVEL_PANEL);
	    	milesNextLandmark = MILES_TO_FORT_STRONG;
	    	oregonTrail.TRAVEL_PANEL.setNextLandmarkNameText("Fort Strong");
	    		travelToggle();
		    	milesTraveled= 100;
		    	milesNextLandmark =50;
	    	}
		    
	    
	    if (milesNextLandmark <= 0 && milesTraveled < MILES_TO_FORT_STRONG + 10) {
	    	oregonTrail.openPanel(oregonTrail.FORT_STRONG_PANEL, oregonTrail.TRAVEL_PANEL);
	    	milesNextLandmark = MILES_TO_FORT_STRONG;
	    	oregonTrail.TRAVEL_PANEL.setNextLandmarkNameText("Fort Oregon");
	    	travelToggle();
	    }
	    
	    
	    if (milesNextLandmark <= 0 && milesTraveled > MILES_TO_FORT_STRONG + 100) {
	    	oregonTrail.openPanel(oregonTrail.FORT_OREGON_PANEL, oregonTrail.TRAVEL_PANEL);
	    	milesNextLandmark = 99999;
	    	travelToggle();
	    }
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
