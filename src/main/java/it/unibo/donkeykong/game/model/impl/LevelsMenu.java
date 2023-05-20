package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.getLevelSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Constants.Level;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Levels menu model.
 */
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
        this.backHome = new ButtonImpl(getSettingsSources().get(SettingsAssets.homeButton),
                                       menuX + MenuAssets.menuTextureBox - SettingsAssets.homeButtonRightDistance, 
                                       menuY + MenuAssets.menuTextureBox - SettingsAssets.homeButtonBottomDistance, 
                                       SettingsAssets.squareButtonSize, 
                                       SettingsAssets.squareButtonSize, Gamestate.MENU);
        this.levelOneButton = new ButtonImpl(getLevelSources().get(Level.levelOneSource).getX(),
                                             menuX + 50, 
                                             menuY + 50, 
                                             192, 
                                             135, Gamestate.PLAYING);
        this.levelsButtons[getLevelSources().get(Level.levelTwoSource).getY()] = new Rectangle(menuX + MenuAssets.menuTextureBox - 50 - 192, 
                                                                                          menuY + 50, 
                                                                                          192, 
                                                                                          135);
        this.levelsButtons[getLevelSources().get(Level.levelThreeSource).getY()] = new Rectangle(menuX + 50, 
                                                                                            menuY + 50 + 135 + 15,
                                                                                            192, 
                                                                                            135);
        this.levelsButtons[getLevelSources().get(Level.levelFourSource).getY()] = new Rectangle(menuX + MenuAssets.menuTextureBox - 50 - 192, 
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
    public final Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>() {{
            put(levelsButtons[getLevelSources().get(Level.levelTwoSource).getY()], getLevelSources().get(Level.levelTwoSource).getX());
            put(levelsButtons[getLevelSources().get(Level.levelThreeSource).getY()], getLevelSources().get(Level.levelThreeSource).getX());
            put(levelsButtons[getLevelSources().get(Level.levelFourSource).getY()], getLevelSources().get(Level.levelFourSource).getX());
        }};
    }
}
