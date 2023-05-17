package it.unibo.donkeykong.game.model.impl;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.api.Gameplay;

public class GameplayImpl implements Gameplay, GameEngine {

    public GameplayImpl() {
        this.initializeGame();
    }

    private void initializeGame() {
        /* da creare dk e vari personaggi
         * caricare levels ecc.
         */
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
    }
}
