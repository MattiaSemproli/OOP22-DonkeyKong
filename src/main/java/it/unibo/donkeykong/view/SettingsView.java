package it.unibo.donkeykong.view;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.SettingsController;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Settings view, manages settings graphics.
 */
public class SettingsView implements GameEngine {

    private final SettingsController settingsController;

    /**
     * Constructor.
     * 
     * @param settingsController the linked controller.
     */
    public SettingsView(final SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    @Override
    public final void update() {
    }

    @Override
    public final void draw(final Graphics g) {
        this.drawBackgroundAssets(g);
        this.settingsController.getButtonsFromModel()
                               .forEach((b, i) -> g.drawImage(i, 
                                                         b.getButtonPos().getX(), 
                                                         b.getButtonPos().getY(),
                                                         b.getButtonDim().getX(),
                                                         b.getButtonDim().getY(), null));
        this.settingsController.getAlternativeButtonsFromModel()
                               .forEach((rectangle, image) -> g.drawImage(image, 
                                                                          rectangle.x, 
                                                                          rectangle.y, 
                                                                          rectangle.width,
                                                                          rectangle.height, null));
    }

    private void drawBackgroundAssets(final Graphics g) {
        g.drawImage(getMenuSources().get(MenuAssets.menuBackground), 
                    0, 
                    0, 
                    Window.GAME_WIDTH, 
                    Window.GAME_HEIGHT, null);
        g.drawImage(getMenuSources().get(MenuAssets.menuTexture), 
                    menuX, 
                    menuY, 
                    MenuAssets.menuTextureBox, 
                    MenuAssets.menuTextureBox, null);
        g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                    menuX + (MenuAssets.menuTextureBox - SettingsAssets.settingsTextWidth) / 2,
                    menuY - (SettingsAssets.settingsTextHeight / 2),
                    SettingsAssets.settingsTextWidth,
                    SettingsAssets.settingsTextHeight, null);
    }
}
