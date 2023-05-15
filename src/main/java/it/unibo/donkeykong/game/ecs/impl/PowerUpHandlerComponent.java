package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.PowerUpType;
import it.unibo.donkeykong.utilities.Constants;

/**
 * This component handles all powerUp.
 */
public class PowerUpHandlerComponent {
    private int lifes;
    private int slow;
    private int doubledmg;
    private boolean shield;
    private boolean immunity;
    private boolean freeze;

    /**
     * This method initializes all powerups.
     */
    public PowerUpHandlerComponent() {
        this.lifes = 3;
        this.shield = false;
        this.immunity = false;
        this.freeze = false;
    }

    /**
     * @return actual lifes of player
     */
    public int getLifesNumber() {
        return this.lifes;
    }

    /**
     * Set lifesNumber.
     */
    public void setLifesNumber() {
        this.lifes = 3;
    }

    /**
     * This method reduce the player's life when a barrel hit the player.
     */
    public void hitPlayer() {
        this.lifes -= 1;
    }

    /**
     * This method modifies player's stats.
     */
    public void addPowerUp() {
    }
}
