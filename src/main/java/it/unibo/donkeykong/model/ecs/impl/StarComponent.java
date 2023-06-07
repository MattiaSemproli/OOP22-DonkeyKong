package it.unibo.donkeykong.model.ecs.impl;

import it.unibo.donkeykong.utilities.ModelConstants.Player;

/**
 * Star component, manages a star power up.
 */
public class StarComponent extends AbstractComponent {

    private boolean isInvincible;
    private int timeElapsed;

    /**
     * Constructor.
     */
    public StarComponent() {
        this.isInvincible = false;
        this.timeElapsed = 0;
    }

    @Override
    public final void update() {
        this.timeElapsed++;
        if (this.isInvincible && this.timeElapsed > Player.STAR_DURATION) {
            this.isInvincible = false;
        }
    }

    /**
     * Checks if the player is invincible.
     * 
     * @return true if the player is invincible.
     */
    public final boolean isInvincible() {
        return this.isInvincible;
    }

    /**
     * Set new invincibility state.
     * 
     * @param isInvincible the invincibility state.
     */
    public final void setInvincible(final boolean isInvincible) {
        this.isInvincible = isInvincible;
        if (isInvincible) {
            this.timeElapsed = 0;
        }
    }
}
