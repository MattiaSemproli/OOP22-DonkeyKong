package it.unibo.donkeykong.game.ecs.impl;

public class FreezeComponent extends AbstractComponent{
    
    private boolean freezer;
    private int timeElapsed;

    public FreezeComponent() {
        this.freezer = false;
        this.timeElapsed = 0;
    }

    @Override
    public void update() {
        this.timeElapsed++;
        if (this.freezer && this.timeElapsed > 600) {
            this.freezer = false;
        }
    }

    public final boolean isFrozen() {
        return this.freezer;
    }

    public final void setFrozen(final boolean freezer) {
        this.freezer = freezer;
        if (freezer) {
            this.timeElapsed = 0;
        }
    }
}
