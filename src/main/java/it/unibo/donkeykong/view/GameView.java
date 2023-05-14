package it.unibo.donkeykong.view;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;

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
    }

}
