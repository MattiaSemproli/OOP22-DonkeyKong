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
    public void mouseClicked(final MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(final MouseEvent arg0) {
    }

    @Override
    public void mouseExited(final MouseEvent arg0) {
    }

    @Override
    public void mousePressed(final MouseEvent arg0) {
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
    public void mouseReleased(final MouseEvent arg0) {
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
}
