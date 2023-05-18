package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;

public class MovementComponent extends AbstractComponent {

    private Pair<Float, Float> movePos = new Pair<>(0f, 0f);
    private Direction direction = Direction.RIGHT;

    @Override
    public void update() {
        this.getEntity().setPosition(movePos);
    }

    public final void moveEntity(final Direction direction) {
        this.movePos = new Pair<>(direction.getX() * 0.3f * 0.2f, direction.getY() * 0.3f * 0.2f);
    }

    public final Direction getFacing() {
        return this.direction;
    }
}
