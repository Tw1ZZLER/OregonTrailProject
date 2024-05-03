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
import oregonTrail.weather.Weather;

/**
 * Main class containing all game logic for switching between panels and instantiating panels.
 * Serializable and works as save data for where a player is.
 *
 * @authors Corbin Hibler, Lukas Dunbar, Ray Otto, Ethan Vaughn
 * @date 2024-04-09
 * @filename OregonTrail.java
 * @version 1.0
 */
public class OregonTrail implements Serializable {

    private JFrame frame;
    private Travel travelState;
    private Weather weatherState;
    private Map<Landmark, JPanel> landmarkToPanelMap = new HashMap<>();
    public final Wagon WAGON;
    public final StartupPanel STARTUP_PANEL;
    public final TravelPanel TRAVEL_PANEL;
    public final TrailMenuPanel TRAIL_MENU_PANEL;
    public final LoadedWagonPanel SUPPLIES_PANEL;
    public HuntingPanel huntingPanel;
    public final TradePanel TRADE_PANEL;
    public final ShopPanel SHOP_PANEL;
    public final PauseDialog PAUSE_DIALOG;
    public final MainMenuPanel MAIN_MENU;
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
        weatherState = new Weather(this);
        TRAIL_MENU_PANEL = new TrailMenuPanel(this);
        SUPPLIES_PANEL = new LoadedWagonPanel(this);
        TRADE_PANEL = new TradePanel(this);
        PAUSE_DIALOG = new PauseDialog(this);
        MAIN_MENU = new MainMenuPanel(this);
        initializeLandmarkPanels();
        initialize();
    }

    /**
     * Opens a new panel and closes previous panel.
     *
     * @param panelOpen  The panel to be opened.
     * @param panelClose The panel to be closed.
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
        } else {
            frame.getContentPane().add(panelOpen);
        }

        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    /**
     * Function that is called whenever the ESC key is pressed during a game.
     * Must only be called during a state that is considered "pausable"
     * (e.g., not during a cutscene or similar).
     *
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

    /**
     * Retrieves the panel associated with a specific landmark.
     *
     * @param landmark The landmark for which to retrieve the panel.
     * @return The panel associated with the landmark.
     */
    public JPanel getPanelForLandmark(Landmark landmark) {
        return landmarkToPanelMap.get(landmark);
    }


    /**
     * Getter method for instantiation of Travel class.
     *
     * @return the travelState
     */
    public Travel getTravelState() {
        return this.travelState;
    }
    
	/**
	 * Getter method for instantiation of Weather class.
	 * @return the weatherState
	 */
	public Weather getWeatherState() {
		return weatherState;
	}

    /**
     * Method to populate the landmark panel map.
     * Iterative approach to create new Panels for all landmarks in landmarkList.
     *
     * @author Corbin Hibler
     * @date 2024-04-17
     */
    public void initializeLandmarkPanels() {
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
    
    public void openMap(String landmarkName) {
        String imagePath = "";

        // Determine the image path based on the landmark name
        switch (landmarkName) {
            case "Kansas River Crossing":
                imagePath = "src/images/mapKansasRiver.png";
                break;
            case "Big Blue River Crossing":
                imagePath = "src/images/mapBigBlue.png";
                break;
            case "Fort Strong":
                imagePath = "src/images/mapFortStrong.png";
                break;
            case "Ash Hollow":
                imagePath = "src/images/mapAshHollow.png";
                break;
            case "Chimney Rock":
                imagePath = "src/images/mapChimneyRock.png";
                break;
            case "Fort Laramie":
                imagePath = "src/images/mapFortLaramie.png";
                break;
            default:
                return;
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

        // Show logo and team name
        frame.getContentPane().add(STARTUP_PANEL, BorderLayout.CENTER);

        Timer startupTimer = new Timer(StartupPanel.STARTUP_TIME, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                //opens main menu
                openPanel(MAIN_MENU);
                // After startup screen is finished, display first panel

            }
        });
        startupTimer.setRepeats(false);
        startupTimer.start();
    }


}
