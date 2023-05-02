package it.unibo.donkeykong.game.model.api;

/**
 * This interface manages the creation of different entities.
 */
public interface EntityFactory {

    /**
     * Create a player.
     */
    Entity generatePlayer();

    /**
     * Create a barrel.
     */
    Entity generateBarrel();

    /**
     * Create a monkey.
     */
    Entity generateMonkey();

    /**
     * Create a princess.
     */
    Entity generatePrincess();

    /**
     * Create a power up.
     */
    Entity generatePowerUp();
}
