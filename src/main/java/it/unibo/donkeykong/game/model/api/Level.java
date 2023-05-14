package it.unibo.donkeykong.game.model.api;

import java.awt.image.BufferedImage;
import java.util.Map;

import it.unibo.donkeykong.utilities.Pair;

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
}
