package it.unibo.donkeykong.model.impl;

import it.unibo.donkeykong.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Endgame or pause model, manages endgame or pause graphics.
 */
public class EndPause implements ViewModel {

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyGamestate(final Gamestate gamestate) {
        Gamestate.setGamestate(gamestate);
    }
}
