package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Constants.Player;

/**
 * Component that represents the lives of the player.
 */
public class HealthComponent extends AbstractComponent {

    private int lifes;

    /**
     * Constructor.
     * 
     * @param numLifes the initial number of lives for the player.
     */
    public HealthComponent(final int numLifes) {
        this.lifes = numLifes;
    }

    @Override
    public void update() {
    }

    /**
     * Gets the current number of lives for the player.
     * 
     * @return the current number of lives.
     */
    public final int getLifes() {
        return this.lifes;
    }

    /**
     * Sets the number of lives for the player.
     * 
     * @param lifes the new number of lives for the player.
     */
    public final void setLifes(final int lifes) {
        this.lifes = this.lifes + lifes > Player.numLifes ? this.lifes : this.lifes + lifes;
        this.lifes = this.lifes < 0 ? 0 : this.lifes;
    }
}
