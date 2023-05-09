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
    private Game game;

    public ApplicationImpl() {
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

    public void initialize() {
        this.mainMenuController = new MainMenuController();
        this.settingsController = new SettingsController();
    }

    public MainMenuController getMainMenuController() {
        return this.mainMenuController;
    }

    public SettingsController getSettingsController() {
        return this.settingsController;
    }

    public Game getGame() {
        return this.game;
    }
}