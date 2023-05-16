package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.Game;
import it.unibo.donkeykong.view.GameView;

/**
 * Game controller.
 */
public class GameController implements GameEngine {

    private final Game game;
    private final GameView gameView;

    /**
     * Constructor.
     */
    public GameController() {
        this.game = new Game();
        this.gameView = new GameView(this);
    }

    @Override
    public final void update() {
        this.gameView.update();
    }

    @Override
    public final void draw(final Graphics g) {
        this.gameView.draw(g);
    }

    /**
     * Get the game model.
     * @return new game model.
     */
    public final Game getGame() {
        return this.game;
    }

}
