package it.unibo.donkeykong.game.ecs.impl;

public class ShieldComponent extends AbstractComponent{

    private boolean isShielded;

    public ShieldComponent() {
        this.isShielded = false;
    }

    @Override
    public void update() {
    }

    public final boolean isShielded() {
        return this.isShielded;
    }

    public final void setShield(final boolean isShielded) {
        this.isShielded = isShielded;
    }
}
