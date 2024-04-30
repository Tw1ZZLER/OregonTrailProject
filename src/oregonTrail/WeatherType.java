package oregonTrail;

/**
 * Enumerated type containing the static constants for
 * each weather type. These weather types come from the original Apple II game
 * and from 'You have Died of Dysentery.'
 * @author Corbin Hibler
 * @date 2024-04-30
 * @filename WeatherType.java
 */
public enum WeatherType {
	VERY_HOT (2, 90, 120, "Very Hot"),
	HOT (1, 70, 90, "Hot"), 
	COOL (0, 50, 70, "Cool"),
	WARM (0, 30, 50, "Warm"),
	COLD (2, 10, 30, "Cold"),
	VERY_COLD (4, -20, 10, "Very Cold");
	
	private final int healthModifier;
	private final int minTemp;
	private final int maxTemp;
	private final String weatherString;
	
	/**
	 * Constructor to create a WeatherType.
	 * @param healthModifier The effect the weatherType has on the player's health.
	 * @param minTemp The minimum temperature for the weather type.
	 * @param maxTemp The maximum temperature for the weather type.
	 * @param weatherString The string version of the variable name for the weather type.
	 */
	WeatherType(int healthModifier, int minTemp, int maxTemp, String weatherString) {
		this.healthModifier = healthModifier;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.weatherString = weatherString;
	}
	
	/**
	 * Returns the health modifier of the weather
	 * @return the healthModifier
	 */
	public int getHealthModifier() {
		return healthModifier;
	}

	/**
	 * Returns the minimum temperature the weather is at
	 * @return the minTemp
	 */
	public int getMinTemp() {
		return minTemp;
	}

	/**
	 * Returns the maximum temperature the weather is at
	 * @return the maxTemp
	 */
	public int getMaxTemp() {
		return maxTemp;
	}

	/**
	 * Returns a string that has the name of the current weather
	 * @return the weatherString
	 */
	public String getWeatherString() {
		return weatherString;
	}

}
