package it.unibo.donkeykong.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.utilities.Constants.Window;

/**
 * Game view.
 */
public class GameView implements GameEngine {

    private final GameController gameController;

    /**
     * 
     * @param gameController set the controller to this view.
     */
    public GameView(final GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public final void update() {
    }

    @Override
    public final void draw(final Graphics g) {
        this.gameController.getGame()
                           .getDataLevel()
                           .forEach((tile, sprite) -> g.drawImage(sprite, 
                                                                  tile.x, 
                                                                  tile.y, 
                                                                  tile.width, 
                                                                  tile.height, 
                                                                  null));
        this.gameController.getGame()
                           .getButtons()
                           .forEach(b -> g.drawImage(b.getButtonImage(), 
                                                     b.getButtonPos().getX(),
                                                     b.getButtonPos().getY(), 
                                                     b.getButtonDim().getX(), 
                                                     b.getButtonDim().getY(), 
                                                     null));
        g.drawImage(new ImageIcon("src/main/resources/mariosingletry.png").getImage(), 36, Window.GAME_HEIGHT - 84, 48, 48, null);
        g.drawImage(new ImageIcon("src/main/resources/peachessingletry.png").getImage(), 48 * 5, 42, 48, 66, null);
    }

}
