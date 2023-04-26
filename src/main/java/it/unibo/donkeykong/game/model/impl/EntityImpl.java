package it.unibo.donkeykong.game.model.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.unibo.donkeykong.game.ecs.api.Component;
import it.unibo.donkeykong.game.ecs.impl.AbstractComponent;
import it.unibo.donkeykong.game.model.api.Entity;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

public class EntityImpl implements Entity {

    private final Type type;
    private final Set<Component> components;
    private Pair<Float,Float> pos;

    public EntityImpl(final Type type, final Pair<Float,Float> pos) {
        this.type = type;
        this.components = new HashSet<>();
        this.pos = new Pair<Float,Float>(pos.getX(), pos.getY());
    }

    @Override
    public Set<Component> getAllComponents() {
        return new HashSet<>(this.components);
    }

    @Override
    public <E extends Component> Optional<E> getComponent(Class<E> classComponent) {
        return this.components.stream().filter(classComponent::isInstance).map(classComponent::cast).findAny();
    }

    @Override
    public Entity addComponent(AbstractComponent component) {
        component.setEntity(this);
        this.components.add(component);
        return this;
    }

    @Override
    public Pair<Float, Float> getPosition() {
        return this.pos;
    }

    @Override
    public void setPosition(Pair<Float, Float> pos) {
        this.pos = new Pair<Float,Float>(pos.getX(), pos.getY());
    }

    @Override
    public Type getEntityType() {
        return this.type;
    }
    
}