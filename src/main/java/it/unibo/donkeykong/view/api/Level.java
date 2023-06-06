package it.unibo.donkeykong.view.api;

import java.awt.image.BufferedImage;
import java.util.Map;

import it.unibo.donkeykong.utilities.Pair;

/**
 * Level interface, models a level.
 */
public interface Level {
    /**
     * Get the level data.
     * 
     * @return a level data's map.
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
