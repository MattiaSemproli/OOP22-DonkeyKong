package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.Game;
import it.unibo.donkeykong.game.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.ButtonFuncUtilities;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.GameView;

/**
 * Game controller.
 */
public class GameController implements GameEngine, MouseListener, KeyListener {

    private final ApplicationImpl application;
    private final GameView gameView;
    private final Game game;
    private final List<KeyEvent> keyInputs;
    private GameplayImpl gameplay;

    /**
     * Constructor.
     * 
     * @param application linked to this controller.
     */
    public GameController(final ApplicationImpl application) {
        this.application = application;
        this.gameView = new GameView(this);
        this.game = new Game();
        this.keyInputs = new ArrayList<>();
    }

    /**
     * Create a new Gameplay.
     */
    public void startGame() {
        this.gameplay = new GameplayImpl(this);
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

    /**
     * Get the game model.
     * @return the game model.
     */
    public final Game getGame() {
        return this.game;
    }

    /**
     * Get the gameplay model.
     * @return the gameplay model.
     */
    public final GameplayImpl getGameplay() {
        return this.gameplay;
    }

    @Override
    public final void mousePressed(final MouseEvent e) {
        ButtonFuncUtilities.getButtonPressed(e, this.game.getButtons()).ifPresent(b -> b.applyGamestate());
    }

    @Override
    public final void keyPressed(final KeyEvent e) { 
        if (e.getKeyCode() != KeyEvent.VK_ESCAPE) {
            if (e.getKeyCode() == KeyEvent.VK_A
                || e.getKeyCode() == KeyEvent.VK_D
                || e.getKeyCode() == KeyEvent.VK_SPACE) {
                this.keyInputs.add(e);
            }
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Gamestate.setGamestate(Gamestate.PAUSE);
        } else {
            if (this.keyInputs.contains(e)) {
                this.keyInputs.remove(e);
            }
        }
    }

    /**
     * Get all the inputs.
     * 
     * @return list of keys pressed.
     */
    public final ArrayList<KeyEvent> getInputs() {
        return new ArrayList<>(this.keyInputs);
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
