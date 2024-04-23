package oregonTrail.panel;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import net.miginfocom.swing.MigLayout;

public class MainMenuPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image backgroundImage;

    /**
     * Create the panel.
     */
    public MainMenuPanel() {
        setLayout(new MigLayout());
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
