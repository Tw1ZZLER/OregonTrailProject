package oregonTrail.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TravelPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TravelPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblDate = new JLabel("Date:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDate, 168, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblDate, 74, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblDate, 186, SpringLayout.WEST, this);
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDate);
		
		JLabel lblWeather = new JLabel("Weather:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblDate, 0, SpringLayout.NORTH, lblWeather);
		springLayout.putConstraint(SpringLayout.NORTH, lblWeather, 185, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblWeather, 74, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblWeather, 202, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblWeather, 186, SpringLayout.WEST, this);
		lblWeather.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblWeather);
		
		JLabel lblHealth = new JLabel("Health:");
		springLayout.putConstraint(SpringLayout.NORTH, lblHealth, 202, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblHealth, 74, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblHealth, 219, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblHealth, 186, SpringLayout.WEST, this);
		lblHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblHealth);
		
		JLabel lblFood = new JLabel("Food:");
		springLayout.putConstraint(SpringLayout.NORTH, lblFood, 220, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblFood, 74, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblFood, 237, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblFood, 186, SpringLayout.WEST, this);
		lblFood.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFood);
		
		JLabel lblNextLandmark = new JLabel("Next Landmark:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNextLandmark, 0, SpringLayout.SOUTH, lblFood);
		springLayout.putConstraint(SpringLayout.WEST, lblNextLandmark, 74, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNextLandmark, 254, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNextLandmark, 0, SpringLayout.EAST, lblDate);
		lblNextLandmark.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNextLandmark);
		
		JButton btnSizeUpThe = new JButton("Size Up the Situation");
		springLayout.putConstraint(SpringLayout.NORTH, btnSizeUpThe, 133, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnSizeUpThe, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnSizeUpThe, -140, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnSizeUpThe, 0, SpringLayout.EAST, this);
		btnSizeUpThe.setBackground(Color.BLACK);
		btnSizeUpThe.setForeground(Color.WHITE);
		add(btnSizeUpThe);
		
		JButton btnContinue = new JButton("Continue");
		springLayout.putConstraint(SpringLayout.NORTH, btnContinue, 9, SpringLayout.SOUTH, lblNextLandmark);
		springLayout.putConstraint(SpringLayout.WEST, btnContinue, 0, SpringLayout.WEST, btnSizeUpThe);
		springLayout.putConstraint(SpringLayout.SOUTH, btnContinue, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnContinue, 0, SpringLayout.EAST, btnSizeUpThe);
		btnContinue.setForeground(Color.WHITE);
		btnContinue.setBackground(Color.BLACK);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(btnContinue);

	}
}
