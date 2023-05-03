package it.unibo.donkeykong.view;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.SettingsController;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

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
    }

    @Override
    public final void draw(final Graphics g) {
        this.drawBackgroundAssets(g);
        this.settingsController.getSettings().draw(g);
    }

    private void drawBackgroundAssets(final Graphics g) {
        g.drawImage(new ImageIcon(MenuAssets.menuBackground).getImage(), 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT,
                null);
        g.drawImage(new ImageIcon(MenuAssets.menuTexture).getImage(),
                menuX, menuY,
                MenuAssets.menuTextureBox, MenuAssets.menuTextureBox, null);
    }

}