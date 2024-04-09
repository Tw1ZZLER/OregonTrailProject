package oregonTrail.panel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrailMenuPanel extends JPanel {
    public TrailMenuPanel() {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        // Display date
        JLabel dateLabel = new JLabel("Date:");
        JLabel dateValueLabel = new JLabel("April 5, 2024");
        add(dateLabel);
        add(dateValueLabel);

        // Display weather
        JLabel weatherLabel = new JLabel("Weather:");
        JLabel weatherValueLabel = new JLabel("Sunny");
        add(weatherLabel);
        add(weatherValueLabel);

        // Display health
        JLabel healthLabel = new JLabel("Health:");
        JLabel healthValueLabel = new JLabel("Good");
        add(healthLabel);
        add(healthValueLabel);

        // Display pace
        JLabel paceLabel = new JLabel("Pace:");
        JLabel paceValueLabel = new JLabel("Medium");
        add(paceLabel);
        add(paceValueLabel);

        // Add buttons
        JButton continueButton = new JButton("Continue on Trail");
        JButton checkSuppliesButton = new JButton("Check Supplies");
        JButton lookMapButton = new JButton("Look at Map");
        JButton changeRationsButton = new JButton("Change Food Rations");
        JButton restButton = new JButton("Stop to Rest");
        JButton tradeButton = new JButton("Attempt to Trade");
        JButton huntButton = new JButton("Hunt for Food");

        // Add action listeners to buttons
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Continue on Trail button clicked");
            }
        });

        checkSuppliesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Check Supplies button clicked");
            }
        });

        lookMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Look at Map button clicked");
            }
        });

        changeRationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Food Rations button clicked");
            }
        });

        restButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Stop to Rest button clicked");
            }
        });

        tradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Attempt to Trade button clicked");
            }
        });

        huntButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hunt for Food button clicked");
            }
        });

        // Add components to the panel with SpringLayout constraints
        add(dateLabel);
        add(dateValueLabel);
        add(weatherLabel);
        add(weatherValueLabel);
        add(healthLabel);
        add(healthValueLabel);
        add(paceLabel);
        add(paceValueLabel);
        add(continueButton);
        add(checkSuppliesButton);
        add(lookMapButton);
        add(changeRationsButton);
        add(restButton);
        add(tradeButton);
        add(huntButton);

        // Set SpringLayout constraints
        layout.putConstraint(SpringLayout.WEST, dateLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, dateLabel, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, dateValueLabel, 5, SpringLayout.EAST, dateLabel);
        layout.putConstraint(SpringLayout.NORTH, dateValueLabel, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, weatherLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, weatherLabel, 5, SpringLayout.SOUTH, dateLabel);
        layout.putConstraint(SpringLayout.WEST, weatherValueLabel, 5, SpringLayout.EAST, weatherLabel);
        layout.putConstraint(SpringLayout.NORTH, weatherValueLabel, 5, SpringLayout.SOUTH, dateValueLabel);
        layout.putConstraint(SpringLayout.WEST, healthLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, healthLabel, 5, SpringLayout.SOUTH, weatherLabel);
        layout.putConstraint(SpringLayout.WEST, healthValueLabel, 5, SpringLayout.EAST, healthLabel);
        layout.putConstraint(SpringLayout.NORTH, healthValueLabel, 5, SpringLayout.SOUTH, weatherValueLabel);
        layout.putConstraint(SpringLayout.WEST, paceLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, paceLabel, 5, SpringLayout.SOUTH, healthLabel);
        layout.putConstraint(SpringLayout.WEST, paceValueLabel, 5, SpringLayout.EAST, paceLabel);
        layout.putConstraint(SpringLayout.NORTH, paceValueLabel, 5, SpringLayout.SOUTH, healthValueLabel);
        layout.putConstraint(SpringLayout.WEST, continueButton, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, continueButton, 10, SpringLayout.SOUTH, paceLabel);
        layout.putConstraint(SpringLayout.WEST, checkSuppliesButton, 5, SpringLayout.EAST, continueButton);
        layout.putConstraint(SpringLayout.NORTH, checkSuppliesButton, 10, SpringLayout.SOUTH, paceValueLabel);
        layout.putConstraint(SpringLayout.WEST, lookMapButton, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lookMapButton, 10, SpringLayout.SOUTH, continueButton);
        layout.putConstraint(SpringLayout.WEST, changeRationsButton, 5, SpringLayout.EAST, lookMapButton);
        layout.putConstraint(SpringLayout.NORTH, changeRationsButton, 10, SpringLayout.SOUTH, checkSuppliesButton);
        layout.putConstraint(SpringLayout.WEST, restButton, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, restButton, 10, SpringLayout.SOUTH, lookMapButton);
        layout.putConstraint(SpringLayout.WEST, tradeButton, 5, SpringLayout.EAST, restButton);
        layout.putConstraint(SpringLayout.NORTH, tradeButton, 10, SpringLayout.SOUTH, changeRationsButton);
        layout.putConstraint(SpringLayout.WEST, huntButton, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, huntButton, 10, SpringLayout.SOUTH, restButton);
    }
}
