package it.unibo.donkeykong.model.api;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.ecs.api.Entity;

/**
 * EntityFactory interface, manages creation of different entities.
 */
public interface EntityFactory {
    /**
     * Create a player.
     * 
     * @param position starting entity's position.
     * @return a player entity.
     */
    Entity generatePlayer(Pair<Float, Float> position);

    /**
     * Create a barrel.
     * 
     * @param position starting entity's position.
     * @return a barrel entity.
     */
    Entity generateBarrel(Pair<Float, Float> position);

    /**
     * Create a monkey.
     * 
     * @param position starting entity's position.
     * @return a monkey entity.
     */
    Entity generateMonkey(Pair<Float, Float> position);

    /**
     * Create a princess.
     * 
     * @param position starting entity's position.
     * @return a princess entity.
     */
    Entity generatePrincess(Pair<Float, Float> position);

    /**
     * Create a block.
     * 
     * @param position starting entity's position.
     * @return a platform block entity.
     */
    Entity generateBlock(Pair<Float, Float> position);

    /**
     * Create a ladder.
     * 
     * @param position starting entity's position.
     * @return a ladder entity.
     */
    Entity generateLadder(Pair<Float, Float> position);

    /**
     * Create a block with upper ladder.
     * 
     * @param position starting entity's position.
     * @return a block with upper ladder entity.
     */
    Entity generateBlockWithUpLadder(Pair<Float, Float> position);

    /**
     * Create a block with lower ladder.
     * 
     * @param position starting entity's position.
     * @return a block with upper lower entity.
     */
    Entity generateBlockWithDownLadder(Pair<Float, Float> position);

    /**
     * Create a block with both ladder.
     * 
     * @param position starting entity's position.
     * @return a block with both ladder entity.
     */
    Entity generateBlockWithUpDownLadder(Pair<Float, Float> position);

    /**
     * Create a heart powerup.
     * 
     * @param position starting entity's position.
     * @return a powerup heart entity.
     */
    Entity generateHeartPowerUp(Pair<Float, Float> position);

    /**
     * Create a shield powerup.
     * 
     * @param position starting entity's position.
     * @return a powerup shield entity.
     */
    Entity generateShieldPowerUp(Pair<Float, Float> position);

    /**
     * Create a star powerup.
     * 
     * @param position starting entity's position.
     * @return a powerup star entity.
     */
    Entity generateStarPowerUp(Pair<Float, Float> position);

    /**
     * Create a snowflake powerup.
     * 
     * @param position starting entity's position.
     * @return a powerup snowflake entity.
     */
    Entity generateSnowflakePowerUp(Pair<Float, Float> position);
}
