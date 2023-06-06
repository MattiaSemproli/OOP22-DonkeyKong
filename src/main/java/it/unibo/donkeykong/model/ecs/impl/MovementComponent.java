package it.unibo.donkeykong.model.ecs.impl;

import java.util.Optional;
import java.util.Random;

import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.PlayerIdle;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Action;
import it.unibo.donkeykong.utilities.Constants.Physics;
import it.unibo.donkeykong.utilities.Constants.Princess;

/**
 * Movement component, manages an entity's movement.
 */
public class MovementComponent extends AbstractComponent {

    private Pair<Float, Float> movePos = new Pair<>(0f, 0f);
    private Direction direction = Direction.RIGHT;
    private float airSpeed;
    private int barrelChangesCounter;
    private boolean inAir;
    private boolean movingInAir;
    private int timeElapsed;
    private boolean isPrincessWalking;
    private final Random random = new Random();
    private boolean onLadder;
    private boolean onFloor;
    private boolean canUseLadder;

    /**
     * Constructor.
     */
    public MovementComponent() {
        this.timeElapsed = 0;
        this.airSpeed = 0f;
        this.barrelChangesCounter = 0;
        this.inAir = false;
        this.movingInAir = false;
        this.isPrincessWalking = false;
        this.onLadder = false;
        this.onFloor = true;
        this.canUseLadder = false;
    }

    @Override
    public final void update() {
        timeElapsed++;
        if (this.getEntity().getEntityType() == Type.PLAYER) {
            if (!this.inAir) {
                this.getEntity().saveNextPosition(this.movePos.equals(new Pair<>(0f, 0f)) ? Optional.empty() 
                                                                                              : Optional.of(this.movePos));
                if (this.onLadder) {
                    PlayerIdle.setPlayerIdle(this.movePos.equals(new Pair<>(0f, 0f)) ? PlayerIdle.STOPCLIMBING
                                                                                         : PlayerIdle.CLIMBING);
                } else {
                    PlayerIdle.setPlayerIdle(this.movePos.equals(new Pair<>(0f, 0f)) ? PlayerIdle.STOP
                                                                                         : PlayerIdle.RUN);
                }
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
            final MovementComponent mc = this.getEntity().getComponent(MovementComponent.class).get();
            if (timeElapsed > Princess.nextRandomMoveTime) {
                isPrincessWalking = true;
                PlayerIdle.setPrincessIdle(PlayerIdle.RUN);
                timeElapsed = 0;
                final int randomInt = random.nextInt(Princess.totalProbability);
                if (randomInt < Princess.sameDirProb) {
                    this.moveEntity(mc.getFacing());
                } else if (randomInt < Princess.changeDirProb) {
                    this.moveEntity(mc.getFacing().getOppositeDirection());
                } else if (randomInt < Princess.noMoveProb) {
                    movePos = new Pair<>(0f, 0f);
                    isPrincessWalking = false;
                    timeElapsed = -Princess.noMoveAddictionalTime;
                    PlayerIdle.setPrincessIdle(PlayerIdle.STOP);
                }
            } else {
                if (isPrincessWalking) {
                    this.moveEntity(mc.getFacing());
                }
            }
            this.getEntity().saveNextPosition(this.movePos.equals(new Pair<>(0f, 0f)) ? Optional.empty() 
                                                                                          : Optional.of(this.movePos));
        }
    }

    /**
     * Moves the entity in the specified direction.
     * 
     * @param direction the direction.
     */
    public final void moveEntity(final Direction direction) {
        if (!this.onLadder) {
            this.direction = direction;
            this.movePos = new Pair<>(direction.getX() * this.getEntity().getSpeed(), 
                                      direction.getY() * this.getEntity().getSpeed());
        }
    }

    /**
     * Moves the entity on the ladder.
     * 
     * @param direction the direction.
     */
    public final void moveEntityOnLadder(final Direction direction) {
        if (this.canUseLadder) {
            this.onLadder = true;
            this.direction = direction;
            this.movePos = new Pair<>(direction.getX() * this.getEntity().getSpeed(), 
                                      direction.getY() * this.getEntity().getSpeed());
        }
    }

    /**
     * Entity executes a jump.
     */
    public final void jump() {
        if (!this.inAir && !this.onLadder) {
            this.setInAir(true);
            this.airSpeed = Physics.jumpSpeed;
        }
    }

    private void isMovingInAir() {
        if (this.getEntity().getGameplay().getInputs().stream()
                .filter(k -> k != Action.JUMP && Action.isMovementCode(k))
                .findFirst().isPresent()) {
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
     * Get barrel's number of direction changes.
     * 
     * @return the number of direction changes.
     */
    public final int getBarrelChangesCounter() {
        return barrelChangesCounter;
    }

    /**
     * Get entity's facing direction.
     * 
     * @return the facing direction.
     */
    public final Direction getFacing() {
        return this.direction;
    }

    /**
     * Change entity's direction.
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
     * @param isInAir the new entity's in air status.
     */
    public final void setInAir(final boolean isInAir) {
        this.inAir = isInAir;
        if (isInAir) {
            this.canUseLadder = false;
            this.onLadder = false;
            this.onFloor = false;
        }
    }

    /**
     * Reset entity's in air status to false.
     */
    public final void resetInAir() {
        this.inAir = false;
        this.airSpeed = 0f;
        this.onFloor = true;
    }

    /**
     * Check if entity is on floor.
     * 
     * @return true if is on floor.
     */
    public final boolean isOnFloor() {
        return this.onFloor;
    }

    /**
     * Set if entity is on floor.
     * 
     * @param onFloor the new entity's on floor status.
     */
    public final void setOnFloor(final boolean onFloor) {
        this.onFloor = onFloor;
    }

    /**
     * Check if entity can use ladder.
     * 
     * @return true if can use ladder.
     */
    public final boolean canUseLadder() {
        return this.canUseLadder;
    }

    /**
     * Set if entity can use ladder.
     * 
     * @param canUseLadder the new entity's can use ladder status.
     */
    public final void setCanUseLadder(final boolean canUseLadder) {
        this.canUseLadder = canUseLadder;
    }

    /**
     * Check if entity is on ladder.
     * 
     * @return true if is on ladder.
     */
    public final boolean isOnLadder() {
        return this.onLadder;
    }

    /**
     * Set if entity is on ladder.
     * 
     * @param onLadder the new entity's on ladder status.
     */
    public final void setOnLadder(final boolean onLadder) {
        this.onLadder = onLadder;
        if (onLadder) {
            this.onFloor = false;
        }
    }
}
