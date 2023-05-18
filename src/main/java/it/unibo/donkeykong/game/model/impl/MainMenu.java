package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Main menu model.
 */
public class MainMenu implements ViewModel {

    private final ButtonImpl[] funcButtons = new ButtonImpl[MenuAssets.numFunctionButtons];
    private final ButtonImpl[] utilityButtons = new ButtonImpl[MenuAssets.numUtilityButtons];

    /**
     * Constructor.
     */
    public MainMenu() {
        this.createFuncButtons();
        this.createUtilityButtons();
    }

    private void createFuncButtons() {
        this.funcButtons[MenuAssets.playB] = new ButtonImpl(menuSources.get(MenuAssets.playButton),
                menuX + (MenuAssets.menuTextureBox - MenuAssets.buttonWidth) / 2,
                menuY + MenuAssets.menuTextureBox / 10,
                MenuAssets.buttonWidth, MenuAssets.buttonHeight, Gamestate.PLAYING);
        this.funcButtons[MenuAssets.levelsB] = new ButtonImpl(menuSources.get(MenuAssets.levelsButton),
                menuX + (MenuAssets.menuTextureBox - MenuAssets.buttonWidth) / 2,
                menuY + MenuAssets.menuTextureBox / 10 + MenuAssets.buttonHeight,
                MenuAssets.buttonWidth, MenuAssets.buttonHeight, Gamestate.CHOSING_LEVELS);
    }

    private void createUtilityButtons() {
        this.utilityButtons[MenuAssets.settingsB] = new ButtonImpl(menuSources.get(MenuAssets.settingsButton),
                menuX + MenuAssets.menuTextureBox / 12,
                menuY + MenuAssets.menuTextureBox - MenuAssets.buttonHeight - MenuAssets.menuTextureBox / 8,
                MenuAssets.buttonWidth, MenuAssets.buttonHeight, Gamestate.SETTINGS);
        this.utilityButtons[MenuAssets.quitB] = new ButtonImpl(menuSources.get(MenuAssets.quitButton),
                menuX + MenuAssets.menuTextureBox - MenuAssets.buttonWidth - MenuAssets.menuTextureBox / 12,
                menuY + MenuAssets.menuTextureBox - MenuAssets.buttonHeight - MenuAssets.menuTextureBox / 8,
                MenuAssets.buttonWidth, MenuAssets.buttonHeight, Gamestate.EXIT);
    }

    @Override
    public final ArrayList<ButtonImpl> getButtons() {
        return new ArrayList<ButtonImpl>() {{
                addAll(Arrays.asList(funcButtons));
                addAll(Arrays.asList(utilityButtons));
        }};
    }

    @Override
    public Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>();
    }
}
