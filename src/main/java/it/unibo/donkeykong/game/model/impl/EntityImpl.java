package it.unibo.donkeykong.game.model.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.unibo.donkeykong.game.ecs.api.Component;
import it.unibo.donkeykong.game.ecs.impl.AbstractComponent;
import it.unibo.donkeykong.game.model.api.Entity;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

/**
 * Entity implementation.
 */
public class EntityImpl implements Entity {

    private final Type type;
    private final Set<Component> components;
    private Pair<Float, Float> pos;
    private GameplayImpl gameplay;

    /**
     * Constructor.
     * 
     * @param type type of the entity.
     * @param pos  position of the entity.
     */
    public EntityImpl(final Type type, final Pair<Float, Float> pos) {
        this.type = type;
        this.components = new HashSet<>();
        this.pos = new Pair<Float, Float>(pos.getX(), pos.getY());
    }

    @Override
    public final Set<Component> getAllComponents() {
        return new HashSet<>(this.components);
    }

    @Override
    public final <E extends Component> Optional<E> getComponent(final Class<E> classComponent) {
        return this.components.stream().filter(classComponent::isInstance).map(classComponent::cast).findAny();
    }

    @Override
    public final Entity addComponent(final AbstractComponent component) {
        component.setEntity(this);
        this.components.add(component);
        return this;
    }

    @Override
    public final Pair<Float, Float> getPosition() {
        return this.pos;
    }

    @Override
    public final void setPosition(final Pair<Float, Float> pos) {
        this.pos = new Pair<Float, Float>(pos.getX(), pos.getY());
    }

    @Override
    public final Type getEntityType() {
        return this.type;
    }

    @Override
    public GameplayImpl getGameplay() {
        return this.gameplay;
    }

    @Override
    public void setGameplay(final GameplayImpl gameplay) {
        this.gameplay = gameplay;
    }

}
