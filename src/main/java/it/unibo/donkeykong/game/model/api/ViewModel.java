package it.unibo.donkeykong.game.model.api;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import it.unibo.donkeykong.game.model.impl.ButtonImpl;

/**
 * View model.
 */
public interface ViewModel {

    /**
     * @return all the buttons.
     */
    ArrayList<ButtonImpl> getButtons();

    /**
     * @return a map containing all the rectangles used as buttons (key) and their images (value).
     */
    Map<Rectangle, BufferedImage> getAlternativeButtons();

}
