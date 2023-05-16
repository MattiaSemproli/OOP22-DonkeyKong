package it.unibo.donkeykong.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.ApplicationPanel;

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
    public void keyPressed(final KeyEvent key) {
        switch (Gamestate.getGamestate()) {
            case PLAYING:
                this.dkPanel.getApplication().getGameController().keyPressed(key);
                break;
            case PAUSE:
                this.dkPanel.getApplication().getPauseController().keyPressed(key);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(final KeyEvent key) {
        switch (Gamestate.getGamestate()) {
            case PLAYING:
                this.dkPanel.getApplication().getGameController().keyReleased(key);
                break;
            case PAUSE:
                this.dkPanel.getApplication().getPauseController().keyReleased(key);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(final KeyEvent key) {
    }
}
