package it.unibo.donkeykong;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Application test.
 */
class AppTest {

    /**
     * Launches the app, intecepts exceptions.
     */
    @Test void testApp() {
        assertNotNull(System.getenv("OMDB_API_KEY"));
        assertFalse(System.getenv("OMDB_API_KEY").isBlank());
    }
}
