package oregonTrail.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.SpringLayout;

public class TravelPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TravelPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblPressEnterTo = new JLabel("Press ENTER to size up the situation...");
		springLayout.putConstraint(SpringLayout.NORTH, lblPressEnterTo, 137, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblPressEnterTo, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblPressEnterTo, 167, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblPressEnterTo, 450, SpringLayout.WEST, this);
		lblPressEnterTo.setOpaque(true);
		lblPressEnterTo.setBackground(Color.BLACK);
		lblPressEnterTo.setForeground(Color.WHITE);
		lblPressEnterTo.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblPressEnterTo);
		
		JLabel lblPressSpaceTo = new JLabel("Press SPACE BAR to continue");
		springLayout.putConstraint(SpringLayout.NORTH, lblPressSpaceTo, -24, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblPressSpaceTo, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblPressSpaceTo, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblPressSpaceTo, 0, SpringLayout.EAST, this);
		lblPressSpaceTo.setOpaque(true);
		lblPressSpaceTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPressSpaceTo.setForeground(Color.WHITE);
		lblPressSpaceTo.setBackground(Color.BLACK);
		add(lblPressSpaceTo);
		
		JLabel lblDate = new JLabel("Date:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDate, 1, SpringLayout.SOUTH, lblPressEnterTo);
		springLayout.putConstraint(SpringLayout.EAST, lblDate, 186, SpringLayout.WEST, this);
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDate);
		
		JLabel lbl = new JLabel("Weather:");
		springLayout.putConstraint(SpringLayout.WEST, lblDate, 0, SpringLayout.WEST, lbl);
		springLayout.putConstraint(SpringLayout.SOUTH, lblDate, 0, SpringLayout.NORTH, lbl);
		springLayout.putConstraint(SpringLayout.NORTH, lbl, 185, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lbl, 74, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lbl, 186, SpringLayout.WEST, this);
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lbl);
		
		JLabel lblHealth = new JLabel("Health:");
		springLayout.putConstraint(SpringLayout.NORTH, lblHealth, 202, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblHealth, 0, SpringLayout.WEST, lbl);
		springLayout.putConstraint(SpringLayout.EAST, lblHealth, -264, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lbl, 0, SpringLayout.NORTH, lblHealth);
		lblHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblHealth);
		
		JLabel lblFood = new JLabel("Food:");
		springLayout.putConstraint(SpringLayout.NORTH, lblFood, 220, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblFood, 0, SpringLayout.WEST, lbl);
		springLayout.putConstraint(SpringLayout.EAST, lblFood, -264, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblHealth, -1, SpringLayout.NORTH, lblFood);
		lblFood.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFood);
		
		JLabel lblNextLandmark = new JLabel("Next Landmark:");
		springLayout.putConstraint(SpringLayout.WEST, lblNextLandmark, 0, SpringLayout.WEST, lbl);
		springLayout.putConstraint(SpringLayout.EAST, lblNextLandmark, -264, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblFood, -6, SpringLayout.NORTH, lblNextLandmark);
		springLayout.putConstraint(SpringLayout.NORTH, lblNextLandmark, 243, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNextLandmark, -16, SpringLayout.NORTH, lblPressSpaceTo);
		lblNextLandmark.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNextLandmark);

	}
}
