package it.unibo.donkeykong.game.ecs.impl;

import java.awt.event.KeyEvent;

import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Constants.Physics;

/**
 * This class handles movement of an entity in a game.
 */
public class MovementComponent extends AbstractComponent {

    private Pair<Float, Float> movePos = new Pair<>(0f, 0f);
    private Direction direction = Direction.RIGHT;
    private float airSpeed = 0f;
    private boolean isInAir = false;
    private boolean isMoving = false;
    private boolean isFalling = false;

    @Override
    public final void update() {
        if(!this.isInAir) {
            this.getEntity().saveNextPosition(movePos);
            this.movePos = new Pair<>(0f, 0f);
            this.isMoving = false;
        } else {
            this.isMovingInAir();
            if (!this.isFalling) {
                this.updateInAirPosition();
            } else {
                this.updateFallingPosition();
            }
            this.getEntity().saveNextPosition(movePos);
        }
    }

    /**
     * Moves the entity in the specified direction.
     * 
     * @param direction the direction in which to move the entity
     */
    public final void moveEntity(final Direction direction) {
        this.direction = direction;
        this.isMoving = true;
        this.movePos = new Pair<>(direction.getX() * this.getEntity().getSpeed(), 
                                  direction.getY() * this.getEntity().getSpeed());
    }

    public final void jump () {
        if(!this.isInAir) {
            this.isInAir = true;
            this.airSpeed = Physics.jumpSpeed;
        }
    }

    private final void isMovingInAir() {
        if (this.getEntity().getGameplay().getController().getInputs()
                    .stream().filter(k -> k == KeyEvent.VK_D
                                        || k == KeyEvent.VK_A
                                        || k == KeyEvent.VK_RIGHT
                                        || k == KeyEvent.VK_LEFT).findAny().isPresent()) {
                this.isMoving = true;
            }
    }

    private final void updateInAirPosition() { 
        if (this.isMoving) {
            this.movePos = new Pair<>(this.direction.getX() * this.getEntity().getSpeed() * Physics.speedInAirMultiplier, 
                                        this.airSpeed);
        } else {
            this.movePos = new Pair<>(0f, this.airSpeed);
        }
        this.airSpeed += Physics.gravity;
    }

    private final void updateFallingPosition() {
        if (this.isMoving) {
            this.movePos = new Pair<>(this.direction.getX() * this.getEntity().getSpeed() * Physics.speedInAirMultiplier, 
                                        Physics.fallingSpeed);
        } else {
            this.movePos = new Pair<>(0f, Physics.fallingSpeed);
        }
    }

    /**
     * This method retrieves the current facing direction of the entity.
     * 
     * @return the direction in which the entity is currently facing
     */
    public final Direction getFacing() {
        return this.direction;
    }

    public boolean getIsInAir () {
        return this.isInAir;
    }

    public void setIsInAir (final boolean isInAir) {
        this.isInAir = isInAir;
    }

    public void resetIsInAir () {
        this.isInAir = false;
        this.airSpeed = 0f;
    }

    public void setFalling(final boolean isFalling) {
        this.isFalling = isFalling;
        this.isInAir = isFalling ? true : false;
    }
}
