package it.unibo.donkeykong.view.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;

/**
 * MouseInputs class, manages mouse's inputs.
 */
public final class MouseInputs implements MouseListener {

    private final Application dkApp;

    /**
     * Constructor.
     * 
     * @param dkApp the linked application.
     */
    public MouseInputs(final Application dkApp) {
        this.dkApp = dkApp;
    }

    @Override
    public void mousePressed(final MouseEvent mouseKey) {
        switch (Gamestate.getGamestate()) {
            case MENU:
                this.dkApp.getMainMenuController().getView().mousePressed(new Pair<>(mouseKey.getX(), mouseKey.getY()));
                break;
            case CHOSING_LEVELS:
                this.dkApp.getLevelsMenuController().getView().mousePressed(new Pair<>(mouseKey.getX(), mouseKey.getY()));
                break;
            case SETTINGS:
                this.dkApp.getSettingsController().getView().mousePressed(new Pair<>(mouseKey.getX(), mouseKey.getY()));
                break;
            case PLAYING:
                this.dkApp.getGameController().getView().mousePressed(new Pair<>(mouseKey.getX(), mouseKey.getY()));
                break;
            case PAUSE:
            case WIN:
            case DEATH:
                this.dkApp.getEndPauseController().getView().mousePressed(new Pair<>(mouseKey.getX(), mouseKey.getY()));
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(final MouseEvent mouseKey) {
    }

    @Override
    public void mouseClicked(final MouseEvent mouseKey) {
    }

    @Override
    public void mouseEntered(final MouseEvent mouseKey) {
    }

    @Override
    public void mouseExited(final MouseEvent mouseKey) {
    }
}
