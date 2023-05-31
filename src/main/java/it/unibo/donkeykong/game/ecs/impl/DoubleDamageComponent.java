package it.unibo.donkeykong.game.ecs.impl;

/**
 * DoubleDamage component, manages a double damage barrel's power up.
 */
public class DoubleDamageComponent extends AbstractComponent {

    private boolean isDoubleDamage;

    /**
     * Constructor.
     */
    public DoubleDamageComponent() {
        this.isDoubleDamage = false;
    }

    @Override
    public void update() {
    }

    /**
     * Check if is a double damage barrel.
     * 
     * @return true if is a double damage barrel.
     */
    public final boolean getDoubleDamage() {
        return this.isDoubleDamage;
    }

    /**
     * Set barrel double damage's state.
     * 
     * @param isDoubleDamage the new double damage's state.
     */
    public final void setDoubleDamage(final boolean isDoubleDamage) {
        this.isDoubleDamage = isDoubleDamage;
    }
}
