package oregonTrail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import oregonTrail.landmark.*;
import oregonTrail.panel.*;

/**
 * Main class containing all game logic for switching between panels and instantiating panels
 * @authors Corbin Hibler, Lukas Dunbar, Ray Otto, Ethan Vaughn
 * @date 2024-04-09
 */
public class OregonTrail implements Serializable {
	
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
 	public final PauseDialog PAUSE_DIALOG;
 	private KeyboardFocusManager manager;
 	private JPanel glassPane;
 	
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
		WAGON.setTotalFoodWeight(2000);
		WAGON.addItem(new Item("Cast Iron Stove"));
		
		SHOP_PANEL = new ShopPanel(this);
		STARTUP_PANEL = new StartupPanel();
		TRAVEL_PANEL = new TravelPanel(this);
		travelState = new Travel(this);
		TRAIL_MENU_PANEL = new TrailMenuPanel(this);
		TRADE_PANEL = new TradePanel(this);
		PAUSE_DIALOG = new PauseDialog(this);
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
	
	/**
	 * Function that is called whenever the ESC key is pressed during a game
	 * Must only be called during a state that is considered "pausable"
	 * (e.g., not during a cutscene or similar)
	 * @author Corbin Hibler
	 * @date 2024-04-22
	 */
	public void pause() {
		System.out.println("PAUSED");

		if (!PAUSE_DIALOG.isVisible()) {
			glassPane = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					g.setColor(new Color(15, 15, 15, 100));
					g.fillRect(0, 0, getWidth(), getHeight());
					super.paintComponent(g);
				}
			};
			
			// Mouse event INTERCEPTOR, does not allow anything beneath
			// the glass pane to be clicked when active
			glassPane.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            e.consume();
		        }

		        @Override
		        public void mousePressed(MouseEvent e) {
		            e.consume();
		        }
		    });
			
			glassPane.setOpaque(false);
			glassPane.setBackground(new Color(15, 15, 15));
			frame.setGlassPane(glassPane);
			glassPane.setVisible(true);
			
			PAUSE_DIALOG.pack();
			PAUSE_DIALOG.setLocationRelativeTo(frame);
			PAUSE_DIALOG.setVisible(true);
		} else {
			glassPane.setVisible(false);
			PAUSE_DIALOG.setVisible(false);
		}
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
	
	
    /**
     *  Method to populate the landmark panel map
     *  @author Corbin Hibler
     *  @date 2024-04-17
     */
    public void initializeLandmarkPanels() {
    	// Iterative approach to create new Panels for all landmarks in landmarkList
        for (Landmark landmark : Landmark.landmarkList) {
            JPanel landmarkPanel;
           
            // Check what type of landmark and what panel is needed
            if (landmark instanceof River) {
                landmarkPanel = new RiverPanel(this, (River) landmark);
            } else if (landmark instanceof Fort) {
                landmarkPanel = new FortPanel(this, (Fort) landmark);
            } else {
                landmarkPanel = new LandmarkPanel(this, (Landmark) landmark);
            }
            landmarkToPanelMap.put(landmark, landmarkPanel);
        }
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new PauseKeyEventDispatcher(this));
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
