package it.unibo.donkeykong.game.model.api;

import java.awt.Rectangle;

import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;

/**
 * This interface models a button.
 */
public interface Button {
    /**
     * @return a rectangle created with the button top left corner and the button bottom right corner.
     */
    Rectangle getCorners();

    /**
     * Set the game state.
     */
    void applyGamestate();

    /**
     * @return a pair of x pos, y pos.
     */
    Pair<Integer, Integer> getButtonPos();

    /**
     * @return a pair of width, height.
     */
    Pair<Integer, Integer> getButtonDim();

    /**
     * @return the Gamestate of the button.
     */
    Gamestate getButtonGamestate();
}
