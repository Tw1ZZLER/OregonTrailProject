package oregonTrail.panel;
	
	import javax.swing.*;
	import oregonTrail.OregonTrail;
	import oregonTrail.Travel;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	/**
	 * Panel class representing options available when located at a river in the Oregon Trail game.
	 * This panel displays buttons for various actions such as opening the map, changing the rate of travel,
	 * changing rations, attempting to cross the river, caulking the wagon, and waiting a day.
	 * It also includes action listeners for each button to handle the corresponding actions.
	 * 
	 * @author Ray Otto
	 * @date 2024-14-04
	 * @filename RiverPanel.java
	 */
	public class RiverPanel extends JPanel {
		public RiverPanel() {
			setLayout(null);
			
			JLabel Rivername = new JLabel("You have reached the Kansas river");
			Rivername.setHorizontalAlignment(SwingConstants.CENTER);
			Rivername.setFont(new Font("Arial", Font.BOLD, 24));
			Rivername.setBounds(0, 0, 450, 28);
			add(Rivername);
			
			JLabel imageLabel = new JLabel((Icon) null);
			imageLabel.setBounds(0, 28, 450, 249);
			add(imageLabel);
			
			JButton openMapButton = new JButton("Open Map");
			openMapButton.setBounds(0, 277, 75, 23);
			add(openMapButton);
			
			JButton changeRateButton = new JButton("Change Rate of Travel");
			changeRateButton.setBounds(75, 277, 75, 23);
			add(changeRateButton);
			
			JButton changeRationsButton = new JButton("Change Rations");
			changeRationsButton.setBounds(150, 277, 75, 23);
			add(changeRationsButton);
			
			JButton attemptToCross = new JButton("Cross river");
			attemptToCross.setBounds(225, 277, 75, 23);
			add(attemptToCross);
			
			JButton CaulkWagonButton = new JButton("Caulk Wagon");
			CaulkWagonButton.setBounds(300, 277, 75, 23);
			add(CaulkWagonButton);
			
			JButton WaitADayButton = new JButton("Wait");
			WaitADayButton.setBounds(375, 277, 75, 23);
			add(WaitADayButton);
		}
	    private static final long serialVersionUID = 1L;
	    private OregonTrail oregonTrail;
	    private Travel travelState;
	    
	    /**
	     * Constructs a new RiverPanel with the specified OregonTrail instance and river image icon.
	     * 
	     * @param pOregonTrail The OregonTrail instance associated with the game.
	     * @paramRriverImageIcon The ImageIcon representing the river.
	     */
	    public RiverPanel(OregonTrail pOregonTrail, ImageIcon riverImageIcon) {
	        this.oregonTrail = pOregonTrail;
	        this.travelState = pOregonTrail.getTravelState();
	        

	        setLayout(new BorderLayout());
	        JLabel welcomeLabel = new JLabel("Welcome the Kansas River");
	        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size to 24
	        add(welcomeLabel, BorderLayout.NORTH);

	        // Create image label and add it to the top half
	        Image scaledImage = riverImageIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);
	        JLabel imageLabel = new JLabel(riverImageIcon);
	        add(imageLabel, BorderLayout.CENTER);

	        // Create panel for buttons on the bottom half
	        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
	        add(buttonPanel, BorderLayout.SOUTH);

	        // Create buttons
	        JButton openMapButton = new JButton("Open Map");
	        JButton changeRateButton = new JButton("Change Rate of Travel");
	        JButton changeRationsButton = new JButton("Change Rations");
	        JButton attemptToCrossButton = new JButton("Attempt to Cross");//cross river, chance to lose items if river is too deep
	        JButton CaulkWagonButton = new JButton("The Wagon has been Caulked");//takes a day and allows you to cross river if light enough
	        JButton WaitButton = new JButton("You waited a day");//should add day to current day

	        // Add buttons to button panel
	        buttonPanel.add(openMapButton);
	        buttonPanel.add(changeRateButton);
	        buttonPanel.add(changeRationsButton);
	        buttonPanel.add(attemptToCrossButton);
	        buttonPanel.add(CaulkWagonButton);
	        buttonPanel.add(WaitButton);

	        // Add action listeners to buttons
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

	        attemptToCrossButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Attempt to Cross button clicked");
	            }
	        });

	        CaulkWagonButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Open Shop button clicked");
	            }
	        });
	        
	        WaitButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL, RiverPanel.this);
	            }
	        });
	    } 
	}

