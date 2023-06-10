package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.controller.api.Controller;
import it.unibo.donkeykong.model.impl.LevelsMenu;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.api.View;
import it.unibo.donkeykong.view.impl.LevelsMenuView;

/**
 * Levels menu controller, manages levels menu view and model and interaction.
 */
public class LevelsMenuController implements Controller {

    private final Application application;
    private final LevelsMenuView levelsMenuView;
    private final LevelsMenu levelsMenu;

    /**
     * Constructor.
     * 
     * @param application the linked application
     */
    public LevelsMenuController(final Application application) {
        this.application = application;
        this.levelsMenuView = new LevelsMenuView(this);
        this.levelsMenu = new LevelsMenu();
    }

    /**
     * Start game controller.
     */
    public final void startGameController() {
        this.application.startGame();
    }

    /**
     * Tell the model which level to set.
     * 
     * @param level the choosen level.
     */
    public void handleChoosenLevel(final CurrentLevel level) {
        this.levelsMenu.setLevel(level);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyGamestate(final Gamestate gamestate) {
        this.levelsMenu.applyGamestate(gamestate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.levelsMenuView;
    }
}
