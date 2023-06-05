package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.LevelsMenu;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.api.View;
import it.unibo.donkeykong.view.impl.LevelsMenuView;

/**
 * Levels menu controller.
 */
public class LevelsMenuController implements MouseListener, GameEngine {

    private final Application application;
    private final LevelsMenuView levelsMenuView;
    private final LevelsMenu levelsMenu;

    /**
     * Constructor.
     */
    public LevelsMenuController(final Application application) {
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

    public void handleChoosenLevel(final CurrentLevel level) {
        this.levelsMenu.setLevel(level);
    }

    @Override
    public final void mousePressed(final MouseEvent e) {
        // ButtonFuncUtilities.getButtonPressed(e, this.levelsMenu.getButtons().keySet()).ifPresent(b -> {
        //     if (b.getButtonGamestate().equals(Gamestate.PLAYING)) {
        //         AudioUtilities.playSoundtrack(Audio.gameMusic0);
        //         this.levelsMenu.setLevelToPlay(b);
        //         this.application.startGameController();
        //     }
        //     //b.applyGamestate();
        // });
    }

    public final void startGameController() {
        this.application.startGameController();
    }

    public final void applyGamestate(final Gamestate gamestate) {
        this.levelsMenu.applyGamestate(gamestate);
    }

    public final LevelsMenuView getView() {
        return this.levelsMenuView;
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
