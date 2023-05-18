package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.Pause;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.ButtonFuncUtilities;
import it.unibo.donkeykong.utilities.Constants.Audio;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.PauseView;

/**
 * Pause controller.
 */
public class PauseController implements MouseListener, KeyListener, GameEngine  {

    private final ApplicationImpl application;
    private final PauseView pauseView;
    private final Pause pause;

    /**
     * Constructor.
     */
    public PauseController(final ApplicationImpl application) {
        this.application = application;
        this.pauseView = new PauseView(this);
        this.pause = new Pause();
    }

    @Override
    public final void update() {
        this.pauseView.update();
    }

    @Override
    public final void draw(Graphics g) {
        this.pauseView.draw(g);
    }

    /**
     * Get the pause model.
     * 
     * @return pause model.
     */
    public final Pause getPause() {
        return this.pause;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ButtonFuncUtilities.getButtonPressed(e, this.pause.getButtons()).ifPresent(b -> b.applyGamestate());
        if(Gamestate.getGamestate().equals(Gamestate.MENU)){
            AudioUtilities.playSoundtrack(Audio.menuMusic0);
        }
        this.pause.mute(e).ifPresent(mute -> AudioUtilities.setMuted(mute));
        this.pause.setTheme(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (Gamestate.getGamestate().equals(Gamestate.PAUSE) && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Gamestate.setGamestate(Gamestate.PLAYING);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {      
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
