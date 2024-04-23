package oregonTrail;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PauseKeyListener implements KeyListener {
	private OregonTrail oregonTrail;
	public PauseKeyListener(OregonTrail oregonTrail) {
		this.oregonTrail = oregonTrail;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			oregonTrail.pause();
			System.out.println("ESCAPE PRESSED");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
