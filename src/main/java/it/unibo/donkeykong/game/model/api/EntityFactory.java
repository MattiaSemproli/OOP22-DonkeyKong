package it.unibo.donkeykong.game.model.api;

/**
 * This interface manages the creation of different entities.
 */
public interface EntityFactory {

    /**
     * Create a player.
     * @return a player type entity.
     */
    Entity generatePlayer();

    /**
     * Create a barrel.
     * @return a barrel type entity.
     */
    Entity generateBarrel();

    /**
     * Create a monkey.
     * @return a monkey type entity.
     */
    Entity generateMonkey();

    /**
     * Create a princess.
     * @return a princess type entity.
     */
    Entity generatePrincess();

    /**
     * Create a power up.
     * @return a power up type entity. 
     */
    Entity generatePowerUp();
}
