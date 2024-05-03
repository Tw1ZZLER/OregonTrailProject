package oregonTrail.weather;

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
        int temperature = this.calcTemperature();
        
        // Add implementation for weather calculation
        // Check current temperature against the minimum temperatures of each weather condition, setting it to that condition if it falls in range.
       if((temperature) > WeatherType.VERY_HOT.getMinTemp() && (temperature) < WeatherType.VERY_HOT.getMaxTemp()) {
    	   this.setWeatherType(WeatherType.VERY_HOT);
       }
       else if((temperature) > WeatherType.HOT.getMinTemp()) {
    	   this.setWeatherType(WeatherType.HOT);
       }
       else if((temperature) > WeatherType.COOL.getMinTemp()) {
    	   this.setWeatherType(WeatherType.COOL);
       }
       else if((temperature) > WeatherType.COLD.getMinTemp()) {
    	   this.setWeatherType(WeatherType.COLD);
       }
       else if((temperature) > WeatherType.VERY_COLD.getMinTemp()) {
    	   this.setWeatherType(WeatherType.VERY_COLD);
       } 
    }
    
    
    public int calcTemperature() {
    	int randomModifier = rand.nextInt(20) + weatherZone.getTempModifier();
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
    
    public double calcPrecipitationChance() {
    	double chance = 0;
    	// get base rain chance based on zone
    	switch(this.getWeatherZone()) {
    		case ZONE_1:
    			chance += .1;
    		
    		case ZONE_2:
    			chance += .05;
    			
    		case ZONE_3,ZONE_4,ZONE_5:
    			chance += .025;
    		
    		case ZONE_6:
    			chance += .2;
    	}
    	
    	// add/subtract rain chance based on month
    	switch(oregonTrail.getTravelState().getMonth()) {
    	case 12, 1, 2, 3:
    		chance += .05;
    	
    	case 4, 5, 6:
    		chance += .07;
    	
    	case 7, 8:
    		chance -= .02;
    	
    	case 9, 10, 11:
    		chance += .03;
    	}
    	
    	return chance;
    }
    
}
