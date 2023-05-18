package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.PowerUpType;

import java.util.ArrayList;
import java.util.List;

import it.unibo.donkeykong.utilities.Constants;

/**
 * This component handles all powerUp.
 */
public class PowerUpHandlerComponent extends AbstractComponent{
    private int lifes;
    private int slow;
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
    public void setLifesNumber(final int lifes) {
        if (getLifesNumber() < 3 && getLifesNumber() >= 1) {
            this.lifes++;
        }
    }

    /**
     * Set shield.
     */
    public void setShield() {
        this.shield = true;
    }

    /**
     * This method reduce the player's life when a barrel hit the player.
     */
    public void hitPlayer(final boolean doubledmg) {
        if (!this.shield && !this.immunity) {
            this.lifes -= doubledmg ? 2 : 1;
        }
        else if(this.shield) {
            this.lifes -= doubledmg ? 1 : 0;
            this.shield = false;
        }
    }

    public List<Integer> getStatusPowerUp() {
        return new ArrayList<>() {{
            add(lifes);
            add(shield ? 1 : 0);
            add(freeze ? 1 : 0);
            add(immunity ? 1 : 0);
        }};
    }

    /**
     * This method modifies player's stats.
     */
    public void addPowerUp(final PowerUpType powerUpType) {
        switch (powerUpType) {
            case SHIELD:
                setShield();
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
            case SLOW:
                this.getEntity();  //decrease speed 
                break;
            default:
                break;
        }
    }

    @Override
    public void update() {
    }
}
