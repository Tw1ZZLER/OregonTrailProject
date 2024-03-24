package oregonTrail;

public class Item {
	protected String name;
	protected double weight;

	public Item(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public double getWeight() {
		return weight;
	}
}