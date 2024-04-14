package oregonTrail.landmark;

import javax.swing.ImageIcon;

public class River extends Landmark {

    private int distanceToKansas;
    private boolean deep;

    public River(String name, int distanceToKansas, boolean deep, ImageIcon picture) {
        super(name, picture);
        this.distanceToKansas = distanceToKansas;
        this.deep = deep;
    }

    public int getDistanceToKansas() {
        return distanceToKansas;
    }

    public boolean isDeep() {
        return deep;
    }
}
