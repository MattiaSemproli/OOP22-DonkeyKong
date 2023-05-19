package it.unibo.donkeykong.controller.api;

import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.controller.impl.GameEngineImpl;
import it.unibo.donkeykong.controller.impl.LevelsMenuController;
import it.unibo.donkeykong.controller.impl.MainMenuController;
import it.unibo.donkeykong.controller.impl.PauseController;
import it.unibo.donkeykong.controller.impl.SettingsController;
import it.unibo.donkeykong.game.model.impl.Game;

/**
 * Application class, models controllers management.
 */
public interface Application {

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
     * Get Game.
     * 
     * @return linked Game.
     */
    Game getGame();

}
