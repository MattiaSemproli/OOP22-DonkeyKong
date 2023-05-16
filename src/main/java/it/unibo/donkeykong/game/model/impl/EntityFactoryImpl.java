package it.unibo.donkeykong.game.model.impl;

import it.unibo.donkeykong.game.ecs.impl.BarrelComponent;
import it.unibo.donkeykong.game.ecs.impl.CollisionComponent;
import it.unibo.donkeykong.game.model.api.Entity;
import it.unibo.donkeykong.game.model.api.EntityFactory;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Barrel;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public Entity generatePlayer() {
        return null;
    }

    @Override
    public final Entity generateBarrel(final Pair<Float,Float> position, final Type type) {
        return new EntityImpl(type, position)
                        .addComponent(new BarrelComponent(Barrel.firstLevelDirection, position))
                        .addComponent(new CollisionComponent(position.getX(), position.getY(), false));
    }

    @Override
    public Entity generateMonkey() {
        return null;
    }

    @Override
    public Entity generatePrincess() {
        return null;
    }

    @Override
    public Entity generatePowerUp() {
        return null;
    }
}
