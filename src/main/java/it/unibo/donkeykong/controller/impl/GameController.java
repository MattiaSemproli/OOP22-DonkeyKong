package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.api.Gameplay;
import it.unibo.donkeykong.game.model.impl.Game;
import it.unibo.donkeykong.game.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.ButtonFuncUtilities;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.GameView;

/**
 * Game controller.
 */
public class GameController implements GameEngine, MouseListener, KeyListener {

    private final GameView gameView;
    private final Game game;
    private final List<Integer> keyInputs;
    private final Gameplay gameplay;
    private Timer timer;
    private int seconds;

    /**
     * Constructor.
     */
    public GameController() {
        this.gameView = new GameView(this);
        this.game = new Game();
        this.keyInputs = new ArrayList<>();
        this.gameplay = new GameplayImpl(this);
        this.initializeTimer();
        this.startTimer();
    }

    @Override
    public final void update() {
        this.gameplay.getEntities().forEach(e -> e.getAllComponents().forEach(c -> c.update()));
        this.gameView.update();
    }

    @Override
    public final void draw(final Graphics g) {
        this.gameView.draw(g);
    }

    @Override
    public final void mousePressed(final MouseEvent e) {
        ButtonFuncUtilities.getButtonPressed(e, this.game.getButtons().keySet()).ifPresent(b -> b.applyGamestate());
        if (Gamestate.getGamestate().equals(Gamestate.PAUSE)) {
            this.keyInputs.removeAll(keyInputs);
            this.pauseTimer();
        }
    }

    @Override
    public final void keyPressed(final KeyEvent e) { 
        if (e.getKeyCode() != KeyEvent.VK_ESCAPE) {
            if (e.getKeyCode() == KeyEvent.VK_A
                || e.getKeyCode() == KeyEvent.VK_LEFT
                || e.getKeyCode() == KeyEvent.VK_D
                || e.getKeyCode() == KeyEvent.VK_RIGHT
                || e.getKeyCode() == KeyEvent.VK_SPACE) {
                this.keyInputs.add(0, e.getKeyCode());
            }
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Gamestate.setGamestate(Gamestate.PAUSE);
            this.keyInputs.removeAll(keyInputs);
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
            this.keyInputs.removeAll(keyInputs);
            this.pauseTimer();
        }
    }

    /**
     * Get the game model.
     * 
     * @return the game model.
     */
    public final Game getGame() {
        return this.game;
    }

    /**
     * Get the gameplay model.
     * 
     * @return the gameplay model.
     */
    public final Gameplay getGameplay() {
        return this.gameplay;
    }

    /**
     * Get all the inputs.
     * 
     * @return list of keys pressed.
     */
    public final ArrayList<Integer> getInputs() {
        return new ArrayList<>(this.keyInputs);
    }

    private void initializeTimer() {
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
        this.seconds = 0;
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
    public final int getSeconds() {
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
