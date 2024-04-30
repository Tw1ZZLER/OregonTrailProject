package oregonTrail;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

/**
 * KeyEventDispatcher for the whole game
 * Allows you to pause no matter where you are (with some exceptions)
 * @author Corbin Hibler
 * @date 2024-4-25
 * @filename PauseKeyEventDispatcher.java
 */
public class PauseKeyEventDispatcher implements KeyEventDispatcher {
    private OregonTrail oregonTrail;

    /**
     * Constructs a new PauseKeyEventDispatcher with a reference to the OregonTrail instance.
     *
     * @param oregonTrail The OregonTrail instance.
     */
    public PauseKeyEventDispatcher(OregonTrail oregonTrail) {
        this.oregonTrail = oregonTrail;
    }

    /**
     * Dispatches the key event to check for the ESC key being pressed.
     *
     * @param event The KeyEvent to be dispatched.
     * @return true if the ESC key was pressed, false otherwise.
     */
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
