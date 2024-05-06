package oregonTrail.weather;

/**
 * Enumerated type for the weather zones
 * @author Corbin Hibler, Ethan Vaughn
 * @date 2024-04-30
 * @filename WeatherZone.java
 */
public enum WeatherZone {
	ZONE_1 (15, 0),
	ZONE_2 (0, 250),
	ZONE_3 (-10, 600),
	ZONE_4 (-15, 800),
	ZONE_5 (15, 1300),
	ZONE_6 (0, 1900);

	private int tempModifier;
	private int mileMarker;
	
	/**
	 * Constructor for weather zones
	 * @param tempModifier The effect that the zone has on the current weather
	 * @param mileMarker The miles traveled where the zone begins (from 'Died of Dysentery')
	 */
	WeatherZone(int tempModifier, int mileMarker) {
		this.tempModifier = tempModifier;
		this.mileMarker = mileMarker;
	}

	/**
	 * Returns the modifier that affects the weather
	 * @return the tempModifier effect that the zone has on the current weather
	 */
	public int getTempModifier() {
		return tempModifier;
	}

	/**
	 * Returns the mile marker where the zone begins
	 * @return the mileMarker milesTraveled point where zone begins
	 */
	public int getMileMarker() {
		return mileMarker;
	}
	
}
