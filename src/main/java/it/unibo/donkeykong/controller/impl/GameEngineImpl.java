package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.ApplicationPanel;
import static it.unibo.donkeykong.utilities.Constants.GameLoop;
    
public class GameEngineImpl implements GameEngine, Runnable {

    private ApplicationPanel dkPanel;
    private ApplicationImpl applicationImpl;
    private Thread gameThread;

    public GameEngineImpl(final ApplicationPanel dkPanel, final ApplicationImpl applicationImpl) {
        this.dkPanel = dkPanel;
        this.applicationImpl = applicationImpl;
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = GameLoop.NANOSECOND / GameLoop.FPS_SET;
        double timePerUpdate = GameLoop.NANOSECOND / GameLoop.UPS_SET;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        double deltaU = GameLoop.DELTAU_DEFAULT;
        double deltaF = GameLoop.DELTAF_DEFAULT;

        while (true) {
            final long actualTime = System.nanoTime();

            deltaF += (actualTime - previousTime) / timePerFrame;
            deltaU += (actualTime - previousTime) / timePerUpdate;

            previousTime = actualTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }

            if (deltaF >= 1) {
                dkPanel.repaint();
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void update() {
        switch (Gamestate.getGamestate()) {
            case MENU:
                applicationImpl.getMainMenuController().update();
                this.applicationImpl.initialize();
                break;
            case CHOSING_LEVELS:
                break;
            case PLAYING:
                applicationImpl.getGameController().update();
                break;
            case SETTINGS:
                applicationImpl.getSettingsController().update();
                break;
            case PAUSE:
                break;
            case DEATH:
                break;
            case WIN:
                break;
            case EXIT:
                Runtime.getRuntime().exit(0);
                break;
            default:
                Runtime.getRuntime().exit(0);
                break;
        }
    }

    @Override
    public void draw(final Graphics g) {
        switch (Gamestate.getGamestate()) {
            case MENU:
                applicationImpl.getMainMenuController().draw(g);
                break;
            case CHOSING_LEVELS:
                break;
            case PLAYING:
                applicationImpl.getGameController().draw(g);
                break;
            case SETTINGS:
                applicationImpl.getSettingsController().draw(g);
                break;
            case PAUSE:
                break;
            case DEATH:
                break;
            case WIN:
                break;
            case EXIT:
                Runtime.getRuntime().exit(0);
                break;
            default:
                Runtime.getRuntime().exit(0);
                break;
        }
    }

}