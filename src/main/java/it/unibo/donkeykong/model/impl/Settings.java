package it.unibo.donkeykong.model.impl;

import it.unibo.donkeykong.utilities.Gamestate;

/** 
 * Settings model, manages settings logics.
 */
public class Settings {

    public void applyGamestate(final Gamestate gamestate) {
        Gamestate.setGamestate(gamestate);
    }

}
