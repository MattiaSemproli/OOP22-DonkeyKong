package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.Game;
import it.unibo.donkeykong.view.GameView;

public class GameController implements GameEngine {

    private final GameView gameView;

    public GameController() {
        this.gameView = new GameView(this);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
        this.gameView.draw(g);
    }

    public final Game getGame() {
        return new Game();
    }

}
