package it.unibo.donkeykong.model.ecs.impl;

import java.util.Optional;

import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Constants.Action;

/**
 * Inputs component, manages inputs handling. 
 */
public class InputsComponent extends AbstractComponent {

    @Override
    public final void update() {
        final Optional<Integer> input = this.getEntity().getGameplay().getController().getInputs().stream().findFirst();
        if (input.isPresent()) {
            switch (input.get()) {
                case Action.MOVE_LEFT:
                case Action.MOVE_LEFT_ARROW:
                    this.move(Direction.LEFT);
                    break;
                case Action.MOVE_RIGHT:
                case Action.MOVE_RIGHT_ARROW:
                    this.move(Direction.RIGHT);
                    break;
                case Action.MOVE_UP:
                case Action.MOVE_UP_ARROW:
                    this.moveOnLadder(Direction.UP);
                    break;
                case Action.MOVE_DOWN:
                case Action.MOVE_DOWN_ARROW:
                    this.moveOnLadder(Direction.DOWN);
                    break;
                case Action.JUMP:
                    this.jump();
                    break;
                default:
                    break;
            }
        }
    }

    private void move(final Direction direction) {
        final Optional<MovementComponent> moveOptional = this.getEntity().getComponent(MovementComponent.class);
        if (moveOptional.isPresent()) {
            moveOptional.get().moveEntity(direction);
        } 
    }

    private void moveOnLadder(final Direction direction) {
        final Optional<MovementComponent> moveOptional = this.getEntity().getComponent(MovementComponent.class);
        if (moveOptional.isPresent()) {
            moveOptional.get().moveEntityOnLadder(direction);
        } 
    }

    private void jump() {
        final Optional<MovementComponent> moveOptional = this.getEntity().getComponent(MovementComponent.class);
        if (moveOptional.isPresent()) {
            moveOptional.get().jump();
        } 
    }
}
