package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.PowerUpType;
import it.unibo.donkeykong.utilities.Constants;

/**
 * This component handles all powerUp.
 */
public class PowerUpHandlerComponent {
    private int lifes;
    private int slow;
    private boolean doubledmg;
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
        this.doubledmg = false;
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
    public void setLifesNumber(final int lifes) {
        this.lifes = lifes;
    }

    /**
     * This method reduce the player's life when a barrel hit the player.
     */
    public void hitPlayer(final boolean shield, final boolean immunity, final boolean doubledmg) {
        if (!this.shield && !this.immunity) {
            this.lifes -= 1;
        }
        if (this.doubledmg) {
            this.lifes -= 1;
        }
    }

    /**
     * This method modifies player's stats.
     */
    public void addPowerUp(final PowerUpType powerUpType) {
        switch (powerUpType) {
            case SHIELD:
                this.shield = true;
                break;
            case HEART:
                if (getLifesNumber() < 3 && getLifesNumber() >= 1) {
                    setLifesNumber(getLifesNumber() + 1);
                } 
                break;
            case STAR:
                this.immunity = true;
                break;
            case SNOWFLAKE:
                this.freeze = true;
                break;
            case DOUBLE_DAMAGE:
                this.doubledmg = true;
                break;
            case SLOW:
                break;
            default:
                break;
        }
    }
}
