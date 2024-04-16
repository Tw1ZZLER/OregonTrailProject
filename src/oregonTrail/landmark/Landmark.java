package oregonTrail.landmark;

import javax.swing.ImageIcon;

import oregonTrail.OregonTrail;

/**
 * Contains methods and logic that are used by all landmarks
 * Subclasses: Fort, River
 * @
 */
public class Landmark {
	protected String name;
	protected ImageIcon picture;
	protected OregonTrail oregonTrail;
	protected int distanceFromStart;
	
	public Landmark(String name, ImageIcon picture, OregonTrail oregonTrail, int distanceFromStart) {
		this.name = name;
		this.picture = picture;
		this.oregonTrail = oregonTrail;
		this.distanceFromStart = distanceFromStart;
	}
}
