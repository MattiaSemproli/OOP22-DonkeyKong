package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.controller.api.Controller;
import it.unibo.donkeykong.model.impl.MainMenu;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.api.View;
import it.unibo.donkeykong.view.impl.MainMenuView;

/**
 * Main menu controller, manages main menu view and model and interaction.
 */
public class MainMenuController implements Controller {

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

    /**
     * Start game controller.
     */
    public void startGameController() {
        this.application.startGameController();
    }
   
    /**
     * Tell the model to start level.
     */
    public void startLevel() {
        this.menu.setLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyGamestate(final Gamestate gamestate) {
        this.menu.applyGamestate(gamestate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView() {
        return this.menuView;
    }
}
