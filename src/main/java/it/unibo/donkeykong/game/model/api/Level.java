package it.unibo.donkeykong.game.model.api;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Optional;

import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

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

    /**
     * Get the type of a block in a x, y position of the matrix.
     * 
     * @param x the x coordinate of the matrix.
     * @param y the y coordinate of the matrix.
     * @return the type of the matrix if present.
     */
    Optional<Type> getLevelMatrixType(int x, int y);
}
