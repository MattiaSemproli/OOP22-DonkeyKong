package it.unibo.donkeykong.model.impl;

import static it.unibo.donkeykong.utilities.ModelConstants.Level.PLATFORM_BLOCK_PADDING;
import static it.unibo.donkeykong.utilities.ModelConstants.Application.SCALED_TILES_SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.model.api.EntityFactory;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.DoubleDamageComponent;
import it.unibo.donkeykong.model.ecs.impl.FreezeComponent;
import it.unibo.donkeykong.model.ecs.impl.HealthComponent;
import it.unibo.donkeykong.model.ecs.impl.ShieldComponent;
import it.unibo.donkeykong.model.ecs.impl.StarComponent;
import it.unibo.donkeykong.utilities.ModelConstants;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.ModelConstants.Action;
import it.unibo.donkeykong.utilities.ModelConstants.Barrel;
import it.unibo.donkeykong.utilities.ModelConstants.Monkey;
import it.unibo.donkeykong.utilities.ModelConstants.Player;
import it.unibo.donkeykong.utilities.ModelConstants.Powerup;
import it.unibo.donkeykong.utilities.ModelConstants.Princess;
import it.unibo.donkeykong.utilities.ModelConstants.Application;

/**
 * Gameplay class, manages a gameplay.
 */
public class GameplayImpl implements Gameplay {

    private final GameController controller;
    private final EntityFactory entityFactoryImpl;
    private final Map<Pair<Integer, Integer>, Integer> levelMap = new HashMap<>();
    private final List<Entity> entities = new ArrayList<>();
    private final Random random = new Random();
    private boolean opPowerUpSpawned;
    private boolean win;
    private final List<Integer> keyInputs;

    /**
     * Constructor.
     * 
     * @param controller the linked GameController
     */
    public GameplayImpl(final GameController controller) {
        this.controller = controller;
        this.entityFactoryImpl = new EntityFactoryImpl(this);
        this.opPowerUpSpawned = false;
        this.win = false;
        this.keyInputs = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void initializeGame(final Map<Pair<Integer, Integer>, Integer> levelMap) {
        this.levelMap.putAll(levelMap);
        this.generateInteractableEntities();
        this.createMapEntities();
        this.generatePowerUps();
    }

    private void generateInteractableEntities() {
        this.generatePlayer();
        this.entities.add(this.entityFactoryImpl.generateMonkey(new Pair<>(Monkey.LEVEL_ONE_STARTING_X, 
                                                                           Monkey.LEVEL_ONE_STARTING_Y)));
        this.entities.add(this.entityFactoryImpl.generatePrincess(new Pair<>(Princess.LEVEL_ONE_STARTING_X, 
                                                                             Princess.LEVEL_ONE_STARTING_Y)));
    }

    private void generatePlayer() {
        float x, y;
        switch (CurrentLevel.getCurrentLevel()) {
            case ONE:
                x = Player.LEVEL_ONE_STARTING_X;
                y = Player.LEVEL_ONE_STARTING_Y;
                break;
            case TWO:
                x = Player.LEVEL_TWO_STARTING_X;
                y = Player.LEVEL_TWO_STARTING_Y;
                break;
            case THREE:
                x = Player.LEVEL_THREE_STARTING_X;
                y = Player.LEVEL_THREE_STARTING_Y;
                break;
            case FOUR:
                default:
                x = Player.LEVEL_FOUR_STARTING_X;
                y = Player.LEVEL_FOUR_STARTING_Y;
                break;
        }
        this.entities.add(this.entityFactoryImpl.generatePlayer(new Pair<>(x, y)));
    }

    private void createMapEntities() {
        this.levelMap.forEach((k, v) -> {
            switch (v) {
                case ModelConstants.Level.PLATFORM_BLOCK:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlock(new Pair<>((float) k.getX() * SCALED_TILES_SIZE,
                                                                    (float) k.getY() * SCALED_TILES_SIZE
                                                                     + PLATFORM_BLOCK_PADDING)));
                    break;
                case ModelConstants.Level.COLORED_LADDER:
                    this.entities.add(this.entityFactoryImpl
                                          .generateLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE
                                                                      + ModelConstants.Level.LADDER_PADDING, 
                                                                     (float) k.getY() * SCALED_TILES_SIZE)));
                    break;
                case ModelConstants.Level.BLOCK_UP_LADDER:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlockWithUpLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE, 
                                                                                (float) k.getY() * SCALED_TILES_SIZE
                                                                                 + PLATFORM_BLOCK_PADDING)));
                    break;
                case ModelConstants.Level.BLOCK_DOWN_LADDER:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlockWithDownLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE, 
                                                                                  (float) k.getY() * SCALED_TILES_SIZE
                                                                                   + PLATFORM_BLOCK_PADDING)));
                    break;
                case ModelConstants.Level.BLOCK_BOTH_LADDER:
                    this.entities.add(this.entityFactoryImpl
                                          .generateBlockWithUpDownLadder(new Pair<>((float) k.getX() * SCALED_TILES_SIZE, 
                                                                                    (float) k.getY() * SCALED_TILES_SIZE
                                                                                     + PLATFORM_BLOCK_PADDING)));
                    break;
                default:
                    break;
            }
        });
    }

    private void generatePowerUps() {
        this.entities.add(this.entityFactoryImpl.generateHeartPowerUp(this.generateRandomPosition(Type.HEART)));
        this.entities.add(this.entityFactoryImpl.generateShieldPowerUp(this.generateRandomPosition(Type.SHIELD)));
    }

    private Pair<Float, Float> generateRandomPosition(final Type t) {
        int passX, passY;
        boolean isOnBlock, isBlock, isOccupied;
        do {
            final int x = random.nextInt(Application.TILES_IN_WIDTH);
            final int y = random.nextInt(Powerup.MIN_SPAWN, Powerup.MAX_SPAWN);
            isBlock = this.getLevelMatrixType(x, y).isPresent();
            isOnBlock = this.getLevelMatrixType(x, y + 1).isPresent();
            isOccupied = this.getEntities().stream()
                             .filter(e -> e.getEntityType() == Type.STAR
                                          || e.getEntityType() == Type.HEART
                                          || e.getEntityType() == Type.SHIELD
                                          || e.getEntityType() == Type.SNOWFLAKE)
                             .anyMatch(e -> {
                               final int eX = (int) (e.getPosition().getX() / SCALED_TILES_SIZE);
                               final int eY = (int) (e.getPosition().getY() / SCALED_TILES_SIZE);
                               return eY == y && (eX == x || eX + 1 == x || eX - 1 == x);
                             });
            passX = x;
            passY = y;
        } while (!isOnBlock || isBlock || isOccupied);
        passX *= SCALED_TILES_SIZE;
        passY *= SCALED_TILES_SIZE;
        if (t == Type.HEART) {
            passY += Powerup.HEART_Y_PADDING;
            passX += Powerup.HEART_X_PADDING;
        } else if (t == Type.SHIELD) {
            passY += Powerup.SHIELD_Y_PADDING;
            passX += Powerup.SHIELD_X_PADDING;
        } else if (t == Type.SNOWFLAKE) {
            passY += Powerup.FREEZE_PADDING;
            passX += Powerup.FREEZE_PADDING;
        } else if (t == Type.STAR) {
            passY += Powerup.STAR_PADDING;
            passX += Powerup.STAR_PADDING;
        }
        return new Pair<>((float) passX, (float) passY + PLATFORM_BLOCK_PADDING);
    }

    private Optional<Type> getLevelMatrixType(final int x, final int y) {
        switch (this.levelMap.get(new Pair<>(x, y))) {
            case ModelConstants.Level.PLATFORM_BLOCK:
            case ModelConstants.Level.COLORED_LADDER:
            case ModelConstants.Level.WHITE_LADDER:
            case ModelConstants.Level.BLOCK_UP_LADDER:
            case ModelConstants.Level.BLOCK_DOWN_LADDER:
            case ModelConstants.Level.BLOCK_BOTH_LADDER:
                return Optional.of(Type.BLOCK);
            default:
                return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWin() {
        return this.win;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWin() {
        this.win = true;
        Gamestate.setGamestate(Gamestate.WIN);
        this.stopTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        if (!this.getEntities().stream()
                               .anyMatch(e -> e.getEntityType() == Type.PLAYER)) {
            Gamestate.setGamestate(Gamestate.DEATH);
            this.stopTimer();
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkIsOver() {
        if (!isOver() && !this.hasPlayerLife()) {
            this.removePlayer();
        }
    }

    private boolean hasPlayerLife() {
        return this.getEntities().stream()
                                 .filter(e -> e.getEntityType() == Type.PLAYER)
                                 .findFirst().get().getComponent(HealthComponent.class).get().getLives() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateKeyPressed(final int keyCode) {
        if (Action.isMovementCode(keyCode)) {
            this.keyInputs.add(0, keyCode);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateKeyReleased(final int keyCode) {
        if (keyCode == Action.ESCAPE) {
            Gamestate.setGamestate(Gamestate.PAUSE);
            resetKeys();
            this.controller.pauseTimer();
        } else {
            if (this.keyInputs.contains(keyCode)) {
                this.keyInputs.removeAll(Collections.singleton(keyCode));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getInputs() {
        return new ArrayList<>(this.keyInputs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetKeysOnFocusLost() {
        if (Gamestate.getGamestate().equals(Gamestate.PLAYING)) {
            Gamestate.setGamestate(Gamestate.PAUSE);
            resetKeys();
            this.controller.pauseTimer();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetKeys() {
        this.keyInputs.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void throwBarrel(final Pair<Float, Float> position) {
        final Entity barrel = this.entityFactoryImpl.generateBarrel(position);
        if (random.nextInt(Barrel.TOTAL_PU_PROBABILITY) < Barrel.DOUBLE_DAMAGE_INDEX) {
            barrel.getComponent(DoubleDamageComponent.class).get().setDoubleDamage(true);
        }
        this.entities.add(barrel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spawnOpPowerUp() {
        if (!this.opPowerUpSpawned) {
            this.entities.add(this.entityFactoryImpl.generateSnowflakePowerUp(this.generateRandomPosition(Type.SNOWFLAKE)));
            this.entities.add(this.entityFactoryImpl.generateStarPowerUp(this.generateRandomPosition(Type.STAR)));
            this.opPowerUpSpawned = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveOpPowerUpRandom() {
        if (this.opPowerUpSpawned) {
            this.entities.stream()
                         .filter(e -> e.getEntityType() == Type.STAR
                                      || e.getEntityType() == Type.SNOWFLAKE)
                         .forEach(e -> e.setPosition(this.generateRandomPosition(e.getEntityType())));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Type> getActivePowerUps() {
        final List<Type> activePowerUps = new ArrayList<>();
        this.entities.stream()
                     .filter(e -> e.getEntityType() == Type.PLAYER).findFirst()
                     .ifPresent(e -> {
                        if (e.getComponent(StarComponent.class).get().isInvincible()) {
                            activePowerUps.add(Type.STAR);
                        }
                        if (e.getComponent(ShieldComponent.class).get().isShielded()) {
                            activePowerUps.add(Type.SHIELD);
                        }
                        if (e.getComponent(FreezeComponent.class).get().isFrozen()) {
                            activePowerUps.add(Type.SNOWFLAKE);
                        }
                     });
        return activePowerUps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(this.entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final Entity entity) {
        this.entities.add(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEntity(final Entity entity) {
        this.entities.remove(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllBarrels() {
        this.entities.removeAll(entities.stream().filter(e -> e.getEntityType() == Type.BARREL).toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removePlayer() {
        this.entities.removeIf(e -> e.getEntityType() == Type.PLAYER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSpawnedOpPowerUp() {
        return this.opPowerUpSpawned;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopTimer() {
        if (this.controller != null) {
            this.controller.stopTimer();
        }
    }
}
