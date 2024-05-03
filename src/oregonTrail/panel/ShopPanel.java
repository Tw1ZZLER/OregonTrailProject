package oregonTrail.panel;

import javax.swing.JPanel;
import java.util.ArrayList;
import net.miginfocom.swing.MigLayout;
import oregonTrail.Item;
import oregonTrail.Shop;
import oregonTrail.OregonTrail;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;


/**
 * Creates a panel with options for purchasing different items. Tallies up the cost of items ordered as orderCost, and stores ordered items as an ArrayList.
 * When the player hits the "Confirm Order" button, it will check to see if they are capable of actually purchasing the items and add the ArrayList to the Wagon if so.
 * 
 * @author Ethan Vaughn
 * @date 2024-04-16
 * @filename ShopPanel.java
 */
public class ShopPanel extends JPanel {

	private int fortNum = 0;
	private static final long serialVersionUID = 1L;
	private JLabel oxPriceLabel;
	private JLabel foodPriceLabel;
	private JLabel clothesPriceLabel;
	private JLabel ammoPriceLabel;
	private JLabel partPriceLabel;
	private JLabel orderCostLabel;
	private JTextField orderField;
	private JButton leaveButton;
	private ArrayList<Item> orderedItems;
	private double orderCost = 0;
	private OregonTrail oregonTrail;
	/**
	 * Shop Panel constructor to create a ShopPanel
	 * @param pOregonTrail oregonTrail game object
	 */
	public ShopPanel(OregonTrail pOregonTrail) {

		orderedItems = new ArrayList<Item>();
		System.out.println(fortNum);
		Shop shop = new Shop(fortNum);
		orderCost = 0;
		this.oregonTrail = pOregonTrail;
		
		
		setLayout(new MigLayout("", "[grow][][][][][grow]", "[][][][][][][][][][][][][]"));
		
		JLabel welcomeLabel = new JLabel("Welcome to the Shop! ");
		welcomeLabel.setOpaque(true);
		welcomeLabel.setFont(new Font("Impact", Font.BOLD, 48));
		add(welcomeLabel, "cell 0 0 6 2,alignx center");
		
		JLabel itemsLabel = new JLabel("Items:");
		itemsLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(itemsLabel, "cell 2 2");
		
		JLabel pricesLabel = new JLabel("Prices:");
		pricesLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(pricesLabel, "cell 3 2");
		
		JLabel oxenLabel = new JLabel("1- Oxen");
		oxenLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(oxenLabel, "cell 2 3");
		
		oxPriceLabel = new JLabel("$"+shop.getPrice(1,1));
		oxPriceLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(oxPriceLabel, "cell 3 3");
		
		JLabel yokeLabel = new JLabel("Per Yoke");
		yokeLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(yokeLabel, "cell 5 3");
		
		JLabel foodLabel = new JLabel("2- Food");
		foodLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(foodLabel, "cell 2 4");
		
		foodPriceLabel = new JLabel("$"+shop.getPrice(2,1));
		foodPriceLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(foodPriceLabel, "cell 3 4");
		
		JLabel poundLabel = new JLabel("Per Pound");
		poundLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(poundLabel, "cell 5 4");
		
		JLabel clothingLabel = new JLabel("3- Clothing");
		clothingLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(clothingLabel, "cell 2 5");
		
		clothesPriceLabel = new JLabel("$"+shop.getPrice(3,1));
		clothesPriceLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(clothesPriceLabel, "cell 3 5");
		
		JLabel perSetlabel = new JLabel("Per Set");
		perSetlabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(perSetlabel, "cell 5 5");
		
		JLabel ammunitionLabel = new JLabel("4- Ammunition");
		ammunitionLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(ammunitionLabel, "cell 2 6");
		
		ammoPriceLabel = new JLabel("$"+shop.getPrice(4,1));
		ammoPriceLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(ammoPriceLabel, "cell 3 6");
		
		JLabel perBoxLabel = new JLabel("Per Box (20 Bullets)");
		perBoxLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(perBoxLabel, "cell 5 6");
		
		JLabel partsLabel = new JLabel("5- Wagon Parts");
		partsLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(partsLabel, "cell 2 7");
		
		partPriceLabel = new JLabel("$"+shop.getPrice(5,1));
		partPriceLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(partPriceLabel, "cell 3 7");
		
		JLabel perPartLabel = new JLabel("Per Part");
		perPartLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(perPartLabel, "cell 5 7");
		
		JLabel currentFundsLabel = new JLabel("Current Funds:");
		currentFundsLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(currentFundsLabel, "cell 2 9");
		
		JLabel moneyLabel = new JLabel(""+oregonTrail.WAGON.getMoney());
		moneyLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(moneyLabel, "cell 3 9");
		
		JLabel instructionLabel = new JLabel("Enter the number of the item you'd like to buy!");
		instructionLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(instructionLabel, "cell 1 10");
		
		JLabel lblNewLabel_9 = new JLabel("Order Cost:");
		lblNewLabel_9.setFont(new Font("Impact", Font.PLAIN, 20));
		add(lblNewLabel_9, "cell 2 10");
		
		orderCostLabel = new JLabel("$0");
		orderCostLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		add(orderCostLabel, "cell 3 10");
		
		orderField = new JTextField();
		orderField.setFont(new Font("Dialog", Font.PLAIN, 20));
		orderField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int itemPurchased = Integer.parseInt(orderField.getText());
				int numPurchased = Integer.parseInt(JOptionPane.showInputDialog(null, "How Many?:", "", JOptionPane.INFORMATION_MESSAGE));
				if (numPurchased != 0) {
				    // get the number of items purchased, use in conjunction with the item purchased to get price and add to orderCost
				    double price = shop.getPrice(itemPurchased, numPurchased);
					orderCost+= price;

				} 
				else if (numPurchased ==0){
				    double price = 0;
					orderCost+= price;
				}
				
				//add the ordered items to 
				for(int i = 0; i<numPurchased; i++) {
					orderedItems.add(shop.itemPurchased(itemPurchased));
				}
				
				orderCostLabel.setText("$"+orderCost);
				
			}
		});
		add(orderField, "cell 1 11,growx");
		orderField.setColumns(10);
		
		JButton confirmButton = new JButton("Confirm Order");
		confirmButton.setBackground(Color.BLACK);
		confirmButton.setForeground(Color.WHITE);
		confirmButton.setFont(new Font("Impact", Font.BOLD, 20));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if funds are insufficient for the order, clear everything.	
				if(orderCost>oregonTrail.WAGON.getMoney()) {
						JOptionPane.showMessageDialog(null, "Insufficient Funds");
						orderCost = 0;
						orderedItems.clear();
						orderCostLabel.setText("$"+orderCost);
					}
				
				//if funds are sufficient, add the items to the wagon, subtract the money from the wagon.	
				else {
						oregonTrail.WAGON.changeMoney(-orderCost);
						
						//go through orderedItems to add all items to WAGON
						for(Item i : orderedItems) {
							oregonTrail.WAGON.addItem(i);
						}
						orderCost = 0;
						orderedItems.clear();
						JOptionPane.showMessageDialog(null, "Purchase Complete!");
						moneyLabel.setText(""+oregonTrail.WAGON.getMoney());
						orderCostLabel.setText(""+orderCost);
					}
				
				
			}
		});
		add(confirmButton, "cell 2 11");
		
		leaveButton = new JButton("Leave Shop");
		leaveButton.setBackground(Color.BLACK);
		leaveButton.setForeground(Color.WHITE);
		leaveButton.setFont(new Font("Impact", Font.BOLD, 20));
		leaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderedItems.clear();
				orderCost = 0;
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
                fortNum++;
			}
		});
		add(leaveButton, "cell 2 12");

	}

}
