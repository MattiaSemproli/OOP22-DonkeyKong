package it.unibo.donkeykong.game.model.api;

import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

/**
 * This interface manages the creation of different entities.
 */
public interface EntityFactory {

    /**
     * Create a player.
     * 
     * @return a player type entity.
     */
    Entity generatePlayer(final Pair<Float, Float> position);

    /**
     * Create a barrel.
     * 
     * @return a barrel type entity.
     */
    Entity generateBarrel(Pair<Float, Float> position);

    /**
     * Create a monkey.
     * 
     * @return a monkey type entity.
     */
    Entity generateMonkey();

    /**
     * Create a princess.
     * 
     * @return a princess type entity.
     */
    Entity generatePrincess();

    /**
     * Create a power up.
     * 
     * @return a power up type entity.
     */
    Entity generatePowerUp();
}
