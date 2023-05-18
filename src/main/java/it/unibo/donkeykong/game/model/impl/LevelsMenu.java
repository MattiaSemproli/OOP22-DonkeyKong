package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.levelSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.settingsSources;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Constants.Level;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Gamestate;

public class LevelsMenu implements ViewModel {
    
    private ButtonImpl backHome, levelOneButton;
    private Rectangle[] levelsButtons = new Rectangle[Level.numLevelsButtons];
    
    /**
     * Constructor.
     */
    public LevelsMenu() {
        this.createButtons();
    }

    private void createButtons() {
        this.backHome = new ButtonImpl(settingsSources.get(SettingsAssets.homeButton),
                                       menuX + MenuAssets.menuTextureBox - SettingsAssets.homeButtonRightDistance, 
                                       menuY + MenuAssets.menuTextureBox - SettingsAssets.homeButtonBottomDistance, 
                                       SettingsAssets.squareButtonSize, 
                                       SettingsAssets.squareButtonSize, Gamestate.MENU);
        this.levelOneButton = new ButtonImpl(levelSources.get(Level.levelOneSource).getX(),
                                             menuX + 50, 
                                             menuY + 50, 
                                             192, 
                                             135, Gamestate.PLAYING);
        this.levelsButtons[levelSources.get(Level.levelTwoSource).getY()] = new Rectangle(menuX + MenuAssets.menuTextureBox - 50 - 192, 
                                                                                          menuY + 50, 
                                                                                          192, 
                                                                                          135);
        this.levelsButtons[levelSources.get(Level.levelThreeSource).getY()] = new Rectangle(menuX + 50, 
                                                                                            menuY + 50 + 135 + 15,
                                                                                            192, 
                                                                                            135);
        this.levelsButtons[levelSources.get(Level.levelFourSource).getY()] = new Rectangle(menuX + MenuAssets.menuTextureBox - 50 - 192, 
                                                                                           menuY + 50 + 135 + 15,
                                                                                           192, 
                                                                                           135);
    }

    @Override
    public final ArrayList<ButtonImpl> getButtons() {
        return new ArrayList<ButtonImpl>() {{
            add(backHome);
            add(levelOneButton);
        }};
    }

    @Override
    public Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>() {{
            put(levelsButtons[levelSources.get(Level.levelTwoSource).getY()], levelSources.get(Level.levelTwoSource).getX());
            put(levelsButtons[levelSources.get(Level.levelThreeSource).getY()], levelSources.get(Level.levelThreeSource).getX());
            put(levelsButtons[levelSources.get(Level.levelFourSource).getY()], levelSources.get(Level.levelFourSource).getX());
        }};
    }
}
