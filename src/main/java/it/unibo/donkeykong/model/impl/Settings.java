package it.unibo.donkeykong.model.impl;

import it.unibo.donkeykong.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Gamestate;

/** 
 * Settings model, manages settings logics.
 */
public class Settings implements ViewModel {

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyGamestate(final Gamestate gamestate) {
        Gamestate.setGamestate(gamestate);
    }

}
