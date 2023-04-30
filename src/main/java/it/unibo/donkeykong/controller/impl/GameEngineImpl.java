package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.ApplicationPanel;
import static it.unibo.donkeykong.utilities.Constants.GameLoop.*;

public class GameEngineImpl implements GameEngine, Runnable{

    private ApplicationPanel dkPanel;
    private Thread gameThread;

    public GameEngineImpl(final ApplicationPanel dkPanel) {
        this.dkPanel = dkPanel;
        startGameLoop();
    }

    private void startGameLoop() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = NANOSECOND / FPS_SET;
        double timePerUpdate = NANOSECOND / UPS_SET;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        int frames = FRAME_DEFAULT;
        int updates = UPDATES_DEFAULT;

        double deltaU = DELTAU_DEFAULT;
        double deltaF = DELTAF_DEFAULT;

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
                frames = 0;
                updates = 0;
            }
        }
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
        switch(Gamestate.getGamestate()){
            case MENU:
                final MainMenuController mmc = new MainMenuController();
                mmc.draw(g);
                break;
            case CHOSING_LEVELS:
                break;
            case PLAYING:
                break;
            case SETTINGS:
                final SettingsController sc = new SettingsController();
                sc.draw(g);
                break;
            case PAUSE:
                break;
            case DEATH:
                break;
            case WIN:
                break;
            default:
                Runtime.getRuntime().exit(0);
                break;
        }
    }

}
