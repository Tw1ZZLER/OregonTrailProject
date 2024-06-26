package oregonTrail.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import oregonTrail.OregonTrail;
import oregonTrail.landmark.Fort;

/**
 * Panel class representing options available when located at a fort in the Oregon Trail game.
 * This panel displays buttons for various actions such as opening the map, changing the rate of travel,
 * changing rations, attempting to trade, opening the shop, and continuing the trail.
 * It also includes action listeners for each button to handle the corresponding actions.
 * 
 * @author Lukas Dunbar
 * @date 2024-04-09
 * @filename FortPanel.java
 */
public class FortPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private OregonTrail oregonTrail;
    private String fortName;
    private ImageIcon fortImageIcon;
    
    /**
     * Constructs a new FortPanel with the specified OregonTrail instance and fort image icon
     * which are obtained from a passed Fort object.
     * 
     * @param pOregonTrail The OregonTrail instance associated with the game.
     * @param fort The Fort object used for this panel.
     */
    public FortPanel(OregonTrail pOregonTrail, Fort fort) {
        this.oregonTrail = pOregonTrail;
        this.fortName = fort.getName();
        
        // Scale up image
        ImageIcon originalIcon = fort.getPicture();
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        this.fortImageIcon = new ImageIcon(scaledImage);
        

        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("WELCOME TO " + fortName + "!!!");
        welcomeLabel.setOpaque(true);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBackground(Color.BLACK);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Impact", Font.BOLD, 30)); // Set font size to 24
        add(welcomeLabel, BorderLayout.NORTH);

        // Create image label and add it to the top half
        JLabel imageLabel = new JLabel(fortImageIcon);
        add(imageLabel, BorderLayout.CENTER);

        // Create panel for buttons on the bottom half
        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        // Create buttons
        JButton openMapButton = new JButton("Open Map");
        openMapButton.setBackground(Color.BLACK);
        openMapButton.setForeground(Color.WHITE);
        openMapButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton changeRateButton = new JButton("Change Rate of Travel");
        changeRateButton.setBackground(Color.BLACK);
        changeRateButton.setForeground(Color.WHITE);
        changeRateButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton changeRationsButton = new JButton("Change Rations");
        changeRationsButton.setBackground(Color.BLACK);
        changeRationsButton.setForeground(Color.WHITE);
        changeRationsButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton attemptTradeButton = new JButton("Attempt to Trade");
        attemptTradeButton.setBackground(Color.BLACK);
        attemptTradeButton.setForeground(Color.WHITE);
        attemptTradeButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton openShopButton = new JButton("Open Shop");
        openShopButton.setBackground(Color.BLACK);
        openShopButton.setForeground(Color.WHITE);
        openShopButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton continueButton = new JButton("Continue Trail");
        continueButton.setBackground(Color.BLACK);
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton talkToLocals = new JButton("Talk to Locals");
        talkToLocals.setBackground(Color.BLACK);
        talkToLocals.setForeground(Color.WHITE);
        talkToLocals.setFont(new Font("Impact", Font.PLAIN, 20));

        // Add buttons to button panel
        buttonPanel.add(openMapButton);
        buttonPanel.add(changeRateButton);
        buttonPanel.add(changeRationsButton);
        buttonPanel.add(attemptTradeButton);
        buttonPanel.add(openShopButton);
        buttonPanel.add(continueButton);
        buttonPanel.add(talkToLocals);

        // Add action listeners to buttons
        openMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openMap(fortName);
            }
        });

        changeRateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //oregonTrail.getTravelState().changeRateOfTravel(this);
            	  System.out.println("Change Rate of Travel button clicked");
            }
        });

        changeRationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // oregonTrail.getTravelState().changeRations(this);
            	  System.out.println("Change Rations button clicked");
            }
        });

        attemptTradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openPanel(oregonTrail.TRADE_PANEL);
            }
        });

        openShopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oregonTrail.openPanel(oregonTrail.SHOP_PANEL);
            }
        });
        
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            }
        });
        
        talkToLocals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	oregonTrail.getDialogueState().talkToFortLocals();
            }
        });
    }
}

