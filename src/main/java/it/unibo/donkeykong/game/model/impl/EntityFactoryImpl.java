package it.unibo.donkeykong.game.model.impl;

import it.unibo.donkeykong.game.ecs.impl.BarrelThrowComponent;
import it.unibo.donkeykong.game.ecs.impl.CollisionComponent;
import it.unibo.donkeykong.game.model.api.Entity;
import it.unibo.donkeykong.game.model.api.EntityFactory;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Barrel;

public class EntityFactoryImpl implements EntityFactory {

    private final GameplayImpl gameplay;

    /**
     * Constructor.
     * 
     * @param gameplay where the entity is
     */
    public EntityFactoryImpl(final GameplayImpl gameplay) {
        this.gameplay = gameplay;
    }

    @Override
    public Entity generatePlayer(final Pair<Float, Float> position) {
        return new EntityImpl(Type.PLAYER, position, this.gameplay);
    }

    @Override
    public final Entity generateBarrel(final Pair<Float,Float> position) {
        return new EntityImpl(Type.BARREL, position, this.gameplay)
                        .addComponent(new CollisionComponent(position.getX(), position.getY(), false));
    }

    @Override
    public Entity generateMonkey(final Pair<Float, Float> position) {
        return new EntityImpl(Type.MONKEY, position, this.gameplay);
    }

    @Override
    public Entity generatePrincess(final Pair<Float, Float> position) {
        return new EntityImpl(Type.PRINCESS, position, this.gameplay);
    }

    @Override
    public Entity generatePowerUp() {
        return null;
    }

    @Override
    public Entity generateBlock(Pair<Float, Float> position) {
        return new EntityImpl(Type.PRINCESS, position, this.gameplay);
    }

    @Override
    public Entity generateLadder(Pair<Float, Float> position) {
        return new EntityImpl(Type.LADDER, position, this.gameplay);
    }

    @Override
    public Entity generateBlockWithUpLadder(Pair<Float, Float> position) {
        return new EntityImpl(Type.BLOCK_LADDER_UP, position, this.gameplay);
    }

    @Override
    public Entity generateBlockWithDownLadder(Pair<Float, Float> position) {
        return new EntityImpl(Type.BLOCK_LADDER_DOWN, position, this.gameplay);
    }

    @Override
    public Entity generateBlockWithUpDownLadder(Pair<Float, Float> position) {
        return new EntityImpl(Type.BLOCK_LADDER_UPDOWN, position, this.gameplay);
    }
}
