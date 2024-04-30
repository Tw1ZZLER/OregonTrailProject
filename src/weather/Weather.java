package weather;

import java.util.Random;

import oregonTrail.OregonTrail;

/**
 * Contains information related to the current state of the weather.
 * Uses WeatherType.java for enumerated type.
 * 
 * @author Corbin Hibler
 * @date 2024-04-30
 * @filename Weather.java
 */
public class Weather {
    private WeatherType weatherType;
    private WeatherZone weatherZone;
    private OregonTrail oregonTrail;
    private static Random rand = new Random();

    /**
     * Default constructor.
     * Sets WeatherType to warm.
     */
    public Weather(OregonTrail oregonTrail) {
        this.weatherType = WeatherType.WARM;
        this.weatherZone = WeatherZone.ZONE_1;
        this.oregonTrail = oregonTrail;
    }
    
    /**
     * Secondary constructor.
     * Will set WeatherType to specified.
     * 
     * @param weatherType WeatherType to set.
     */
    public Weather(OregonTrail oregonTrail, WeatherType weatherType) {
        this.weatherType = weatherType;
        this.weatherZone = WeatherZone.ZONE_1;
        this.oregonTrail = oregonTrail;
    }

    /**
     * Getter method for weatherType.
     * 
     * @return The current WeatherType.
     */
    public WeatherType getWeatherType() {
        return weatherType;
    }

    /**
     * Setter method for weatherType.
     * 
     * @param weatherType The WeatherType to set.
     */
    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }
    
    /**
     * Getter method for health modifier.
     * 
     * @return The health modifier based on health modifiers from 'You have Died of Dysentery'.
     */
    public int getHealthModifier() {
        return this.weatherType.getHealthModifier();
    }
    
    /**
     * Getter method for descriptive weather string.
     * 
     * @return String describing the weather.
     */
    public String getWeatherString() {
        return this.weatherType.getWeatherString();
    }

	/**
	 * Getter method for the current weather zone
	 * @return the weatherZone
	 */
	public WeatherZone getWeatherZone() {
		return weatherZone;
	}

	/**
	 * Sets the current weather zone
	 * Used in Travel.java to set weather zone every travel cycle
	 * @param weatherZone the weatherZone to set
	 */
	public void setWeatherZone(WeatherZone weatherZone) {
		this.weatherZone = weatherZone;
	}
    
    /**
     * Uses current temperature to get the weather type and set accordingly
     * Uses the algorithm described in 'You Have Died of Dysentery'.
     * Gets called per travel cycle in Travel.java.
     * 
     * @author Corbin Hibler
     * @date 2024-04-29
     */
    public void calcWeather() {
        Random rand = new Random();
        int option = rand.nextInt();
        // Add implementation for weather calculation
    }
    
    
    public int calcTemperature() {
    	int randomModifier = rand.nextInt(weatherZone.getTempModifier());
    	int zone;
    	int month = oregonTrail.getTravelState().getMonth();
       	// Switch to check which month it is. Uses a modifier from the location to change the temperature of the month.
		switch (month) {
		case 1:
			return 10 + randomModifier;
		case 2:
			return 25 + randomModifier;
		case 3:
			return 40 + randomModifier;
		case 4: 
			return 50 + randomModifier;
		case 5:
			return 60 + randomModifier;
		case 6:
			return 70 + randomModifier;
		case 7: 
			return 80 + randomModifier;
		case 8:
			return 75 + randomModifier;
		case 9:
			return 65 + randomModifier;
		case 10:
			return 45 + randomModifier;
		case 11:
			return 40 + randomModifier;
		case 12:
			return 20 + randomModifier;
			
    	}
    	// Error case
    	return 0;
    }
}
