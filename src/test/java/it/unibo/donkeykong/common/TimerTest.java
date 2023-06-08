package it.unibo.donkeykong.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TimerTest {

    @Test
    void testStartWhileAlreadyStarted() {
        final Timer timer = new Timer();
        timer.start();
        final Exception exception = assertThrows(IllegalStateException.class, () -> timer.start()); 
        assertEquals("Timer is already running", exception.getMessage());
    }

    @Test
    void testStopWhileNotStarted() {
        final Timer timer = new Timer();
        final Exception exception = assertThrows(IllegalStateException.class, () -> timer.stop()); 
        assertEquals("Timer is not running", exception.getMessage());
    }

    @Test
    void testPauseWhileNotStarted() {
        final Timer timer = new Timer();
        final Exception exception = assertThrows(IllegalStateException.class, () -> timer.pause()); 
        assertEquals("Timer is not running", exception.getMessage());
    }

    @Test
    void testResumeWhileNotStarted() {
        final Timer timer = new Timer();
        final Exception exception = assertThrows(IllegalStateException.class, () -> timer.resume()); 
        assertEquals("Timer is not running", exception.getMessage());
    }
}
