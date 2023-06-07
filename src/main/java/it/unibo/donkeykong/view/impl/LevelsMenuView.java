package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.MENU_X;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.MENU_Y;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.LevelAssets.LEVEL_FOUR_SOURCE;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.LevelAssets.LEVEL_ONE_SOURCE;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.LevelAssets.LEVEL_THREE_SOURCE;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.LevelAssets.LEVEL_TWO_SOURCE;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.LevelAssets.getLevelSources;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.SettingsAssets.getSettingsSources;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.controller.impl.LevelsMenuController;
import it.unibo.donkeykong.utilities.ViewConstants.AudioAssets;
import it.unibo.donkeykong.utilities.ViewConstants.MenuAssets;
import it.unibo.donkeykong.utilities.ViewConstants.Window;
import it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.LevelAssets;
import it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Gamestate;
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

        final Button backHome = new ButtonImpl(MenuAssets.RIGHT_MENU_BORDER - SettingsAssets.HOME_BUTTON_RIGHT_DISTANCE, 
                                               MenuAssets.BOTTOM_MENU_BORDER - SettingsAssets.HOME_BUTTON_BOTTOM_DISTANCE, 
                                               SettingsAssets.SQUARE_BUTTON_SIZE, 
                                               SettingsAssets.SQUARE_BUTTON_SIZE, Gamestate.MENU);
        this.levelOneButton = new ButtonImpl(LevelAssets.LEFT_LEVEL_BUTTON_X, 
                                             LevelAssets.TOP_LEVEL_BUTTON_Y, 
                                             LevelAssets.LEVEL_BUTTON_WIDTH, 
                                             LevelAssets.LEVEL_BUTTON_HEIGHT, Gamestate.PLAYING);
        this.levelTwoButton = new ButtonImpl(LevelAssets.RIGHT_LEVEL_BUTTON_X, 
                                             LevelAssets.TOP_LEVEL_BUTTON_Y, 
                                             LevelAssets.LEVEL_BUTTON_WIDTH, 
                                             LevelAssets.LEVEL_BUTTON_HEIGHT, Gamestate.PLAYING);
        this.levelThreeButton = new ButtonImpl(LevelAssets.LEFT_LEVEL_BUTTON_X, 
                                               LevelAssets.BOT_LEVEL_BUTTON_Y, 
                                               LevelAssets.LEVEL_BUTTON_WIDTH, 
                                               LevelAssets.LEVEL_BUTTON_HEIGHT, Gamestate.PLAYING);
        this.levelFourButton = new ButtonImpl(LevelAssets.RIGHT_LEVEL_BUTTON_X, 
                                              LevelAssets.BOT_LEVEL_BUTTON_Y,
                                              LevelAssets.LEVEL_BUTTON_WIDTH, 
                                              LevelAssets.LEVEL_BUTTON_HEIGHT, Gamestate.PLAYING);

        buttons.put(backHome, getSettingsSources().get(SettingsAssets.HOME_BUTTON));
        buttons.put(this.levelOneButton, getLevelSources().get(LEVEL_ONE_SOURCE).getX());
        buttons.put(this.levelTwoButton, getLevelSources().get(LEVEL_TWO_SOURCE).getX());
        buttons.put(this.levelThreeButton, getLevelSources().get(LEVEL_THREE_SOURCE).getX());
        buttons.put(this.levelFourButton, getLevelSources().get(LEVEL_FOUR_SOURCE).getX());
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
                            AudioUtilities.playSoundtrack(AudioAssets.GAME_MUSIC_0);
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
        g.drawImage(getMenuSources().get(MenuAssets.MENU_BACKGROUND), 
                    0, 
                    0, 
                    Window.GAME_WIDTH, 
                    Window.GAME_HEIGHT, null);
        g.drawImage(getMenuSources().get(MenuAssets.MENU_TEXTURE), 
                    MENU_X, 
                    MENU_Y, 
                    MenuAssets.MENU_TEXTURE_BOX, 
                    MenuAssets.MENU_TEXTURE_BOX, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final int keyCode) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final int keyCode) {
    }
}
