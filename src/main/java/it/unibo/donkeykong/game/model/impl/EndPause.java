package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
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

/**
 * Pause game model.
 */
public class EndPause implements ViewModel, VolumeSettings {

    private Button backHome, backToPlay;
    private final Rectangle[] volumeButtons = new Rectangle[SettingsAssets.numVolumeButtons];
    private final Rectangle[] gameThemesButtons = new Rectangle[Audio.numGameThemesButtons];

    /**
     * Constructor.
     */
    public EndPause() {
        this.createButtons();
    }

    private void createButtons() {
        this.backHome = new ButtonImpl(MenuAssets.rightMenuBorder - SettingsAssets.homeButtonRightDistance, 
                                       MenuAssets.bottomMenuBorder - SettingsAssets.homeButtonBottomDistance, 
                                       SettingsAssets.squareButtonSize, 
                                       SettingsAssets.squareButtonSize, Gamestate.MENU);
        this.backToPlay = new ButtonImpl(menuX + SettingsAssets.repeatButtonLeftDistance - SettingsAssets.squareButtonSize, 
                                         MenuAssets.bottomMenuBorder - SettingsAssets.repeatButtonBottomDistance, 
                                         SettingsAssets.squareButtonSize, 
                                         SettingsAssets.squareButtonSize, Gamestate.PLAYING);
        this.volumeButtons[SettingsAssets.volOnB] = new Rectangle(SettingsAssets.leftSettingsButtonX, 
                                                                  SettingsAssets.muteButtonY, 
                                                                  SettingsAssets.squareButtonSize, 
                                                                  SettingsAssets.squareButtonSize);

        this.volumeButtons[SettingsAssets.volOffB] = new Rectangle(SettingsAssets.rightSettingsButtonX, 
                                                                   SettingsAssets.muteButtonY, 
                                                                   SettingsAssets.squareButtonSize, 
                                                                   SettingsAssets.squareButtonSize);
        this.gameThemesButtons[Audio.getGameSources().get(Audio.gameMusic0)] = new Rectangle(SettingsAssets.leftSettingsButtonX, 
                                                                                             SettingsAssets.musicsButtonY, 
                                                                                             SettingsAssets.squareButtonSize, 
                                                                                             SettingsAssets.squareButtonSize);

        this.gameThemesButtons[Audio.getGameSources().get(Audio.gameMusic1)] = new Rectangle(SettingsAssets.rightSettingsButtonX, 
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
        if (this.gameThemesButtons[Audio.getGameSources().get(Audio.gameMusic0)].contains(e.getPoint())) {
            AudioUtilities.playSoundtrack(Audio.gameMusic0);
        } else if (this.gameThemesButtons[Audio.getGameSources().get(Audio.gameMusic1)].contains(e.getPoint())) {
            AudioUtilities.playSoundtrack(Audio.gameMusic1);
        }
    }

    @Override
    public final Map<Button, BufferedImage> getButtons() {
        return new HashMap<>() {{
            put(backHome, getSettingsSources().get(SettingsAssets.homeButton));
            put(backToPlay, getSettingsSources().get(SettingsAssets.backToPlayButton));
        }};
    }

    @Override
    public final Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>() {{
            put(volumeButtons[SettingsAssets.volOnB], 
                getSettingsSources().get(SettingsAssets.roundedVolumeOn));
            put(volumeButtons[SettingsAssets.volOffB], 
                getSettingsSources().get(SettingsAssets.roundedVolumeOff));
            put(gameThemesButtons[Audio.getGameSources().get(Audio.gameMusic0)], 
                getSettingsSources().get(SettingsAssets.themesButton));
            put(gameThemesButtons[Audio.getGameSources().get(Audio.gameMusic1)], 
                getSettingsSources().get(SettingsAssets.themesButton));
        }};
    }
}
