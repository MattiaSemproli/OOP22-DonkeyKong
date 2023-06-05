package it.unibo.donkeykong.model.impl;

import it.unibo.donkeykong.model.api.ViewModel;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Levels menu model, manages levels menu logics.
 */
public class LevelsMenu implements ViewModel {

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyGamestate(final Gamestate gamestate) {
        Gamestate.setGamestate(gamestate);
    }

    /**
     * Set the level to be played.
     * 
     * @param level the level to be played.
     */
    public void setLevel(final CurrentLevel level) {
        CurrentLevel.setCurrentLevel(level);
    }
}
