package it.unibo.donkeykong.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Timer;

import it.unibo.donkeykong.controller.api.Controller;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.HealthComponent;
import it.unibo.donkeykong.model.impl.Game;
import it.unibo.donkeykong.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.Constants.Action;
import it.unibo.donkeykong.utilities.Constants.PowerupAssets;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;
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
    private float seconds;
    private int timeElapsed;

    /**
     * Constructor.
     */
    public GameController() {
        this.game = new Game();
        this.gameplay = new GameplayImpl(this);
        this.gameView = new GameView(this);
        this.gameplay.initializeGame(this.gameView.getLevelMap());
        this.initializeTimer();
    }

    /**
     * Update the game.
     */
    public void update() {
        this.timeElapsed++;
        this.gameplay.getEntities().forEach(e -> e.getAllComponents().forEach(c -> c.update()));
        if (!this.gameplay.isSpawnedOpPowerUp() && this.timeElapsed > PowerupAssets.spawnOpPowerUpDelay) {
            this.gameplay.spawnOpPowerUp();
            this.timeElapsed = 0;
        } else if (this.gameplay.isSpawnedOpPowerUp() && this.timeElapsed > PowerupAssets.moveOpPowerUpDelay) {
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

    public void resetKeys() {
        this.gameplay.resetKeys();
    }

    public void resetKeysOnFocusLost() {
        this.gameplay.resetKeysOnFocusLost();
    }

    /**
     * Handle the key pressed.
     * 
     * @param keyCode the int code of key pressed.
     */
    public void notifyKeyPressed(final int keyCode) {
        this.gameplay.updateKeyPressed(keyCode);
    }

    /**
     * Handle the key released.
     * 
     * @param keyCode the int code of key released.
     */
    public void notifyKeyReleased(final int keyCode) {
        this.gameplay.updateKeyReleased(keyCode);
    }

    private boolean hasPlayerLife() {
        return this.gameplay.getEntities().stream()
                                          .filter(e -> e.getEntityType() == Type.PLAYER)
                                          .findFirst().get().getComponent(HealthComponent.class).get().getLives() > 0;
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

    private void initializeTimer() {
        this.seconds = 0;
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                seconds++;
            }
        });
        this.timer.start();
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
        this.timer.stop();
    }

    /**
     * Get seconds passed from game start.
     * 
     * @return seconds passed.
     */
    public float getSeconds() {
        return this.seconds;
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
