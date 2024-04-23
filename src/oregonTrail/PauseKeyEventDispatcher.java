package oregonTrail;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class PauseKeyEventDispatcher implements KeyEventDispatcher {
	private OregonTrail oregonTrail;
	
	public PauseKeyEventDispatcher(OregonTrail oregonTrail) {
		this.oregonTrail = oregonTrail;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getID() == KeyEvent.KEY_PRESSED) {			
			if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
				oregonTrail.pause();
				System.out.println("ESCAPE PRESSED");
				return true;
			}
		}
		return false;
	}

}
