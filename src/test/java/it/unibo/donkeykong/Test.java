package it.unibo.donkeykong;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Application test.
 */
class AppTest {

    /**
     * Launches the app, intecepts exceptions.
     */
    @Test
    void testPlay() {
        Gamestate.setGamestate(Gamestate.PLAYING);
        assertEquals(Gamestate.PLAYING, Gamestate.getGamestate());
    }
}
