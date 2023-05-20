package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.levelOne;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;
import static it.unibo.donkeykong.utilities.Constants.Window.SCALED_TILES_SIZE;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private Map<Rectangle, BufferedImage> dataLevel = new HashMap<>();
    private final ButtonImpl settingsPauseButton;

    /**
     * Constructor.
     */
    public Game() {
        this.level = new LevelImpl(levelOne);
        this.mapDataLevel();
        this.settingsPauseButton = new ButtonImpl(getSettingsSources().get(SettingsAssets.roundedSettingsButton), 
                                                  Window.GAME_WIDTH - SCALED_TILES_SIZE - Window.TILES_DEFAULT_SIZE, 
                                                  Window.TILES_DEFAULT_SIZE, 
                                                  SCALED_TILES_SIZE, 
                                                  SCALED_TILES_SIZE, Gamestate.PAUSE);
    }

    private void mapDataLevel() {
        Map<Pair<Integer, Integer>, Integer> lvl = this.level.getLevelData();
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
    public final ArrayList<ButtonImpl> getButtons() {
        return new ArrayList<ButtonImpl>() {{
            add(settingsPauseButton);
        }};
    }

    @Override
    public final Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>();
    }
}
