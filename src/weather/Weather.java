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
    	int month;
    	switch (zone) {
    	case 1:
    		switch (month) {
    		case 1, 2, 3:  return 1;
    		}
    	}
    	// Error case
    	return 0;
    }
}
