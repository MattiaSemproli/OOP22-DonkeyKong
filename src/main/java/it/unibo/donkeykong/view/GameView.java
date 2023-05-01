package it.unibo.donkeykong.view;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;

public class GameView implements GameEngine{

    private final GameController gameController;

    public GameView(final GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        this.gameController.getGame();
    }

}
