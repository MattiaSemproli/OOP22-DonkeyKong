package it.unibo.donkeykong.controller.api;

import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.controller.impl.GameEngineImpl;
import it.unibo.donkeykong.controller.impl.LevelsMenuController;
import it.unibo.donkeykong.controller.impl.MainMenuController;
import it.unibo.donkeykong.controller.impl.PauseController;
import it.unibo.donkeykong.controller.impl.SettingsController;
import it.unibo.donkeykong.game.model.impl.Game;

public interface Application {

    GameEngineImpl getGameEngine();

    void initialize();
    
    MainMenuController getMainMenuController();

    SettingsController getSettingsController();

    GameController getGameController();

    PauseController getPauseController();

    LevelsMenuController getLevelsMenuController();

    Game getGame();
}