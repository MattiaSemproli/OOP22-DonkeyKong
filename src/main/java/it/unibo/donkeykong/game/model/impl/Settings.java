package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        this.backHome = new ButtonImpl(getSettingsSources().get(SettingsAssets.homeButton),
                                       MenuAssets.rightMenuBorder - SettingsAssets.homeButtonRightDistance, 
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
                                                                                          SettingsAssets.squareButtonSize, 
                                                                                          SettingsAssets.squareButtonSize);
        this.themesButtons[Audio.getThemeSources().get(Audio.menuMusic1)] = new Rectangle(SettingsAssets.rightSettingsButtonX, 
                                                                                          SettingsAssets.musicsButtonY, 
                                                                                          SettingsAssets.squareButtonSize, 
                                                                                          SettingsAssets.squareButtonSize);
    }

    @Override
    public final Optional<Boolean> mute(final MouseEvent e) {
        if (this.volumeButtons[SettingsAssets.volOnB].contains(e.getPoint())) {
            return Optional.of(false);
        } else if (this.volumeButtons[SettingsAssets.volOffB].contains(e.getPoint())) {
            return Optional.of(true);
        }
        return Optional.empty();
    }

    @Override
    public final void setTheme(final MouseEvent e) {
        if (this.themesButtons[Audio.getThemeSources().get(Audio.menuMusic0)].contains(e.getPoint())) {
            AudioUtilities.playSoundtrack(Audio.menuMusic0);
        } else if (this.themesButtons[Audio.getThemeSources().get(Audio.menuMusic1)].contains(e.getPoint())) {
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
    public final Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>() {{
            put(volumeButtons[SettingsAssets.volOnB], 
                getSettingsSources().get(SettingsAssets.roundedVolumeOn));
            put(volumeButtons[SettingsAssets.volOffB], 
                getSettingsSources().get(SettingsAssets.roundedVolumeOff));
            put(themesButtons[Audio.getThemeSources().get(Audio.menuMusic0)], 
                getSettingsSources().get(SettingsAssets.themesButton));
            put(themesButtons[Audio.getThemeSources().get(Audio.menuMusic1)], 
                getSettingsSources().get(SettingsAssets.themesButton));
        }};
    }
}
