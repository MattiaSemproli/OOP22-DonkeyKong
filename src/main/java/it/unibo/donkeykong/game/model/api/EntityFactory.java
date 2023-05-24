package it.unibo.donkeykong.game.model.api;

import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.utilities.Pair;

/**
 * This interface manages the creation of different entities.
 */
public interface EntityFactory {

    /**
     * Create a player.
     * 
     * @param position starting position of the entity.
     * @return a player type entity.
     */
    Entity generatePlayer(Pair<Float, Float> position);

    /**
     * Create a barrel.
     * 
     * @param position starting position of the entity.
     * @return a barrel type entity.
     */
    Entity generateBarrel(Pair<Float, Float> position);

    /**
     * Create a monkey.
     * 
     * @param position starting position of the entity.
     * @return a monkey type entity.
     */
    Entity generateMonkey(Pair<Float, Float> position);

    /**
     * Create a princess.
     * 
     * @param position starting position of the entity.
     * @return a princess type entity.
     */
    Entity generatePrincess(Pair<Float, Float> position);

    /**
     * Create a block.
     * 
     * @param position starting position of the entity.
     * @return a platform block type entity.
     */
    Entity generateBlock(Pair<Float, Float> position);

    /**
     * Create a ladder.
     * 
     * @param position starting position of the entity.
     * @return a ladder type entity.
     */
    Entity generateLadder(Pair<Float, Float> position);

    /**
     * Create a block with upper ladder.
     * 
     * @param position starting position of the entity.
     * @return a block with upper ladder type entity.
     */
    Entity generateBlockWithUpLadder(Pair<Float, Float> position);

    /**
     * Create a block with lower ladder.
     * 
     * @param position starting position of the entity.
     * @return a block with upper lower type entity.
     */
    Entity generateBlockWithDownLadder(Pair<Float, Float> position);

    /**
     * Create a block with both ladder.
     * 
     * @param position starting position of the entity.
     * @return a block with both ladder type entity.
     */
    Entity generateBlockWithUpDownLadder(Pair<Float, Float> position);

    /**
     * Create a heart powerup.
     * 
     * @param position starting position of the entity.
     * @return a powerup heart entity.
     */
    Entity generateHeartPowerUp(Pair<Float, Float> position);

    /**
     * Create a shield powerup.
     * 
     * @param position starting position of the entity.
     * @return a powerup shield entity.
     */
    Entity generateShieldPowerUp(Pair<Float, Float> position);

    /**
     * Create a star powerup.
     * 
     * @param position starting position of the entity.
     * @return a powerup star entity.
     */
    Entity generateStarPowerUp(Pair<Float, Float> position);

    /**
     * Create a snowflake powerup.
     * 
     * @param position starting position of the entity.
     * @return a powerup snowflake entity.
     */
    Entity generateSnowflakePowerUp(Pair<Float, Float> position);
}
