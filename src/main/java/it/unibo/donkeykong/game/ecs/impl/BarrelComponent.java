package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Constants.Barrel;

public class BarrelComponent extends AbstractComponent {

    private Direction direction;
    private int velocity;
    private Pair<Float,Float> actualPos;
    private int bounces;

    public BarrelComponent(final Direction direction, final Pair<Float,Float> initialPos) {
        this.direction = direction;
        this.velocity = Barrel.velocity;
        this.actualPos = initialPos;
        this.bounces = Barrel.startingBounces;
    }

    @Override
    public void update() {
    }

    public Direction getDirection() {
        return direction;
    }

    public void changeDirection() {
        this.direction = this.direction.getOppositeDirection();
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void increaseBounces() {
        this.bounces ++;
    }
}
