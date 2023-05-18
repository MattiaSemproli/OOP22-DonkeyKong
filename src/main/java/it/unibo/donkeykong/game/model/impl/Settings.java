package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.settingsSources;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.game.model.api.VolumeSettings;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.Constants.Audio;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Gamestate;

/** 
 * Settings model. 
 */
public class Settings implements ViewModel, VolumeSettings {

    private ButtonImpl backHome;
    private final Rectangle[] volumeButtons = new Rectangle[SettingsAssets.numVolumeButtons];
    private final Rectangle[] themesButtons = new Rectangle[Audio.numThemesButtons];

    /**
     * Constructor.
     */
    public Settings() {
        this.createButtons();
    }

    private void createButtons() {
        this.backHome = new ButtonImpl(settingsSources.get(SettingsAssets.homeButton),
                                       menuX + MenuAssets.menuTextureBox - SettingsAssets.homeButtonRightDistance, 
                                       menuY + MenuAssets.menuTextureBox - SettingsAssets.homeButtonBottomDistance, 
                                       SettingsAssets.squareButtonSize, 
                                       SettingsAssets.squareButtonSize, Gamestate.MENU);

        this.volumeButtons[SettingsAssets.volOnB] = new Rectangle(MenuAssets.menuTextureBox / 2 - SettingsAssets.squareButtonSize / 2, 
                                                                  menuY + SettingsAssets.squareButtonSize, 
                                                                  SettingsAssets.squareButtonSize, 
                                                                  SettingsAssets.squareButtonSize);
        this.volumeButtons[SettingsAssets.volOffB] = new Rectangle(MenuAssets.menuTextureBox / 2 + SettingsAssets.squareButtonSize * 2, 
                                                                   menuY + SettingsAssets.squareButtonSize, 
                                                                   SettingsAssets.squareButtonSize, 
                                                                   SettingsAssets.squareButtonSize);
        this.themesButtons[Audio.themeSources.get(Audio.menuMusic0)] = new Rectangle(MenuAssets.menuTextureBox / 2 - SettingsAssets.squareButtonSize / 2, 
                                                                                     menuY + SettingsAssets.squareButtonSize * 3, 
                                                                                     SettingsAssets.squareButtonSize, 
                                                                                     SettingsAssets.squareButtonSize);
        this.themesButtons[Audio.themeSources.get(Audio.menuMusic1)] = new Rectangle(MenuAssets.menuTextureBox / 2 + SettingsAssets.squareButtonSize * 2, 
                                                                                     menuY + SettingsAssets.squareButtonSize * 3, 
                                                                                     SettingsAssets.squareButtonSize, 
                                                                                     SettingsAssets.squareButtonSize);
    }

    @Override
    public final Optional<Boolean> mute(final MouseEvent e) {
        if(this.volumeButtons[SettingsAssets.volOnB].contains(e.getPoint())) {
            return Optional.of(false);
        } else if(this.volumeButtons[SettingsAssets.volOffB].contains(e.getPoint())) {
            return Optional.of(true);
        }
        return Optional.empty();
    }

    @Override
    public final void setTheme(final MouseEvent e) {
        if(this.themesButtons[Audio.themeSources.get(Audio.menuMusic0)].contains(e.getPoint())) {
            AudioUtilities.playSoundtrack(Audio.menuMusic0);
        } else if(this.themesButtons[Audio.themeSources.get(Audio.menuMusic1)].contains(e.getPoint())) {
            AudioUtilities.playSoundtrack(Audio.menuMusic1);
        }
    }

    

    @Override
    public final ArrayList<ButtonImpl> getButtons() {
        return new ArrayList<>() {{
            add(backHome);
        }};
    }

    @Override
    public Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>() {{
            put(volumeButtons[SettingsAssets.volOnB], settingsSources.get(SettingsAssets.roundedVolumeOn));
            put(volumeButtons[SettingsAssets.volOffB], settingsSources.get(SettingsAssets.roundedVolumeOff));
            put(themesButtons[Audio.themeSources.get(Audio.menuMusic0)], settingsSources.get(SettingsAssets.themesButton));
            put(themesButtons[Audio.themeSources.get(Audio.menuMusic1)], settingsSources.get(SettingsAssets.themesButton));
        }};
    }
}
