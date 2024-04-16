package oregonTrail.landmark;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import oregonTrail.OregonTrail;

/**
 * Contains methods and logic that are used by all landmarks
 * Subclasses: Fort, River
 * @
 */
public class Landmark {
	protected String name;
	protected String nextLandmarkName;
	protected ImageIcon picture;
	protected int distanceFromStart;
	protected int distanceFromPrevious;
	protected int distanceToNext;
	
	public static final Landmark KANSAS_RIVER = new River("Kansas River", new ImageIcon("/images/KansasRiver.jpg"), 100);
	public static final Landmark FORT_STRONG = new Fort("Fort Strong", new ImageIcon("/images/FortStrong.jpg"), 150);
	public static final Landmark FORT_OREGON = new Fort("Fort Oregon", new ImageIcon("/images/FortOregon.jpg"), 400);

	
	// List of all landmarks in the game
	public static final ArrayList<Landmark> landmarkList = new ArrayList<Landmark>(Arrays.asList(
		KANSAS_RIVER, FORT_STRONG, FORT_OREGON
	));
	
	public Landmark(String name, ImageIcon picture, int distanceFromStart) {
		this.name = name;
		this.picture = picture;
		this.distanceFromStart = distanceFromStart;
	}

	/**
	 * @return the distanceFromPrevious
	 */
	public int getDistanceFromPrevious() {
		return distanceFromPrevious;
	}

	/**
	 * @param distanceFromPrevious the distanceFromPrevious to set
	 */
	public void setDistanceFromPrevious(int distanceFromPrevious) {
		this.distanceFromPrevious = distanceFromPrevious;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the picture
	 */
	public ImageIcon getPicture() {
		return picture;
	}

	/**
	 * @return the distanceFromStart
	 */
	public int getDistanceFromStart() {
		return distanceFromStart;
	}
}
