package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.PowerUpType;

/**
 * This component manage all powerUps.
 */
public class PowerUpComponent extends AbstractComponent {
    private final PowerUpType powerUpType;

    /**
     * Powerup constructor.
     * 
     * @param powerUpType the tpe of powerup.
     */
    public PowerUpComponent(final PowerUpType powerUpType) {
        this.powerUpType = powerUpType;
    }

    @Override
    public void update() {
    }

    /**
     * @return the type of powerup.
     */
    public PowerUpType getPowerUpType() {
        return this.powerUpType;
    } 
}
