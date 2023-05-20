package it.unibo.donkeykong.game.model.impl;

import java.awt.Graphics;
import java.util.ArrayList;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.game.model.api.Entity;
import it.unibo.donkeykong.game.model.api.Gameplay;
import it.unibo.donkeykong.game.model.api.Level;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.Pair;

public class GameplayImpl implements Gameplay, GameEngine {

    private final EntityFactoryImpl entityFactoryImpl;
    private final GameController controller;
    private final Level level;
    private ArrayList<Entity> entities = new ArrayList<>();

    public GameplayImpl(final GameController controller) {
        this.level = new LevelImpl(Constants.Level.levelOne);
        this.controller = controller;
        this.entityFactoryImpl = new EntityFactoryImpl(this);
        this.initializeGame();
    }

    private void initializeGame() {
        this.entities.add(this.entityFactoryImpl.generatePlayer(new Pair<Float,Float>((float) 36, (float) 588)));
        this.entities.add(this.entityFactoryImpl.generateMonkey(new Pair<Float,Float>((float) 24, (float) 108)));
        this.entities.add(this.entityFactoryImpl.generatePrincess(new Pair<Float,Float>((float) 240, (float) 42)));
        this.createMapEntities();
    }

    private void createMapEntities() {
        this.level.getLevelData().forEach((k, v) -> {
            switch (v) {
                case Constants.Level.platformBlock:
                    this.entities.add(this.entityFactoryImpl.generateBlock(new Pair<>((float) k.getX() * 48, (float) k.getY() * 48)));
                    break;
                case Constants.Level.whiteLadder:
                    this.entities.add(this.entityFactoryImpl.generateLadder(new Pair<>((float) k.getX() * 48, (float) k.getY() * 48)));
                    break;
                case Constants.Level.blockWithUpperLadder:
                    this.entities.add(this.entityFactoryImpl.generateBlockWithUpLadder(new Pair<>((float) k.getX() * 48, (float) k.getY() * 48)));
                    break;
                case Constants.Level.blockWithLowerLadder:
                    this.entities.add(this.entityFactoryImpl.generateBlockWithDownLadder(new Pair<>((float) k.getX() * 48, (float) k.getY() * 48)));
                    break;
                case Constants.Level.blockWithDoubleLadder:
                    this.entities.add(this.entityFactoryImpl.generateBlockWithUpDownLadder(new Pair<>((float) k.getX() * 48, (float) k.getY() * 48)));
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(final Graphics g) {
    }

    @Override
    public final ArrayList<Entity> getEntities() {
        return this.entities;
    }

    @Override
    public final GameController getController() {
        return this.controller;
    }
}
