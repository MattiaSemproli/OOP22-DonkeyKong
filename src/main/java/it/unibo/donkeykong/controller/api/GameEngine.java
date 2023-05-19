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
     * @param g graphic linked to GameEngine.
     */
    void draw(Graphics g);

}
