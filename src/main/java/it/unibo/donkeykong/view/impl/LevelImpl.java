package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.levelFour;
import static it.unibo.donkeykong.utilities.Constants.Level.levelOne;
import static it.unibo.donkeykong.utilities.Constants.Level.levelSpritesLength;
import static it.unibo.donkeykong.utilities.Constants.Level.levelThree;
import static it.unibo.donkeykong.utilities.Constants.Level.levelTwo;
import static it.unibo.donkeykong.utilities.ResourceFuncUtilities.loadSources;
import static it.unibo.donkeykong.utilities.ViewConstants.Window.TILES_DEFAULT_SIZE;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.view.api.Level;

/**
 * Level class, manages a level.
 */
public class LevelImpl implements Level {

    private final Map<Pair<Integer, Integer>, Integer> levelData;
    private final BufferedImage[] spritesArray = new BufferedImage[levelSpritesLength];
    private final String levelSpriteName;

    /**
     * Constructor.
     */
    public LevelImpl() {
        switch (CurrentLevel.getCurrentLevel()) {
            case ONE:
                this.levelSpriteName = levelOne;
                break;
            case TWO:
                this.levelSpriteName = levelTwo;
                break;
            case THREE:
                this.levelSpriteName = levelThree;
                break;
            case FOUR:
            default:
                this.levelSpriteName = levelFour;
                break;
        }
        this.levelData = createLevel();
        this.importLevelSprites();
    }

    private Map<Pair<Integer, Integer>, Integer> createLevel() {
        final Map<Pair<Integer, Integer>, Integer> level = new HashMap<>();
        final BufferedImage img = loadSources("level_" + levelSpriteName + "_data");
        for (int r = 0; r < img.getHeight(); r++) {
            for (int c = 0; c < img.getWidth(); c++) {
                final Color color = new Color(img.getRGB(c, r));
                int value = color.getRed();
                if (value >= levelSpritesLength) {
                    value = 0;
                }
                level.put(new Pair<>(c, r), value);
            }
        }
        return level;
    }

    private void importLevelSprites() {
        final BufferedImage img = loadSources("platform_ladder_" + levelSpriteName);
        for (int i = 0; i < levelSpritesLength; i++) {
            spritesArray[i] = img.getSubimage(i * TILES_DEFAULT_SIZE, 0, TILES_DEFAULT_SIZE, TILES_DEFAULT_SIZE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getLevelSprite(final int val) {
        return this.spritesArray[val];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Pair<Integer, Integer>, Integer> getLevelData() {
        return new HashMap<>(this.levelData);
    }
}
