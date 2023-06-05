package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.game.ecs.impl.HealthComponent;
import it.unibo.donkeykong.game.model.api.Gameplay;
import it.unibo.donkeykong.game.model.impl.Game;
import it.unibo.donkeykong.game.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.Constants.PowerupAssets;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.view.impl.GameView;

/**
 * Game controller.
 */
public class GameController implements GameEngine, MouseListener, KeyListener {

    private final GameView gameView;
    private final Game game;
    private final List<Integer> keyInputs;
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
        this.gameplay.initializeGame();
        this.gameView = new GameView(this);
        this.keyInputs = new ArrayList<>();
        this.initializeTimer();
        this.startTimer();
    }

    @Override
    public final void update() {
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

    private boolean hasPlayerLife() {
        return this.gameplay.getEntities()
                            .stream()
                            .filter(e -> e.getEntityType() == Type.PLAYER)
                            .findFirst().get().getComponent(HealthComponent.class).get().getLives() > 0;
    }

    @Override
    public final void draw(final Graphics g) {
        this.gameView.draw(g);
    }

    @Override
    public final void mousePressed(final MouseEvent e) {
        //ButtonFuncUtilities.getButtonPressed(e, this.game.getButtons().keySet()).ifPresent(b -> b.applyGamestate());
        if (Gamestate.getGamestate().equals(Gamestate.PAUSE)) {
            this.keyInputs.clear();
            this.pauseTimer();
        }
    }

    @Override
    public final void keyPressed(final KeyEvent e) { 
        if (e.getKeyCode() != KeyEvent.VK_ESCAPE
            && (e.getKeyCode() == KeyEvent.VK_A
                || e.getKeyCode() == KeyEvent.VK_LEFT
                || e.getKeyCode() == KeyEvent.VK_D
                || e.getKeyCode() == KeyEvent.VK_RIGHT
                || e.getKeyCode() == KeyEvent.VK_SPACE
                || e.getKeyCode() == KeyEvent.VK_UP
                || e.getKeyCode() == KeyEvent.VK_W
                || e.getKeyCode() == KeyEvent.VK_DOWN
                || e.getKeyCode() == KeyEvent.VK_S)) {
            this.keyInputs.add(0, e.getKeyCode());
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Gamestate.setGamestate(Gamestate.PAUSE);
            this.keyInputs.clear();
            this.pauseTimer();
        } else {
            if (this.keyInputs.contains(e.getKeyCode())) {
                this.keyInputs.removeAll(Collections.singleton(e.getKeyCode()));
            }
        }
    }

    /**
     * If we change PC's window and therefor our application loses focus, the game pauses.
     */
    public final void resetKeysOnFocusLost() {
        if (Gamestate.getGamestate().equals(Gamestate.PLAYING)) {
            Gamestate.setGamestate(Gamestate.PAUSE);
            this.keyInputs.clear();
            this.pauseTimer();
        }
    }

    /**
     * Get from the model the right animation sprite.
     * 
     * @param type of the entity.
     * @param row of the animation.
     * @param col of the animation.
     * @return the correct animation sprite.
     */
    public final BufferedImage getAnimationFromModel(final Type type, final int row, final int col) {
        return this.game.getEntityAni(type, row, col);
    }

    /**
     * Get all the data level from the model.
     * 
     * @return map of every tiles and its images.
     */
    public final Map<Rectangle, BufferedImage> getDataLevelFromModel() {
        return this.game.getDataLevel();
    }

    /**
     * Get all the entities from the gameplay.
     * 
     * @return list of entities.
     */
    public final List<Entity> getEntitiesFromGameplay() {
        return this.gameplay.getEntities();
    }

    /**
     * Get all the entities that are not blocks from the gameplay.
     * 
     * @return list of entities.
     */
    public final List<Entity> getInteractableEntitiesFromGameplay() {
        return this.gameplay.getEntities()
                   .stream().filter(e -> e.getEntityType() == Type.PLAYER
                                         || e.getEntityType() == Type.MONKEY
                                         || e.getEntityType() == Type.BARREL
                                         || e.getEntityType() == Type.PRINCESS).toList();
    }

    /**
     * Get all the powerups active in the game.
     * 
     * @return a list of type of active power ups.
     */
    public final List<Type> getListOfActivePowerUps() {
        return new ArrayList<>(this.gameplay.getActivePowerUps());
    }

    /**
     * Update the animation indexes.
     */
    public final void updateAniIndex() {
        this.game.updateAnimations();
    }

    /**
     * Get the correct animation sprite and index of the entity.
     * 
     * @param entity to get the animation.
     * @return a pair with the animation to get and the index of the animation.
     */
    public final Pair<Integer, Integer> getIdleFromModel(final Entity entity) {
        return this.game.getIdle(entity);
    }

    /**
     * Get all the inputs.
     * 
     * @return list of keys pressed.
     */
    public final List<Integer> getInputs() {
        return new ArrayList<>(this.keyInputs);
    }

    private void initializeTimer() {
        this.seconds = 0;
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                seconds++;
            }
        });
    }

    /**
     * Start the game timer.
     */
    public final void startTimer() {
        this.timer.start();
    }

    /**
     * Stop and reset the game timer.
     */
    public final void stopTimer() {
        this.timer.stop();
    }

    /**
     * Pause temporarily the game timer.
     */
    public final void pauseTimer() {
        this.timer.stop();
    }

    /**
     * Get seconds passed from game start.
     * 
     * @return seconds passed.
     */
    public final float getSeconds() {
        return this.seconds;
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }
}
