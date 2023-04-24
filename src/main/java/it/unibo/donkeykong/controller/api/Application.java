package it.unibo.donkeykong.controller.api;

import it.unibo.donkeykong.controller.impl.GameEngineImpl;

public interface Application {

    public GameEngineImpl getGameEngine();
}
