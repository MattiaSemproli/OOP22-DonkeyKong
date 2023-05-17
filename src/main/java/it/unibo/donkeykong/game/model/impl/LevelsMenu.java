package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.levelSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.settingsSources;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Constants.Level;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Gamestate;

public class LevelsMenu implements GameEngine, ViewModel {
    
    private ButtonImpl backHome;
    private Rectangle[] levelsButtons = new Rectangle[Level.numLevelsButtons];
    
    /**
     * Constructor.
     */
    public LevelsMenu() {
        this.createButtons();
    }

    @Override
    public final void update() {
    }

    @Override
    public final void draw(Graphics g) {
        g.drawImage(settingsSources.get(SettingsAssets.homeButton),
                    this.backHome.getButtonPos().getX(),
                    this.backHome.getButtonPos().getY(),
                    this.backHome.getButtonDim().getX(),
                    this.backHome.getButtonDim().getY(), null);
        g.drawImage(levelSources.get(Level.levelOneSource).getX(),
                    this.levelsButtons[levelSources.get(Level.levelOneSource).getY()].x,
                    this.levelsButtons[levelSources.get(Level.levelOneSource).getY()].y,
                    this.levelsButtons[levelSources.get(Level.levelOneSource).getY()].width,
                    this.levelsButtons[levelSources.get(Level.levelOneSource).getY()].height, null);
        g.drawImage(levelSources.get(Level.levelTwoSource).getX(),
                    this.levelsButtons[levelSources.get(Level.levelTwoSource).getY()].x,
                    this.levelsButtons[levelSources.get(Level.levelTwoSource).getY()].y,
                    this.levelsButtons[levelSources.get(Level.levelTwoSource).getY()].width,
                    this.levelsButtons[levelSources.get(Level.levelTwoSource).getY()].height, null);
        g.drawImage(levelSources.get(Level.levelThreeSource).getX(),
                    this.levelsButtons[levelSources.get(Level.levelThreeSource).getY()].x,
                    this.levelsButtons[levelSources.get(Level.levelThreeSource).getY()].y,
                    this.levelsButtons[levelSources.get(Level.levelThreeSource).getY()].width,
                    this.levelsButtons[levelSources.get(Level.levelThreeSource).getY()].height, null);
        g.drawImage(levelSources.get(Level.levelFourSource).getX(),
                    this.levelsButtons[levelSources.get(Level.levelFourSource).getY()].x,
                    this.levelsButtons[levelSources.get(Level.levelFourSource).getY()].y,
                    this.levelsButtons[levelSources.get(Level.levelFourSource).getY()].width,
                    this.levelsButtons[levelSources.get(Level.levelFourSource).getY()].height, null);        
    }

    private void createButtons() {
        this.backHome = new ButtonImpl(menuX + MenuAssets.menuTextureBox - SettingsAssets.homeButtonRightDistance, 
                                       menuY + MenuAssets.menuTextureBox - SettingsAssets.homeButtonBottomDistance, 
                                       SettingsAssets.squareButtonSize, 
                                       SettingsAssets.squareButtonSize, Gamestate.MENU);
        this.levelsButtons[levelSources.get(Level.levelOneSource).getY()] = new Rectangle(menuX + 50, 
                                                                                          menuY + 50, 
                                                                                          192, 
                                                                                          135);
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
        }};
    }
}
