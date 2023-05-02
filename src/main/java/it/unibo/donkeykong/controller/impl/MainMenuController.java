package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.MainMenu;
import it.unibo.donkeykong.view.MainMenuView;

/**
 * Main menu controller.
 */
public class MainMenuController implements MouseListener, GameEngine {

    private final MainMenuView menuView;

    /**
     * Constructor.
     */
    public MainMenuController() {
        this.menuView = new MainMenuView(this);
    }

    @Override
    public void update() {
    }

    @Override
    public final void draw(final Graphics g) {
        this.menuView.draw(g);
    }

    /**
     * Get the main menu model.
     * @return new main menu model.
     */
    public final MainMenu getMainMenu() {
        return new MainMenu();
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
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
