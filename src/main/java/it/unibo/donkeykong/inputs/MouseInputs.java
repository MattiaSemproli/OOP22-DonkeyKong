package it.unibo.donkeykong.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * MouseInputsImpl class.
 */
public final class MouseInputs implements MouseListener {

    private final Application dkApp;

    /**
     * MouseInputsImpl constructor.
     * 
     * @param dkApp Panel of application.
     */
    public MouseInputs(final Application dkApp) {
        this.dkApp = dkApp;
    }

    @Override
    public void mousePressed(final MouseEvent mouseKey) {
        switch (Gamestate.getGamestate()) {
            case MENU:
                this.dkApp.getMainMenuController().mousePressed(mouseKey);
                break;
            case CHOSING_LEVELS:
                this.dkApp.getLevelsMenuController().mousePressed(mouseKey);
                break;
            case SETTINGS:
                this.dkApp.getSettingsController().mousePressed(mouseKey);
                break;
            case PLAYING:
                this.dkApp.getGameController().mousePressed(mouseKey);
                break;
            case PAUSE:
                this.dkApp.getPauseController().mousePressed(mouseKey);
                break;
            case WIN:
            case DEATH:
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(final MouseEvent mouseKey) {
        switch (Gamestate.getGamestate()) {
            case MENU:
                this.dkApp.getMainMenuController().mouseReleased(mouseKey);
                break;
            case CHOSING_LEVELS:
                this.dkApp.getLevelsMenuController().mouseReleased(mouseKey);
                break;
            case SETTINGS:
                this.dkApp.getSettingsController().mouseReleased(mouseKey);
                break;
            case PLAYING:
                this.dkApp.getGameController().mouseReleased(mouseKey);
                break;
            case PAUSE:
                this.dkApp.getPauseController().mouseReleased(mouseKey);
                break;
            case WIN:
            case DEATH:
                break;
            default:
                break;
        }
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
