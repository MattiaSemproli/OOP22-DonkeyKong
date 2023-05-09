package it.unibo.donkeykong.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.ApplicationPanel;

/**
 * MouseInputsImpl class.
 */
public class MouseInputs implements MouseListener {

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
                break;
            case SETTINGS:
                break;
            case PLAYING:
                break;
            case PAUSE:
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
                break;
            case SETTINGS:
                break;
            case PLAYING:
                break;
            case PAUSE:
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
