package it.unibo.donkeykong.controller.api;

import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.controller.impl.GameEngineImpl;
import it.unibo.donkeykong.controller.impl.LevelsMenuController;
import it.unibo.donkeykong.controller.impl.MainMenuController;
import it.unibo.donkeykong.controller.impl.PauseController;
import it.unibo.donkeykong.controller.impl.SettingsController;

/**
 * Application class, models controllers management.
 */
public interface Application {

    /**
     * Call method in gamecontroller (if exists) when the window lose the focus.
     */
    void windowFocusLost();

    /**
     * Get GameEngine.
     * 
     * @return linked GameEngine.
     */
    GameEngineImpl getGameEngine();

    /**
     * Initialize controllers.
     */
    void initialize();

    /**
     * Get MainMenuController.
     * 
     * @return linked MainMenuController.
     */
    MainMenuController getMainMenuController();

    /**
     * Get SettingsController.
     * 
     * @return linked SettingsController.
     */
    SettingsController getSettingsController();

    /**
     * Get GameController.
     * 
     * @return linked GameController.
     */
    GameController getGameController();

    /**
     * Get PauseController.
     * 
     * @return linked PauseController.
     */
    PauseController getPauseController();

    /**
     * Get LevelsMenuController.
     * 
     * @return linked LevelsMenuController.
     */
    LevelsMenuController getLevelsMenuController();

    /**
     * Create GameController and start Gameplay.
     */
    void startGameController();
}
