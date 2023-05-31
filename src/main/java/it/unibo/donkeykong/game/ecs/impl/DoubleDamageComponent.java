package it.unibo.donkeykong.game.ecs.impl;

/**
 * Component that represents the double damage effect of barrels.
 */
public class DoubleDamageComponent extends AbstractComponent {

    private boolean isDoubleDamage;

    /**
     * Constructs a DoubleDamageComponent object.
     */
    public DoubleDamageComponent() {
        this.isDoubleDamage = false;
    }

    @Override
    public void update() {
    }

    /**
     * Checks if the double damage effect is currently active.
     * 
     * @return true if the double damage effect is active, false otherwise.
     */
    public final boolean getDoubleDamage() {
        return this.isDoubleDamage;
    }

    /**
     * Sets the state of the double damage effect.
     * 
     * @param isDoubleDamage true to activate the double damage effect, false to disable it.
     */
    public final void setDoubleDamage(final boolean isDoubleDamage) {
        this.isDoubleDamage = isDoubleDamage;
    }
}
