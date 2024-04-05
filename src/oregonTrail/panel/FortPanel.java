package oregonTrail.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FortPanel extends JPanel {
    public FortPanel() {
        setLayout(new BorderLayout());

        // Create image label and add it to the top half
        ImageIcon imageIcon = new ImageIcon("/src/images/chimney_rock_1.jpg"); // Replace path_to_your_image
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
        
       
    } public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FortPanel().setVisible(true);
            }
        });
    }
}
