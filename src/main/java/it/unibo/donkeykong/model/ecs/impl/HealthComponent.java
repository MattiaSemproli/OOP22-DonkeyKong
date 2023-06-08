package it.unibo.donkeykong.model.ecs.impl;

import it.unibo.donkeykong.utilities.ModelConstants.Player;

/**
 * Health component, manages player's health.
 */
public class HealthComponent extends AbstractComponent {

    private int lives;

    /**
     * Constructor.
     * 
     * @param numLives the initial player's number of lives.
     */
    public HealthComponent(final int numLives) {
        this.lives = numLives;
    }

    @Override
    public void update() {
    }

    /**
     * Get player's current lives.
     * 
     * @return the current number of lives.
     */
    public final int getLives() {
        return this.lives;
    }

    /**
     * Set player's lives.
     * 
     * @param lives the new player's number of lives.
     */
    public final void setLifes(final int lives) {
        this.lives = this.lives + lives > Player.NUM_LIVES ? Player.NUM_LIVES : this.lives + lives;
        this.lives = this.lives < 0 ? 0 : this.lives;
    }
}
