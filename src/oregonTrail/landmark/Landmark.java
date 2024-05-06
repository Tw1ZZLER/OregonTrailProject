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
 * @filename Landmark.java
 */
public class Landmark {
	protected String name;
	protected String nextLandmarkName;
	protected ImageIcon picture;
	protected int distanceFromStart;
	protected int distanceFromPrevious;
	protected int distanceToNext;
	protected boolean isVisited;
	
	// The following should be an enumerated type, however it gave us a lot of issues so we are sticking with this.
	public static final Landmark KANSAS_RIVER      = new River   ("Kansas River Crossing", new ImageIcon("src/images/KansasRiver.jpg"), 100);
	public static final Landmark BIG_BLUE_RIVER    = new River   ("Big Blue River Crossing", new ImageIcon("src/images/BigBlueRiver.jpg"), 150);
	public static final Landmark FORT_STRONG       = new Fort    ("Fort Strong", new ImageIcon("src/images/FortStrong.jpg"), 250);
	public static final Landmark ASH_HOLLOW        = new Landmark("Ash Hollow", new ImageIcon("src/images/AshHollow.jpg"), 400);
	public static final Landmark CHIMNEY_ROCK      = new Landmark("Chimney Rock", new ImageIcon("src/images/ChimneyRock.jpg"), 500);
	public static final Landmark FORT_LARAMIE      = new Fort    ("Fort Laramie", new ImageIcon("src/images/FortLaramie.jpg"), 600);
	public static final Landmark INDEPENDENCE_ROCK = new Landmark("Independence Rock", new ImageIcon("src/images/IndependenceRock.jpg"), 800);
	public static final Landmark SOUTH_PASS		   = new Landmark("South Pass", new ImageIcon("src/images/SouthPass.jpg"), 1050);
	public static final Landmark GREEN_RIVER       = new River   ("Green River Crossing", new ImageIcon("src/images/GreenRiver.jpg"), 1150);
	public static final Landmark FORT_BRIDGER	   = new Fort    ("Fort Bridger", new ImageIcon("src/images/FortBridger.jpg"), 1200);
	public static final Landmark SODA_SPRINGS      = new Landmark("Soda Springs", new ImageIcon("src/images/SodaSprings.jpg"), 1250);
	public static final Landmark FORT_HALL         = new Fort    ("Fort Hall", new ImageIcon("src/images/FortHall.jpg"), 1300);
	public static final Landmark SNAKE_RIVER       = new River   ("Snake River Crossing", new ImageIcon("src/images/SnakeRiver.jpg"), 1450);
	public static final Landmark FORT_BOISE        = new Fort    ("Fort Boise", new ImageIcon("src/images/FortBoise.jpg"), 1550);
	public static final Landmark BLUE_MOUNTAINS    = new Landmark("Blue Mountains", new ImageIcon("src/images/BlueMountains.jpg"), 1650);
	public static final Landmark FORT_WALLA_WALLA  = new Fort    ("Fort Walla Walla", new ImageIcon("src/images/FortWallaWalla.jpg"), 1750);
	public static final Landmark THE_DALLES        = new Landmark("The Dalles", new ImageIcon("src/images/TheDalles.jpg"), 1900);
	public static final Landmark OREGON_CITY       = new Fort    ("Fort Oregon", new ImageIcon("src/images/OregonCity.jpg"), 2000);
	
	// List of all landmarks in the game
	public static final ArrayList<Landmark> landmarkList = new ArrayList<Landmark>(Arrays.asList(
			KANSAS_RIVER,      
			BIG_BLUE_RIVER,    
			FORT_STRONG,       
			ASH_HOLLOW,       
			CHIMNEY_ROCK,      
			FORT_LARAMIE,      
			INDEPENDENCE_ROCK, 
			SOUTH_PASS,		   
			GREEN_RIVER,       
			FORT_BRIDGER,	   
			SODA_SPRINGS,     
			FORT_HALL,         
			SNAKE_RIVER,
			FORT_BOISE,        
			BLUE_MOUNTAINS,    
			FORT_WALLA_WALLA,  
			THE_DALLES,        
			OREGON_CITY
	));
	
	public Landmark(String name, ImageIcon picture, int distanceFromStart) {
		this.name = name;
		this.picture = picture;
		System.out.println("Width: " + picture.getIconWidth() + ", Height: " + picture.getIconHeight());
		this.distanceFromStart = distanceFromStart;
		this.isVisited = false; // set by Travel class
	}

	/**
	 * Calculates distance from previous and returns integer value
	 * @return int distance from the previous landmark
	 */
	public int getDistanceFromPrevious() {
		if (this == Landmark.KANSAS_RIVER) {
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
	    int currentIndex = landmarkList.indexOf(this);
	    
	    if (currentIndex < landmarkList.size() - 1) {
	        // Return the next landmark in the list
	        return landmarkList.get(currentIndex + 1);
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
	 * @param isVisited the visited to set
	 */
	public void setVisited() {
		this.isVisited = true;
	}
	

	/**
	 * Getter method for if a landmark has been visited yet
	 * @return the visited
	 */
	public boolean isVisited() {
		return isVisited;
	}
}
