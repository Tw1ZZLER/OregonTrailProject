package oregonTrail;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import oregonTrail.panel.HuntingPanel;
/**
 * KeyEventDispatcher for the hunting minigame
 * Allows you to execute the controls of the hunting minigame
 * @author Ray Otto
 * @date 2024-5-8
 * @filename PauseKeyEventDispatcher.java
 */
public class HuntingKeyEventDispatcher implements KeyEventDispatcher {
    private HuntingPanel huntingPanel;

    /**
     * Constructs a new HuntingKeyEventDispatcher with a reference to the OregonTrail instance.
     *
     * @param oregonTrail The OregonTrail instance.
     */
    public HuntingKeyEventDispatcher(HuntingPanel huntingPanel) {
        this.huntingPanel = huntingPanel;
    }
    /**
     * Dispatches the key event to check for the A,D,and SPACE key being pressed.
     *
     * @param event The KeyEvent to be dispatched.
     * @return true if any of these key were pressed, false otherwise.
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_SPACE:
                    huntingPanel.block();
                    break;
                case KeyEvent.VK_A:
                    huntingPanel.leftPunch();
                    break;
                case KeyEvent.VK_D:
                    huntingPanel.rightPunch();
                    break;
            }
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            huntingPanel.unblock();
        }
        return false;
    }
}
