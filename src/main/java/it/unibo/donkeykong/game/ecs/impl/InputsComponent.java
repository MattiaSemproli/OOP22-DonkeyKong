package it.unibo.donkeykong.game.ecs.impl;

import java.awt.event.KeyEvent;
import java.util.Optional;

import it.unibo.donkeykong.utilities.Direction;

/**
 * Inputs component, manages inputs handling. 
 */
public class InputsComponent extends AbstractComponent {

    @Override
    public final void update() {
        final Optional<Integer> input = this.getEntity().getGameplay().getController().getInputs().stream().findFirst();
        if (input.isPresent()) {
            switch (input.get()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    this.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    this.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    this.moveOnLadder(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    this.moveOnLadder(Direction.DOWN);
                    break;
                case KeyEvent.VK_SPACE:
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
