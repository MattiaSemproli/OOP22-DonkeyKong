package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.getLevelSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelFourSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelOneSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelThreeSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelTwoSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.game.model.api.Button;
import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Levels menu model.
 */
public class LevelsMenu implements ViewModel {

    private Button backHome, levelOneButton, levelTwoButton, levelThreeButton, levelFourButton;

    /**
     * Constructor.
     */
    public LevelsMenu() {
        this.createButtons();
    }

    private void createButtons() {
        this.backHome = new ButtonImpl(MenuAssets.rightMenuBorder - SettingsAssets.homeButtonRightDistance, 
                                       MenuAssets.bottomMenuBorder - SettingsAssets.homeButtonBottomDistance, 
                                       SettingsAssets.squareButtonSize, 
                                       SettingsAssets.squareButtonSize, Gamestate.MENU);
        this.levelOneButton = new ButtonImpl(LevelAssets.leftLevelButtonX, 
                                             LevelAssets.topLevelbuttonY, 
                                             LevelAssets.levelButtonWidth, 
                                             LevelAssets.levelButtonHeight, Gamestate.PLAYING);
        this.levelTwoButton = new ButtonImpl(LevelAssets.rightLevelButtonX, 
                                             LevelAssets.topLevelbuttonY, 
                                             LevelAssets.levelButtonWidth, 
                                             LevelAssets.levelButtonHeight, Gamestate.PLAYING);
        this.levelThreeButton = new ButtonImpl(LevelAssets.leftLevelButtonX, 
                                               LevelAssets.botLevelButtonY, 
                                               LevelAssets.levelButtonWidth, 
                                               LevelAssets.levelButtonHeight, Gamestate.PLAYING);
        this.levelFourButton = new ButtonImpl(LevelAssets.rightLevelButtonX, 
                                               LevelAssets.botLevelButtonY,
                                               LevelAssets.levelButtonWidth, 
                                               LevelAssets.levelButtonHeight, Gamestate.PLAYING);
    }

    /**
     * Check which button is pressed.
     * 
     * @param b button to check for setting the level.
     */
    public final void setLevelToPlay(final Button b) {
        if (b.equals(this.levelOneButton)) {
            CurrentLevel.setCurrentLevel(CurrentLevel.ONE);
        } else if (b.equals(this.levelTwoButton)) {
            CurrentLevel.setCurrentLevel(CurrentLevel.TWO);
        } else if (b.equals(this.levelThreeButton)) {
            CurrentLevel.setCurrentLevel(CurrentLevel.THREE);
        } else if (b.equals(this.levelFourButton)) {
            CurrentLevel.setCurrentLevel(CurrentLevel.FOUR);
        }
    }

    @Override
    public final Map<Button, BufferedImage> getButtons() {
        final Map<Button, BufferedImage> buttons = new HashMap<>();
        buttons.put(this.backHome, getSettingsSources().get(SettingsAssets.homeButton));
        buttons.put(this.levelOneButton, getLevelSources().get(levelOneSource).getX());
        buttons.put(this.levelTwoButton, getLevelSources().get(levelTwoSource).getX());
        buttons.put(this.levelThreeButton, getLevelSources().get(levelThreeSource).getX());
        buttons.put(this.levelFourButton, getLevelSources().get(levelFourSource).getX());
        return buttons;
        // return new HashMap<>() {{
        //     put(backHome, getSettingsSources().get(SettingsAssets.homeButton));
        //     put(levelOneButton, getLevelSources().get(levelOneSource).getX());
        //     put(levelTwoButton, getLevelSources().get(levelTwoSource).getX());
        //     put(levelThreeButton, getLevelSources().get(levelThreeSource).getX());
        //     put(levelFourButton, getLevelSources().get(levelFourSource).getX());
        // }};
    }

    @Override
    public final Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>();
    }
}
