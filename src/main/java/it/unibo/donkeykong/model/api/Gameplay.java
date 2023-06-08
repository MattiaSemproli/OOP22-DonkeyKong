package it.unibo.donkeykong.model.api;

import java.util.List;
import java.util.Map;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.utilities.Type;

/**
 * Gameplay interface, models a gameplay.
 */
public interface Gameplay {
    /**
     * Get entities of gameplay.
     * 
     * @return a list of entities.
     */
    List<Entity> getEntities();

    /**
     * Add entity to the game.
     * 
     * @param entity the entity to be added.
     */
    void addEntity(Entity entity);

    /**
     * Remove entity from game.
     * 
     * @param entity the entity to be removed.
     */
    void removeEntity(Entity entity);

    /**
     * Create a barrel entity.
     *
     * @param position the new barrel's starting position.
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
     * Initialize game's entitities.
     * 
     * @param levelMap the linked level map scheme.
     */
    void initializeGame(Map<Pair<Integer, Integer>, Integer> levelMap);

    /**
     * Every three seconds move randomly most powerful powerups.
     */
    void moveOpPowerUpRandom();

    /**
     * After ten seconds from game's start, spawn op powerups.
     */
    void spawnOpPowerUp();

    /**
     * Check if op powerups are spawned.
     * 
     * @return true if op powerups are spawned.
     */
    boolean isSpawnedOpPowerUp();

    /**
     * Get active powerups.
     * 
     * @return a list of active powerups.
     */
    List<Type> getActivePowerUps();

    /**
     * Reset input keys list.
     */
    void resetKeys();

    /**
     * Update input keys list with pressed key.
     * 
     * @param keyCode the pressed key.
     */
    void updateKeyPressed(int keyCode);

    /**
     * Remove released key from input keys list.
     * 
     * @param keyCode the released key.
     */
    void updateKeyReleased(int keyCode);

    /**
     * Reset input keys list when window focus is lost.
     */
    void resetKeysOnFocusLost();

    /**
     * Get input keys list.
     * 
     * @return the list of pressed keys.
     */
    List<Integer> getInputs();

    /**
     * Stop timer when player wins or loses.
     */
    void stopTimer();

    /**
     * Set player's win.
     */
    void setWin();

    /**
     * Get if player won.
     * 
     * @return true if player won.
     */
    boolean isWin();

    /**
     * Check if game is over.
     */
    void checkIsOver();

    /**
     * Get if game is over.
     * 
     * @return true if game is over.
     */
    boolean isOver();
}
