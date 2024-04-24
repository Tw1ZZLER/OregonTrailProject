package oregonTrail.panel;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import net.miginfocom.swing.MigLayout;
import oregonTrail.OregonTrail;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuPanel extends JPanel {
	public TravelPanel TRAVEL_PANEL;
    private static final long serialVersionUID = 1L;
    private Image backgroundImage;
    OregonTrail oregonTrail;
    
    /**
     * Create the panel.
     */
    public MainMenuPanel(OregonTrail oregonTrail) {
    	this.oregonTrail = oregonTrail;
        this.TRAVEL_PANEL = null;
		setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][]"));
        
        JButton btnNewButton = new JButton("Continue");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {JOptionPane.showMessageDialog(null, "There is no save to continue");
        	}
        });
     
        add(btnNewButton, "cell 4 18");
        
        JButton btnNewButton_1 = new JButton("New Game");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
				oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
			}
        });
        add(btnNewButton_1, "cell 10 18,alignx trailing");
        
        JButton btnNewButton_2 = new JButton("Load Game");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {JOptionPane.showMessageDialog(null, "There is no data to load");
        	}
        });
        add(btnNewButton_2, "cell 16 18");
        
        JButton btnNewButton_3 = new JButton("Exit Game");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton_3.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	
        	public void mouseClicked(MouseEvent e) {System.exit(0);
        	}
        
        });
        add(btnNewButton_3, "cell 21 18");
        try {
            // Load the background image
            backgroundImage = ImageIO.read(new File("src/images/MainMenu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
