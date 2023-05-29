package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Constants.Player;

public class HealthComponent extends AbstractComponent {

    private int lifes;

    public HealthComponent (final int numLifes) {
        this.lifes = numLifes;
    }

    @Override
    public void update() {
    }
    
    public final int getLifes() {
        return this.lifes;
    }

    public final void setLifes (final int lifes) {
        this.lifes = this.lifes + lifes > Player.numLifes ? this.lifes : this.lifes + lifes;
    }
}
