package oregonTrail.landmark;

import javax.swing.ImageIcon;

/**
 * Contains methods and logic that are used by all landmarks
 * Subclasses: Fort, River
 * @
 */
public class Landmark {
	private String name;
	private ImageIcon picture;
	
	public Landmark(String name, ImageIcon picture) {
		this.name = name;
		this.picture = picture;
	}
}
