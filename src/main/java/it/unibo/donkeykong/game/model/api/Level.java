package it.unibo.donkeykong.game.model.api;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Optional;

import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

/**
 * This interface manages levels.
 */
public interface Level {

    /**
     * Get the level data.
     * 
     * @return a map of the level data.
     */
    Map<Pair<Integer, Integer>, Integer> getLevelData();

    /**
     * Get the level sprite.
     * 
     * @param val the index of the sprite.
     * @return the sprite.
     */
    BufferedImage getLevelSprite(int val);

    Optional<Type> getLevelMatrixType (final int x, final int y);
}
