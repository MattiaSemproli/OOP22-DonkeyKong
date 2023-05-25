package it.unibo.donkeykong.game.ecs.impl;

public class StarComponent extends AbstractComponent{
    
    private boolean isInvincible;
    private int timeElapsed;

    public StarComponent() {
        this.isInvincible = false;
        this.timeElapsed = 0;
    }

    @Override
    public void update() {
        this.timeElapsed++;
        if (this.isInvincible && this.timeElapsed > 480) {
            this.isInvincible = false;
        }
    }

    public final boolean isInvincible() {
        return this.isInvincible;
    }

    public final void setInvincible(final boolean isInvincible) {
        this.isInvincible = isInvincible;
        if (isInvincible) {
            this.timeElapsed = 0;
        }
    }
}