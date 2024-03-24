package wagonLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Item {
    protected String name;
    protected double weight;

    public Item(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}

class Food extends Item {
    public Food(String name, double weight) {
        super(name, weight);
    }
}

class Wagon {
    private ArrayList<Item> contents;
    private double totalWeight;

    public Wagon() {
        contents = new ArrayList<>();
        totalWeight = 0.0;
    }

    public void addItem(Item item) {
        contents.add(item);
        totalWeight += item.getWeight();
    }

    public void removeItem(Item item) {
        contents.remove(item);
        totalWeight -= item.getWeight();
    }

    public double getTotalWeight() {
        return totalWeight;
    }
}

public class OregonTrailApp extends JFrame implements ActionListener {
    private Wagon wagon;
    private JLabel totalWeightLabel;
    private JTextField warningTextField;
    private ArrayList<JCheckBox> checkBoxes;

    public OregonTrailApp() {
        wagon = new Wagon();
        checkBoxes = new ArrayList<>();

        setTitle("Oregon Trail");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(0, 2));

        // Adding checkboxes for items
        String[] itemNames = {"Apple Vinegar", "Bedroll", "Bacon", "Blacksmithing Tools", "Beans", "Books",
                "Coffee", "Medicine", "Dried Apples", "Cast Iron Stove", "Flour", "Chair", "Hardtack",
                "Cookware & Eating Utensils", "Lard", "Granny’s Clock", "Salt", "Gun Making Tools", "Sugar",
                "Keepsakes", "Rice", "Lead Shot", "Water", "Mirror", "Whiskey", "Gunpowder", "Tent & Gear", "Tools", "Toys"};
        double[] itemWeights = {25, 15, 400, 200, 200, 75, 80, 10, 80, 300, 500, 20, 200, 75, 200, 15, 50, 200, 40,
                40, 200, 25, 100, 15, 40, 80, 150, 50, 15};
        for (int i = 0; i < itemNames.length; i++) {
            JCheckBox checkBox = new JCheckBox(itemNames[i]);
            checkBox.addActionListener(this);
            controlPanel.add(checkBox);
            checkBoxes.add(checkBox);
        }

        // Display total weight
        totalWeightLabel = new JLabel("Total Weight: ");
        controlPanel.add(totalWeightLabel);

        // Warning text field
        warningTextField = new JTextField();
        warningTextField.setEditable(false);
        controlPanel.add(warningTextField);

        // Travel button
        JButton travelButton = new JButton("Travel");
        travelButton.addActionListener(this);
        controlPanel.add(travelButton);

        // Add control panel to frame
        add(controlPanel, BorderLayout.CENTER);

        // Add Oregon Trail image
        ImageIcon oregonTrailImage = new ImageIcon("oregon_trail_image.jpg");
        JLabel imageLabel = new JLabel(oregonTrailImage);
        add(imageLabel, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) source;
            String itemName = checkBox.getText();
            double itemWeight = getItemWeight(itemName);
            if (checkBox.isSelected()) {
                // Add item to wagon
                Item item = new Item(itemName, itemWeight);
                wagon.addItem(item);
            } else {
                // Remove item from wagon
                Item item = new Item(itemName, itemWeight);
                wagon.removeItem(item);
            }
            totalWeightLabel.setText("Total Weight: " + wagon.getTotalWeight());
            if (wagon.getTotalWeight() > 2400) {
                warningTextField.setText("You may not travel with more than 2400lbs in your wagon");
            } else {
                warningTextField.setText("");
            }
        } else if (source instanceof JButton) {
            // Code to handle travel button click
            JButton button = (JButton) source;
            if (button.getText().equals("Travel")) {
                int travelSpeed = 12; // Default travel speed
                String[] options = {"Sumo Wrestler", "Regular Joe", "Starving Homeless person"};
                String foodConsumption = (String) JOptionPane.showInputDialog(this, "Select food consumption level:",
                        "Food Consumption", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (foodConsumption != null) {
                    String speedInput = JOptionPane.showInputDialog(this, "Enter travel speed (miles per day):");
                    if (speedInput != null) {
                        try {
                            travelSpeed = Integer.parseInt(speedInput);
                            if (travelSpeed < 12 || travelSpeed > 20) {
                                JOptionPane.showMessageDialog(this, "Invalid travel speed. Please enter a value between 12 and 20.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } else {
                        return; // User canceled speed input
                    }

                    // Calculate journey duration
                    int journeyDays = 2200 / travelSpeed;

                    // Determine food consumption rate based on user input
                    double foodConsumptionRate;
                    switch (foodConsumption) {
                        case "Sumo Wrestler":
                            foodConsumptionRate = 1.5;
                            break;
                        case "Regular Joe":
                            foodConsumptionRate = 1.0;
                            break;
                        case "Starving Homeless person":
                            foodConsumptionRate = 0.5;
                            break;
                        default:
                            foodConsumptionRate = 1.0; // Default value
                    }

                    // Calculate required food amount
                    double requiredFood = journeyDays * foodConsumptionRate;

                    // Check if there's enough food
                    if (requiredFood > wagon.getTotalWeight()) {
                        warningTextField.setText("Insufficient food for the journey.");
                    } else {
                        warningTextField.setText("Food is sufficient for the journey.");
                    }
                }
            }
        }
    }
    private double getItemWeight(String itemName) {
        // Dummy implementation, you may want to fetch weights from a data source or a predefined map
        // For simplicity, using a simple mapping here
        switch (itemName) {
            case "Apple Vinegar":
                return 25;
            case "Bedroll":
                return 15;
            case "Bacon":
                return 400;
            case "Blacksmithing Tools":
                return 200;
            case "Beans":
                return 200;
            case "Books":
                return 75;
            case "Coffee":
                return 80;
            case "Medicine":
                return 10;
            case "Dried Apples":
                return 80;
            case "Cast Iron Stove":
                return 300;
            case "Flour":
                return 500;
            case "Chair":
                return 20;
            case "Hardtack":
                return 200;
            case "Cookware & Eating Utensils":
                return 75;
            case "Lard":
                return 200;
            case "Granny’s Clock":
                return 15;
            case "Salt":
                return 50;
            case "Gun Making Tools":
                return 200;
            case "Sugar":
                return 40;
            case "Keepsakes":
                return 40;
            case "Rice":
                return 200;
            case "Lead Shot":
                return 25;
            case "Water":
                return 100;
            case "Mirror":
                return 15;
            case "Whiskey":
                return 40;
            case "Gunpowder":
                return 80;
            case "Tent & Gear":
                return 150;
            case "Tools":
                return 50;
            case "Toys":
                return 15;
            default:
                return 0; // Unknown item
        }
    }

    public static void main(String[] args) {
        OregonTrailApp app = new OregonTrailApp();
        app.setVisible(true);
    }
}
