package oregonTrail;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import oregonTrail.panel.LoadWagonPanel;

public class OregonTrail {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OregonTrail window = new OregonTrail();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OregonTrail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Oregon Trail");
		frame.setBounds(100, 100, 839, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		// Always start program maximized!
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		
		// Add Oregon Trail image
		ImageIcon chimneyRockImage = new ImageIcon("src/images/chimney_rock_1.jpg");
		// Resize image to fit into frame
        Image image = chimneyRockImage.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(image);
        
		JLabel imageLabel = new JLabel(scaledImage);
		frame.getContentPane().add(imageLabel, BorderLayout.NORTH);
		
		// Add LoadWagonPanel to frame (temporary)
		frame.getContentPane().add(new LoadWagonPanel(), BorderLayout.CENTER);

	}

}
