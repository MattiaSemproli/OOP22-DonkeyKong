package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.model.impl.MainMenu;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.impl.MainMenuView;

/**
 * Main menu controller.
 */
public class MainMenuController {

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

    public final void startLevel() {
        this.menu.startLevel();
    }

    public final void startGameController() {
        this.application.startGameController();
    }

    public final void applyGamestate(final Gamestate gamestate) {
        this.menu.applyGamestate(gamestate);
    }

    public final MainMenuView getView() {
        return this.menuView;
    }
}
