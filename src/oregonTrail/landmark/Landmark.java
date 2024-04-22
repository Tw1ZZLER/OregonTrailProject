package oregonTrail.landmark;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

/**
 * Contains methods and logic that are used by all landmarks
 * Constants static constants used for each landmark
 * Subclasses: Fort, River
 * @author Corbin Hibler
 * @date 2024-04-16
 */
public class Landmark {
	protected String name;
	protected String nextLandmarkName;
	protected ImageIcon picture;
	protected int distanceFromStart;
	protected int distanceFromPrevious;
	protected int distanceToNext;
	protected boolean visited;

	public static final Landmark KANSAS_RIVER = new River("Kansas River Crossing", new ImageIcon("src/images/KansasRiver.jpg"), 100);
	public static final Landmark BIG_BLUE_RIVER = new River("Big Blue River Crossing", new ImageIcon("src/images/BigBlueRiver.jpg"), 150);
	public static final Landmark FORT_STRONG = new Fort("Fort Strong", new ImageIcon("src/images/FortStrong.jpg"), 250);
	public static final Landmark ASH_HOLLOW = new Landmark("Ash Hollow", new ImageIcon("src/images/AshHollow.jpg"), 400);
	public static final Landmark FORT_LARAMIE = new Fort("Fort Laramie", new ImageIcon("src/images/FortLaramie.jpg"), 700);
	public static final Landmark CHIMNEY_ROCK = new Landmark("Chimney Rock", new ImageIcon("src/images/ChimneyRock.jpg"), 600);
	public static final Landmark FORT_OREGON = new Fort("Fort Oregon", new ImageIcon("src/images/FortOregon.jpg"), 2000);

	
	// List of all landmarks in the game
	public static final ArrayList<Landmark> landmarkList = new ArrayList<Landmark>(Arrays.asList(
		KANSAS_RIVER, BIG_BLUE_RIVER, FORT_STRONG, ASH_HOLLOW,FORT_LARAMIE, CHIMNEY_ROCK, FORT_OREGON
	));
	
	public Landmark(String name, ImageIcon picture, int distanceFromStart) {
		this.name = name;
		this.picture = picture;
		System.out.println("Width: " + picture.getIconWidth() + ", Height: " + picture.getIconHeight());
		this.distanceFromStart = distanceFromStart;
		this.visited = false; // set by Travel class
	}

	/**
	 * Calculates distance from previous and returns integer value
	 * @return int distance from the previous landmark
	 */
	public int getDistanceFromPrevious() {
		if (this == KANSAS_RIVER) {
			distanceFromPrevious = distanceFromStart;
		} else {
			int landmarkIndex = landmarkList.indexOf(this);
			distanceFromPrevious = this.getDistanceFromStart() - landmarkList.get(landmarkIndex - 1).getDistanceFromStart();
		}
		return distanceFromPrevious;
	}
	

	/**
	 * Retrieve the next landmark in the list given the current landmark
	 * @param currentLandmark 
	 * @return Landmark object of the next Landmark
	 */
	public Landmark getNextLandmark() {
	    int currentIndex = Landmark.landmarkList.indexOf(this);
	    
	    if (currentIndex < Landmark.landmarkList.size() - 1) {
	        // Return the next landmark in the list
	        return Landmark.landmarkList.get(currentIndex + 1);
	    } else
	        // If the current landmark is the last one, return null
	        return null;
	}


	/**
	 * Returns the name of the Landmark
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns in ImageIcon of the picture used
	 * for the landmark
	 * @return the picture
	 */
	public ImageIcon getPicture() {
		return picture;
	}

	/**
	 * Returns the distance from the beginning of the game,
	 * (Independence, MI) to the Landmark.
	 * @return the distanceFromStart
	 */
	public int getDistanceFromStart() {
		return distanceFromStart;
	}
	
	/**
	 * Set a landmark has having been visited already cannot be visited again.
	 * Cannot set to false, must always be set to true.
	 * @param visited the visited to set
	 */
	public void setVisited() {
		this.visited = true;
	}
	

	/**
	 * Getter method for if a landmark has been visited yet
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}
}
