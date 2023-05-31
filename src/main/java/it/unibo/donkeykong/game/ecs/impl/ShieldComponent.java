package it.unibo.donkeykong.game.ecs.impl;

/**
 * Shield component, manages a shield power up.
 */
public class ShieldComponent extends AbstractComponent {

    private boolean shielded;

    /**
     * Constructor.
     */
    public ShieldComponent() {
        this.shielded = false;
    }

    @Override
    public void update() {
    }

    /**
     * Checks if the shield is active.
     * 
     * @return true if the shield is active.
     */
    public final boolean isShielded() {
        return this.shielded;
    }

    /**
     * Set the new shield's state.
     * 
     * @param isShielded the new shield's state.
     */
    public final void setShield(final boolean isShielded) {
        this.shielded = isShielded;
    }
}
