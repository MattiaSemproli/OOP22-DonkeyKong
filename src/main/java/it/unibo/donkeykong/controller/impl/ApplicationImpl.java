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
    private Game game;

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
    public final void initialize() {
        this.mainMenuController = new MainMenuController(this);
        this.settingsController = new SettingsController(this);
        this.gameController = new GameController(this);
        this.pauseController = new PauseController(this);
        this.levelsMenuController = new LevelsMenuController(this);
    }

    @Override
    public MainMenuController getMainMenuController() {
        return this.mainMenuController;
    }

    @Override
    public SettingsController getSettingsController() {
        return this.settingsController;
    }

    @Override
    public GameController getGameController() {
        return this.gameController;
    }

    @Override
    public PauseController getPauseController() {
        return this.pauseController;
    }

    @Override
    public LevelsMenuController getLevelsMenuController() {
        return this.levelsMenuController;
    }

    @Override
    public Game getGame() {
        return this.game;
    }
}
