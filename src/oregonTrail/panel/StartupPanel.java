package oregonTrail.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JProgressBar;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Java Swing panel for flair to show a quick startup animation
 * @author Corbin Hibler
 * @date 2024-04-05
 * @filename StartupPanel
 */
public class StartupPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int progressBarValue;
	public static final int STARTUP_TIME = 1000;

	/**
	 * Create the panel.
	 */
	public StartupPanel() {
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblCompany = new JLabel("REGISTER CLIFF GAMES LLC");
		lblCompany.setBackground(Color.DARK_GRAY);
		lblCompany.setForeground(Color.GREEN);
		lblCompany.setFont(new Font("Dialog", Font.BOLD, 45));
		lblCompany.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCompany, BorderLayout.CENTER);
		
		JLabel lblDisclaimerNotA = new JLabel("disclaimer: not a real llc");
		lblDisclaimerNotA.setForeground(Color.WHITE);
		lblDisclaimerNotA.setFont(new Font("Dialog", Font.BOLD, 22));
		lblDisclaimerNotA.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDisclaimerNotA, BorderLayout.SOUTH);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(148, 60));
		progressBar.setForeground(Color.GREEN);
		progressBar.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		add(progressBar, BorderLayout.NORTH);
		
		progressBarValue = 0;
		Timer progressBarTimer = new Timer(STARTUP_TIME / 100, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setValue(progressBarValue);
				progressBarValue++;
			}
		});
		progressBarTimer.start();
	}
}