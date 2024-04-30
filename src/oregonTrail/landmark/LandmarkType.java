package oregonTrail.landmark;

import javax.swing.ImageIcon;

/**
 * Enumerated type for all Landmarks in the game
 * @author Corbin Hibler
 * @date 2024-04-29
 * @filename LandmarkType.java
 */
public enum LandmarkType {
	KANSAS_RIVER   (new River("Kansas River Crossing", new ImageIcon(LandmarkType.class.getResource("src/images/KansasRiver.jpg")), 100)),
	BIG_BLUE_RIVER (new River("Big Blue River Crossing", new ImageIcon(LandmarkType.class.getResource("src/images/BigBlueRiver.jpg")), 150)),
	FORT_STRONG    (new Fort("Fort Strong", new ImageIcon(LandmarkType.class.getResource("src/images/FortStrong.jpg")), 250)),
	ASH_HOLLOW     (new Landmark("Ash Hollow", new ImageIcon(LandmarkType.class.getResource("src/images/AshHollow.jpg")), 400)),
	CHIMNEY_ROCK   (new Landmark("Chimney Rock", new ImageIcon(LandmarkType.class.getResource("src/images/ChimneyRock.jpg")), 600)),
	FORT_LARAMIE   (new Fort("Fort Laramie", new ImageIcon(LandmarkType.class.getResource("src/images/FortLaramie.jpg")), 700)),
	FORT_OREGON    (new Fort("Fort Oregon", new ImageIcon(LandmarkType.class.getResource("src/images/FortOregon.jpg")), 2000));

	private final Landmark landmark;
	
	/**
	 * Constructor to create LandmarkType constants from Landmarks
	 * @param landmark The landmark to create an enum
	 */
	LandmarkType(Landmark landmark) {
		this.landmark = landmark;
	}

	/**
	 * Getter method to get a Landmark object from a LandmarkType object
	 * (LandmarkType does nothing, Landmark is what you need in most cases).
	 * @return the Landmark object.
	 */
	public Landmark getLandmark() {
		return landmark;
	}
}
