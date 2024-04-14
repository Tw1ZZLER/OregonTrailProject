package oregonTrail.landmark;

import javax.swing.ImageIcon;

import oregonTrail.panel.TravelPanel;

public class RiverPanel extends Landmark {
    private int distance;
    private boolean isDeep;
    private int height; // River height in feet
    private int flow;  // River flow in cubic feet per second
    private int width; // River width in feet

    public RiverPanel(String name, int distance, boolean isDeep, ImageIcon picture) {
        super(name, picture);
        this.distance = distance;
        this.isDeep = isDeep;
        generateRiverData(); // Generate random river data
    }

    public RiverPanel(RiverPanel currentRiver, TravelPanel travelPanel) {
        super(currentRiver.getName(), null); 
        this.distance = currentRiver.getDistance();
        this.isDeep = currentRiver.isDeep();
        generateRiverData(); // Generate random river data
    }

    public int getDistance() {
        return distance;
    }

    public boolean isDeep() {
        return isDeep;
    }

    public int getHeight() {
        return height;
    }

    public int getFlow() {
        return flow;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    private void generateRiverData() {
        // Generate random values for river height, flow, width, etc.
        // You can adjust the range and distribution of random values as needed
        height = getRandomValueInRange(3, 10); // Example: Height between 3 to 10 feet
        flow = getRandomValueInRange(100, 500); // Example: Flow between 100 to 500 cubic feet per second
        width = getRandomValueInRange(50, 200); // Example: Width between 50 to 200 feet
    }

    private int getRandomValueInRange(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
