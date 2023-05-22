package it.unibo.donkeykong.view;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.PauseController;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;

/**
 * Pause view.
 */
public final class PauseView implements GameEngine {

    private final PauseController pauseController;

    /**
     * Constructor.
     * 
     * @param pauseController set the controller to this view.
     */
    public PauseView(final PauseController pauseController) {
        this.pauseController = pauseController;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(final Graphics g) {
        final Graphics2D pause;
        if (g instanceof Graphics2D) {
            pause = (Graphics2D) g;
            pause.setColor(new Color(0, 0, 0, SettingsAssets.pauseBgOpacity));
            pause.fillRect(0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT);
            pause.drawImage(getMenuSources().get(MenuAssets.menuTexture), 
                            menuX, 
                            menuY, 
                            MenuAssets.menuTextureBox, 
                            MenuAssets.menuTextureBox, null);
            this.pauseController.getButtonsFromModel()
                                .forEach((b, i) -> g.drawImage(i, 
                                                          b.getButtonPos().getX(), 
                                                          b.getButtonPos().getY(),
                                                          b.getButtonDim().getX(),
                                                          b.getButtonDim().getY(), null));
            this.pauseController.getAlternativeButtonsFromModel()
                                .forEach((rectangle, image) -> g.drawImage(image, 
                                                                           rectangle.x, 
                                                                           rectangle.y, 
                                                                           rectangle.width,
                                                                           rectangle.height, null));
        }
    }
}
