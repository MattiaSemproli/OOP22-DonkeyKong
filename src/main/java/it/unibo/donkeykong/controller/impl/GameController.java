package it.unibo.donkeykong.controller.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.common.Timer;
import it.unibo.donkeykong.controller.api.Controller;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.HealthComponent;
import it.unibo.donkeykong.model.impl.Game;
import it.unibo.donkeykong.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.Constants.Powerup;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.view.api.View;
import it.unibo.donkeykong.view.impl.GameView;

/**
 * Game controller, manages game view and model and interaction.
 */
public class GameController implements Controller {

    private final GameView gameView;
    private final Game game;
    private final Gameplay gameplay;
    private Timer timer;
    private int timeElapsed;

    /**
     * Constructor.
     */
    public GameController() {
        this.game = new Game();
        this.gameplay = new GameplayImpl(this);
        this.gameView = new GameView(this);
        this.gameplay.initializeGame(this.gameView.getLevelMap());
        this.timer = new Timer();
        this.timer.start();
    }

    /**
     * Update the game.
     */
    public void update() {
        this.timeElapsed++;
        this.gameplay.getEntities().forEach(e -> e.getAllComponents().forEach(c -> c.update()));
        if (!this.gameplay.isSpawnedOpPowerUp() && this.timeElapsed > Powerup.PUPS_SPAWN_DELAY) {
            this.gameplay.spawnOpPowerUp();
            this.timeElapsed = 0;
        } else if (this.gameplay.isSpawnedOpPowerUp() && this.timeElapsed > Powerup.PUPS_MOVE_DELAY) {
            this.gameplay.moveOpPowerUpRandom();
            this.timeElapsed = 0;
        }
        if (!hasPlayerLife()) {
            Gamestate.setGamestate(Gamestate.DEATH);
            this.stopTimer();
        } else {
            this.gameView.update();
        }
    }

    private boolean hasPlayerLife() {
        return this.gameplay.getEntities().stream()
                                          .filter(e -> e.getEntityType() == Type.PLAYER)
                                          .findFirst().get().getComponent(HealthComponent.class).get().getLives() > 0;
    }

    /**
     * Notify the model with the pressed key.
     * 
     * @param keyCode the int code of the pressed key.
     */
    public void notifyKeyPressed(final int keyCode) {
        this.gameplay.updateKeyPressed(keyCode);
    }

    /**
     * Notify the model with the released key.
     * 
     * @param keyCode the int code of the released key.
     */
    public void notifyKeyReleased(final int keyCode) {
        this.gameplay.updateKeyReleased(keyCode);
    }

    /**
     * Notify the model to reset the keys.
     */
    public void notifyResetKeys() {
        this.gameplay.resetKeys();
    }

    /**
     * Notify the model to reset the keys when the focus is lost.
     */
    public void notifyResetKeysOnFocusLost() {
        this.gameplay.resetKeysOnFocusLost();
    }

    /**
     * Get all the entities from the gameplay.
     * 
     * @return list of entities.
     */
    public List<Entity> getEntitiesFromGameplay() {
        return this.gameplay.getEntities();
    }

    /**
     * Get all the entities that are neither powerups nor blocks from the gameplay.
     * 
     * @return list of entities.
     */
    public List<Entity> getInteractableEntitiesFromGameplay() {
        return this.gameplay.getEntities().stream()
                                          .filter(e -> e.getEntityType() == Type.PLAYER
                                                       || e.getEntityType() == Type.MONKEY
                                                       || e.getEntityType() == Type.BARREL
                                                       || e.getEntityType() == Type.PRINCESS).toList();
    }

    /**
     * Get all the powerups entities the gameplay.
     * 
     * @return list of entities.
     */
    public List<Entity> getPowerupEntitiesFromGameplay() {
        return this.gameplay.getEntities().stream()
                                          .filter(e -> e.getEntityType() == Type.STAR
                                                       || e.getEntityType() == Type.HEART
                                                       || e.getEntityType() == Type.SNOWFLAKE
                                                       || e.getEntityType() == Type.SHIELD).toList();
    }

    /**
     * Get player lives.
     * 
     * @return the number of lives.
     */
    public int getPlayerLives() {
        return this.gameplay.getEntities().stream()
                            .filter(e -> e.getEntityType() == Type.PLAYER)
                            .findFirst()
                            .map(e -> e.getComponent(HealthComponent.class).get().getLives())
                            .orElse(0);
    }

    /**
     * Get all the powerups active in the game.
     * 
     * @return a list of type of active power ups.
     */
    public List<Type> getListOfActivePowerUps() {
        return new ArrayList<>(this.gameplay.getActivePowerUps());
    }

    /**
     * Update the animation indexes.
     */
    public void updateAniIndex() {
        this.game.updateAnimations();
    }

    /**
     * Get the correct animation sprite and index of the entity.
     * 
     * @param entity to get the animation.
     * @return a pair with the animation to get and the index of the animation.
     */
    public Pair<Integer, Integer> getIdleFromModel(final Entity entity) {
        return this.game.getIdle(entity);
    }

    /**
     * Start the game timer.
     */
    public void startTimer() {
        this.timer.start();
    }

    /**
     * Stop and reset the game timer.
     */
    public void stopTimer() {
        this.timer.stop();
    }

    /**
     * Pause temporarily the game timer.
     */
    public void pauseTimer() {
        this.timer.pause();
    }

    /**
     * Resume the game timer.
     */
    public void resumeTimer() {
        this.timer.resume();
    }

    /**
     * Get time passed from game start.
     * 
     * @return time passed.
     */
    public double getElapsedTime() {
        return this.timer.getElapsedSeconds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyGamestate(final Gamestate gamestate) {
        this.game.applyGamestate(gamestate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView() {
        return this.gameView;
    }
}
