package it.unibo.donkeykong.game.model.api;

import java.util.ArrayList;

import it.unibo.donkeykong.controller.impl.GameController;

public interface Gameplay {
    
    ArrayList<Entity> getEntities();

    GameController getController();
}
