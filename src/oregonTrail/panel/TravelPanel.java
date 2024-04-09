package oregonTrail.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class TravelPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TravelPanel() {
		setLayout(new MigLayout("", "[100.00px,grow][100.00,grow,right]", "[115.00px,grow,top][27px,grow][][grow][grow][grow][grow][grow]"));
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setForeground(Color.WHITE);
		btnContinue.setBackground(Color.BLACK);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnSizeUpThe = new JButton("Size Up the Situation");
		btnSizeUpThe.setBackground(Color.BLACK);
		btnSizeUpThe.setForeground(Color.WHITE);
		add(btnSizeUpThe, "cell 0 1 2 1,growx,aligny top");
		
		JLabel lblDistanceTraveled = new JLabel("Distance Traveled:");
		lblDistanceTraveled.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblDistanceTraveled, "cell 0 2,alignx right");
		
		JLabel lblNextLandmark = new JLabel("Next Landmark:");
		lblNextLandmark.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblNextLandmark, "cell 0 3,alignx right,aligny top");
		
		JLabel lblHealth = new JLabel("Health:");
		lblHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblHealth, "cell 0 4,alignx right,aligny top");
		
		JLabel lblFood = new JLabel("Food:");
		lblFood.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFood, "cell 0 5,alignx right,aligny top");
		
		JLabel lblWeather = new JLabel("Weather:");
		lblWeather.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblWeather, "cell 0 6,alignx right,aligny top");
		add(btnContinue, "cell 0 7 2 1,growx,aligny top");

	}
}
