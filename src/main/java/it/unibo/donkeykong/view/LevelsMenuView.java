package it.unibo.donkeykong.view;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.LevelsMenuController;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;

/**
 * levels menu view, manages levels menu graphics.
 */
public class LevelsMenuView implements GameEngine {

    private final LevelsMenuController levelsMenuController;

    /**
     * Constructor.
     * 
     * @param levelsMenuController the linked controller.
     */
    public LevelsMenuView(final LevelsMenuController levelsMenuController) {
        this.levelsMenuController = levelsMenuController;
    }

    @Override
    public final void update() {
    }

    @Override
    public final void draw(final Graphics g) {
        this.drawBackgroundAssets(g);
        this.levelsMenuController.getButtonsFromModel()
                                 .forEach((b, i) -> g.drawImage(i, 
                                                           b.getButtonPos().getX(), 
                                                           b.getButtonPos().getY(),
                                                           b.getButtonDim().getX(),
                                                           b.getButtonDim().getY(), null));
        this.levelsMenuController.getAlternativeButtonsFromModel()
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
    }
}
