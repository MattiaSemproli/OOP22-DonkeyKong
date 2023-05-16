package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Constants.Barrel;

public class BarrelComponent extends AbstractComponent {

    private Direction direction;
    private final int velocity;

    public BarrelComponent(final Direction direction) {
        this.direction = direction;
        this.velocity = Barrel.velocity;
    }

    @Override
    public void update() {
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getVelocity() {
        return velocity;
    }
}
