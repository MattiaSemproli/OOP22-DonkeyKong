package it.unibo.donkeykong.game.model.impl;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

/**
 * Main menu model.
 */
public class MainMenu implements GameEngine {

    private final ButtonImpl[] funcButtons = new ButtonImpl[MenuAssets.numFunctionButtons];
    private final ButtonImpl[] utilityButtons = new ButtonImpl[MenuAssets.numUtilityButtons];

    /**
     * Constructor.
     */
    public MainMenu() {
        this.createFuncButtons();
        this.createUtilityButtons();
    }

    @Override
    public final void draw(final Graphics g) {
        this.drawFuncButtons(g);
        this.drawUtilityButtons(g);
    }

    private void createFuncButtons() {
        this.funcButtons[MenuAssets.playB] = new ButtonImpl(
                menuX + (MenuAssets.menuTextureBox - MenuAssets.buttonWidth) / 2,
                menuY + MenuAssets.menuTextureBox / 10,
                MenuAssets.buttonWidth, MenuAssets.buttonHeight, Gamestate.PLAYING);
        this.funcButtons[MenuAssets.levelsB] = new ButtonImpl(
                menuX + (MenuAssets.menuTextureBox - MenuAssets.buttonWidth) / 2,
                menuY + MenuAssets.menuTextureBox / 10 + MenuAssets.buttonHeight,
                MenuAssets.buttonWidth, MenuAssets.buttonHeight, Gamestate.CHOSING_LEVELS);
    }

    private void createUtilityButtons() {
        this.utilityButtons[MenuAssets.settingsB] = new ButtonImpl(menuX + MenuAssets.menuTextureBox / 12,
                menuY + MenuAssets.menuTextureBox - MenuAssets.buttonHeight - MenuAssets.menuTextureBox / 8,
                MenuAssets.buttonWidth, MenuAssets.buttonHeight, Gamestate.SETTINGS);
        this.utilityButtons[MenuAssets.quitB] = new ButtonImpl(
                menuX + MenuAssets.menuTextureBox - MenuAssets.buttonWidth - MenuAssets.menuTextureBox / 12,
                menuY + MenuAssets.menuTextureBox - MenuAssets.buttonHeight - MenuAssets.menuTextureBox / 8,
                MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);
    }

    private void drawFuncButtons(final Graphics g) {
        g.drawImage(new ImageIcon(MenuAssets.playButton).getImage(),
                funcButtons[MenuAssets.playB].getButtonPos().getX(),
                funcButtons[MenuAssets.playB].getButtonPos().getY(),
                funcButtons[MenuAssets.playB].getButtonDim().getX(),
                funcButtons[MenuAssets.playB].getButtonDim().getY(), null);
        g.drawImage(new ImageIcon(MenuAssets.levelsButton).getImage(),
                funcButtons[MenuAssets.levelsB].getButtonPos().getX(),
                funcButtons[MenuAssets.levelsB].getButtonPos().getY(),
                funcButtons[MenuAssets.levelsB].getButtonDim().getX(),
                funcButtons[MenuAssets.levelsB].getButtonDim().getY(), null);
    }

    private void drawUtilityButtons(final Graphics g) {
        g.drawImage(new ImageIcon(MenuAssets.settingsButton).getImage(),
                utilityButtons[MenuAssets.settingsB].getButtonPos().getX(),
                utilityButtons[MenuAssets.settingsB].getButtonPos().getY(),
                utilityButtons[MenuAssets.settingsB].getButtonDim().getX(),
                utilityButtons[MenuAssets.settingsB].getButtonDim().getY(), null);
        g.drawImage(new ImageIcon(MenuAssets.quitButton).getImage(),
                utilityButtons[MenuAssets.quitB].getButtonPos().getX(),
                utilityButtons[MenuAssets.quitB].getButtonPos().getY(),
                utilityButtons[MenuAssets.quitB].getButtonDim().getX(),
                utilityButtons[MenuAssets.quitB].getButtonDim().getY(), null);
    }

    @Override
    public void update() {
    }
}