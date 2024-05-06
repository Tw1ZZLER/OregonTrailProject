package oregonTrail.randomEvent;

import oregonTrail.OregonTrail;

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
