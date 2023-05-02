package it.unibo.donkeykong.game.model.api;

import java.util.ArrayList;

import it.unibo.donkeykong.utilities.Pair;

/**
 * This interface models a button.
 */
public interface Button {

    /**
     * @return a boolean if the mouse is over the button.
     */
    boolean isMouseOver();

    /**
     * @return a boolean if the button is pressed.
     */
    boolean isMousePressed();

    /**
     * @return an array of pairs containing top-left and bottom-right corners.
     */
    ArrayList<Pair<Integer, Integer>> getCorners();

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
}
