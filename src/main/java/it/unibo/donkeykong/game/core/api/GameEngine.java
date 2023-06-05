package it.unibo.donkeykong.game.core.api;

/**
 * GameEngine interface, models gameloop.
 */
public interface GameEngine {

    /**
     * Update method.
     */
    void updateGame();

    /**
     * Render method.
     */
    void render();

    /**
     * Main loop.
     */
    void mainLoop();
}
