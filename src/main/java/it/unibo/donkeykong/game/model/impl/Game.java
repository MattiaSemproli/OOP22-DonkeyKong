package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.levelOne;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;
import static it.unibo.donkeykong.utilities.Constants.Window.SCALED_TILES_SIZE;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.game.model.api.Button;
import it.unibo.donkeykong.game.model.api.Level;
import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;

/**
 * Game model (model of gameview like buttons etc...).
 */
public class Game implements ViewModel {

    private final Level level;
    private final Map<Rectangle, BufferedImage> dataLevel = new HashMap<>();
    private final Button settingsPauseButton;
    private Map<Integer, BufferedImage[][]> animations = new HashMap<>();

    /**
     * Constructor.
     */
    public Game() {
        this.level = new LevelImpl(levelOne);
        this.mapDataLevel();
        this.settingsPauseButton = new ButtonImpl(Window.GAME_WIDTH - SCALED_TILES_SIZE - Window.TILES_DEFAULT_SIZE, 
                                                  Window.TILES_DEFAULT_SIZE, 
                                                  SCALED_TILES_SIZE, 
                                                  SCALED_TILES_SIZE, Gamestate.PAUSE);
        this.bufferAnimations();
    }

    private void bufferAnimations() {
        final BufferedImage[][] playerAnimations = new BufferedImage[4][3];
        for (int r = 0; r < playerAnimations.length; r++) {
            for (int c = 0; c < playerAnimations.length; c++) {
                
            }
        }
    }

    private void mapDataLevel() {
        final Map<Pair<Integer, Integer>, Integer> lvl = this.level.getLevelData();
        for (int r = 0; r < Window.TILES_IN_HEIGHT; r++) {
            for (int c = 0; c < Window.TILES_IN_WIDTH; c++) {
                this.dataLevel.put(new Rectangle(SCALED_TILES_SIZE * r, 
                                                 SCALED_TILES_SIZE * c, 
                                                 SCALED_TILES_SIZE, 
                                                 SCALED_TILES_SIZE),
                                   this.level.getLevelSprite(lvl.get(new Pair<>(r, c))));
            }
        }
    }

    /**
     * This method is used by the GameView to get the data of the level to be drawn.
     * 
     * @return a map containing the data of the level to be drawn.
     */
    public Map<Rectangle, BufferedImage> getDataLevel() {
        return new HashMap<>(this.dataLevel);
    }

    @Override
    public final Map<Button, BufferedImage> getButtons() {
        return new HashMap<>() {{
            put(settingsPauseButton, getSettingsSources().get(SettingsAssets.roundedSettingsButton));
        }};
    }

    @Override
    public final Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>();
    }
}
