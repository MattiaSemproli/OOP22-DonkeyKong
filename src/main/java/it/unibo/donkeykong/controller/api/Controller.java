package it.unibo.donkeykong.controller.api;

import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.api.View;

/**
 * Controller interface, models a controller.
 */
public interface Controller {
    /**
     * Tell the model which gamestate to apply.
     * 
     * @param gamestate the new gamestate.
     */
    void applyGamestate(Gamestate gamestate);

    /**
     * Get linked view.
     * 
     * @return the linked view.
     */
    View getView();
}
