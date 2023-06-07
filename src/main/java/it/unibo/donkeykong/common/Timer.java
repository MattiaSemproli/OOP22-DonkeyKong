package it.unibo.donkeykong.common;

public class Timer {

    private static final double NANO_TO_MILLIS = 1_000_000;
    private static final double NANO_TO_SECOND = 1_000_000_000;
    
    private long initialTime;
    private long elapsedTime;
    private long pausedInitialTime;
    private long pausedElapsedTime;
    private boolean isStarted;
    private boolean isPaused;

    public Timer() {
        this.initialTime = 0;
        this.elapsedTime = 0;
        this.pausedInitialTime = 0;
        this.pausedElapsedTime = 0;
        this.isStarted = false;
        this.isPaused = false;
        this.start();
    }

    public void start() {
        if(this.isStarted) {
            throw new RuntimeException("Timer is already running");
        }
        this.initialTime = System.nanoTime();
        this.isStarted = true;
    }

    public void stop() {
        if(!this.isStarted) {
            throw new RuntimeException("Timer is not running");
        }
        if(isPaused) {
            this.pausedElapsedTime += System.nanoTime() - this.pausedInitialTime;
            this.isPaused = false;
        } else {
            this.elapsedTime += System.nanoTime() - this.initialTime - this.pausedElapsedTime;
        }
        this.isStarted = false;
        this.initialTime = 0;
    }

    public void pause() {
        if(!this.isStarted) {
            throw new RuntimeException("Timer is not running");
        }
        if(!this.isPaused) {
            this.pausedInitialTime = System.nanoTime();
            this.isPaused = true;
        }
    }

    public void resume() {
        if(!this.isStarted) {
            throw new RuntimeException("Timer is not running");
        }
        if(this.isPaused) {
            this.pausedElapsedTime += System.nanoTime() - this.pausedInitialTime;
            this.isPaused = false;
        }
    }

    public long getElapsedNanos() {
        if(this.isStarted) {
            long elapsed = this.elapsedTime + (System.nanoTime() - this.initialTime);
            if(this.isPaused) {
                elapsed -= System.nanoTime() - this.pausedInitialTime;
            }
            return elapsed;
        }
        return this.elapsedTime;
    }

    public double getElapsedMillis() {
        return this.getElapsedNanos() / NANO_TO_MILLIS;
    }

    public double getElapsedSeconds() {
        return this.getElapsedNanos() / NANO_TO_SECOND;
    }
}
