package it.unibo.donkeykong.game.core.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.game.core.api.GameEngine;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.Constants.GameLoop;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * GameEngine class, manage gameloop.
 */
public class GameEngineImpl implements GameEngine {

    private final Application applicationImpl;
    private final double framePerSecond;

    /**
     * Constructor.
     * 
     * @param applicationImpl linked application.
     */
    public GameEngineImpl(final Application applicationImpl) {
        this.applicationImpl = applicationImpl;
        this.framePerSecond = GameLoop.NANOSECOND / GameLoop.FPS_SET;
    }

    @Override
    public void mainLoop() {
        long previousTime = System.nanoTime();
        double deltaF = GameLoop.DELTAF_DEFAULT;

        while (Gamestate.getGamestate() != Gamestate.EXIT) {
            final long actualTime = System.nanoTime();
            deltaF += (actualTime - previousTime) / framePerSecond;

            if (deltaF >= 1) {
                if (Gamestate.getGamestate() == Gamestate.PLAYING) {
                    updateGame();
                }
                render();
                deltaF--;
            }
            previousTime = actualTime;
        }
        AudioUtilities.stopSoundtrack();
        Runtime.getRuntime().exit(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame() {
        this.applicationImpl.updateGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.applicationImpl.redraw();
    }
}
