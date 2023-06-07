package it.unibo.donkeykong.model.ecs.api;

import java.util.Optional;
import java.util.Set;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.impl.AbstractComponent;
import it.unibo.donkeykong.utilities.Type;

/**
 * Entity interface, models an entity.
 */
public interface Entity {
    /**
     * Get all entity's components.
     * 
     * @return a list of related components.
     */
    Set<Component> getAllComponents();

    /**
     * Get an entity's component if present.
     * 
     * @param <E> the requested component's type.
     * @param classComponent the requested component's class.
     * @return an optional with requested component if present.
     */
    <E extends Component> Optional<E> getComponent(Class<E> classComponent);

    /**
     * Add a component to an entity.
     * 
     * @param component the component.
     * @return the entity with the added component.
     */
    Entity addComponent(AbstractComponent component);

    /**
     * Get entity's position.
     * 
     * @return the entity's position.
     */
    Pair<Float, Float> getPosition();

    /**
     * Save the next position where the entity will be.
     * 
     * @param position the next position.
     */
    void saveNextPosition(Optional<Pair<Float, Float>> position);

    /**
     * Return entity's next position.
     * 
     * @return an optional with the next position if present.
     */
    Optional<Pair<Float, Float>> getNextPosition();

    /**
     * Set entity's position.
     * 
     * @param position the new position.
     */
    void setPosition(Pair<Float, Float> position);

    /**
     * Get the entity's type.
     * 
     * @return the entity's type.
     */
    Type getEntityType();

    /**
     * Get linked gameplay.
     * 
     * @return the gameplay where the entity is placed.
     */
    Gameplay getGameplay();

    /**
     * Get entity's width.
     * 
     * @return the entity's width.
     */
    int getWidth();

    /**
     * Get entity's height.
     * 
     * @return the entity's height.
     */
    int getHeight();

    /**
     * Get entity's speed.
     * 
     * @return the entity's speed.
     */
    float getSpeed();

    /**
     * Modify entity's speed.
     * 
     * @param speedModifier the speed modifier.
     */
    void setSpeed(float speedModifier);
}
