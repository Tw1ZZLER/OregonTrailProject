package oregonTrail;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import oregonTrail.landmark.*;
import oregonTrail.panel.*;

/**
 * Main class containing all game logic for switching between panels and instantiating panels
 * @authors Corbin Hibler, Lukas Dunbar, Ray Otto, Ethan Vaughn
 * @date 2024-04-09
 */
public class OregonTrail {
	
	private JFrame frame;
	private Travel travelState;
	private Map<Landmark, JPanel> landmarkToPanelMap = new HashMap<>();
	public final Wagon WAGON;
	public final StartupPanel STARTUP_PANEL;
	public final TravelPanel TRAVEL_PANEL;
	public final TrailMenuPanel TRAIL_MENU_PANEL;
 	public HuntingPanel huntingPanel;
 	public final TradePanel TRADE_PANEL;
 	public final ShopPanel SHOP_PANEL;
 	
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
		// Create wagon and preload items
		WAGON = new Wagon();
		WAGON.addItem(new Item("Apple Vinegar"));
		WAGON.addItem(new Item("Bacon"));
		WAGON.addItem(new Item("Water"));
		WAGON.addItem(new Item("Bullets"));
		WAGON.setTotalFoodWeight(350);
		WAGON.addItem(new Item("Cast Iron Stove"));
		
		SHOP_PANEL = new ShopPanel(this);
		STARTUP_PANEL = new StartupPanel();
		TRAVEL_PANEL = new TravelPanel(this);
		travelState = new Travel(this);
		TRAIL_MENU_PANEL = new TrailMenuPanel(this);
		TRADE_PANEL = new TradePanel(this);
		initializeLandmarkPanels();
		initialize();
	}
	
	/**
	 * Opens a new panel and closes previous panel
	 * @param panelOpen The panel to be opened
	 * @param panelClose The panel to be closed
	 * @author Corbin Hibler
	 * @date 2024-04-08
	 */
	public void openPanel(JPanel panelOpen) {
		frame.getContentPane().removeAll();
		
		// Create new hunting panel if called.
		// Otherwise, use final (constant) panel
		if (panelOpen == this.huntingPanel) {
			this.huntingPanel = new HuntingPanel();
			frame.getContentPane().add(this.huntingPanel);
		}
		else {
			frame.getContentPane().add(panelOpen);
		}
		
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
	}
	
	public JPanel getPanelForLandmark(Landmark landmark) {
        return landmarkToPanelMap.get(landmark);
    }

	/**
	 * Getter method for instantation of Travel class
	 * @return the travelState
	 */
	public Travel getTravelState() {
		return this.travelState;
	}
	
    // Example method to populate the map (call this during initialization)
    public void initializeLandmarkPanels() {
        // Create JPanels for each landmark
		final JPanel KANSAS_RIVER_PANEL = new RiverPanel (this, (River) Landmark.KANSAS_RIVER);
		final JPanel FORT_STRONG_PANEL = new FortPanel(this, (Fort) Landmark.FORT_STRONG);
		final JPanel FORT_OREGON_PANEL = new FortPanel(this, (Fort) Landmark.FORT_OREGON);

        // Populate the map with associations
        landmarkToPanelMap.put(Landmark.KANSAS_RIVER, KANSAS_RIVER_PANEL);
        landmarkToPanelMap.put(Landmark.FORT_STRONG, FORT_STRONG_PANEL);
        landmarkToPanelMap.put(Landmark.FORT_OREGON, FORT_OREGON_PANEL);
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.GRAY);
		frame.setTitle("Oregon Trail");
		frame.setBounds(100, 100, 839, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		// Always start program maximized!
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// TODO: Separate image logic into its own class with images stored
		// TODO (optional): Make automatic parsing of image files in directory and 
		//                  automatically create JLabels from them. Perhaps we could
		//                  also have a CSV file which contains the appropriate scaling for each image.
		
		// Show logo and team name
		frame.getContentPane().add(STARTUP_PANEL, BorderLayout.CENTER);
		
		Timer startupTimer = new Timer(StartupPanel.STARTUP_TIME, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// After startup screen is finished, display first panel
				openPanel(TRAVEL_PANEL);
				
			}
		});
		startupTimer.setRepeats(false);
		startupTimer.start();
	}

}
