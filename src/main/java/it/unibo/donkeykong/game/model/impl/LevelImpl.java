package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.levelOne;
import static it.unibo.donkeykong.utilities.Constants.Level.levelSprites;
import static it.unibo.donkeykong.utilities.Constants.Level.levelSpritesLength;
import static it.unibo.donkeykong.utilities.Constants.Window.TILES_DEFAULT_SIZE;
import static it.unibo.donkeykong.utilities.ResourceFuncUtilities.loadSources;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.game.model.api.Level;
import it.unibo.donkeykong.utilities.Pair;

/**
 * This class manages a new level.
 */
public class LevelImpl implements Level {

    private final Map<Pair<Integer, Integer>, Integer> levelData;
    private final BufferedImage[] spritesArray = new BufferedImage[levelSpritesLength];

    /**
     * Constructor.
     * 
     * @param levelSpriteName the level to be created.
     */
    public LevelImpl(final String levelSpriteName) {
        this.levelData = createLevel(levelSpriteName);
        this.importLevelSprites();
    }

    private Map<Pair<Integer, Integer>, Integer> createLevel(final String levelSpriteName) {
        return new HashMap<>() {
            {
                BufferedImage img = loadSources(levelOne);
                for (int r = 0; r < img.getHeight(); r++) {
                    for (int c = 0; c < img.getWidth(); c++) {
                        Color color = new Color(img.getRGB(c, r));
                        int value = color.getRed();
                        if (value >= levelSpritesLength) {
                            value = 0;
                        }
                        put(new Pair<>(c, r), value);
                    }
                }
            }
        };
    }

    private void importLevelSprites() {
        BufferedImage img = loadSources(levelSprites);
        for (int i = 0; i < levelSpritesLength; i++) {
            spritesArray[i] = img.getSubimage(i * TILES_DEFAULT_SIZE, 0, TILES_DEFAULT_SIZE, TILES_DEFAULT_SIZE);
        }
    }

    @Override
    public final BufferedImage getLevelSprite(final int val) {
        return this.spritesArray[val];
    }

    @Override
    public final Map<Pair<Integer, Integer>, Integer> getLevelData() {
        return new HashMap<>(this.levelData);
    }

}
