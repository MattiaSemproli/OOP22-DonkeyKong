package it.unibo.donkeykong.game.ecs.impl;

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
        this.lifes = this.lifes + lifes > 3 ? this.lifes : this.lifes + lifes;
    }
}
