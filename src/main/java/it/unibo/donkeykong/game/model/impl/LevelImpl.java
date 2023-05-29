package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.levelSpritesLength;
import static it.unibo.donkeykong.utilities.Constants.Window.TILES_DEFAULT_SIZE;
import static it.unibo.donkeykong.utilities.ResourceFuncUtilities.loadSources;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.donkeykong.game.model.api.Level;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

import static it.unibo.donkeykong.utilities.Constants.Level.levelOne;
import static it.unibo.donkeykong.utilities.Constants.Level.levelTwo;
import static it.unibo.donkeykong.utilities.Constants.Level.levelThree;

/**
 * This class manages a new level.
 */
public class LevelImpl implements Level {

    private final Map<Pair<Integer, Integer>, Integer> levelData;
    private final BufferedImage[] spritesArray = new BufferedImage[levelSpritesLength];
    private final String levelSpriteName;

    /**
     * Constructor.
     * 
     * @param levelSpriteName the level to be created.
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
            default:
                this.levelSpriteName = levelThree;
                break;
        }
        this.levelData = createLevel();
        this.importLevelSprites();
    }

    private Map<Pair<Integer, Integer>, Integer> createLevel() {
        return new HashMap<>() {{
                final BufferedImage img = loadSources("level_" + levelSpriteName + "_data");
                for (int r = 0; r < img.getHeight(); r++) {
                    for (int c = 0; c < img.getWidth(); c++) {
                        final Color color = new Color(img.getRGB(c, r));
                        int value = color.getRed();
                        if (value >= levelSpritesLength) {
                            value = 0;
                        }
                        put(new Pair<>(c, r), value);
                    }
                }
            }};
    }

    private void importLevelSprites() {
        final BufferedImage img = loadSources("platform_ladder_" + levelSpriteName);
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

    @Override
    public final Optional<Type> getLevelMatrixType (final int x, final int y) {
        switch (this.levelData.get(new Pair<>(x, y))) {
            case Constants.Level.platformBlock:
            case Constants.Level.coloredLadder:
            case Constants.Level.whiteLadder:
            case Constants.Level.blockWithUpperLadder:
            case Constants.Level.blockWithLowerLadder:
            case Constants.Level.blockWithDoubleLadder:
                return Optional.of(Type.BLOCK);
            default:
                return Optional.empty();
        }
    }
}
