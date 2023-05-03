package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.view.ApplicationPanel;
import it.unibo.donkeykong.view.ApplicationWindow;

public class ApplicationImpl implements Application {

    private ApplicationPanel dkPanel;
    private ApplicationWindow dkWindow;
    private GameEngineImpl gameEngine;

    public ApplicationImpl() {
        this.dkPanel = new ApplicationPanel(this);
        this.dkWindow = new ApplicationWindow(dkPanel);
        dkPanel.requestFocus();
        this.gameEngine = new GameEngineImpl(dkPanel);
    }

    @Override
    public GameEngineImpl getGameEngine() {
        return this.gameEngine;
    }

    public void windowsFocusLost() {
    }

}