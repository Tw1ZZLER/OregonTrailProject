package oregonTrail;
import java.util.ArrayList;

public class Wagon {
	private ArrayList<Item> contents;
	private double totalWeight;

	public Wagon() {
		contents = new ArrayList<>();
		totalWeight = 0.0;
	}

	public void addItem(Item item) {
		contents.add(item);
		totalWeight += item.getWeight();
	}

	public void removeItem(Item item) {
		contents.remove(item);
		totalWeight -= item.getWeight();
	}

	public double getTotalWeight() {
		return totalWeight;
	}
}
