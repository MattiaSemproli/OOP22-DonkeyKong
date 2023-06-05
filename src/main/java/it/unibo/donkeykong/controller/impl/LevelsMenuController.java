package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.model.impl.LevelsMenu;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.impl.LevelsMenuView;

/**
 * Levels menu controller.
 */
public class LevelsMenuController {

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

    public void handleChoosenLevel(final CurrentLevel level) {
        this.levelsMenu.setLevel(level);
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
}
