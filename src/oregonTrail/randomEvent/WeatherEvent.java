package oregonTrail.randomEvent;

import java.lang.Math;

import oregonTrail.Food;
import oregonTrail.OregonTrail;
import oregonTrail.weather.WeatherZone;

import javax.swing.JOptionPane;

/**
 * Contains the methods and constructor for determining whether or not a weather random event will occur.
 * Uses the rollEvent abstract class to base its own rollEvent off of.
 * 
 * @author Ethan Vaughn
 * @date 2024-05-06
 * @filename WeatherEvent.java
 * @version 1.0
 */
public class WeatherEvent extends RandomEvent {

	private boolean happened = false;
	private OregonTrail oregonTrail;
	
	/**
	 * Initializes a WeatherEvent object that holds whether or not an event has happened
	 * @param oregonTrail
	 */
	public WeatherEvent(OregonTrail oregonTrail) {
		this.happened = false;
		this.oregonTrail = oregonTrail;
	}
	
	/**
	 * rolls to see what the current weather event is, if one occurs at all.
	 * Bases possible outcomes on current month, location, and weather conditions
	 */
	@Override
	public void rollEvent() {
		int roll;
		int month = oregonTrail.getTravelState().getMonth();
		roll = (int)((Math.random())*100) + 1;
		String weather = oregonTrail.getWeatherState().getWeatherString();
		WeatherZone zone = oregonTrail.getWeatherState().getWeatherZone();
		
		if(roll <= 25) {
			//possible thunderstorm / blizzard (depends on the month, blizzards in winter)
			if(month > 3 && month != 12) {
				if(oregonTrail.getWeatherState().calcPrecipitationChance() >= .25 && weather.equals("Cold") || weather.equals("Very Cold")) {
					oregonTrail.getTravelState().incrementDate(2);
					Food.consumeFood(oregonTrail);
					Food.consumeFood(oregonTrail);
					JOptionPane.showMessageDialog(null, "Severe Blizzard! Lose 2 days.");
					this.happened = true;
			}
			else {
				if(oregonTrail.getWeatherState().calcPrecipitationChance() >= .25) {
					oregonTrail.getTravelState().incrementDate(2);
					Food.consumeFood(oregonTrail);
					Food.consumeFood(oregonTrail);
					JOptionPane.showMessageDialog(null, "Severe Thunderstorm! Lose 2 days.");
					this.happened = true;
				}
			}
		}
		}
		
		else if(roll <= 50) {
			//possible drought events
			if(oregonTrail.getWeatherState().calcPrecipitationChance() < .15) {
				JOptionPane.showMessageDialog(null, "Bad Water! Some of your family feels ill...");
				//!make health worse by 20!
				this.happened = true;
			}
			else if(oregonTrail.getWeatherState().calcPrecipitationChance() <= .2) {
				JOptionPane.showMessageDialog(null, "Very Little Water!");
				//!make health worse by 10!
				this.happened = true;
			}
		}
		
		else if(roll <= 60) {
			//possible Heavy fog
			if(!(weather.equals("Very Hot")) && zone.equals(WeatherZone.ZONE_5) || zone.equals(WeatherZone.ZONE_6)) {
				//50% chance of a day being lost
				int loseDay = (int)((Math.random()*1)+.5);
				if(loseDay == 1) {
					oregonTrail.getTravelState().incrementDate(1);
					Food.consumeFood(oregonTrail);
					JOptionPane.showMessageDialog(null, "Heavy Fog! You've gotten lost for a day!");
					this.happened = true;
				}
			}			
		}
		
		else if(roll <= 70) {
			//possible hail storm
			if(weather.equals("Cold") || weather.equals("Very Cold")) {
				oregonTrail.getTravelState().incrementDate(1);
				oregonTrail.getTravelState().incrementDate(1);
				JOptionPane.showMessageDialog(null, "Hailstorm! You had to take shelter for 2 days!");
				this.happened = true;
			}
		}
		
		else {
			//clear skies (no event)
			
		}
		
	}
		
	/**
	 * Returns whether or not a random event in this category has happened
	 * @return happened A variable representing the status of random events having happened
	 */
	public boolean getHappened() {
		return this.happened;
	}
	
}
