package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.game.core.api.GameEngine;
import it.unibo.donkeykong.game.core.impl.GameEngineImpl;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.ViewConstants;
import it.unibo.donkeykong.view.impl.ApplicationPanel;
import it.unibo.donkeykong.view.impl.ApplicationWindow;

/**
 * ApplicationImpl class, manages controllers.
 */
public class ApplicationImpl implements Application {

    private final ApplicationPanel dkPanel;
    private final GameEngine gameEngine;
    private MainMenuController mainMenuController;
    private SettingsController settingsController;
    private GameController gameController;
    private EndPauseController endPauseController;
    private LevelsMenuController levelsMenuController;

    /**
     * Constructor.
     */
    public ApplicationImpl() {
        AudioUtilities.playSoundtrack(ViewConstants.AudioAssets.MENU_MUSIC_0);
        loadAllSources();
        initialize();
        this.dkPanel = new ApplicationPanel(this);
        this.gameEngine = new GameEngineImpl(this);
        new ApplicationWindow(dkPanel, this);
        this.dkPanel.requestFocusInWindow();
        this.gameEngine.mainLoop();
    }

    private void initialize() {
        this.mainMenuController = new MainMenuController(this);
        this.settingsController = new SettingsController();
        this.endPauseController = new EndPauseController(this);
        this.levelsMenuController = new LevelsMenuController(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowFocusLost() {
        if (this.gameController != null) {
            this.gameController.notifyResetKeysOnFocusLost();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame() {
        this.gameController.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        this.dkPanel.repaint();
    }

    private void loadAllSources() {
        ViewConstants.MenuAssets.loadMenuSources();
        ViewConstants.MenuAssets.SettingsAssets.loadSettingsSources();
        ViewConstants.AudioAssets.loadThemes();
        ViewConstants.MenuAssets.LevelAssets.loadLevelSources();
        ViewConstants.PlayerAssets.loadPlayerSources();
        ViewConstants.BarrelAssets.loadBarrelSources();
        ViewConstants.MonkeyAssets.loadMonkeySources();
        ViewConstants.PrincessAssets.loadPrincessSources();
        ViewConstants.MenuAssets.SettingsAssets.loadTextSources();
        ViewConstants.PowerupAssets.loadPowerupSources();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGameController() {
        this.gameController = new GameController();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainMenuController getMainMenuController() {
        return this.mainMenuController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SettingsController getSettingsController() {
        return this.settingsController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameController getGameController() {
        return this.gameController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EndPauseController getEndPauseController() {
        return this.endPauseController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelsMenuController getLevelsMenuController() {
        return this.levelsMenuController;
    }
}
