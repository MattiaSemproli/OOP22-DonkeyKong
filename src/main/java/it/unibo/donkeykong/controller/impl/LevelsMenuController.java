package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.LevelsMenu;
import it.unibo.donkeykong.utilities.ButtonFuncUtilities;
import it.unibo.donkeykong.view.LevelsMenuView;

public class LevelsMenuController implements MouseListener, GameEngine {

    private final ApplicationImpl application;
    private final LevelsMenuView levelsMenuView;
    private final LevelsMenu levelsMenu;

    /**
     * Constructor.
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

    /**
     * Get the levelsMenu model.
     * 
     * @return the levelsMenu model.
     */
    public final LevelsMenu getLevelsMenu() {
        return this.levelsMenu;
    }

    @Override
    public final void mousePressed(final MouseEvent e) {
        ButtonFuncUtilities.getButtonPressed(e, this.levelsMenu.getButtons()).ifPresent(b -> b.applyGamestate());
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
