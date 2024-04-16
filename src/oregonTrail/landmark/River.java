package oregonTrail.landmark;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import oregonTrail.panel.RiverPanel;

public class River extends Landmark {
	
	private static Random rnd = new Random();

	public River(String name, ImageIcon picture, int distanceFromStart) {
		super(name, picture, distanceFromStart);
	}
	
	public static void caulkWagon(RiverPanel riverPanel) {
        int randomNumber = rnd.nextInt(); // Generate random number between 1 and 100
        if (randomNumber <= 70) {
        	JOptionPane.showMessageDialog(riverPanel, "You have successfully caulked the wagon!");
        } else {
            JOptionPane.showMessageDialog(riverPanel, "Your wagon sank to the bottom of the river");
            System.exit(0);
        }
	}
	
	public static void attemptToCross(RiverPanel riverPanel) {
        int random = (int)(Math.random() * 100) + 1; // Generate random number between 1 and 100
        if (random <= 70) {
            JOptionPane.showMessageDialog(null, "You successfully crossed the river!");

        } else {
            JOptionPane.showMessageDialog(null, "Your wagon got swept away by the river!");
            System.exit(0);
        }
	}

}
