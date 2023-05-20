package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.getLevelSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelFourSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelOneSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelThreeSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelTwoSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Constants.Level;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Levels menu model.
 */
public class LevelsMenu implements ViewModel {

    private ButtonImpl backHome, levelOneButton;
    private Rectangle[] levelsButtons = new Rectangle[Level.numLevelsButtons];

    /**
     * Constructor.
     */
    public LevelsMenu() {
        this.createButtons();
    }

    private void createButtons() {
        this.backHome = new ButtonImpl(getSettingsSources().get(SettingsAssets.homeButton),
                                       MenuAssets.rightMenuBorder - SettingsAssets.homeButtonRightDistance, 
                                       MenuAssets.bottomMenuBorder - SettingsAssets.homeButtonBottomDistance, 
                                       SettingsAssets.squareButtonSize, 
                                       SettingsAssets.squareButtonSize, Gamestate.MENU);
        this.levelOneButton = new ButtonImpl(getLevelSources().get(levelOneSource).getX(),
                                             LevelAssets.leftLevelButtonX, 
                                             LevelAssets.topLevelbuttonY, 
                                             LevelAssets.levelButtonWidth, 
                                             LevelAssets.levelButtonHeight, Gamestate.PLAYING);
        this.levelsButtons[getLevelSources().get(levelTwoSource).getY()] = new Rectangle(LevelAssets.rightLevelButtonX, 
                                                                                         LevelAssets.topLevelbuttonY, 
                                                                                         LevelAssets.levelButtonWidth, 
                                                                                         LevelAssets.levelButtonHeight);
        this.levelsButtons[getLevelSources().get(levelThreeSource).getY()] = new Rectangle(LevelAssets.leftLevelButtonX, 
                                                                                           LevelAssets.botLevelButtonY,
                                                                                           LevelAssets.levelButtonWidth, 
                                                                                           LevelAssets.levelButtonHeight);
        this.levelsButtons[getLevelSources().get(levelFourSource).getY()] = new Rectangle(LevelAssets.rightLevelButtonX, 
                                                                                          LevelAssets.botLevelButtonY,
                                                                                          LevelAssets.levelButtonWidth, 
                                                                                          LevelAssets.levelButtonHeight);
    }

    @Override
    public final ArrayList<ButtonImpl> getButtons() {
        return new ArrayList<ButtonImpl>() {{
            add(backHome);
            add(levelOneButton);
        }};
    }

    @Override
    public final Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>() {{
            put(levelsButtons[getLevelSources().get(levelTwoSource).getY()], 
                getLevelSources().get(levelTwoSource).getX());
            put(levelsButtons[getLevelSources().get(levelThreeSource).getY()], 
                getLevelSources().get(levelThreeSource).getX());
            put(levelsButtons[getLevelSources().get(levelFourSource).getY()], 
                getLevelSources().get(levelFourSource).getX());
        }};
    }
}
