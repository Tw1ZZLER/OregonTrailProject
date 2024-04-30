package oregonTrail;

import java.util.Random;

/**
 * Contains information related to the current state of the weather
 * Uses WeatherType.java for enumerated type
 * @author Corbin Hibler
 * @date 2024-04-30
 * @filename Weather.java
 */
public class Weather {
	private WeatherType weatherType;

	/**
	 * Default constructor
	 * Sets WeatherType to warm
	 */
	public Weather() {
		this.weatherType = WeatherType.WARM;
	}
	
	/**
	 * Secondary constructor
	 * Will set WeatherType to specified
	 * @param weatherType WeatherType to set
	 */
	public Weather(WeatherType weatherType) {
		this.weatherType = weatherType;
	}

	/**
	 * Getter method for weatherType
	 * @return weatherType the current WeatherType
	 */
	public WeatherType getWeatherType() {
		return weatherType;
	}

	/**
	 * Setter method for weatherType
	 * @param weatherType the WeatherType to set
	 */
	public void setWeatherType(WeatherType weatherType) {
		this.weatherType = weatherType;
	}
	
	/**
	 * Getter method for health modifier
	 * @return healthModifier int based on health modifiers from 'You have Died of Dysentery'
	 */
	public int getHealthModifier() {
		return this.weatherType.getHealthModifier();
	}
	
	/**
	 * Getter method for descriptive weather string
	 * @return string String describing the weather
	 */
	public String getWeatherString() {
		return this.weatherType.getWeatherString();
	}
	
	/**
	 * Method to use travel data and location data to calculate weather somewhat randomly
	 * Uses the algorithm described in 'You Have Died of Dysentery'
	 * Gets called per travel cycle in Travel.java
	 * @author Corbin Hibler
	 * @date 2024-04-29
	 */
	public void calcWeather() {
		Random rand = new Random();
		int option = rand.nextInt();
	}
}
