package oregonTrail.landmark;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import oregonTrail.panel.RiverPanel;

/**
 * Subclass of Landmark for River landmarks
 * Contains methods for crossing, caulking, and viewing river data.
 * @author Corbin Hibler
 * @date 2024-04-05
 * @filename River.java
 */
public class River extends Landmark {
	
	private int width;
	private int height;
	private int flow;
	private static Random rnd = new Random();

	/**
	 * Constructor for a new River object
	 * @param name The name of the river
	 * @param picture The picture of the river as ImageIcon
	 * @param distanceFromStart Distance in miles from Independence, MI
	 */
	public River(String name, ImageIcon picture, int distanceFromStart) {
		super(name, picture, distanceFromStart);
		
		width = rnd.nextInt(91) + 10;
		height = rnd.nextInt(10) + 1;
		flow = rnd.nextInt(5) + 1;
	}
	
	/**
	 * Simple method to display a dialog with all river data
	 * @param riverPanel The JPanel where this method was called from
	 * @author Corbin Hibler
	 * @date 2024-04-10
	 */
	public void viewRiverData(RiverPanel riverPanel) {
		JOptionPane.showMessageDialog(riverPanel, "River Width: " + width + " ft" + "\nRiver Height: " + height + " ft" +  "\nRiver Flow: " + flow + " mph");
	}
	
	/**
	 * Method for attempting to caulk the wagon across the river.
	 * @param riverPanel The JPanel where this method was called from
	 * @author Ray Otto
	 * @date 2024-03-29
	 */
	public void caulkWagon(RiverPanel riverPanel) {
        int randomNumber = rnd.nextInt(); // Generate random number between 1 and 100
        if (randomNumber <= 70) {
        	JOptionPane.showMessageDialog(riverPanel, "You have successfully caulked the wagon!");
        } else {
            JOptionPane.showMessageDialog(riverPanel, "Your wagon sank to the bottom of the river");
            System.exit(0);
        }
	}
	
	/**
	 * Method for attempting to cross the river.
	 * @param riverPanel The JPanel where this method was called from
	 * @author Ray Otto
	 * @date 2024-03-25
	 */
	public void attemptToCross(RiverPanel riverPanel) {
        int random = (int)(Math.random() * 100) + 1; // Generate random number between 1 and 100
        if (random <= 70) {
            JOptionPane.showMessageDialog(null, "You successfully crossed the river!");

        } else {
            JOptionPane.showMessageDialog(null, "Your wagon got swept away by the river!");
            System.exit(0);
        }
	}

}
