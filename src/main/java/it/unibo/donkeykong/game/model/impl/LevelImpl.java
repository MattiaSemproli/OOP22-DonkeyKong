package it.unibo.donkeykong.game.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.image.BufferedImage;

import it.unibo.donkeykong.game.model.api.Level;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;
import it.unibo.donkeykong.utilities.Pair;
import static it.unibo.donkeykong.utilities.Constants.Window.TILES_DEFAULT_SIZE;
import static it.unibo.donkeykong.utilities.Constants.Level.levelSprites;
import static it.unibo.donkeykong.utilities.Constants.Level.levelSpritesLength;

public class LevelImpl implements Level {

    private final Map<Pair<Integer,Integer>, Integer> levelData;
    private final BufferedImage[] spritesArray = new BufferedImage[levelSpritesLength];

    public LevelImpl(final String levelSpriteName) {
        this.levelData = createLevel(levelSpriteName);
        this.importLevelSprites();
    }

    private Map<Pair<Integer,Integer>, Integer> createLevel(final String levelSpriteName) {
        return new HashMap<>(){{
            BufferedImage img = ResourceFuncUtilities.getSources("level_one_data.png");
            for(int r = 0; r < img.getHeight(); r++) {
                for(int c = 0; c < img.getWidth(); c++) {
                    Color color = new Color(img.getRGB(c, r));
                    int value = color.getRed();
                    if(value >= 3) {
                        value = 0;
                    }
                    put(new Pair<>(c, r), value);
                }
            }
        }};
    }

    private void importLevelSprites(){
        BufferedImage img = ResourceFuncUtilities.getSources(levelSprites);
        for(int i = 0; i < levelSpritesLength; i++) {
            spritesArray[i] = img.getSubimage(i * TILES_DEFAULT_SIZE, 0, TILES_DEFAULT_SIZE, TILES_DEFAULT_SIZE);
        }
    }

    @Override
    public BufferedImage getLevelSprite(final int val) {
        return this.spritesArray[val];
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getLevelData() {
        return this.levelData;
    }
    
}
