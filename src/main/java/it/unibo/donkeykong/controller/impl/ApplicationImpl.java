package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.view.ApplicationPanel;
import it.unibo.donkeykong.view.ApplicationWindow;
import it.unibo.donkeykong.game.model.impl.Game;

public class ApplicationImpl implements Application {

    private ApplicationPanel dkPanel;
    private GameEngineImpl gameEngine;
    private MainMenuController mainMenuController;
    private SettingsController settingsController;
    private GameController gameController;
    private PauseController pauseController;
    private LevelsMenuController levelsMenuController;
    private Game game;

    public ApplicationImpl() {
        loadAllSources();
        initialize();
        this.dkPanel = new ApplicationPanel(this);
        new ApplicationWindow(dkPanel);
        dkPanel.requestFocus();
        this.gameEngine = new GameEngineImpl(dkPanel, this);
    }

    @Override
    public GameEngineImpl getGameEngine() {
        return this.gameEngine;
    }

    public void startGame() {
        this.game = new Game();
    }

    private void loadAllSources() {
        Constants.MenuAssets.loadMenuSources();
        Constants.MenuAssets.SettingsAssets.loadSettingsSources();
    }

    public void initialize() {
        this.mainMenuController = new MainMenuController();
        this.settingsController = new SettingsController();
        this.gameController = new GameController();
        this.pauseController = new PauseController();
        this.levelsMenuController = new LevelsMenuController();
    }

    public MainMenuController getMainMenuController() {
        return this.mainMenuController;
    }

    public SettingsController getSettingsController() {
        return this.settingsController;
    }

    public GameController getGameController() {
        return this.gameController;
    }

    public PauseController getPauseController() {
        return this.pauseController;
    }

    public LevelsMenuController getLevelsMenuController() {
        return this.levelsMenuController;
    }

    public Game getGame() {
        return this.game;
    }
}