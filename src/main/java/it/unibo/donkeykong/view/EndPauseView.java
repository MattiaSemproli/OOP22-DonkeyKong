package it.unibo.donkeykong.view;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.EndPauseController;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;

/**
 * Pause view.
 */
public final class EndPauseView implements GameEngine {

    private final EndPauseController endPauseController;

    /**
     * Constructor.
     * 
     * @param endPauseController set the controller to this view.
     */
    public EndPauseView(final EndPauseController endPauseController) {
        this.endPauseController = endPauseController;
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
            this.endPauseController.getButtonsFromModel()
                                .forEach((b, i) -> g.drawImage(i, 
                                                          b.getButtonPos().getX(), 
                                                          b.getButtonPos().getY(),
                                                          b.getButtonDim().getX(),
                                                          b.getButtonDim().getY(), null));
            if (Gamestate.getGamestate().equals(Gamestate.PAUSE)) {
                this.endPauseController.getAlternativeButtonsFromModel()
                                    .forEach((rectangle, image) -> g.drawImage(image, 
                                                                               rectangle.x, 
                                                                               rectangle.y, 
                                                                               rectangle.width,
                                                                               rectangle.height, null));
            }
        }
    }
}
