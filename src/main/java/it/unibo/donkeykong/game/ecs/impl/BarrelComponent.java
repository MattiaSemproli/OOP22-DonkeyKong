package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Constants.Barrel;

public class BarrelComponent extends AbstractComponent {

    private Direction direction;
    private final int velocity;
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
        if(this.bounces == Barrel.maxBounces) {
            
        } else {
            
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void changeDirection() {
        this.direction = this.direction.getOppositeDirection();
        this.bounces ++;
    }

    public int getVelocity() {
        return velocity;
    }
}
