package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.Constants.GameLoop;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * GameEngine class, manage gameloop.
 */
public class GameEngineImpl implements GameEngine, Runnable {

    private final Application applicationImpl;

    /**
     * Constructor.
     * 
     * @param applicationImpl linked application.
     */
    public GameEngineImpl(final Application applicationImpl) {
        this.applicationImpl = applicationImpl;
        startGameLoop();
    }

    private void startGameLoop() {
        final Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public final void run() {

        final double timePerFrame = GameLoop.NANOSECOND / GameLoop.FPS_SET;
        final double timePerUpdate = GameLoop.NANOSECOND / GameLoop.UPS_SET;
        long previousTime = System.nanoTime();

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
                this.applicationImpl.redraw();
                deltaF--;
            }
        }
    }

    @Override
    public final void update() {
        switch (Gamestate.getGamestate()) {
            case MENU:
                this.applicationImpl.getMainMenuController().update();
                this.applicationImpl.initialize();
                break;
            case CHOSING_LEVELS:
                this.applicationImpl.getLevelsMenuController().update();
                break;
            case PLAYING:
                this.applicationImpl.getGameController().update();
                break;
            case SETTINGS:
                this.applicationImpl.getSettingsController().update();
                break;
            case PAUSE:
            case WIN:
            case DEATH:
                this.applicationImpl.getEndPauseController().update();
                break;
            case EXIT:
            default:
                AudioUtilities.stopSoundtrack();
                Runtime.getRuntime().exit(0);
                break;
        }
    }

    @Override
    public final void draw(final Graphics g) {
        switch (Gamestate.getGamestate()) {
            case MENU:
                this.applicationImpl.getMainMenuController().draw(g);
                break;
            case CHOSING_LEVELS:
                this.applicationImpl.getLevelsMenuController().draw(g);
                break;
            case PLAYING:
                this.applicationImpl.getGameController().draw(g);
                break;
            case SETTINGS:
                this.applicationImpl.getSettingsController().draw(g);
                break;
            case PAUSE:
            case WIN:
            case DEATH:
                this.applicationImpl.getGameController().draw(g);
                this.applicationImpl.getEndPauseController().draw(g);
                break;
            case EXIT:
            default:
                Runtime.getRuntime().exit(0);
                break;
        }
    }
}
