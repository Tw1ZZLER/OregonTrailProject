package oregonTrail;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import oregonTrail.landmark.RiverPanel;
import oregonTrail.panel.*;

/**
 * Main class containing all game logic for switching between panels and instantiating panels
 * @authors Corbin Hibler, Lukas Dunbar, Ray Otto, Ethan Vaughn
 * @date 2024-04-09
 */
public class OregonTrail {
    
    private JFrame frame;
    private Travel travelState;
    public final Wagon WAGON;
    public final StartupPanel STARTUP_PANEL;
    public final TravelPanel TRAVEL_PANEL;
    public final TrailMenuPanel TRAIL_MENU_PANEL;
    public final FortPanel FORT_STRONG_PANEL;
    public final SecondFortPanel FORT_OREGON_PANEL;
    public HuntingPanel huntingPanel;
    public final TradePanel TRADE_PANEL;

    public static final String KANSAS_RIVER_IMAGE_PATH = "C:\\Users\\rjott\\OneDrive\\Documents\\College files\\College sem 2\\Programming 2\\Java files\\OregonTrailProject\\OregonTrailProject\\src\\images\\KansasRiveratTopeka.jpg";

    public static final int DISTANCE_TO_KANSAS = 100;

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
        
        STARTUP_PANEL = new StartupPanel();
        TRAVEL_PANEL = new TravelPanel(this);
        travelState = new Travel(this);
        TRAIL_MENU_PANEL = new TrailMenuPanel(this);
        FORT_STRONG_PANEL = new FortPanel(this, new ImageIcon(this.getClass().getResource("/images/FortStrong.jpg")));
        FORT_OREGON_PANEL = new SecondFortPanel(this, new ImageIcon(this.getClass().getResource("/images/FortOregon.jpg")));
        TRADE_PANEL = new TradePanel();
        initialize();
        
    }
    
    /**
     * Opens a new panel and closes previous panel
     * @param riverPanel The panel to be opened
     * @param panelClose The panel to be closed
     * @author Corbin Hibler
     * @date 2024-04-08
     */
    public void openPanel(Component riverPanel, JPanel panelClose) {
        frame.getContentPane().removeAll();
        
        // Create new hunting panel if called.
        // Otherwise, use final (constant) panel
        if (riverPanel == this.huntingPanel) {
            this.huntingPanel = new HuntingPanel();
            frame.getContentPane().add(this.huntingPanel);
        }
        else {
            frame.getContentPane().add(riverPanel);
        }
        
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
        this.huntingPanel.setVisible(true);
    }

    /**
     * Getter method for instantiation of Travel class
     * @return the travelState
     */
    public Travel getTravelState() {
        return this.travelState;
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

        frame.getContentPane().add(STARTUP_PANEL, BorderLayout.CENTER);
        
        Timer startupTimer = new Timer(StartupPanel.STARTUP_TIME, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.getContentPane().remove(STARTUP_PANEL);
                frame.getContentPane().add(TRAVEL_PANEL);
                frame.getContentPane().validate();
                frame.getContentPane().repaint();
                
                // Check if the player has reached the Kansas River
                checkForKansasRiver();
            }
        });
        startupTimer.setRepeats(false);
        startupTimer.start();
    }

    private void checkForKansasRiver() {
        if (travelState.getMilesNextLandmark() <= 0) {
            if (travelState.getMilesTraveled() == DISTANCE_TO_KANSAS) {
                ImageIcon kansasRiverImage = new ImageIcon(KANSAS_RIVER_IMAGE_PATH);
                showKansasRiverPanel(kansasRiverImage);
            }
        }
    }

    private void showKansasRiverPanel(ImageIcon image) {
        JPanel kansasRiverPanel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel(image);
        kansasRiverPanel.add(imageLabel, BorderLayout.CENTER);

        JFrame kansasRiverFrame = new JFrame("Kansas River");
        kansasRiverFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        kansasRiverFrame.getContentPane().add(kansasRiverPanel);
        kansasRiverFrame.pack();
        kansasRiverFrame.setLocationRelativeTo(null); // Center the window
        kansasRiverFrame.setVisible(true);
    }
}

