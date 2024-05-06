package oregonTrail.randomEvent;

import oregonTrail.OregonTrail;

/**
 * Contains abstract method rollEvent to set up other random event classes
 * 
 * @author Ethan Vaughn
 * @date 2024-05-06
 * @filename RandomEvent.java
 * @version 1.0
 */
public abstract class RandomEvent {

	protected boolean happened = false;
	protected OregonTrail oregonTrail;
	
	/**
	 * Initializes a WeatherEvent object that holds whether or not an event has happened
	 * @param oregonTrail
	 */
	public RandomEvent(OregonTrail oregonTrail) {
		this.happened = false;
		this.oregonTrail = oregonTrail;
	}
	
	/**
	 * Gets the current status of whether or not a SuppliesEvent has happened
	 * @return happened, the state of whether or not an event has occurred
	 */
	public boolean getHappened() {
		return this.happened;
	}
	
	/**
	 * Abstract method unique to each RandomEvent
	 */
	public abstract void rollEvent();
}
