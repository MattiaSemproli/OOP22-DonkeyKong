package it.unibo.donkeykong.game.ecs.impl;

/**
 * 
 */
public class SlowComponent extends AbstractComponent {

    private boolean giveSlow;

    /**
     * Constructor.
     */
    public SlowComponent() {
        this.giveSlow = false;
    }

    @Override
    public void update() {
    }

    /**
     * 
     * @return giveSlow 
     */
    public final boolean getIfSlow() {
        return this.giveSlow;
    }

    /**
     * 
     * @param giveSlow 
     */
    public final void setIfSlow(final boolean giveSlow) {
        this.giveSlow = giveSlow;
    }
}
