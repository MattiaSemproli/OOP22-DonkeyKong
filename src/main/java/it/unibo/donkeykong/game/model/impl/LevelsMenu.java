package it.unibo.donkeykong.game.model.impl;

import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Levels menu model, manages levels menu logics.
 */
public class LevelsMenu {

    public void applyGamestate(final Gamestate gamestate) {
        Gamestate.setGamestate(gamestate);
    }

    public void setLevel(final CurrentLevel level) {
        CurrentLevel.setCurrentLevel(level);
    }
}
