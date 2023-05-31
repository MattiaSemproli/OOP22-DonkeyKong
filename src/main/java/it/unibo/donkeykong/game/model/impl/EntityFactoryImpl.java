package it.unibo.donkeykong.game.model.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.game.ecs.impl.CollisionComponent;
import it.unibo.donkeykong.game.ecs.impl.DoubleDamageComponent;
import it.unibo.donkeykong.game.ecs.impl.EntityImpl;
import it.unibo.donkeykong.game.ecs.impl.FreezeComponent;
import it.unibo.donkeykong.game.ecs.impl.HealthComponent;
import it.unibo.donkeykong.game.ecs.impl.InputsComponent;
import it.unibo.donkeykong.game.ecs.impl.MovementComponent;
import it.unibo.donkeykong.game.ecs.impl.ShieldComponent;
import it.unibo.donkeykong.game.ecs.impl.SlowComponent;
import it.unibo.donkeykong.game.ecs.impl.StarComponent;
import it.unibo.donkeykong.game.ecs.impl.ThrowComponent;
import it.unibo.donkeykong.game.model.api.EntityFactory;
import it.unibo.donkeykong.game.model.api.Gameplay;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Player;

/**
 * EntityFactory class, generates entities.
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

    @Override
    public final Entity generatePlayer(final Pair<Float, Float> position) {
        return new EntityImpl(Type.PLAYER, position, this.gameplay)
                   .addComponent(new InputsComponent())
                   .addComponent(new MovementComponent())
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.PLAYER))
                   .addComponent(new HealthComponent(Player.numLifes))
                   .addComponent(new StarComponent())
                   .addComponent(new ShieldComponent())
                   .addComponent(new FreezeComponent());
    }

    @Override
    public final Entity generateBarrel(final Pair<Float, Float> position) {
        return new EntityImpl(Type.BARREL, position, this.gameplay)
                   .addComponent(new MovementComponent())
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.BARREL))
                   .addComponent(new DoubleDamageComponent())
                   .addComponent(new SlowComponent());
    }

    @Override
    public final Entity generateMonkey(final Pair<Float, Float> position) {
        return new EntityImpl(Type.MONKEY, position, this.gameplay)
                   .addComponent(new ThrowComponent())
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.MONKEY));
    }

    @Override
    public final Entity generatePrincess(final Pair<Float, Float> position) {
        return new EntityImpl(Type.PRINCESS, position, this.gameplay)
                   .addComponent(new MovementComponent())
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.PRINCESS));
    }

    @Override
    public final Entity generateBlock(final Pair<Float, Float> position) {
        return new EntityImpl(Type.BLOCK, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.BLOCK));
    }

    @Override
    public final Entity generateLadder(final Pair<Float, Float> position) {
        return new EntityImpl(Type.LADDER, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.LADDER));
    }

    @Override
    public final Entity generateBlockWithUpLadder(final Pair<Float, Float> position) {
        return new EntityImpl(Type.BLOCK_LADDER_UP, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.BLOCK_LADDER_UP));
    }

    @Override
    public final Entity generateBlockWithDownLadder(final Pair<Float, Float> position) {
        return new EntityImpl(Type.BLOCK_LADDER_DOWN, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.BLOCK_LADDER_DOWN));
    }

    @Override
    public final Entity generateBlockWithUpDownLadder(final Pair<Float, Float> position) {
        return new EntityImpl(Type.BLOCK_LADDER_UPDOWN, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.BLOCK_LADDER_UPDOWN));
    }

    @Override
    public final Entity generateHeartPowerUp(final Pair<Float, Float> position) {
        return new EntityImpl(Type.HEART, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.HEART));
    }

    @Override
    public final Entity generateShieldPowerUp(final Pair<Float, Float> position) {
        return new EntityImpl(Type.SHIELD, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.SHIELD));
    }

    @Override
    public final Entity generateStarPowerUp(final Pair<Float, Float> position) {
        return new EntityImpl(Type.STAR, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.STAR));
    }

    @Override
    public final Entity generateSnowflakePowerUp(final Pair<Float, Float> position) {
        return new EntityImpl(Type.SNOWFLAKE, position, this.gameplay)
                   .addComponent(new CollisionComponent(position.getX(), position.getY(), Type.SNOWFLAKE));
    }

}
