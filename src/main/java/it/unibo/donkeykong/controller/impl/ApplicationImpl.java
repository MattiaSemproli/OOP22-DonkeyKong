package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.game.model.impl.Game;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.view.ApplicationPanel;
import it.unibo.donkeykong.view.ApplicationWindow;

/**
 * ApplicationImpl class, manage controllers.
 */
public class ApplicationImpl implements Application {

    private ApplicationPanel dkPanel;
    private GameEngineImpl gameEngine;
    private MainMenuController mainMenuController;
    private SettingsController settingsController;
    private GameController gameController;
    private PauseController pauseController;
    private LevelsMenuController levelsMenuController;

    /**
     * Constructor.
     */
    public ApplicationImpl() {
        AudioUtilities.playSoundtrack(Constants.Audio.menuMusic0);
        loadAllSources();
        initialize();
        this.dkPanel = new ApplicationPanel(this);
        new ApplicationWindow(dkPanel);
        dkPanel.requestFocus();
        this.gameEngine = new GameEngineImpl(dkPanel, this);
    }

    @Override
    public final GameEngineImpl getGameEngine() {
        return this.gameEngine;
    }

    private void loadAllSources() {
        Constants.MenuAssets.loadMenuSources();
        Constants.MenuAssets.SettingsAssets.loadSettingsSources();
        Constants.Audio.loadThemes();
        Constants.Level.loadLevelSources();
    }

    @Override
    public final void startGameController() {
        this.gameController = new GameController(this);
        this.gameController.startGame();
    }

    @Override
    public final void initialize() {
        this.mainMenuController = new MainMenuController(this);
        this.settingsController = new SettingsController(this);
        this.pauseController = new PauseController(this);
        this.levelsMenuController = new LevelsMenuController(this);
    }

    @Override
    public final MainMenuController getMainMenuController() {
        return this.mainMenuController;
    }

    @Override
    public final SettingsController getSettingsController() {
        return this.settingsController;
    }

    @Override
    public final GameController getGameController() {
        return this.gameController;
    }

    @Override
    public final PauseController getPauseController() {
        return this.pauseController;
    }

    @Override
    public final LevelsMenuController getLevelsMenuController() {
        return this.levelsMenuController;
    }

}
