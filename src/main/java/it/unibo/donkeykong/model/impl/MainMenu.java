package it.unibo.donkeykong.model.impl;

import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Main menu model, manages main menu logics.
 */
public class MainMenu {

    public void applyGamestate(final Gamestate gamestate) {
        Gamestate.setGamestate(gamestate);
    }
    
    public void startLevel() {
        CurrentLevel.setCurrentLevel(CurrentLevel.ONE);
    }
}
