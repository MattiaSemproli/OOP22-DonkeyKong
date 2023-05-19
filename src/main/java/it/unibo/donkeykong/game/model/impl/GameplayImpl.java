package it.unibo.donkeykong.game.model.impl;

import java.awt.Graphics;
import java.util.ArrayList;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.game.model.api.Entity;
import it.unibo.donkeykong.game.model.api.Gameplay;

public class GameplayImpl implements Gameplay, GameEngine {

    private final EntityFactoryImpl entityFactoryImpl;
    private final GameController controller;
    private ArrayList<Entity> entities = new ArrayList<>();

    public GameplayImpl(final GameController controller) {
        this.controller = controller;
        this.entityFactoryImpl = new EntityFactoryImpl();
        this.initializeGame();
    }

    private void initializeGame() {
        this.entityFactoryImpl.generatePlayer();
        this.entityFactoryImpl.generateMonkey();
        this.entityFactoryImpl.generatePrincess();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
    }

    @Override
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    @Override
    public GameController getController() {
        return this.controller;
    }
}
