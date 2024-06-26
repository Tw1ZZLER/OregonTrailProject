package oregonTrail.randomEvent;

import oregonTrail.OregonTrail;
import javax.swing.JOptionPane;
import java.io.Serializable;
import java.lang.Math;
import oregonTrail.weather.WeatherZone;

/**
 * Contains the methods and constructor for determining whether or not a weather health-modifying event will occur.
 * Uses the rollEvent abstract class to base its own rollEvent off of.
 * 
 * @author Ethan Vaughn
 * @date 2024-05-06
 * @filename HealthEvent.java
 * @version 1.0
 */
public class HealthEvent extends RandomEvent {
	
	public HealthEvent(OregonTrail oregonTrail) {
		super(oregonTrail);
	}

	/**
	 * "rolls" on a table of random, health-modifying events
	 */
	@Override
	public void rollEvent() {
		int roll = (int)(Math.random()*100)+1;
		WeatherZone zone = oregonTrail.getWeatherState().getWeatherZone();
		
		if(roll == 1) {
			//snakebite, severe health damage
			JOptionPane.showMessageDialog(null, "A member of your party was bitten by a snake!");
			oregonTrail.getTravelState().getThisHealth().damageHealth(40);
			this.happened = true;
		}
		
		else if(roll <= 11) {
			//party member injury
			int chance = (int)(Math.random()*100)+1;
			if(zone.equals(WeatherZone.ZONE_5) || zone.equals(WeatherZone.ZONE_6)) {
				//chances are higher in mountainous terrain
				if(chance<=35) {
					//party member gets injured
					JOptionPane.showMessageDialog(null, "A member of your party has been injured!");
					oregonTrail.getTravelState().getThisHealth().damageHealth(8);
					this.happened = true;
				}
			}
			else {
				//injury chances outside of mountainous terrain
				if(chance<=20) {
					//party member gets injured
					JOptionPane.showMessageDialog(null, "A member of your party has been injured!");
					oregonTrail.getTravelState().getThisHealth().damageHealth(5);
					this.happened = true;
				}
			}
		}
		else {
			//chance of illness
			String disease = "";
			int sickChance = 10;
			int health = 0; //!get health value!
			int healthThreshold = 140;
			double healthMod = health / healthThreshold;
			int chance = (int)(Math.random()*100)+1;
			
			//roll for disease
			int ill = (int)(Math.random()*6)+1;
			
			switch(ill) {
			case 1:
				disease = "Exhaustion";
			case 2:
				disease = "Typhoid";
			case 3:
				disease = "Cholera";
			case 4:
				disease = "Measles";
			case 5:
				disease = "Dysentery";
			case 6:
				disease = "Fever";
			}
			
			if(chance <= (sickChance+(healthMod*60))) {
				JOptionPane.showMessageDialog(null, "A member of your party has contracted "+disease+"!");
				//!take health down by 20!
				oregonTrail.getTravelState().getThisHealth().damageHealth(20);
				this.happened = true;
			}	
		}		
	}
}
