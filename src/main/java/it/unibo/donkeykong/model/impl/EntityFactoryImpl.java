package it.unibo.donkeykong.model.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.donkeykong.utilities.Constants.Player;
import it.unibo.donkeykong.model.api.EntityFactory;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.CollisionComponent;
import it.unibo.donkeykong.model.ecs.impl.DoubleDamageComponent;
import it.unibo.donkeykong.model.ecs.impl.EntityImpl;
import it.unibo.donkeykong.model.ecs.impl.FreezeComponent;
import it.unibo.donkeykong.model.ecs.impl.HealthComponent;
import it.unibo.donkeykong.model.ecs.impl.InputsComponent;
import it.unibo.donkeykong.model.ecs.impl.MovementComponent;
import it.unibo.donkeykong.model.ecs.impl.ShieldComponent;
import it.unibo.donkeykong.model.ecs.impl.SlowComponent;
import it.unibo.donkeykong.model.ecs.impl.StarComponent;
import it.unibo.donkeykong.model.ecs.impl.ThrowComponent;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

/**
 * EntityFactory class, manages an entity factory.
 */
public class EntityFactoryImpl implements EntityFactory {

    private final Gameplay gameplay;

    /**
     * Constructor.
     * 
     * @param gameplay the linked gameplay.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public EntityFactoryImpl(final Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generatePlayer(final Pair<Float, Float> position) {
        return new EntityImpl(Type.PLAYER, position, this.gameplay)
                   .addComponent(new InputsComponent())
                   .addComponent(new MovementComponent())
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.PLAYER))
                   .addComponent(new HealthComponent(Player.numLives))
                   .addComponent(new StarComponent())
                   .addComponent(new ShieldComponent())
                   .addComponent(new FreezeComponent());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generateBarrel(final Pair<Float, Float> position) {
        return new EntityImpl(Type.BARREL, position, this.gameplay)
                   .addComponent(new MovementComponent())
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.BARREL))
                   .addComponent(new DoubleDamageComponent())
                   .addComponent(new SlowComponent());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generateMonkey(final Pair<Float, Float> position) {
        return new EntityImpl(Type.MONKEY, position, this.gameplay)
                   .addComponent(new ThrowComponent())
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.MONKEY));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generatePrincess(final Pair<Float, Float> position) {
        return new EntityImpl(Type.PRINCESS, position, this.gameplay)
                   .addComponent(new MovementComponent())
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.PRINCESS));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generateBlock(final Pair<Float, Float> position) {
        return new EntityImpl(Type.BLOCK, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.BLOCK));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generateLadder(final Pair<Float, Float> position) {
        return new EntityImpl(Type.LADDER, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.LADDER));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generateBlockWithUpLadder(final Pair<Float, Float> position) {
        return new EntityImpl(Type.BLOCK_LADDER_UP, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.BLOCK_LADDER_UP));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generateBlockWithDownLadder(final Pair<Float, Float> position) {
        return new EntityImpl(Type.BLOCK_LADDER_DOWN, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.BLOCK_LADDER_DOWN));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generateBlockWithUpDownLadder(final Pair<Float, Float> position) {
        return new EntityImpl(Type.BLOCK_LADDER_UPDOWN, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.BLOCK_LADDER_UPDOWN));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generateHeartPowerUp(final Pair<Float, Float> position) {
        return new EntityImpl(Type.HEART, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.HEART));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generateShieldPowerUp(final Pair<Float, Float> position) {
        return new EntityImpl(Type.SHIELD, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.SHIELD));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generateStarPowerUp(final Pair<Float, Float> position) {
        return new EntityImpl(Type.STAR, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.STAR));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity generateSnowflakePowerUp(final Pair<Float, Float> position) {
        return new EntityImpl(Type.SNOWFLAKE, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.SNOWFLAKE));
    }

}
