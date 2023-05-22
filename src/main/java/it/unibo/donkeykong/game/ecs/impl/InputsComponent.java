package it.unibo.donkeykong.game.ecs.impl;

import java.awt.event.KeyEvent;
import java.util.Optional;

import it.unibo.donkeykong.game.model.impl.AbstractComponent;
import it.unibo.donkeykong.utilities.Direction;

/**
 * Component that handles game inputs. 
 */
public class InputsComponent extends AbstractComponent {
    
    @Override
    public final void update() {
        Optional<Integer> input = this.getEntity().getGameplay().getController().getInputs().stream().findFirst();
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
