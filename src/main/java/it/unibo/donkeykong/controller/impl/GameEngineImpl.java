package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.view.GamePanel;
import it.unibo.donkeykong.view.GameWindow;

public class GameEngineImpl implements GameEngine, Runnable{

    private final GamePanel dkPanel;
    private GameWindow dkWindow;
    private Thread gameThread;

    public GameEngineImpl() {

        this.dkPanel = new GamePanel();
        this.dkWindow = new GameWindow(dkPanel);
        dkPanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = Constants.NANOSECOND / Constants.FPS_SET;
        double timePerUpdate = Constants.NANOSECOND / Constants.UPS_SET;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        int frames = Constants.FRAME_DEFAULT;
        int updates = Constants.UPDATES_DEFAULT;

        double deltaU = Constants.DELTAU_DEFAULT;
        double deltaF = Constants.DELTAF_DEFAULT;

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

}
