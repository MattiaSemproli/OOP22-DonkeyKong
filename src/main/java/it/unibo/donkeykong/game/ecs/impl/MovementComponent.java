package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.game.model.impl.AbstractComponent;
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
        this.getEntity().saveNextPosition(movePos);
        this.movePos = new Pair<>(0f, 0f);
    }

    /**
     * Moves the entity in the specified direction.
     * 
     * @param direction the direction in which to move the entity
     */
    public final void moveEntity(final Direction direction) {
        this.direction = direction;
        this.movePos = new Pair<>(direction.getX() * this.getEntity().getSpeed(), 
                                  direction.getY() * this.getEntity().getSpeed());
    }

    /**
     * This method retrieves the current facing direction of the entity.
     * 
     * @return the direction in which the entity is currently facing
     */
    public final Direction getFacing() {
        return this.direction;
    }
}
