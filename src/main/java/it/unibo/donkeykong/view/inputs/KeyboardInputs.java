package it.unibo.donkeykong.view.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * KeyboardInputs class, manages keyboard's inputs.
 */
public final class KeyboardInputs implements KeyListener {

    private final Application dkApp;

    /**
     * Constructor.
     * 
     * @param dkApp the linked application.
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
            default:
                break;
        }
    }

    @Override
    public void keyTyped(final KeyEvent key) {
    }
}
