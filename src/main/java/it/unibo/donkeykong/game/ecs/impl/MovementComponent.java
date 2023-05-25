package it.unibo.donkeykong.game.ecs.impl;

import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.Random;

import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.PlayerIdle;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Physics;

/**
 * This class handles movement of an entity in a game.
 */
public class MovementComponent extends AbstractComponent {

    private Pair<Float, Float> movePos = new Pair<>(0f, 0f);
    private Direction direction = Direction.RIGHT;
    private float airSpeed;
    private int barrelChangesCounter;
    private boolean inAir;
    private boolean movingInAir;
    private int timeElapsed = 0;
    private boolean isPrincessWalking = true;
    private Random random = new Random();
    private boolean canUseLadder;

    /**
     * Constructor.
     */
    public MovementComponent() {
        this.airSpeed = 0f;
        this.barrelChangesCounter = 0;
        this.inAir = false;
        this.movingInAir = false;
        this.canUseLadder = false;
    }

    @Override
    public final void update() {
        timeElapsed++;
        if (this.getEntity().getEntityType() == Type.PLAYER) {
                if (!this.inAir) {
                    this.getEntity().saveNextPosition(this.movePos.equals(new Pair<>(0f, 0f)) ? Optional.empty() 
                                                                                                : Optional.of(this.movePos));
                    PlayerIdle.setPlayerIdle(this.movePos.equals(new Pair<>(0f, 0f)) ? PlayerIdle.STOP : PlayerIdle.RUN);
                    this.movePos = new Pair<>(0f, 0f);
                    this.movingInAir = false;
                } else {
                    this.isMovingInAir();
                    this.updateInAirPosition();
                    this.getEntity().saveNextPosition(Optional.of(this.movePos));
                    PlayerIdle.setPlayerIdle(this.airSpeed > 0 ? PlayerIdle.FALLING : PlayerIdle.JUMP);
                }
        } else if (this.getEntity().getEntityType() == Type.BARREL) {
            if (!this.inAir) {
                this.moveEntity(this.getEntity().getComponent(MovementComponent.class).get().getFacing());
                this.getEntity().saveNextPosition(Optional.of(this.movePos));
            } else {
                this.updateInAirPosition();
                this.getEntity().saveNextPosition(Optional.of(this.movePos));
            }
        } else if (this.getEntity().getEntityType() == Type.PRINCESS) {
            if(timeElapsed > 24) {
                isPrincessWalking = true;
                timeElapsed = 0;
                final int randomInt = random.nextInt(10);
                if(randomInt < 4) {
                    this.moveEntity(this.getEntity().getComponent(MovementComponent.class).get().getFacing());
                } else if(randomInt < 7) {
                    this.moveEntity(this.getEntity().getComponent(MovementComponent.class).get().getFacing().getOppositeDirection());
                } else if(randomInt < 9) {
                    movePos = new Pair<>(0f, 0f);
                    isPrincessWalking = false;
                    timeElapsed = -24;
                }
            } else {
                if(isPrincessWalking)
                    this.moveEntity(this.getEntity().getComponent(MovementComponent.class).get().getFacing());
            }
            this.getEntity().saveNextPosition(this.movePos.equals(new Pair<>(0f, 0f)) ? Optional.empty() 
                                                                                          : Optional.of(this.movePos));
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

    /**
     * Moves the entity on the ladder.
     * 
     * @param direction the direction in which to move the entity
     */
    public final void moveEntityOnLadder(final Direction direction) {
        if(this.canUseLadder) {
            this.direction = direction;
            this.movePos = new Pair<>(direction.getX() * this.getEntity().getSpeed(), 
                                      direction.getY() * this.getEntity().getSpeed());
        }
    }


    /**
     * Set the entity to jump.
     */
    public final void jump() {
        if (!this.inAir) {
            this.inAir = true;
            this.airSpeed = Physics.jumpSpeed;
        }
    }

    private void isMovingInAir() {
        if (this.getEntity().getGameplay().getController().getInputs()
                .stream().filter(k -> k == KeyEvent.VK_D
                                      || k == KeyEvent.VK_A
                                      || k == KeyEvent.VK_RIGHT
                                      || k == KeyEvent.VK_LEFT)
                .findAny().isPresent()) {
                this.movingInAir = true;
            }
    }

    private void updateInAirPosition() {
        if (this.getEntity().getEntityType() == Type.PLAYER) {
            if (this.movingInAir) {
                this.movePos = new Pair<>(this.direction.getX() * this.getEntity().getSpeed()
                                          * Physics.speedInAirMultiplierPlayer, this.airSpeed);
            } else {
                this.movePos = new Pair<>(0f, this.airSpeed);
            }
            this.airSpeed += Physics.gravity * Physics.jumpGravityMultiplier;
        } else if (this.getEntity().getEntityType() == Type.BARREL) {
            this.movePos = new Pair<>(this.direction.getX() * this.getEntity().getSpeed()
                                      * Physics.speedInAirMultiplierBarrel, this.airSpeed);
            this.airSpeed += Physics.gravity;
        }
    }

    /**
     * Get the number of times the barrel has changed direction.
     * 
     * @return numeber of direction changes.
     */
    public final int getBarrelChangesCounter() {
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

    /**
     * Change entity direction.
     */
    public final void changeDirection() {
        this.direction = direction.getOppositeDirection();
        this.barrelChangesCounter++;
    }

    /**
     * Check if entity is in the air.
     * 
     * @return true if is in the air.
     */
    public final boolean isInAir() {
        return this.inAir;
    }

    /**
     * Set if entity is in the air.
     * 
     * @param isInAir set as new entity status.
     */
    public final void setIsInAir(final boolean isInAir) {
        this.inAir = isInAir;
        if(isInAir) {
            this.canUseLadder = false;
        }
    }

    /**
     * Reset entity status in air equals false.
     */
    public final void resetIsInAir() {
        this.inAir = false;
        this.airSpeed = 0f;
    }

    /**
     * Reset entity status in air equals false.
     */
    public final boolean canUseLadder() {
        return this.canUseLadder;
    }

    /**
     * Reset entity status in air equals false.
     */
    public final void setCanUseLadder(final boolean canUseLadder) {
        this.canUseLadder = canUseLadder;
    }
}
