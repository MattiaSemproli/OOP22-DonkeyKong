package it.unibo.donkeykong.game.model.api;

import java.util.ArrayList;

import it.unibo.donkeykong.controller.impl.GameController;

/**
 * Gameplay interface, models Gameplay management.
 */
public interface Gameplay {

    /**
     * Get entities of Gameplay.
     * 
     * @return list of entities.
     */
    ArrayList<Entity> getEntities();

    /**
     * Get GameController.
     * 
     * @return linked GameController.
     */
    GameController getController();
}
