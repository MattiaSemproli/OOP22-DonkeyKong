package it.unibo.donkeykong.view.api;

import java.awt.Rectangle;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Button interface, models a button.
 */
public interface Button {
    /**
     * Get the button corners.
     * 
     * @return a rectangle created with the top left corner and the bottom right corner.
     */
    Rectangle getCorners();

    /**
     * Get button position.
     * 
     * @return a pair of x, y pos.
     */
    Pair<Integer, Integer> getButtonPos();

    /**
     * Get button dimension.
     * 
     * @return a pair of width, height.
     */
    Pair<Integer, Integer> getButtonDim();

    /**
     * Get button game state.
     * 
     * @return the button's game state.
     */
    Gamestate getButtonGamestate();
}
