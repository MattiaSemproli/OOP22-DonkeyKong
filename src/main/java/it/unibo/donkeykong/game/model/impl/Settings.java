package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.donkeykong.game.model.api.Button;
import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.game.model.api.VolumeSettings;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.Constants.Audio;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;

/** 
 * Settings model, manages settings logics.
 */
public class Settings implements ViewModel, VolumeSettings {

    private Button backHome;
    private final Rectangle[] volumeButtons = new Rectangle[SettingsAssets.numVolumeButtons];
    private final Rectangle[] themesButtons = new Rectangle[Audio.numThemesButtons];

    /**
     * Constructor.
     */
    public Settings() {
        this.createButtons();
    }

    private void createButtons() {
        this.backHome = new ButtonImpl(MenuAssets.rightMenuBorder - SettingsAssets.homeButtonRightDistance, 
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
    public final Map<Button, BufferedImage> getButtons() {
        final Map<Button, BufferedImage> buttons = new HashMap<>();
        buttons.put(this.backHome, getSettingsSources().get(SettingsAssets.homeButton));
        return buttons;
        // return new HashMap<>() {{
        //     put(backHome, getSettingsSources().get(SettingsAssets.homeButton));
        // }};
    }

    @Override
    public final Map<Rectangle, BufferedImage> getAlternativeButtons() {
        final Map<Rectangle, BufferedImage> alternativeButton = new HashMap<>();
        alternativeButton.put(volumeButtons[SettingsAssets.volOnB], 
            getSettingsSources().get(SettingsAssets.roundedVolumeOn));
        alternativeButton.put(volumeButtons[SettingsAssets.volOffB], 
            getSettingsSources().get(SettingsAssets.roundedVolumeOff));
        alternativeButton.put(themesButtons[Audio.getThemeSources().get(Audio.menuMusic0)],
            ResourceFuncUtilities.loadSources("1"));
        alternativeButton.put(themesButtons[Audio.getThemeSources().get(Audio.menuMusic1)],
            ResourceFuncUtilities.loadSources("2"));
        return alternativeButton;
        // return new HashMap<>() {{
        //     put(volumeButtons[SettingsAssets.volOnB], 
        //         getSettingsSources().get(SettingsAssets.roundedVolumeOn));
        //     put(volumeButtons[SettingsAssets.volOffB], 
        //         getSettingsSources().get(SettingsAssets.roundedVolumeOff));
        //     put(themesButtons[Audio.getThemeSources().get(Audio.menuMusic0)],
        //         //getSettingsSources().get(SettingsAssets.themesButton)
        //         ResourceFuncUtilities.loadSources("1"));
        //     put(themesButtons[Audio.getThemeSources().get(Audio.menuMusic1)],
        //         //getSettingsSources().get(SettingsAssets.themesButton)
        //         ResourceFuncUtilities.loadSources("2"));
        // }};
    }
}
