package oregonTrail.randomEvent;

import oregonTrail.OregonTrail;
import java.lang.Math;

import javax.swing.JOptionPane;

import oregonTrail.weather.WeatherZone;
import oregonTrail.Wagon;
import oregonTrail.Item;

/**
 * Contains the methods and constructor for determining whether or not a part-breaking random event will occur.
 * Uses the rollEvent abstract class to base its own rollEvent off of.
 * 
 * @author Ethan Vaughn
 * @date 2024-05-06
 * @filename PartBreakEvent.java
 * @version 1.0
 */
public class PartBreakEvent extends RandomEvent {
	
	public PartBreakEvent(OregonTrail oregonTrail) {
		super(oregonTrail);
	}

	/**
	 * "rolls" on a table of random events to determine if a wagon part breaks
	 */
	@Override
	public void rollEvent() {
		int roll = (int)(Math.random()*100)+1;
		WeatherZone zone = oregonTrail.getWeatherState().getWeatherZone();
		int breakChance = (int)(Math.random()*100)+1;

		
		if(roll <= 25) {
			//possible wheel break			
			if(breakChance <= 35) {
				if(oregonTrail.WAGON.getPartsAmount() > 0) {
					for(Item i: oregonTrail.WAGON.getItemContents()) {
						if(i.getName().equals("SpareParts")) {
							oregonTrail.WAGON.removeItem(i);
							JOptionPane.showMessageDialog(null, "A wheel broke on your wagon, but you had spare parts to fix it!");
							break;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "One of your wagon's wheels has broken!");
					//TODO make this decrease max speed
				}
			}
			this.happened = true;
		}
		
		else if (roll <= 50) {
			//possible axle break 
			if(breakChance <= 35) {
				if(oregonTrail.WAGON.getPartsAmount() > 0) {
					for(Item i: oregonTrail.WAGON.getItemContents()) {
						if(i.getName().equals("SpareParts")) {
							oregonTrail.WAGON.removeItem(i);
							JOptionPane.showMessageDialog(null, "An axle broke on your wagon, but you had spare parts to fix it!");
							break;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "One of your wagon's axles has broken!");
					//TODO make this decrease max speed
				}
			}
			this.happened = true;
		}
		
		else if (roll <= 75) {
			//possible tongue break
			if(breakChance <= 35) {
				if(oregonTrail.WAGON.getPartsAmount() > 0) {
					for(Item i: oregonTrail.WAGON.getItemContents()) {
						if(i.getName().equals("SpareParts")) {
							oregonTrail.WAGON.removeItem(i);
							JOptionPane.showMessageDialog(null, "Your wagon's tongue broke, but you had spare parts to fix it!");
							break;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Your wagon's tongue has broken!");
					//TODO make this decrease max speed
				}
			}
			this.happened = true;
		}
		
		else {
			//safe travels, no parts break
		}
	}
}
