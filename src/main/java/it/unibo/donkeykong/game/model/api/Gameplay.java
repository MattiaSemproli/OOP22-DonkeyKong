package it.unibo.donkeykong.game.model.api;

import java.util.List;

import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.utilities.Pair;

/**
 * Gameplay interface, models Gameplay management.
 */
public interface Gameplay {

    /**
     * Get entities of Gameplay.
     * 
     * @return list of entities.
     */
    List<Entity> getEntities();

    /**
     * Remove entity from game.
     * 
     * @param entity linked entity to be removed.
     */
    void removeEntity(Entity entity);

    /**
     * Get GameController.
     * 
     * @return linked GameController.
     */
    GameController getController();

    /**
     * Create a barrel entity.
     *
     * @param position new barrel starting position.
     */
    void throwBarrel(Pair<Float, Float> position);

    /**
     * If player lose all lives or he falls out of map, remove him.
     */
    void removePlayer();

    /**
     * Remove all barrels from game.
     */
    void removeAllBarrels();

    /**
     * Initialize entitities of game.
     */
    void initializeGame();
}
