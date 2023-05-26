package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Map;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.api.GenericController;
import it.unibo.donkeykong.game.model.api.Button;
import it.unibo.donkeykong.game.model.impl.EndPause;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.ButtonFuncUtilities;
import it.unibo.donkeykong.utilities.Constants.Audio;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.EndPauseView;

/**
 * Pause controller.
 */
public class EndPauseController implements MouseListener, KeyListener, GameEngine, GenericController {

    private final Application application;
    private final EndPauseView endPauseView;
    private final EndPause endPause;

    /**
     * Constructor.
     * 
     * @param application the application.
     */
    public EndPauseController(final ApplicationImpl application) {
        this.application = application;
        this.endPauseView = new EndPauseView(this);
        this.endPause = new EndPause();
    }

    @Override
    public final void update() {
        this.endPauseView.update();
    }

    @Override
    public final void draw(final Graphics g) {
        this.endPauseView.draw(g);
    }

    @Override
    public final Map<Button, BufferedImage> getButtonsFromModel() {
        return this.endPause.getButtons();
    }

    @Override
    public final Map<Rectangle, BufferedImage> getAlternativeButtonsFromModel() {
        return this.endPause.getAlternativeButtons();
    }

    @Override
    public final void mousePressed(final MouseEvent e) {
        ButtonFuncUtilities.getButtonPressed(e, this.endPause.getButtons().keySet()).ifPresent(b -> {
            if (b.getButtonGamestate().equals(Gamestate.PLAYING)) {
                if (Gamestate.getGamestate() == Gamestate.PAUSE) {
                    this.application.getGameController().startTimer();
                } else if (Gamestate.getGamestate() == Gamestate.WIN 
                           || Gamestate.getGamestate() == Gamestate.DEATH) {
                    AudioUtilities.playSoundtrack(Audio.gameMusic1);
                    this.application.startGameController();        
                }
            }
            b.applyGamestate();
        });
        if (Gamestate.getGamestate().equals(Gamestate.MENU)) {
            AudioUtilities.playSoundtrack(Audio.menuMusic0);
            this.application.getGameController().stopTimer();
        }
        this.endPause.mute(e).ifPresent(mute -> AudioUtilities.setMuted(mute));
        this.endPause.setTheme(e);
    }

    @Override
    public final void keyReleased(final KeyEvent e) {
        if (Gamestate.getGamestate().equals(Gamestate.PAUSE) && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Gamestate.setGamestate(Gamestate.PLAYING);
            this.application.getGameController().startTimer();
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {
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
