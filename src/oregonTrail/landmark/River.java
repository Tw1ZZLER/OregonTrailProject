package oregonTrail.landmark;

import oregonTrail.Travel;

public class River {
    private String name;
    private boolean isDeep;
    private Travel travelState;

    public River(String name, boolean isDeep, Travel travelState) {
        this.name = name;
        this.isDeep = isDeep;
        this.travelState = travelState;
    }

    public boolean isDeep() {
        return isDeep;
    }

    public String getName() {
        return name;
    }

    public Travel getTravelState() {
        return travelState;
    }

}
