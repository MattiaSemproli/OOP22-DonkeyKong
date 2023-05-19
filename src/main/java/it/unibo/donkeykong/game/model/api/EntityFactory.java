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
    Entity generateMonkey(final Pair<Float, Float> position);

    /**
     * Create a princess.
     * 
     * @return a princess type entity.
     */
    Entity generatePrincess(final Pair<Float, Float> position);

    /**
     * Create a power up.
     * 
     * @return a power up type entity.
     */
    Entity generatePowerUp();

    /**
     * Create a block.
     * 
     * @return a platform block type entity.
     */
    Entity generateBlock(final Pair<Float, Float> position);

    /**
     * Create a ladder.
     * 
     * @return a ladder type entity.
     */
    Entity generateLadder(final Pair<Float, Float> position);

    /**
     * Create a block with upper ladder.
     * 
     * @return a block with upper ladder type entity.
     */
    Entity generateBlockWithUpLadder(final Pair<Float, Float> position);

    /**
     * Create a block with lower ladder.
     * 
     * @return a block with upper lower type entity.
     */
    Entity generateBlockWithDownLadder(final Pair<Float, Float> position);

    /**
     * Create a block with both ladder.
     * 
     * @return a block with both ladder type entity.
     */
    Entity generateBlockWithUpDownLadder(final Pair<Float, Float> position);
}
