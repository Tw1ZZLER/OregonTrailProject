package oregonTrail.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FortPanel extends JPanel {
    private static final long serialVersionUID = 1L;
	public FortPanel(Image fortImage) {
        setLayout(new BorderLayout());

        // Create image label and add it to the top half
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/FortStrong.jpg")); // Replace path_to_your_image
        Image scaledImage = imageIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(imageIcon);
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

        // Add buttons to button panel
        buttonPanel.add(openMapButton);
        buttonPanel.add(changeRateButton);
        buttonPanel.add(changeRationsButton);
        buttonPanel.add(attemptTradeButton);
        buttonPanel.add(openShopButton);

        // Add action listeners to buttons (for demonstration, they just print a message)
        openMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open Map button clicked");
            }
        });

        changeRateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Rate of Travel button clicked");
            }
        });

        changeRationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Rations button clicked");
            }
        });

        attemptTradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Attempt to Trade button clicked");
            }
        });

        openShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open Shop button clicked");
            }
        });
        
       
    } 
}
