package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.MENU_X;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.MENU_Y;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.controller.impl.SettingsController;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.Constants.Audio;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;
import it.unibo.donkeykong.view.api.Button;
import it.unibo.donkeykong.view.api.View;

/**
 * Settings view, manages settings graphics.
 */
public class SettingsView implements View {

    private final SettingsController settingsController;

    private final Rectangle[] volumeButtons = new Rectangle[SettingsAssets.NUM_VOLUME_BUTTONS];
    private final Rectangle[] themesButtons = new Rectangle[Audio.numThemesButtons];
    private final Map<Button, BufferedImage> buttons = new HashMap<>();
    private final Map<Rectangle, BufferedImage> alternativeButtons = new HashMap<>();

    /**
     * Constructor.
     * 
     * @param settingsController the linked controller.
     */
    public SettingsView(final SettingsController settingsController) {
        this.settingsController = settingsController;

        final Button backHome = new ButtonImpl(MenuAssets.RIGHT_MENU_BORDER - SettingsAssets.HOME_BUTTON_RIGHT_DISTANCE, 
                                               MenuAssets.BOTTOM_MENU_BORDER - SettingsAssets.HOME_BUTTON_BOTTOM_DISTANCE, 
                                               SettingsAssets.SQUARE_BUTTON_SIZE, 
                                               SettingsAssets.SQUARE_BUTTON_SIZE, Gamestate.MENU);
        this.volumeButtons[SettingsAssets.VOL_ON_B] = new Rectangle(SettingsAssets.LEFT_SETTINGS_BUTTON_X, 
                                                                    SettingsAssets.MUTE_BUTTON_Y, 
                                                                    SettingsAssets.SQUARE_BUTTON_SIZE, 
                                                                    SettingsAssets.SQUARE_BUTTON_SIZE);
        this.volumeButtons[SettingsAssets.VOL_OFF_B] = new Rectangle(SettingsAssets.RIGHT_SETTINGS_BUTTON_X, 
                                                                     SettingsAssets.MUTE_BUTTON_Y, 
                                                                     SettingsAssets.SQUARE_BUTTON_SIZE, 
                                                                     SettingsAssets.SQUARE_BUTTON_SIZE);
        this.themesButtons[Audio.getThemeSources()
                                .get(Audio.menuMusic0)] = new Rectangle(SettingsAssets.LEFT_SETTINGS_BUTTON_X, 
                                                                        SettingsAssets.MUSIC_BUTTON_Y, 
                                                                        SettingsAssets.THEMES_BUTTON_WIDTH, 
                                                                        SettingsAssets.SQUARE_BUTTON_SIZE);
        this.themesButtons[Audio.getThemeSources()
                                .get(Audio.menuMusic1)] = new Rectangle(SettingsAssets.RIGHT_SETTINGS_BUTTON_X, 
                                                                        SettingsAssets.MUSIC_BUTTON_Y, 
                                                                        SettingsAssets.THEMES_BUTTON_WIDTH, 
                                                                        SettingsAssets.SQUARE_BUTTON_SIZE);

        buttons.put(backHome, getSettingsSources().get(SettingsAssets.HOME_BUTTON));

        alternativeButtons.put(volumeButtons[SettingsAssets.VOL_ON_B], 
                               getSettingsSources().get(SettingsAssets.ROUNDED_VOLUME_ON));
        alternativeButtons.put(volumeButtons[SettingsAssets.VOL_OFF_B], 
                               getSettingsSources().get(SettingsAssets.ROUNDED_VOLUME_OFF));
        alternativeButtons.put(themesButtons[Audio.getThemeSources().get(Audio.menuMusic0)],
                               ResourceFuncUtilities.loadSources("1"));
        alternativeButtons.put(themesButtons[Audio.getThemeSources().get(Audio.menuMusic1)],
                               ResourceFuncUtilities.loadSources("2"));
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
        this.alternativeButtons.forEach((rectangle, image) -> g.drawImage(image, 
                                                                          rectangle.x, 
                                                                          rectangle.y, 
                                                                          rectangle.width,
                                                                          rectangle.height, null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final Pair<Integer, Integer> point) {
        final Point e = new Point(point.getX(), point.getY());
        this.buttons.keySet().forEach(b -> {
            if (b.getCorners().contains(e)) {
                this.settingsController.applyGamestate(b.getButtonGamestate());
            }
        });
        this.mute(e);
        this.themeChange(e);
    }

    private void mute(final Point point) {
        if (this.volumeButtons[SettingsAssets.VOL_ON_B].contains(point)) {
            AudioUtilities.setMuted(false);
        } else if (this.volumeButtons[SettingsAssets.VOL_OFF_B].contains(point)) {
            AudioUtilities.setMuted(true);
        }
    }

    private void themeChange(final Point point) {
        if (this.themesButtons[Audio.getThemeSources().get(Audio.menuMusic0)].contains(point)) {
            AudioUtilities.playSoundtrack(Audio.menuMusic0);
        } else if (this.themesButtons[Audio.getThemeSources().get(Audio.menuMusic1)].contains(point)) {
            AudioUtilities.playSoundtrack(Audio.menuMusic1);
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
        g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                    MENU_X + (MenuAssets.MENU_TEXTURE_BOX - SettingsAssets.SETTINGS_TEXT_WIDTH) / 2,
                    MENU_Y - (SettingsAssets.SETTINGS_TEXT_HEIGHT / 2),
                    SettingsAssets.SETTINGS_TEXT_WIDTH,
                    SettingsAssets.SETTINGS_TEXT_HEIGHT, null);
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
