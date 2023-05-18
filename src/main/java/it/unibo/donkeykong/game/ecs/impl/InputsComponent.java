package it.unibo.donkeykong.game.ecs.impl;

import java.awt.event.KeyEvent;
import java.util.Optional;

import it.unibo.donkeykong.utilities.Direction;

/**
 * Component that handles game inputs. 
 */
public class InputsComponent extends AbstractComponent {

    private Optional<KeyEvent> input;

    @Override
    public final void update() {
        this.input = this.getEntity().getGameplay().getController().getInputs().stream().findFirst();
        if (this.input.isPresent()) {
            switch (this.input.get().getKeyCode()) {
                case KeyEvent.VK_A:
                    this.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_D:
                    this.move(Direction.RIGHT);
                    break;
                default:
                    //jump to handle 
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
}
