package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.PowerUpType;
/**
 * This component manage all powerUps.
 */
public class PowerUpComponent extends AbstractComponent {
    private final PowerUpType powerUpType;

    /**
     * This method set the type of powerUp.
     * 
     * @param powerUpType type of powerUp
     */
    public PowerUpComponent(final PowerUpType powerUpType) {
        this.powerUpType = powerUpType;
    }

    @Override
    public void update() {
    }

    /**
     * @return powerup type
     */
    public PowerUpType getPowerUpType() {
        return this.powerUpType;
    } 
}
