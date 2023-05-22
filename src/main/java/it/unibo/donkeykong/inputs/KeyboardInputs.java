package it.unibo.donkeykong.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * KeyboardInputsImpl class.
 */
public final class KeyboardInputs implements KeyListener {

    private final Application dkApp;

    /**
     * KeyboardInputsImpl constructor.
     * 
     * @param dkApp Panel of application.
     */
    public KeyboardInputs(final Application dkApp) {
        this.dkApp = dkApp;
    }

    @Override
    public void keyPressed(final KeyEvent key) {
        switch (Gamestate.getGamestate()) {
            case PLAYING:
                this.dkApp.getGameController().keyPressed(key);
                break;
            case PAUSE:
                this.dkApp.getPauseController().keyPressed(key);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(final KeyEvent key) {
        switch (Gamestate.getGamestate()) {
            case PLAYING:
                this.dkApp.getGameController().keyReleased(key);
                break;
            case PAUSE:
                this.dkApp.getPauseController().keyReleased(key);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(final KeyEvent key) {
    }
}
