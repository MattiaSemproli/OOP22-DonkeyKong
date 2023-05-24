package it.unibo.donkeykong.game.ecs.impl;

public class SlowComponent extends AbstractComponent {

    private boolean giveSlow;

    public SlowComponent() {
        this.giveSlow = false;
    }

    @Override
    public void update() {
    }

    public final boolean getIfSlow() {
        return this.giveSlow;
    }

    public final void setIfSlow(final boolean giveSlow) {
        this.giveSlow = giveSlow;
    }
}
