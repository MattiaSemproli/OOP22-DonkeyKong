package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.view.ApplicationPanel;
import it.unibo.donkeykong.view.ApplicationWindow;

/**
 * ApplicationImpl class, manage controllers.
 */
public class ApplicationImpl implements Application {

    private final ApplicationPanel dkPanel;
    private final GameEngine gameEngine;
    private MainMenuController mainMenuController;
    private SettingsController settingsController;
    private GameController gameController;
    private EndPauseController pauseController;
    private LevelsMenuController levelsMenuController;

    /**
     * Constructor.
     */
    public ApplicationImpl() {
        AudioUtilities.playSoundtrack(Constants.Audio.menuMusic0);
        loadAllSources();
        initialize();
        this.dkPanel = new ApplicationPanel(this);
        this.gameEngine = new GameEngineImpl(this);
        new ApplicationWindow(dkPanel, this);
        this.dkPanel.requestFocusInWindow();
    }

    @Override
    public final void windowFocusLost() {
        if (this.gameController != null) {
            this.gameController.resetKeysOnFocusLost();
        }
    }

    @Override
    public final GameEngine getGameEngine() {
        return this.gameEngine;
    }

    @Override
    public final void redraw() {
        this.dkPanel.repaint();
    }

    private void loadAllSources() {
        Constants.MenuAssets.loadMenuSources();
        Constants.MenuAssets.SettingsAssets.loadSettingsSources();
        Constants.Audio.loadThemes();
        Constants.MenuAssets.LevelAssets.loadLevelSources();
        Constants.Player.loadPlayerSources();
        Constants.Barrel.loadBarrelSources();
        Constants.Monkey.loadMonkeySources();
        Constants.Princess.loadPrincessSources();
    }

    @Override
    public final void startGameController() {
        this.gameController = new GameController();
    }

    @Override
    public final void initialize() {
        this.mainMenuController = new MainMenuController(this);
        this.settingsController = new SettingsController();
        this.pauseController = new EndPauseController(this);
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
    public final EndPauseController getPauseController() {
        return this.pauseController;
    }

    @Override
    public final LevelsMenuController getLevelsMenuController() {
        return this.levelsMenuController;
    }
}
