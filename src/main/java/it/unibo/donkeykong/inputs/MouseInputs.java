package it.unibo.donkeykong.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.ApplicationPanel;

/**
 * MouseInputsImpl class.
 */
public final class MouseInputs implements MouseListener {

    private final ApplicationPanel dkPanel;

    /**
     * MouseInputsImpl constructor.
     * 
     * @param dkPanel Panel of application.
     */
    public MouseInputs(final ApplicationPanel dkPanel) {
        this.dkPanel = dkPanel;
    }

    @Override
    public void mousePressed(final MouseEvent mouseKey) {
        switch (Gamestate.getGamestate()) {
            case MENU:
                this.dkPanel.getApplication().getMainMenuController().mousePressed(mouseKey);
                break;
            case CHOSING_LEVELS:
                this.dkPanel.getApplication().getLevelsMenuController().mousePressed(mouseKey);
                break;
            case SETTINGS:
                this.dkPanel.getApplication().getSettingsController().mousePressed(mouseKey);
                break;
            case PLAYING:
                this.dkPanel.getApplication().getGameController().mousePressed(mouseKey);
                break;
            case PAUSE:
                this.dkPanel.getApplication().getPauseController().mousePressed(mouseKey);
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
                this.dkPanel.getApplication().getMainMenuController().mouseReleased(mouseKey);
                break;
            case CHOSING_LEVELS:
                this.dkPanel.getApplication().getLevelsMenuController().mouseReleased(mouseKey);
                break;
            case SETTINGS:
                this.dkPanel.getApplication().getSettingsController().mouseReleased(mouseKey);
                break;
            case PLAYING:
                this.dkPanel.getApplication().getGameController().mouseReleased(mouseKey);
                break;
            case PAUSE:
                this.dkPanel.getApplication().getPauseController().mouseReleased(mouseKey);
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
