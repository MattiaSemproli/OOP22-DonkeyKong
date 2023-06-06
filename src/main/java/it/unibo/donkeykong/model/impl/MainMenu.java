package it.unibo.donkeykong.model.impl;

import it.unibo.donkeykong.model.api.ViewModel;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Main menu model, manages main menu logics.
 */
public class MainMenu implements ViewModel {

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyGamestate(final Gamestate gamestate) {
        Gamestate.setGamestate(gamestate);
    }

    /**
     * Set level one.
     */
    public void setLevel() {
        CurrentLevel.setCurrentLevel(CurrentLevel.ONE);
    }
}
