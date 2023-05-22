package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.game.model.impl.AbstractComponent;
import it.unibo.donkeykong.utilities.PowerUpType;

import java.util.ArrayList;
import java.util.List;

/**
 * This component handles all powerUp.
 */
public class PowerUpHandlerComponent extends AbstractComponent {
    private int lifes;
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
     * @param lifes the number of lives to set
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
     * @param doubledmg indicates if the damage is doubled
     */
    public void hitPlayer(final boolean doubledmg) {
        if (!this.shield && !this.immunity) {
            this.lifes -= doubledmg ? 2 : 1;
        } else if (this.shield) {
            this.lifes -= doubledmg ? 1 : 0;
            this.shield = false;
        }
    }

    /**
     * This method retrieves the status of the player's power-ups.
     * @return a list of integers representing the status of each power-up
     */
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
     * @param powerUpType powerUpType the type of powerup to apply to the player
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
                //this.getEntity();  //decrease speed 
                break;
            default:
                break;
        }
    }

    @Override
    public void update() {
    }
}
