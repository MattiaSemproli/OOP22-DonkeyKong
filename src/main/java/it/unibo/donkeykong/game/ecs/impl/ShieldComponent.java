package it.unibo.donkeykong.game.ecs.impl;

/**
 * Component that represents the shield powerup in the game.
 */
public class ShieldComponent extends AbstractComponent {

    private boolean isShielded;

    /**
     * Constructor.
     */
    public ShieldComponent() {
        this.isShielded = false;
    }

    @Override
    public void update() {
    }

    /**
     * Checks if the shield is currently active.
     * 
     * @return true if the shield is active, false otherwise.
     */
    public final boolean isShielded() {
        return this.isShielded;
    }

    /**
     * Sets the state of the shield.
     * 
     * @param isShielded true to activate the shield, false to deactivate it.
     */
    public final void setShield(final boolean isShielded) {
        this.isShielded = isShielded;
    }
}
