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
        this.gameController.getGame().update();
    }

    @Override
    public final void draw(final Graphics g) {
        this.gameController.getGame().draw(g);
        g.drawImage(new ImageIcon("src/main/resources/mariosingletry.png").getImage(), 36, Window.GAME_HEIGHT - 84, 48, 48, null);
        g.drawImage(new ImageIcon("src/main/resources/peachessingletry.png").getImage(), 48 * 5, 60, 48, 48, null);
    }

}
