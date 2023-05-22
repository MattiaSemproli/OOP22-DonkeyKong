package it.unibo.donkeykong.controller.api;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Map;

import it.unibo.donkeykong.game.model.api.Button;

/**
 *  Generic controller interface. 
 */
public interface GenericController {
 
    /**
     * Method that get all the buttons from the model.
     * 
     * @return a map with all the buttons and their images.
     */
    Map<Button, BufferedImage> getButtonsFromModel();

    /**
     * Method that get all the rectangle used as buttons from the model.
     * 
     * @return a map with all the Rectangle and their images.
     */
    Map<Rectangle, BufferedImage> getAlternativeButtonsFromModel();
}
