package it.unibo.donkeykong.game.model.api;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * View model.
 */
public interface ViewModel {

    /**
     * @return all the buttons.
     */
    Map<Button, BufferedImage> getButtons();

    /**
     * @return a map containing all the rectangles used as buttons (key) and their images (value).
     */
    Map<Rectangle, BufferedImage> getAlternativeButtons();

}
