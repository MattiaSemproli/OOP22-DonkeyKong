package it.unibo.donkeykong.game.model.api;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * ViewModel interface, models button functions for view.
 */
public interface ViewModel {

    /**
     * Get all the buttons to be displayed.
     * 
     * @return a map of buttons and images.
     */
    Map<Button, BufferedImage> getButtons();

    /**
     * Get all the alternative buttons to be displayed.
     * 
     * @return a map of rectangles used as buttons and images.
     */
    Map<Rectangle, BufferedImage> getAlternativeButtons();

}
