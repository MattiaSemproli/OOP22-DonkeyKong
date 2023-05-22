package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.game.model.impl.AbstractComponent;
import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;

/**
 * This class handles movement of an entity in a game.
 */
public class MovementComponent extends AbstractComponent {

    private Pair<Float, Float> movePos;
    private Direction direction = Direction.RIGHT;

    @Override
    public final void update() {
        if (this.movePos != null) {
            this.getEntity().setPosition(movePos);
        }
    }

    /**
     * Moves the entity in the specified direction.
     * 
     * @param direction the direction in which to move the entity
     */
    public final void moveEntity(final Direction direction) {
        this.direction = direction;
        this.movePos = new Pair<>(direction.getX() * 1f + this.getEntity().getPosition().getX(), 
                                  direction.getY() * 1f + this.getEntity().getPosition().getY());
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
