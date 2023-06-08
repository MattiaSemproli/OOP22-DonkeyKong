package it.unibo.donkeykong.model.ecs.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Component;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.utilities.ModelConstants.Application;
import it.unibo.donkeykong.utilities.ModelConstants.Barrel;
import it.unibo.donkeykong.utilities.ModelConstants.Monkey;
import it.unibo.donkeykong.utilities.ModelConstants.Player;
import it.unibo.donkeykong.utilities.ModelConstants.Powerup;
import it.unibo.donkeykong.utilities.ModelConstants.Princess;
import it.unibo.donkeykong.utilities.Type;

/**
 * Entity class, manages a entity.
 */
public class EntityImpl implements Entity {

    private final Type type;
    private final Set<Component> components;
    private Pair<Float, Float> pos;
    private Optional<Pair<Float, Float>> nextPosition = Optional.empty();
    private final Gameplay gameplay;
    private final int width, height;
    private float speed;

    /**
     * Constructor.
     * 
     * @param type the entity's type.
     * @param pos the entity's position.
     * @param gameplay the entity's linked gameplay.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public EntityImpl(final Type type, final Pair<Float, Float> pos, final Gameplay gameplay) {
        this.type = type;
        this.components = new HashSet<>();
        this.pos = new Pair<>(pos.getX(), pos.getY());
        this.gameplay = gameplay;
        this.speed = 0;
        switch (type) {
            case BARREL:
                this.width = Barrel.BARREL_WIDTH;
                this.height = Barrel.BARREL_HEIGHT;
                this.speed = Barrel.VELOCITY;
                break;
            case MONKEY:
                this.width = Monkey.MONKEY_WIDTH;
                this.height = Monkey.MONKEY_HEIGHT;
                break;
            case PLAYER:
                this.width = Application.SCALED_TILES_SIZE;
                this.height = Application.SCALED_TILES_SIZE;
                this.speed = Player.VELOCITY;
                break;
            case PRINCESS:
                this.width = Princess.PRINCESS_WIDTH;
                this.height = Princess.PRINCESS_HEIGHT;
                this.speed = Princess.VELOCITY;
                break;
            case HEART:
                this.width = Powerup.HEART_WIDTH;
                this.height = Powerup.HEART_HEIGHT;
                break;
            case SHIELD:
                this.width = Powerup.SHIELD_WIDTH;
                this.height = Powerup.SHIELD_HEIGHT;
                break;
            case SNOWFLAKE:
                this.width = Powerup.FREEZE_DIMENSION;
                this.height = Powerup.FREEZE_DIMENSION;
                break;
            case STAR:
                this.width = Powerup.STAR_DIMENSION;
                this.height = Powerup.STAR_DIMENSION;
                break;
            case BLOCK:
            case BLOCK_LADDER_DOWN:
            case BLOCK_LADDER_UP:
            case BLOCK_LADDER_UPDOWN:
            case LADDER:
            default:
                this.width = Application.SCALED_TILES_SIZE;
                this.height = Application.SCALED_TILES_SIZE;
                break;

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Component> getAllComponents() {
        return new HashSet<>(this.components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends Component> Optional<E> getComponent(final Class<E> classComponent) {
        return this.components.stream().filter(classComponent::isInstance).map(classComponent::cast).findAny();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity addComponent(final AbstractComponent component) {
        component.setEntity(this);
        this.components.add(component);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Float, Float> getPosition() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveNextPosition(final Optional<Pair<Float, Float>> nextPos) {
        this.nextPosition = nextPos.isPresent() ? Optional.of(new Pair<>(nextPos.get().getX() + this.pos.getX(), 
                                                                         nextPos.get().getY() + this.pos.getY()))
                                                : Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Pair<Float, Float>> getNextPosition() {
        return this.nextPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Pair<Float, Float> pos) {
        this.pos = new Pair<>(pos.getX(), pos.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getEntityType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    @Override
    public Gameplay getGameplay() {
        return this.gameplay;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final float speedModifier) {
        this.speed += speedModifier;
    }
}
