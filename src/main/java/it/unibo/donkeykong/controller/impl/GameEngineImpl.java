package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.Constants.GameLoop;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.ApplicationPanel;

/**
 * GameEngine class, manage gameloop.
 */
public class GameEngineImpl implements GameEngine, Runnable {

    private ApplicationPanel dkPanel;
    private ApplicationImpl applicationImpl;
    private Thread gameThread;

    /**
     * Constructor.
     * 
     * @param dkPanel linked panel.
     * @param applicationImpl linked application.
     */
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
    public final void run() {

        double timePerFrame = GameLoop.NANOSECOND / GameLoop.FPS_SET;
        double timePerUpdate = GameLoop.NANOSECOND / GameLoop.UPS_SET;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        int frames = GameLoop.FRAME_DEFAULT;
        int updates = GameLoop.UPDATES_DEFAULT;

        double deltaU = GameLoop.DELTAU_DEFAULT;
        double deltaF = GameLoop.DELTAF_DEFAULT;

        while (true) {
            final long actualTime = System.nanoTime();

            deltaF += (actualTime - previousTime) / timePerFrame;
            deltaU += (actualTime - previousTime) / timePerUpdate;

            previousTime = actualTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                dkPanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                /*if (Gamestate.getGamestate().equals(Gamestate.PLAYING) || Gamestate.getGamestate().equals(Gamestate.PAUSE)) {
                    System.out.println("Timer: " + this.applicationImpl.getGameController().getSeconds());
                }*/
                frames = 0;
                updates = 0;
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
                this.applicationImpl.getPauseController().update();
                break;
            case DEATH:
                break;
            case WIN:
                break;
            case EXIT:
                AudioUtilities.stopSoundtrack();
                Runtime.getRuntime().exit(0);
                break;
            default:
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
                this.applicationImpl.getGameController().draw(g);
                this.applicationImpl.getPauseController().draw(g);
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
