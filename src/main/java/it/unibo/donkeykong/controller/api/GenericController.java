package it.unibo.donkeykong.controller.api;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Map;

import it.unibo.donkeykong.game.model.api.Button;

public interface GenericController {
 
    Map<Button, BufferedImage> getButtonsFromModel();

    Map<Rectangle, BufferedImage> getAlternativeButtonsFromModel();
}
