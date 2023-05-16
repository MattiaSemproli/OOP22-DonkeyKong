package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.settingsSources;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
public class Settings implements GameEngine, ViewModel, VolumeSettings {

    private ButtonImpl backHome;
    private final Rectangle[] volumeButtons = new Rectangle[SettingsAssets.numVolumeButtons];
    private final Rectangle[] themesButtons = new Rectangle[Audio.numThemesButtons];

    /**
     * Constructor.
     */
    public Settings() {
        this.createButtons();
    }

    @Override
    public final void update() {
    }

    @Override
    public final void draw(final Graphics g) {
        g.drawImage(settingsSources.get(SettingsAssets.homeButton),
                    this.backHome.getButtonPos().getX(),
                    this.backHome.getButtonPos().getY(),
                    this.backHome.getButtonDim().getX(),
                    this.backHome.getButtonDim().getY(), null);
        g.drawImage(settingsSources.get(SettingsAssets.roundedVolumeOn), 
                    this.volumeButtons[SettingsAssets.volOnB].x,
                    this.volumeButtons[SettingsAssets.volOnB].y,
                    this.volumeButtons[SettingsAssets.volOnB].height,
                    this.volumeButtons[SettingsAssets.volOnB].width, null);
        g.drawImage(settingsSources.get(SettingsAssets.roundedVolumeOff), 
                    this.volumeButtons[SettingsAssets.volOffB].x,
                    this.volumeButtons[SettingsAssets.volOffB].y,
                    this.volumeButtons[SettingsAssets.volOffB].height,
                    this.volumeButtons[SettingsAssets.volOffB].width, null);
        g.drawImage(settingsSources.get(SettingsAssets.themesButton),
                    this.themesButtons[Audio.themeSources.get(Audio.menuMusic0)].x,
                    this.themesButtons[Audio.themeSources.get(Audio.menuMusic0)].y, 
                    this.themesButtons[Audio.themeSources.get(Audio.menuMusic0)].height, 
                    this.themesButtons[Audio.themeSources.get(Audio.menuMusic0)].width, null);
        g.drawImage(settingsSources.get(SettingsAssets.themesButton),
                    this.themesButtons[Audio.themeSources.get(Audio.menuMusic1)].x,
                    this.themesButtons[Audio.themeSources.get(Audio.menuMusic1)].y, 
                    this.themesButtons[Audio.themeSources.get(Audio.menuMusic1)].height, 
                    this.themesButtons[Audio.themeSources.get(Audio.menuMusic1)].width, null);
    }

    private void createButtons() {
        this.backHome = new ButtonImpl(menuX + MenuAssets.menuTextureBox - SettingsAssets.homeButtonRightDistance, 
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
        return new ArrayList<ButtonImpl>() {{
            add(backHome);
        }};
    }
}
