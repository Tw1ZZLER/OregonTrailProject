package oregonTrail.panel;
import oregonTrail.*;
import oregonTrail.panel.TradePanel.TRADING;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class TradePanel extends JPanel {
    public class TRADING {

	}

	public TradePanel() {
        JLabel label = new JLabel(oregonTrail.Trading.generateTradeOffer());
        add(label);

        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Trade Confirmed!");
               
            }
        });
        add(yesButton);

        JButton noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Trade Denied!");
            }
        });
        add(noButton);
    }

	
	
}

