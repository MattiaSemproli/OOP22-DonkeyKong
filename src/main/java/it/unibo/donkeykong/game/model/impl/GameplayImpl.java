package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.platformBlockPadding;
import static it.unibo.donkeykong.utilities.Constants.Window.SCALED_TILES_SIZE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.game.ecs.impl.DoubleDamageComponent;
import it.unibo.donkeykong.game.model.api.EntityFactory;
import it.unibo.donkeykong.game.model.api.Gameplay;
import it.unibo.donkeykong.game.model.api.Level;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Constants.Monkey;
import it.unibo.donkeykong.utilities.Constants.Player;
import it.unibo.donkeykong.utilities.Constants.Window;

/**
 * Gameplay class, manage and initialize entities and map.
 */
public class GameplayImpl implements Gameplay {

    private final EntityFactory entityFactoryImpl;
    private final GameController controller;
    private final Level level;
    private final List<Entity> entities = new ArrayList<>();
    private final Random random = new Random();

    /**
     * Constructor.
     * 
     * @param controller linked GameController
     */
    public GameplayImpl(final GameController controller) {
        this.level = new LevelImpl();
        this.controller = controller;
        this.entityFactoryImpl = new EntityFactoryImpl(this);
        this.initializeGame();
    }

    private void initializeGame() {
        this.generateInteractableEntities();
        this.createMapEntities();
        this.generatePowerUps();
    }

    private void generateInteractableEntities() {
        this.generatePlayer();
        this.entities.add(this.entityFactoryImpl.generateMonkey(new Pair<>(Constants.Monkey.levelOneStartingMonkeyX, 
                                                                           Constants.Monkey.levelOneStartingMonkeyY)));
        this.entities.add(this.entityFactoryImpl.generatePrincess(new Pair<>(Constants.Princess.levelOneStartingPrincessX, 
                                                                             Constants.Princess.levelOneStartingPrincessY)));
    }

    private void generatePlayer() {
        float x, y;
        switch (CurrentLevel.getCurrentLevel()) {
            case ONE:
                x = Player.levelOneStartingPlayerX;
                y = Player.levelOneStartingPlayerY;
                break;
            case TWO:
                x = Player.levelTwoStartingPlayerX;
                y = Player.levelTwoStartingPlayerY;
                break;
            case THREE:
                x = Player.levelThreeStartingPlayerX;
                y = Player.levelThreeStartingPlayerY;
                break;
            case FOUR:
                default:
                x = Player.levelFourStartingPlayerX;
                y = Player.levelFourStartingPlayerY;
                break;
        }
        this.entities.add(this.entityFactoryImpl.generatePlayer(new Pair<>(x, y)));
    }

    private void createMapEntities() {
        this.level.getLevelData().forEach((k, v) -> {
            switch (v) {
                case Constants.Level.platformBlock:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlock(new Pair<>((float) k.getX() * SCALED_TILES_SIZE,
                                                                    (float) k.getY() * SCALED_TILES_SIZE
                                                                     + platformBlockPadding)));
                    break;
                case Constants.Level.coloredLadder:
                    this.entities.add(this.entityFactoryImpl
                                          .generateLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE
                                                                      + Constants.Level.ladderPadding, 
                                                                     (float) k.getY() * SCALED_TILES_SIZE)));
                    break;
                case Constants.Level.blockWithUpperLadder:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlockWithUpLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE, 
                                                                                (float) k.getY() * SCALED_TILES_SIZE
                                                                                 + platformBlockPadding)));
                    break;
                case Constants.Level.blockWithLowerLadder:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlockWithDownLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE, 
                                                                                  (float) k.getY() * SCALED_TILES_SIZE
                                                                                   + platformBlockPadding)));
                    break;
                case Constants.Level.blockWithDoubleLadder:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlockWithUpDownLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE, 
                                                                                    (float) k.getY() * SCALED_TILES_SIZE
                                                                                     + platformBlockPadding)));
                    break;
                default:
                    break;
            }
        });
    }

    private void generatePowerUps() {
        this.entities.add(this.entityFactoryImpl.generateHeartPowerUp(this.generateRandomPosition()));
        this.entities.add(this.entityFactoryImpl.generateShieldPowerUp(this.generateRandomPosition()));
    }

    private Pair<Float, Float> generateRandomPosition() {
        int passX, passY;
        boolean isOnBlock = false, isBlock = false, isOccupied = true;
        do {
            int x = random.nextInt(Window.TILES_IN_WIDTH);
            int y = random.nextInt((int) (Monkey.levelOneStartingMonkeyY / SCALED_TILES_SIZE) + 2,
                                   (int) (Player.levelOneStartingPlayerY / SCALED_TILES_SIZE) - 1);
            isBlock = this.level.getLevelMatrixType(x, y).isPresent();
            isOnBlock = this.level.getLevelMatrixType(x, y + 1).isPresent();
            isOccupied = this.getEntities().stream()
                              .filter(e -> e.getEntityType() == Type.STAR
                                           || e.getEntityType() == Type.HEART
                                           || e.getEntityType() == Type.SHIELD
                                           || e.getEntityType() == Type.SNOWFLAKE)
                              .anyMatch(e -> {
                                int eX = (int) (e.getPosition().getX() / SCALED_TILES_SIZE);
                                int eY = (int) (e.getPosition().getY() / SCALED_TILES_SIZE);
                                return eX == x && eY == y;
                              });
            passX = x;
            passY = y;
        } while (!isOnBlock || isBlock || isOccupied);
        return new Pair<>((float) passX * SCALED_TILES_SIZE, (float) passY * SCALED_TILES_SIZE + platformBlockPadding);
    }

    @Override
    public final void throwBarrel(final Pair<Float, Float> position) {
        final Entity barrel = this.entityFactoryImpl.generateBarrel(position);
        if (random.nextInt(Barrel.totalPUProbability) < Barrel.doubleDamageProbability) {
            barrel.getComponent(DoubleDamageComponent.class).get().setDoubleDamage(true);
        }
        this.entities.add(barrel);
    }

    @Override
    public final ArrayList<Entity> getEntities() {
        return new ArrayList<>(this.entities);
    }

    @Override
    public final GameController getController() {
        return this.controller;
    }

    @Override
    public final void removeEntity(final Entity entity) {
        this.entities.remove(entity);
    }

    @Override
    public final void removeAllBarrels() {
        this.entities.removeAll(entities.stream().filter(e -> e.getEntityType() == Type.BARREL).toList());
    }

    @Override
    public final void removePlayer() {
        this.entities.removeIf(e -> e.getEntityType() == Type.PLAYER);
    }
}
