package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.MainMenu;
import it.unibo.donkeykong.utilities.ButtonFuncUtilities;
import it.unibo.donkeykong.view.MainMenuView;

/**
 * Main menu controller.
 */
public class MainMenuController implements MouseListener, GameEngine {

    private final MainMenuView menuView;
    private final MainMenu menu;

    /**
     * Constructor.
     */
    public MainMenuController() {
        this.menuView = new MainMenuView(this);
        this.menu = new MainMenu();
    }

    @Override
    public final void update() {
        this.menuView.update();
    }

    @Override
    public final void draw(final Graphics g) {
        this.menuView.draw(g);
    }

    /**
     * Get the main menu model.
     * 
     * @return new main menu model.
     */
    public final MainMenu getMainMenu() {
        return this.menu;
    }

    @Override
    public final void mousePressed(final MouseEvent e) {
        ButtonFuncUtilities.getButtonPressed(e, this.menu.getButtons()).ifPresent(b -> b.applyGamestate());
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
