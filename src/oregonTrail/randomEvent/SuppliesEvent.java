package oregonTrail.randomEvent;

import oregonTrail.Item;
import oregonTrail.OregonTrail;
import java.lang.Math;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SuppliesEvent extends RandomEvent {

	private OregonTrail oregonTrail;
	private boolean happened;
	
	/**
	 * Initializes a SuppliesEvent object, which stores the state of whether or not an event has occurred.
	 * 
	 * @param oregonTrail, the current state of the game
	 */
	public SuppliesEvent(OregonTrail oregonTrail) {
		this.happened = false;
		this.oregonTrail = oregonTrail;
	}
	
	/**
	 * "rolls" on a table of random events that effect your current supplies
	 */
	@Override
	public void rollEvent() {
		int roll = (int)(Math.random()*100)+1;
		int month = oregonTrail.getTravelState().getMonth();
		ArrayList<Item> items = oregonTrail.WAGON.getItemContents();
		
		if(roll <= 20) {
			//wild fruit event
			if(month >= 4 && month <= 9) {
				Item food = new Item("Food");
				for(int i = 0; i < 20; i++) {
					oregonTrail.WAGON.addItem(food);
				}
				JOptionPane.showMessageDialog(null, "Found Wild Fruit!");
				this.happened = true;
			}
		}
		
		else if(roll <= 40) {
			//abandoned wagon event
			int oxenAdd = (int)(Math.random()*3);
			Item oxen = new Item("Oxen");
			int foodAdd = (int)(Math.random()*(50));
			Item food = new Item("Food");
			int clothesAdd = (int)(Math.random()*10);
			Item clothes = new Item("Clothing");
			int partsAdd = (int)(Math.random()*5);
			Item parts = new Item("SpareParts");
			int chance = (int)(Math.random()*100)+1;
			
			
			if(chance<20) {
				for(int i = 0; i<oxenAdd; i++) {
					oregonTrail.WAGON.addItem(oxen);
				}
				for(int i = 0; i<foodAdd; i++) {
					oregonTrail.WAGON.addItem(food);
				}
				for(int i = 0; i<clothesAdd; i++) {
					oregonTrail.WAGON.addItem(clothes);
				}
				for(int i = 0; i<partsAdd; i++) {
					oregonTrail.WAGON.addItem(parts);
				}
			JOptionPane.showMessageDialog(null, "Found an abandoned wagon! Found "+oxenAdd+" oxen, "+foodAdd+"pounds of food, "+clothesAdd+" sets of clothes, and "+partsAdd+" spare parts.");
			this.happened = true;	
			}
			
			
		}
		
		else if(roll <= 60) {
			//wagon fire event
			int oxenAdd = (int)(Math.random()*3);
			Item oxen = new Item("Oxen");
			int foodAdd = (int)(Math.random()*(50));
			Item food = new Item("Food");
			int clothesAdd = (int)(Math.random()*10);
			Item clothes = new Item("Clothing");
			int partsAdd = (int)(Math.random()*5);
			Item parts = new Item("SpareParts");
			int chance = (int)(Math.random()*100)+1;
			
			
			if(chance<20) {
				for(int i = 0; i<oxenAdd; i++) {
					if(oregonTrail.WAGON.getItemContents().contains(oxen)) {
					oregonTrail.WAGON.removeItem(oxen);
					}
				}
				for(int i = 0; i<foodAdd; i++) {
					if(oregonTrail.WAGON.getItemContents().contains(food)) {
					oregonTrail.WAGON.removeItem(food);
					}				}
				for(int i = 0; i<clothesAdd; i++) {
					if(oregonTrail.WAGON.getItemContents().contains(clothes)) {
					oregonTrail.WAGON.removeItem(clothes);
					}				}
				for(int i = 0; i<partsAdd; i++) {
					if(oregonTrail.WAGON.getItemContents().contains(parts)) {
					oregonTrail.WAGON.removeItem(parts);
					}				}
			JOptionPane.showMessageDialog(null, "A fire broke out in the wagon! Lost "+oxenAdd+" oxen, "+foodAdd+"pounds of food, "+clothesAdd+" sets of clothes, and "+partsAdd+" spare parts.");
			this.happened = true;	
		}
		}
		
		else if(roll <= 80) {
			//thief event
			int foodStolen = (int)(Math.random()*(50));
			Item food = new Item("Food");
			
			for(int i = 0; i < foodStolen; i++) {
				if(oregonTrail.WAGON.getItemContents().contains(food)) {
					oregonTrail.WAGON.removeItem(food);
				}
			}
			this.happened = true;
		}
		
		else {
			//no event
		}
	}
	
	/**
	 * Gets the current status of whether or not a SuppliesEvent has happened
	 * @return happened, the state of whether or not an event has occurred
	 */
	public boolean getHappened() {
		return this.happened;
	}

}
