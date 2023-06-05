package it.unibo.donkeykong.game.model.impl;

import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Endgame or pause model, manages endgame or pause graphics.
 */
public class EndPause {
    
    public void applyGamestate(final Gamestate gamestate) {
        Gamestate.setGamestate(gamestate);
    }
}
