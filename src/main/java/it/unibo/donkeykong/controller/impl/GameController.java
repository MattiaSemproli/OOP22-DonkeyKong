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
import it.unibo.donkeykong.utilities.ButtonFuncUtilities;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.GameView;

/**
 * Game controller.
 */
public class GameController implements GameEngine, MouseListener, KeyListener {

    private final GameView gameView;
    private final Game game;
    private final List<KeyEvent> keyInputs;

    /**
     * Constructor.
     */
    public GameController() {
        this.gameView = new GameView(this);
        this.game = new Game();
        this.keyInputs = new ArrayList<>();
    }

    @Override
    public final void update() {
        this.gameView.update();
    }

    @Override
    public final void draw(final Graphics g) {
        this.gameView.draw(g);
    }

    /**
     * Get the game model.
     * @return new game model.
     */
    public final Game getGame() {
        return this.game;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ButtonFuncUtilities.getButtonPressed(e, this.game.getButtons()).ifPresent(b -> b.applyGamestate());
    }
    
    @Override
    public void keyPressed(KeyEvent e) { 
        if (e.getKeyCode() != KeyEvent.VK_ESCAPE) {
            this.keyInputs.add(e);
        } 
    }
   
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Gamestate.setGamestate(Gamestate.PAUSE);
        } 
        else {
            if (this.keyInputs.contains(e)) {
                this.keyInputs.remove(e);
            }
        }
    }

    public final ArrayList<KeyEvent> getInputs() {
        return new ArrayList<>(this.keyInputs);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
