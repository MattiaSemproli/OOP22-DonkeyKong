package it.unibo.donkeykong.game.ecs.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.donkeykong.game.ecs.api.Component;
import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.game.model.api.Gameplay;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Constants.PowerupAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

/**
 * Entity implementation.
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
     * @param type type of the entity.
     * @param pos position of the entity.
     * @param gameplay gameplay where entity is placed.
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
                this.width = Barrel.barrelWidth;
                this.height = Barrel.barrelHeight;
                this.speed = Constants.Barrel.velocity;
                break;
            case MONKEY:
                this.width = Constants.Monkey.monkeyWidth;
                this.height = Constants.Monkey.monkeyHeight;
                break;
            case PLAYER:
                this.width = Window.SCALED_TILES_SIZE;
                this.height = Window.SCALED_TILES_SIZE;
                this.speed = Constants.Player.velocity;
                break;
            case PRINCESS:
                this.width = Constants.Princess.princessWidth;
                this.height = Constants.Princess.princessHeight;
                this.speed = Constants.Princess.velocity;
                break;
            case HEART:
                this.width = PowerupAssets.heartWidth;
                this.height = PowerupAssets.heartHeight;
                break;
            case SHIELD:
                this.width = PowerupAssets.shieldWidth;
                this.height = PowerupAssets.shieldHeight;
                break;
            case SNOWFLAKE:
                this.width = PowerupAssets.freezeDimension;
                this.height = PowerupAssets.freezeDimension;
                break;
            case STAR:
                this.width = PowerupAssets.starDimension;
                this.height = PowerupAssets.starDimension;
                break;
            case BLOCK:
            case BLOCK_LADDER_DOWN:
            case BLOCK_LADDER_UP:
            case BLOCK_LADDER_UPDOWN:
            case LADDER:
            default:
                this.width = Window.SCALED_TILES_SIZE;
                this.height = Window.SCALED_TILES_SIZE;
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
    public final void saveNextPosition(final Optional<Pair<Float, Float>> nextPos) {
        this.nextPosition = nextPos.isPresent() ? Optional.of(new Pair<>(nextPos.get().getX() + this.pos.getX(), 
                                                                         nextPos.get().getY() + this.pos.getY()))
                                                : Optional.empty();
    }

    @Override
    public final Optional<Pair<Float, Float>> getNextPosition() {
        return this.nextPosition;
    }

    @Override
    public final void setPosition(final Pair<Float, Float> pos) {
        this.pos = new Pair<>(pos.getX(), pos.getY());
    }

    @Override
    public final Type getEntityType() {
        return this.type;
    }

    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    @Override
    public final Gameplay getGameplay() {
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

    @Override
    public final void setSpeed(final float speedModifier) {
        this.speed += speedModifier;
    }
}
