package it.unibo.donkeykong.common;

/**
 * Timer class, manages a timer.
 */
public class Timer {

    private static final double NANO_TO_MILLIS = 1_000_000;
    private static final double NANO_TO_SECOND = 1_000_000_000;

    private long initialTime;
    private long elapsedTime;
    private long pausedInitialTime;
    private long pausedElapsedTime;
    private boolean isStarted;
    private boolean isPaused;

    /**
     * Constructor.
     */
    public Timer() {
        this.initialTime = 0;
        this.elapsedTime = 0;
        this.pausedInitialTime = 0;
        this.pausedElapsedTime = 0;
        this.isStarted = false;
        this.isPaused = false;
    }

    /**
     * Starts the timer.
     */
    public void start() {
        if (this.isStarted) {
            throw new IllegalStateException("Timer is already running");
        }
        this.initialTime = System.nanoTime();
        this.isStarted = true;
    }

    /**
     * Stops the timer.
     */
    public void stop() {
        if (!this.isStarted) {
            throw new IllegalStateException("Timer is not running");
        }
        if (isPaused) {
            this.pausedElapsedTime += System.nanoTime() - this.pausedInitialTime;
            this.isPaused = false;
        } else {
            this.elapsedTime += System.nanoTime() - this.initialTime - this.pausedElapsedTime;
        }
        this.isStarted = false;
        this.initialTime = 0;
    }

    /**
     * Pauses the timer.
     */
    public void pause() {
        if (!this.isStarted) {
            throw new IllegalStateException("Timer is not running");
        }
        if (!this.isPaused) {
            this.pausedInitialTime = System.nanoTime();
            this.isPaused = true;
        }
    }

    /**
     * Resumes the timer.
     */
    public void resume() {
        if (!this.isStarted) {
            throw new IllegalStateException("Timer is not running");
        }
        if (this.isPaused) {
            this.pausedElapsedTime += System.nanoTime() - this.pausedInitialTime;
            this.isPaused = false;
        }
    }

    /**
     * Get the elapsed time in nanoseconds.
     * 
     * @return the elapsed time in nanoseconds.
     */
    public long getElapsedNanos() {
        if (this.isStarted) {
            long elapsed = this.elapsedTime + (System.nanoTime() - this.initialTime);
            if (this.isPaused) {
                elapsed -= System.nanoTime() - this.pausedInitialTime;
            }
            return elapsed;
        }
        return this.elapsedTime;
    }

    /**
     * Get the elapsed time in milliseconds.
     * 
     * @return the elapsed time in milliseconds.
     */
    public double getElapsedMillis() {
        return this.getElapsedNanos() / NANO_TO_MILLIS;
    }

    /**
     * Get the elapsed time in seconds.
     * 
     * @return the elapsed time in seconds.
     */
    public double getElapsedSeconds() {
        return this.getElapsedNanos() / NANO_TO_SECOND;
    }
}
