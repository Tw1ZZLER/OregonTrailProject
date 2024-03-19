package wagonLoad;

public class Item {
	private static int totalWeight = 0;
	private int itemWeight = 0;
	private int cost = 0;
	
	public Item() {
	}
	
	public Item(int itemWeight) {
		this.itemWeight = itemWeight;
	}
	
	public int getItemWeight() {
		return this.itemWeight;
	}
	
	public void setItemWeight(int itemWeight) {
		this.itemWeight = itemWeight;
	}
	
	public static int getTotalWeight() {
		return totalWeight;
	}
}
