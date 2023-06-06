package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.getLevelSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelFourSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelOneSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelThreeSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets.levelTwoSource;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.controller.impl.LevelsMenuController;
import it.unibo.donkeykong.utilities.Constants.Audio;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.view.api.Button;
import it.unibo.donkeykong.view.api.View;

/**
 * levels menu view, manages levels menu graphics.
 */
public class LevelsMenuView implements View {

    private final LevelsMenuController levelsMenuController;

    private final Button levelOneButton, levelTwoButton, levelThreeButton, levelFourButton;
    private final Map<Button, BufferedImage> buttons = new HashMap<>();

    /**
     * Constructor.
     * 
     * @param levelsMenuController the linked controller.
     */
    public LevelsMenuView(final LevelsMenuController levelsMenuController) {
        this.levelsMenuController = levelsMenuController;

        final Button backHome = new ButtonImpl(MenuAssets.rightMenuBorder - SettingsAssets.homeButtonRightDistance, 
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

        buttons.put(backHome, getSettingsSources().get(SettingsAssets.homeButton));
        buttons.put(this.levelOneButton, getLevelSources().get(levelOneSource).getX());
        buttons.put(this.levelTwoButton, getLevelSources().get(levelTwoSource).getX());
        buttons.put(this.levelThreeButton, getLevelSources().get(levelThreeSource).getX());
        buttons.put(this.levelFourButton, getLevelSources().get(levelFourSource).getX());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Graphics g) {
        this.drawBackgroundAssets(g);
        this.buttons.forEach((b, bi) -> g.drawImage(bi, 
                                                    b.getButtonPos().getX(), 
                                                    b.getButtonPos().getY(),
                                                    b.getButtonDim().getX(),
                                                    b.getButtonDim().getY(), null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final Pair<Integer, Integer> point) {
        this.buttons.keySet().forEach(b -> {
            if (b.getCorners().contains(new Point(point.getX(), point.getY()))) {
                if (b.getButtonGamestate().equals(Gamestate.PLAYING)) {
                            AudioUtilities.playSoundtrack(Audio.gameMusic0);
                            this.levelToBePlayed(b);
                            this.levelsMenuController.startGameController();
                        }
                this.levelsMenuController.applyGamestate(b.getButtonGamestate());
            }
        });
    }

    private void levelToBePlayed(final Button b) {
        if (b.equals(this.levelOneButton)) {
            this.levelsMenuController.handleChoosenLevel(CurrentLevel.ONE);
        } else if (b.equals(this.levelTwoButton)) {
            this.levelsMenuController.handleChoosenLevel(CurrentLevel.TWO);
        } else if (b.equals(this.levelThreeButton)) {
            this.levelsMenuController.handleChoosenLevel(CurrentLevel.THREE);
        } else if (b.equals(this.levelFourButton)) {
            this.levelsMenuController.handleChoosenLevel(CurrentLevel.FOUR);
        }
    }

    private void drawBackgroundAssets(final Graphics g) {
        g.drawImage(getMenuSources().get(MenuAssets.menuBackground), 
                    0, 
                    0, 
                    Window.GAME_WIDTH, 
                    Window.GAME_HEIGHT, null);
        g.drawImage(getMenuSources().get(MenuAssets.menuTexture), 
                    menuX, 
                    menuY, 
                    MenuAssets.menuTextureBox, 
                    MenuAssets.menuTextureBox, null);
    }
}
