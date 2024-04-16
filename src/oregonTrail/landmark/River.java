package oregonTrail.landmark;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import oregonTrail.OregonTrail;
import oregonTrail.panel.RiverPanel;

public class River extends Landmark {
	
	private static Random rnd = new Random();

	public River(String name, ImageIcon picture, OregonTrail oregonTrail, int distanceFromStart) {
		super(name, picture, oregonTrail, distanceFromStart);
	}
	
	public static void caulkWagon(RiverPanel riverPanel) {
        int randomNumber = rnd.nextInt(); // Generate random number between 1 and 100
        if (randomNumber <= 70) {
        	JOptionPane.showMessageDialog(null, "You have successfully caulked the wagon!");
        	oregonTrail.openPanel(OregonTrail.TRAVEL_PANEL, riverPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Your wagon sank to the bottom of the river");
            System.exit(0);
        }
	}

}
