package oregonTrail.panel;
import oregonTrail.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class TradePanel extends JPanel {
	private OregonTrail oregonTrail;
	
	public TradePanel(OregonTrail oregonTrail) {
		this.oregonTrail = oregonTrail;
		
        JLabel label = new JLabel(Trading.generateTradeOffer());
        add(label);

        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Trade Confirmed!");
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            }
        });
        add(yesButton);

        JButton noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Trade Denied!");
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            }
        });
        add(noButton);
    }

	
	
}

