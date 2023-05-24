package it.unibo.donkeykong.game.ecs.api;

import java.util.Optional;
import java.util.Set;

import it.unibo.donkeykong.game.ecs.impl.AbstractComponent;
import it.unibo.donkeykong.game.model.api.Gameplay;
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
     * Save the next pos where the entity will be.
     * 
     * @param position next position to be saved for collision check.
     */
    void saveNextPosition(Optional<Pair<Float, Float>> position);

    /**
     * Return the next position of the entity.
     * 
     * @param position position to be checked.
     * @return an optional with the next position, an empty if not present.
     */
    Optional<Pair<Float, Float>> getNextPosition();

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

    /**
     * Modify entity speed.
     */
    void setSpeed(float speedModifier);
}
