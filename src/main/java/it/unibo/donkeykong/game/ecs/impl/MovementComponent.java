package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;

/**
 * This class handles movement of an entity in a game.
 */
public class MovementComponent extends AbstractComponent {

    private Pair<Float, Float> movePos = new Pair<>(0f, 0f);
    private Direction direction = Direction.RIGHT;

    @Override
    public final void update() {
        this.getEntity().setPosition(movePos);
    }

    /**
     * Moves the entity in the specified direction.
     * @param direction the direction in which to move the entity
     */
    public final void moveEntity(final Direction direction) {
        this.movePos = new Pair<>(direction.getX() * 0.3f * 0.2f, direction.getY() * 0.3f * 0.2f);
    }

    /**
     * This method retrieves the current facing direction of the entity.
     * @return the direction in which the entity is currently facing
     */
    public final Direction getFacing() {
        return this.direction;
    }
}
