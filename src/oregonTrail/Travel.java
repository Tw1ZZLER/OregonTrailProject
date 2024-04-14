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

import oregonTrail.landmark.Fort;
import oregonTrail.landmark.RiverPanel;
import oregonTrail.panel.TravelPanel;

public class Travel {
    private OregonTrail oregonTrail;
    private int milesTraveled;
    private int milesNextLandmark;
    private Fort currentFort;
    private RiverPanel currentRiver;
    private Random rand = new Random();
    private Calendar date = new GregorianCalendar(1848, 8, 11);
    private Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            travelCycle();
        }
    });
    public static final int MILES_TO_FORT_STRONG = 150;
    public static final int MILES_TO_FORT_OREGON = 400;

    // Distances for the rivers
    public static final int DISTANCE_TO_WAKARUSA = 50;
    public static final int DISTANCE_TO_KANSAS = 100;
    public static final int DISTANCE_TO_VERMILION = 200;
    public static final int DISTANCE_TO_BIG_BLUE = 300;
    public static final int DISTANCE_TO_LITTLE_BLUE = 350;

    public Travel(OregonTrail oregonTrail) {
        this.oregonTrail = oregonTrail;
        this.milesNextLandmark = MILES_TO_FORT_STRONG;
    }

    public int getMilesNextLandmark() {
        return milesNextLandmark;
    }

    public int getMilesTraveled() {
        return milesTraveled;
    }

    private void travelCycle() {
        date.add(Calendar.DAY_OF_MONTH, 1);
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        String formattedDate = dateFormat.format(date.getTime());
        oregonTrail.TRAVEL_PANEL.setDateText(formattedDate);
        oregonTrail.TRAIL_MENU_PANEL.setDateText(formattedDate);

        int milesTraveledCycle = rand.nextInt(oregonTrail.WAGON.getTravelSpeed());
        milesTraveled += milesTraveledCycle;
        oregonTrail.TRAVEL_PANEL.setDistanceTraveledText(milesTraveled);

        milesNextLandmark -= milesTraveledCycle;
        oregonTrail.TRAVEL_PANEL.setNextLandmarkMilesText(milesNextLandmark);

        if (milesNextLandmark <= 0 && milesTraveled < MILES_TO_FORT_STRONG + 10) {
            oregonTrail.openPanel(oregonTrail.FORT_STRONG_PANEL, oregonTrail.TRAVEL_PANEL);
            milesNextLandmark = MILES_TO_FORT_OREGON;
            oregonTrail.TRAVEL_PANEL.setNextLandmarkNameText("Fort Oregon");
            travelToggle();
        }

        if (milesNextLandmark <= 0 && milesTraveled > MILES_TO_FORT_STRONG + 100) {
            oregonTrail.openPanel(oregonTrail.FORT_OREGON_PANEL, oregonTrail.TRAVEL_PANEL);
            milesNextLandmark = 99999;
            travelToggle();
        }

        checkForLandmark(); // Check if the wagon has reached a landmark
    }

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

    private void checkForLandmark() {
        if (milesNextLandmark <= 0) {
            
            if (milesTraveled == DISTANCE_TO_KANSAS) {
                currentRiver = new RiverPanel("Kansas River", 100, true, null);
            } else if (milesTraveled == DISTANCE_TO_VERMILION) {
                currentRiver = new RiverPanel("Vermilion River", 200, false, null);
            } else if (milesTraveled == DISTANCE_TO_BIG_BLUE) {
                currentRiver = new RiverPanel("Big Blue River", 300, true, null);
            } else if (milesTraveled == DISTANCE_TO_LITTLE_BLUE) {
                currentRiver = new RiverPanel("Little Blue River", 350, false, null);
            }

            if (currentRiver != null) {
                // Prompt the player to interact with the river
                oregonTrail.openPanel(new RiverPanel(currentRiver, oregonTrail.TRAVEL_PANEL), oregonTrail.TRAVEL_PANEL);
            }
        }
    }

    public void handleRiverCrossingDecision(String decision) {
        if (currentRiver != null) {
            switch (decision) {
                case "caulk":
                    caulkWagon();
                    break;
                case "wait":
                    waitAtRiver();
                    break;
                case "drive":
                    driveThroughRiver();
                    break;
                default:
                    oregonTrail.TRAVEL_PANEL.displayMessage("Invalid option. Please choose caulk, wait, or drive.");
                    break;
            }
        }
    }

    private void caulkWagon() {
        if (currentRiver != null) {
            oregonTrail.TRAVEL_PANEL.displayMessage("You attempt to caulk the wagon and float it across the " + currentRiver.getName() + ".");
            boolean success = !currentRiver.isDeep() || rand.nextBoolean(); // Randomly determine success if the river is not deep
            if (success) {
                oregonTrail.TRAVEL_PANEL.displayMessage("You successfully cross the river!");
            } else {
                oregonTrail.TRAVEL_PANEL.displayMessage("Caulking the wagon failed, and you lose some supplies.");
                // Implement deduction of supplies or other consequences
            }
        }
    }

    private void waitAtRiver() {
        if (currentRiver != null) {
            oregonTrail.TRAVEL_PANEL.displayMessage("You decide to wait at the " + currentRiver.getName() + " for better conditions.");
            boolean conditionsImprove = rand.nextBoolean(); // Randomly determine if conditions improve
            if (conditionsImprove) {
                oregonTrail.TRAVEL_PANEL.displayMessage("The river conditions improve, and you successfully cross!");
            } else {
                oregonTrail.TRAVEL_PANEL.displayMessage("You waited, but conditions did not improve. You might want to try another option.");
              
            }
        }
    }

    private void driveThroughRiver() {
        if (currentRiver != null) {
            oregonTrail.TRAVEL_PANEL.displayMessage("You attempt to drive the wagon through the " + currentRiver.getName() + ".");
            boolean success = !currentRiver.isDeep() || rand.nextBoolean(); // Randomly determine success if the river is not deep
            if (success) {
                oregonTrail.TRAVEL_PANEL.displayMessage("You successfully drive through the river!");
            } else {
                oregonTrail.TRAVEL_PANEL.displayMessage("Driving through the river failed, and you lose some supplies.");
                // Implement deduction of supplies or other consequences
            }
        }
    }

	public Fort getCurrentFort() {
		return currentFort;
	}

	public void setCurrentFort(Fort currentFort) {
		this.currentFort = currentFort;
	}
}

