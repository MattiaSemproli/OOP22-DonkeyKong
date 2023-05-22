package it.unibo.donkeykong.game.model.api;

import java.util.Optional;
import java.util.Set;

import it.unibo.donkeykong.game.ecs.api.Component;
import it.unibo.donkeykong.game.model.impl.AbstractComponent;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

/**
 * This interface models the structure of an entity.
 */
public interface Entity {

    /**
     * @return list of related components
     */
    Set<Component> getAllComponents();

    /**
     * @param <E>            type of the component requested
     * @param classComponent class of the requested component
     * @return an optional with requested component, an empty if not present
     */
    <E extends Component> Optional<E> getComponent(Class<E> classComponent);

    /**
     * @param component to add to an entity
     * @return the entity with the added component
     */
    Entity addComponent(AbstractComponent component);

    /**
     * @return the position of the entity
     */
    Pair<Float, Float> getPosition();

    /**
     * @param position of the entity to be set
     */
    void setPosition(Pair<Float, Float> position);

    /**
     * @return the entity's type
     */
    Type getEntityType();

    /**
     * @return the gameplay where the entity is
     */
    Gameplay getGameplay();

    /**
     * @return the entity's width
     */
    int getWidth();

    /**
     * @return the entity's height
     */
    int getHeight();

    /**
     * @return the entity's speed
     */
    float getSpeed();
}
