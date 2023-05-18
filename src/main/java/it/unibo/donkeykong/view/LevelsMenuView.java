package it.unibo.donkeykong.view;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.LevelsMenuController;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;

/**
 * Levels menu view.
 */
public class LevelsMenuView implements GameEngine {

    private final LevelsMenuController levelsMenuController;

    /**
     * 
     * @param levelsMenuController set the controller to this view.
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
        this.levelsMenuController.getLevelsMenu()
                                 .getButtons()
                                 .forEach(b -> g.drawImage(b.getButtonImage(), 
                                                           b.getButtonPos().getX(), 
                                                           b.getButtonPos().getY(),
                                                           b.getButtonDim().getX(),
                                                           b.getButtonDim().getY(),
                                                           null));
        this.levelsMenuController.getLevelsMenu()
                                 .getAlternativeButtons()
                                 .forEach((rectangle, image) -> g.drawImage(image, 
                                                                            rectangle.x, 
                                                                            rectangle.y, 
                                                                            rectangle.width,
                                                                            rectangle.height,
                                                                            null));
    }

    private void drawBackgroundAssets(final Graphics g) {
        g.drawImage(menuSources.get(MenuAssets.menuBackground), 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT, null);
        g.drawImage(menuSources.get(MenuAssets.menuTexture), menuX, menuY, MenuAssets.menuTextureBox, MenuAssets.menuTextureBox, null);
    }
}
