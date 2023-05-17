package it.unibo.donkeykong.game.model.impl;

import it.unibo.donkeykong.game.model.api.Timer;

public class TimerImpl implements Timer{

    private int secondsToCount;
    private boolean isOver;

    public TimerImpl(final int secondsToCount) {
        this.secondsToCount = secondsToCount;
        this.isOver = false;
    }

    @Override
    public void startTimer() {
        int startingTime = Math.round(System.currentTimeMillis());
        int actualTime = startingTime;
        int passedTime = 0;
        while(true) {
            actualTime = Math.round(System.currentTimeMillis());
            passedTime = actualTime - startingTime;
            if(passedTime == secondsToCount) {
                break;
            }
        }
        setOverTrue();
    }

    @Override
    public boolean isTimerOver() {
        return this.isOver;
    }

    private void setOverTrue() {
        this.isOver = true;
    }
}
