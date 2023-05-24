package it.unibo.donkeykong.game.ecs.impl;

public class DoubleDamageComponent extends AbstractComponent {

    private boolean isDoubleDamage;

    public DoubleDamageComponent() {
        this.isDoubleDamage = false;
    }

    @Override
    public void update() {
    }

    public final boolean getDoubleDamage() {
        return this.isDoubleDamage;
    }

    public final void setDoubleDamage(final boolean isDoubleDamage) {
        this.isDoubleDamage = isDoubleDamage;
    }    
}
