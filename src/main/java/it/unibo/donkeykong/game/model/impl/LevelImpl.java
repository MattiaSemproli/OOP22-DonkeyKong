package it.unibo.donkeykong.game.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.image.BufferedImage;

import it.unibo.donkeykong.game.model.api.Level;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;
import it.unibo.donkeykong.utilities.Pair;

public class LevelImpl implements Level {

    private final Map<Pair<Integer,Integer>, Integer> levelData;

    public LevelImpl(final String fileName) {
        this.levelData = createLevel(fileName);
    }

    private Map<Pair<Integer,Integer>, Integer> createLevel(final String fileName) {
        return new HashMap<>(){{
            BufferedImage img = ResourceFuncUtilities.getSources("level_one_data.png");
            for(int r = 0; r < img.getHeight(); r++) {
                for(int c = 0; c < img.getWidth(); c++) {
                    Color color = new Color(img.getRGB(c, r));
                    int value = color.getRed();
                    if(value >= 3) {
                        value = 0;
                    }
                    put(new Pair<>(r, c), value);
                }
            }
        }};
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getLevelData() {
        return this.levelData;
    }
    
}
