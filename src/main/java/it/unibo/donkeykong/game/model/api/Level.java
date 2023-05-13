package it.unibo.donkeykong.game.model.api;

import java.awt.image.BufferedImage;
import java.util.Map;

import it.unibo.donkeykong.utilities.Pair;

public interface Level {
    
    Map<Pair<Integer,Integer>, Integer> getLevelData();

    BufferedImage getLevelSprite(int val);
}
