package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Map;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.api.GenericController;
import it.unibo.donkeykong.game.model.api.Button;
import it.unibo.donkeykong.game.model.impl.LevelsMenu;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.ButtonFuncUtilities;
import it.unibo.donkeykong.utilities.Constants.Audio;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.LevelsMenuView;

/**
 * Levels menu controller.
 */
public class LevelsMenuController implements MouseListener, GameEngine, GenericController {

    private final Application application;
    private final LevelsMenuView levelsMenuView;
    private final LevelsMenu levelsMenu;

    /**
     * Constructor.
     * 
     * @param application the application.
     */
    public LevelsMenuController(final ApplicationImpl application) {
        this.application = application;
        this.levelsMenuView = new LevelsMenuView(this);
        this.levelsMenu = new LevelsMenu();
    }

    @Override
    public final void update() {
        this.levelsMenuView.update();
    }

    @Override
    public final void draw(final Graphics g) {
        this.levelsMenuView.draw(g);
    }

    @Override
    public final Map<Button, BufferedImage> getButtonsFromModel() {
        return this.levelsMenu.getButtons();
    }

    @Override
    public final Map<Rectangle, BufferedImage> getAlternativeButtonsFromModel() {
        return this.levelsMenu.getAlternativeButtons();
    }
    
    @Override
    public final void mousePressed(final MouseEvent e) {
        ButtonFuncUtilities.getButtonPressed(e, this.levelsMenu.getButtons().keySet()).ifPresent(b -> {
            if (b.getButtonGamestate().equals(Gamestate.PLAYING)) {
                AudioUtilities.playSoundtrack(Audio.gameMusic0);
                this.application.startGameController();
            }
            b.applyGamestate();
        });
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
