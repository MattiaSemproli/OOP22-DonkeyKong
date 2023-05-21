package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Window.SCALED_TILES_SIZE;

import java.awt.Graphics;
import java.util.ArrayList;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.game.model.api.Entity;
import it.unibo.donkeykong.game.model.api.Gameplay;
import it.unibo.donkeykong.game.model.api.Level;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.Pair;

/**
 * Gameplay class, manage and initialize entities and map.
 */
public class GameplayImpl implements Gameplay, GameEngine {

    private final EntityFactoryImpl entityFactoryImpl;
    private final GameController controller;
    private final Level level;
    private ArrayList<Entity> entities = new ArrayList<>();

    /**
     * Constructor.
     * 
     * @param controller linked GameController
     */
    public GameplayImpl(final GameController controller) {
        this.level = new LevelImpl(Constants.Level.levelOne);
        this.controller = controller;
        this.entityFactoryImpl = new EntityFactoryImpl(this);
        this.initializeGame();
    }

    private void initializeGame() {
        this.entities.add(this.entityFactoryImpl.generatePlayer(new Pair<>(Constants.Entity.levelOneStartingPlayerX, 
                                                                           Constants.Entity.levelOneStartingPlayerY)));
        this.entities.add(this.entityFactoryImpl.generateMonkey(new Pair<>(Constants.Entity.levelOneStartingMonkeyX, 
                                                                           Constants.Entity.levelOneStartingMonkeyY)));
        this.entities.add(this.entityFactoryImpl.generatePrincess(new Pair<>(Constants.Entity.levelOneStartingPrincessX, 
                                                                             Constants.Entity.levelOneStartingPrincessY)));
        this.createMapEntities();
    }

    private void createMapEntities() {
        this.level.getLevelData().forEach((k, v) -> {
            switch (v) {
                case Constants.Level.platformBlock:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlock(new Pair<>((float) k.getX() * SCALED_TILES_SIZE, 
                                                                    (float) k.getY() * SCALED_TILES_SIZE)));
                    break;
                case Constants.Level.whiteLadder:
                    this.entities.add(this.entityFactoryImpl
                                          .generateLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE, 
                                                                     (float) k.getY() * SCALED_TILES_SIZE)));
                    break;
                case Constants.Level.blockWithUpperLadder:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlockWithUpLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE, 
                                                                                (float) k.getY() * SCALED_TILES_SIZE)));
                    break;
                case Constants.Level.blockWithLowerLadder:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlockWithDownLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE, 
                                                                                  (float) k.getY() * SCALED_TILES_SIZE)));
                    break;
                case Constants.Level.blockWithDoubleLadder:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlockWithUpDownLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE, 
                                                                                    (float) k.getY() * SCALED_TILES_SIZE)));
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

    @Override
    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }
}
