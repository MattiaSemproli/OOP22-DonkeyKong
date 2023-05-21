package it.unibo.donkeykong.game.model.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.unibo.donkeykong.game.ecs.api.Component;
import it.unibo.donkeykong.game.ecs.impl.AbstractComponent;
import it.unibo.donkeykong.game.model.api.Entity;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Window;

/**
 * Entity implementation.
 */
public class EntityImpl implements Entity {

    private final Type type;
    private final Set<Component> components;
    private Pair<Float, Float> pos;
    private final GameplayImpl gameplay;
    private final int width, height;
    private final float speed;

    /**
     * Constructor.
     * 
     * @param type type of the entity.
     * @param pos position of the entity.
     * @param gameplay gameplay where entity is placed.
     */
    public EntityImpl(final Type type, final Pair<Float, Float> pos, final GameplayImpl gameplay) {
        this.type = type;
        this.components = new HashSet<>();
        this.pos = new Pair<Float, Float>(pos.getX(), pos.getY());
        this.gameplay = gameplay;
        switch(type) {
            case BARREL:
                this.width = Window.SCALED_TILES_SIZE;
                this.height = Window.SCALED_TILES_SIZE;
                this.speed = Constants.Barrel.barrelVelocity;
                break;
            case MONKEY:
                this.width = Constants.Entity.monkeyWidth;
                this.height = Constants.Entity.monkeyHeight;
                this.speed = 0;
                break;
            case PLAYER:
                this.width = Window.SCALED_TILES_SIZE;
                this.height = Window.SCALED_TILES_SIZE;
                this.speed = 2f;
                break;
            case PRINCESS:
                this.width = Constants.Entity.princessWidth;
                this.height = Constants.Entity.princessHeight;
                this.speed = 0;
                break;
            case POWER_UP:
                this.width = Window.SCALED_TILES_SIZE;
                this.height = Window.SCALED_TILES_SIZE;
                this.speed = 0;
                break;
            case BLOCK:
            case BLOCK_LADDER_DOWN:
            case BLOCK_LADDER_UP:
            case BLOCK_LADDER_UPDOWN:
            case LADDER:
            default:
                this.width = Window.SCALED_TILES_SIZE;
                this.height = Window.SCALED_TILES_SIZE;
                this.speed = 0;
                break;

        }
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
    public final GameplayImpl getGameplay() {
        return this.gameplay;
    }

    @Override
    public final int getWidth() {
        return this.width;
    }

    @Override
    public final int getHeight() {
        return this.height;
    }

    @Override
    public final float getSpeed() {
        return this.speed;
    }
}
