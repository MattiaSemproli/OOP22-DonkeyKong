package it.unibo.donkeykong.game.ecs.impl;

import java.awt.event.KeyEvent;
import java.util.Optional;

import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Physics;

/**
 * This class handles movement of an entity in a game.
 */
public class MovementComponent extends AbstractComponent {

    private Pair<Float, Float> movePos = new Pair<>(0f, 0f);
    private Direction direction = Direction.RIGHT;
    private float airSpeed = 0f;
    private int barrelChangesCounter = 0;
    private boolean isInAir = false;
    private boolean movingInAir = false;

    @Override
    public final void update() {
        if(this.getEntity().getEntityType() == Type.PLAYER) {
            if(!this.isInAir){
                this.getEntity().saveNextPosition(this.movePos.equals(new Pair<>(0f, 0f)) ? Optional.empty() 
                                                                                            : Optional.of(this.movePos));
                this.movePos = new Pair<>(0f, 0f);
                this.movingInAir = false;
            } else {
                this.isMovingInAir();
                this.updateInAirPosition();
                this.getEntity().saveNextPosition(Optional.of(this.movePos));
            }
        } else if (this.getEntity().getEntityType() == Type.BARREL) {
            if(!this.isInAir){
                this.moveEntity(this.getEntity().getComponent(MovementComponent.class).get().getFacing());
                this.getEntity().saveNextPosition(Optional.of(this.movePos));
            } else {
                this.updateInAirPosition();
                this.getEntity().saveNextPosition(Optional.of(this.movePos));
            }
        }
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
                                      || k == KeyEvent.VK_LEFT)
                .findAny().isPresent()) {
                this.movingInAir = true;
            }
    }

    private final void updateInAirPosition() {
        if(this.getEntity().getEntityType() == Type.PLAYER) {
            if (this.movingInAir) {
                this.movePos = new Pair<>(this.direction.getX() * this.getEntity().getSpeed() * Physics.speedInAirMultiplierPlayer, this.airSpeed);
            } else {
                this.movePos = new Pair<>(0f, this.airSpeed);
            }
            this.airSpeed += Physics.gravity * Physics.jumpGravityMultiplier;
        } else if (this.getEntity().getEntityType() == Type.BARREL) {
            this.movePos = new Pair<>(this.direction.getX() * this.getEntity().getSpeed() * Physics.speedInAirMultiplierBarrel, this.airSpeed);
            this.airSpeed += Physics.gravity;
        }
    }

    public int getBarrelChangesCounter() {
        return barrelChangesCounter;
    }

    /**
     * This method retrieves the current facing direction of the entity.
     * 
     * @return the direction in which the entity is currently facing
     */
    public final Direction getFacing() {
        return this.direction;
    }

    public final void changeBarrelDirection() {
        this.direction = direction.getOppositeDirection();
        this.barrelChangesCounter++;
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
}
