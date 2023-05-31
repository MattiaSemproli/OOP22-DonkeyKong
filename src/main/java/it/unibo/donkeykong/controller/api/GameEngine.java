package it.unibo.donkeykong.controller.api;

import java.awt.Graphics;

/**
 * GameEngine interface, models gameloop.
 */
public interface GameEngine {

    /**
     * Updating method.
     */
    void update();

    /**
     * Drawing method.
     * 
     * @param g the linked graphic.
     */
    void draw(Graphics g);

}
