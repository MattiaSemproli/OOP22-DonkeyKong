package it.unibo.donkeykong.model.ecs.impl;

/**
 * Slow component, manages a slow barrel's power up.
 */
public class SlowComponent extends AbstractComponent {

    private boolean slow;

    /**
     * Constructor.
     */
    public SlowComponent() {
        this.slow = false;
    }

    @Override
    public void update() {
    }

    /**
     * Get if a barrel slows.
     * 
     * @return true if is a slow barrel. 
     */
    public final boolean isSlow() {
        return this.slow;
    }

    /**
     * Set if a barrel slows.
     * 
     * @param giveSlow the new slow's state.
     */
    public final void setSlow(final boolean giveSlow) {
        this.slow = giveSlow;
    }
}
