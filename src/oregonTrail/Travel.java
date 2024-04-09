package oregonTrail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.*;
import javax.swing.Timer;

import oregonTrail.panel.TravelPanel;

import java.text.*;

/**
 * Handles all logic related to traveling. Contains Swing timer for traveling
 * and updates TravelPanel accordingly.
 */
public class Travel {
	private OregonTrail oregonTrail;
	private final TravelPanel TRAVEL_PANEL;
	private int milesTraveled;
	private int milesNextLandmark;
	private Random rand = new Random();
	private Calendar date = new GregorianCalendar(1848, 8, 11); // Set to August 11, 1848
	private Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			travelCycle();
		}
	});
	public static final int MILES_TO_FORT_STRONG = 150;
	public static final int MILES_TO_FORT_OREGON = 400;
	
	public Travel(OregonTrail oregonTrail, TravelPanel TRAVEL_PANEL) {
		this.oregonTrail = oregonTrail;
		this.TRAVEL_PANEL = TRAVEL_PANEL;
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
	    
	    // Generate miles generated and update label
	    int milesTraveledCycle = rand.nextInt(25);
	    milesTraveled += milesTraveledCycle;
	    oregonTrail.TRAVEL_PANEL.setDistanceTraveledText(milesTraveled);
	    
	    // Update miles until next landmark
	    milesNextLandmark -= milesTraveledCycle;
	    oregonTrail.TRAVEL_PANEL.setNextLandmarkText(milesNextLandmark);
	    
	    // Check if we have reached fort
	    if (milesNextLandmark <= 0) {
	    	oregonTrail.openFortPanel(TRAVEL_PANEL);
	    	milesNextLandmark = MILES_TO_FORT_OREGON;
	    	travelToggle();
	    }
	}
	
	/**
	 * Function to handle starting and stopping of the travel timer
	 * @author Corbin Hibler
	 * @date 2024-04-09
	 */
	public void travelToggle() {
		this.milesNextLandmark = MILES_TO_FORT_STRONG;
		this.oregonTrail.TRAVEL_PANEL.setNextLandmarkText(milesNextLandmark);

		if (!timer.isRunning()) {
			timer.start();
			TRAVEL_PANEL.btnContinue.setText("Stop Traveling!");
		}
		else {
			timer.stop();
			TRAVEL_PANEL.btnContinue.setText("Continue on Trail!");
		}
	}
}
