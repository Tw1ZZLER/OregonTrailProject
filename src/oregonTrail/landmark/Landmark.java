package oregonTrail.landmark;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public static List<Landmark> landmarkList = Arrays.stream(LandmarkType.values())
	                                      .map(LandmarkType::getLandmark)
	                                      .collect(Collectors.toList());
	
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
		if (this == LandmarkType.KANSAS_RIVER.getLandmark()) {
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
