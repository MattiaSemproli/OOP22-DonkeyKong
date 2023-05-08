package it.unibo.donkeykong.inputs;

import java.awt.event.KeyListener;
import it.unibo.donkeykong.view.ApplicationPanel;
import java.awt.event.KeyEvent;

/**
 * KeyboardInputsImpl class.
 */
public final class KeyboardInputs implements KeyListener {

    private final ApplicationPanel dkPanel;

    /**
     * KeyboardInputsImpl constructor.
     * 
     * @param dkPanel Panel of application.
     */
    public KeyboardInputs(final ApplicationPanel dkPanel) {
        this.dkPanel = dkPanel;
    }

    @Override
    public void keyPressed(final KeyEvent arg0) {
    }

    @Override
    public void keyReleased(final KeyEvent arg0) {
    }

    @Override
    public void keyTyped(final KeyEvent arg0) {
    }
}
