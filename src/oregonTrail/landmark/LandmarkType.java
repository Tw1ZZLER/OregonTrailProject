package oregonTrail.landmark;

import javax.swing.ImageIcon;

/**
 * Enumerated type for all Landmarks in the game
 * @author Corbin Hibler
 * @date 2024-04-29
 */
public enum LandmarkType {
	KANSAS_RIVER (new River("Kansas River Crossing", new ImageIcon("src/images/KansasRiver.jpg"), 100)),
	BIG_BLUE_RIVER (new River("Big Blue River Crossing", new ImageIcon("src/images/BigBlueRiver.jpg"), 150)),
	FORT_STRONG (new Fort("Fort Strong", new ImageIcon("src/images/FortStrong.jpg"), 250)),
	ASH_HOLLOW (new Landmark("Ash Hollow", new ImageIcon("src/images/AshHollow.jpg"), 400)),
	FORT_LARAMIE (new Fort("Fort Laramie", new ImageIcon("src/images/FortLaramie.jpg"), 700)),
	CHIMNEY_ROCK (new Landmark("Chimney Rock", new ImageIcon("src/images/ChimneyRock.jpg"), 600)),
	FORT_OREGON (new Fort("Fort Oregon", new ImageIcon("src/images/FortOregon.jpg"), 2000));

	private final Landmark landmark;
	
	LandmarkType(Landmark landmark) {
		this.landmark = landmark;
	}

	/**
	 * @return the landmark
	 */
	public Landmark getLandmark() {
		return landmark;
	}
}
