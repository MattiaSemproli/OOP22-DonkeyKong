package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;
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

    private final Rectangle[] volumeButtons = new Rectangle[SettingsAssets.numVolumeButtons];
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

        final Button backHome = new ButtonImpl(MenuAssets.rightMenuBorder - SettingsAssets.homeButtonRightDistance, 
                                               MenuAssets.bottomMenuBorder - SettingsAssets.homeButtonBottomDistance, 
                                               SettingsAssets.squareButtonSize, 
                                               SettingsAssets.squareButtonSize, Gamestate.MENU);
        this.volumeButtons[SettingsAssets.volOnB] = new Rectangle(SettingsAssets.leftSettingsButtonX, 
                                                                  SettingsAssets.muteButtonY, 
                                                                  SettingsAssets.squareButtonSize, 
                                                                  SettingsAssets.squareButtonSize);
        this.volumeButtons[SettingsAssets.volOffB] = new Rectangle(SettingsAssets.rightSettingsButtonX, 
                                                                   SettingsAssets.muteButtonY, 
                                                                   SettingsAssets.squareButtonSize, 
                                                                   SettingsAssets.squareButtonSize);
        this.themesButtons[Audio.getThemeSources().get(Audio.menuMusic0)] = new Rectangle(SettingsAssets.leftSettingsButtonX, 
                                                                                          SettingsAssets.musicsButtonY, 
                                                                                          SettingsAssets.themesButtonWidth, 
                                                                                          SettingsAssets.squareButtonSize);
        this.themesButtons[Audio.getThemeSources().get(Audio.menuMusic1)] = new Rectangle(SettingsAssets.rightSettingsButtonX, 
                                                                                          SettingsAssets.musicsButtonY, 
                                                                                          SettingsAssets.themesButtonWidth, 
                                                                                          SettingsAssets.squareButtonSize);

        buttons.put(backHome, getSettingsSources().get(SettingsAssets.homeButton));

        alternativeButtons.put(volumeButtons[SettingsAssets.volOnB], 
                               getSettingsSources().get(SettingsAssets.roundedVolumeOn));
        alternativeButtons.put(volumeButtons[SettingsAssets.volOffB], 
                               getSettingsSources().get(SettingsAssets.roundedVolumeOff));
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
        if (this.volumeButtons[SettingsAssets.volOnB].contains(point)) {
            AudioUtilities.setMuted(false);
        } else if (this.volumeButtons[SettingsAssets.volOffB].contains(point)) {
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
        g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                    menuX + (MenuAssets.menuTextureBox - SettingsAssets.settingsTextWidth) / 2,
                    menuY - (SettingsAssets.settingsTextHeight / 2),
                    SettingsAssets.settingsTextWidth,
                    SettingsAssets.settingsTextHeight, null);
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(int keyCode) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(int keyCode) {
    }
}
