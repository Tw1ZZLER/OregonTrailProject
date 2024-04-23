package oregonTrail.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import oregonTrail.OregonTrail;
import oregonTrail.PauseKeyListener;

public class PauseDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private OregonTrail oregonTrail;

	/**
	 * Create the dialog.
	 * @param oregonTrail 
	 */
	public PauseDialog(OregonTrail oregonTrail) {
		this.oregonTrail = oregonTrail;
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 128));
		getContentPane().setLayout(new MigLayout("", "[grow,fill][150px,fill][grow,fill]", "[grow,fill][][][][][grow,fill]"));
		
		JLabel lblPaused = new JLabel("PAUSED");
		lblPaused.setForeground(Color.WHITE);
		lblPaused.setFont(new Font("Impact", Font.BOLD, 36));
		lblPaused.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPaused.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblPaused, "cell 1 1");
		
		JButton btnResumeGame = new JButton("RESUME GAME");
		btnResumeGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Pause also has resume functionality :)
				oregonTrail.pause();
			}
		});
		btnResumeGame.setForeground(Color.WHITE);
		btnResumeGame.setBackground(Color.DARK_GRAY);
		getContentPane().add(btnResumeGame, "cell 1 2");
		
		JButton btnSaveGame = new JButton("SAVE GAME");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSaveGame.setForeground(Color.WHITE);
		btnSaveGame.setBackground(Color.DARK_GRAY);
		getContentPane().add(btnSaveGame, "cell 1 3");
		
		JButton btnExitGame = new JButton("EXIT GAME");
		btnExitGame.setForeground(Color.WHITE);
		btnExitGame.setBackground(Color.DARK_GRAY);
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Create exit options
				String[] options = new String[3];
				options[0] = "Exit and Save";
				options[1] = "Exit without Saving";
				options[2] = "Cancel";
				String message = "Make sure to save your game before exiting!";
				String title = "Confirm Exit";
				
				// Display dialog for exit options
				int option = JOptionPane.showOptionDialog(PauseDialog.this, message, title, 0, JOptionPane.WARNING_MESSAGE, null, options, null);
				
				// Do the following based on option chosen
				switch (option) {
				case 0: 
					// Exit and save
					System.out.println("exit and save");
					break;
				case 1:
					// Exit without saving
					System.out.println("exit without saving");
					break;
				case 2:
					// Cancel: do nothing
					break;
				}
			}
		});
		getContentPane().add(btnExitGame, "cell 1 4");
	}
}
