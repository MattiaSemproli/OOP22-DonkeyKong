package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Constants.Player;

/**
 * Component that represents the star power up.
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
        if (this.isInvincible && this.timeElapsed > Player.starDuration) {
            this.isInvincible = false;
        }
    }

    /**
     * Checks if the player is currently invincible.
     * 
     * @return true if the player is invincible, false otherwise.
     */
    public final boolean isInvincible() {
        return this.isInvincible;
    }

    /**
     * Sets the invincibility state of the player.
     * 
     * @param isInvincible true to make the player invincible, false to remove invincibility.
     */
    public final void setInvincible(final boolean isInvincible) {
        this.isInvincible = isInvincible;
        if (isInvincible) {
            this.timeElapsed = 0;
        }
    }
}
