package it.unibo.donkeykong.controller.api;

import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.controller.impl.LevelsMenuController;
import it.unibo.donkeykong.controller.impl.MainMenuController;
import it.unibo.donkeykong.controller.impl.EndPauseController;
import it.unibo.donkeykong.controller.impl.SettingsController;
import it.unibo.donkeykong.game.core.api.GameEngine;

/**
 * Application interface, models controllers management.
 */
public interface Application {

    /**
     * Update game.
     */
    void updateGame();

    /**
     * Call method in gamecontroller (if exists) when the window lose the focus.
     */
    void windowFocusLost();

    /**
     * Get GameEngine.
     * 
     * @return the linked GameEngine.
     */
    GameEngine getGameEngine();

    /**
     * Call repaint method.
     */
    void redraw();

    /**
     * Get MainMenuController.
     * 
     * @return the linked MainMenuController.
     */
    MainMenuController getMainMenuController();

    /**
     * Get SettingsController.
     * 
     * @return the linked SettingsController.
     */
    SettingsController getSettingsController();

    /**
     * Get GameController.
     * 
     * @return the linked GameController.
     */
    GameController getGameController();

    /**
     * Get PauseController.
     * 
     * @return the linked PauseController.
     */
    EndPauseController getEndPauseController();

    /**
     * Get LevelsMenuController.
     * 
     * @return the linked LevelsMenuController.
     */
    LevelsMenuController getLevelsMenuController();

    /**
     * Create GameController and start Gameplay.
     */
    void startGame();
}
