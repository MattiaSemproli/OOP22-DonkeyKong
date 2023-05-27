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
import it.unibo.donkeykong.game.model.impl.MainMenu;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.ButtonFuncUtilities;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Constants.Audio;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.MainMenuView;

/**
 * Main menu controller.
 */
public class MainMenuController implements MouseListener, GameEngine, GenericController {

    private final Application application;
    private final MainMenuView menuView;
    private final MainMenu menu;

    /**
     * Constructor.
     * 
     * @param application the application.
     */
    public MainMenuController(final ApplicationImpl application) {
        this.application = application;
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

    @Override
    public final Map<Button, BufferedImage> getButtonsFromModel() {
        return this.menu.getButtons();
    }

    @Override
    public final Map<Rectangle, BufferedImage> getAlternativeButtonsFromModel() {
        return this.menu.getAlternativeButtons();
    }

    @Override
    public final void mousePressed(final MouseEvent e) {
        ButtonFuncUtilities.getButtonPressed(e, this.menu.getButtons().keySet()).ifPresent(b -> {
            if (b.getButtonGamestate().equals(Gamestate.PLAYING)) {
                AudioUtilities.playSoundtrack(Audio.gameMusic0);
                CurrentLevel.setCurrentLevel(CurrentLevel.ONE);
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
