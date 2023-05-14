package it.unibo.donkeykong.view;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.controller.impl.SettingsController;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;
import static it.unibo.donkeykong.utilities.ResourceFuncUtilities.getSources;

/**
 * Settings view.
 */
public class SettingsView implements GameEngine {

    private final SettingsController settingsController;

    /**
     * 
     * @param settingsController set the controller to this view.
     */
    public SettingsView(final SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    @Override
    public void update() {
        this.settingsController.getSettings().update();
    }

    @Override
    public final void draw(final Graphics g) {
        this.drawBackgroundAssets(g);
        this.settingsController.getSettings().draw(g);
    }

    private void drawBackgroundAssets(final Graphics g) {
        g.drawImage(getSources(MenuAssets.menuBackground), 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT,null);
        g.drawImage(getSources(MenuAssets.menuTexture), menuX, menuY, MenuAssets.menuTextureBox, MenuAssets.menuTextureBox, null);
    }

}
