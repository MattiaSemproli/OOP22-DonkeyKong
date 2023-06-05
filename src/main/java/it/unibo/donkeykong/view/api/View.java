package it.unibo.donkeykong.view.api;

import it.unibo.donkeykong.utilities.Pair;

/**
 * View interface, models a view.
 */
public interface View {
    /**
     * Handle mouse pressed.
     * 
     * @param pt the point where the mouse is pressed.
     */
    void mousePressed(final Pair<Integer,Integer> pt);
}
