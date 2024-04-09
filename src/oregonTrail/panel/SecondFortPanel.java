package oregonTrail.panel;

import javax.swing.*;

import oregonTrail.OregonTrail;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Shows options related to being located at a Fort.
 * @author Lukas Dunbar
 * @date 2024-04-09
 */
public class SecondFortPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private OregonTrail oregonTrail;
    public SecondFortPanel(OregonTrail pOregonTrail, ImageIcon fortImageIcon) {
		this.oregonTrail = pOregonTrail;
		pOregonTrail.getTravelState();
		
        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Fort Oregon !!!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size to 24
        add(welcomeLabel, BorderLayout.NORTH);


        fortImageIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(fortImageIcon);
        add(imageLabel, BorderLayout.CENTER);

        // Create panel for buttons on the bottom half
        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        // Create buttons
        JButton openMapButton = new JButton("Open Map");
        JButton changeRateButton = new JButton("Change Rate of Travel");
        JButton changeRationsButton = new JButton("Change Rations");
        JButton attemptTradeButton = new JButton("Attempt to Trade");
        JButton openShopButton = new JButton("Open Shop");
        JButton continueButton = new JButton("Continue Trail");


        // Add buttons to button panel
        buttonPanel.add(openMapButton);
        buttonPanel.add(changeRateButton);
        buttonPanel.add(changeRationsButton);
        buttonPanel.add(attemptTradeButton);
        buttonPanel.add(openShopButton);
        buttonPanel.add(continueButton);

        // Add action listeners to buttons (for demonstration, they just print a message)
        openMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open Map button clicked");
            }
        });

        changeRateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Rate of Travel button clicked");
            }
        });

        changeRationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Rations button clicked");
            }
        });

        attemptTradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Attempt to Trade button clicked");
            }
        });

        openShopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open Shop button clicked");
            }
        });
        
        continueButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL, SecondFortPanel.this);
        	}
        });
       
    } 
}
