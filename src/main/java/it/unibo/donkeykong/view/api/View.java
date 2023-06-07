package it.unibo.donkeykong.view.api;

import java.awt.Graphics;

import it.unibo.donkeykong.common.Pair;

/**
 * View interface, models a view.
 */
public interface View {
    /**
     * Draw the view.
     * 
     * @param g the graphics.
     */
    void draw(Graphics g);

    /**
     * Handle mouse pressed.
     * 
     * @param pt the point where the mouse is pressed.
     */
    void mousePressed(Pair<Integer, Integer> pt);

    /**
     * Handle key pressed.
     * 
     * @param keyCode the int code of pressed key.
     */
    void keyPressed(int keyCode);

    /**
     * Handle key released.
     * 
     * @param keyCode the int code of released key.
     */
    void keyReleased(int keyCode);
}
