package it.unibo.donkeykong.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.api.EntityFactory;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.DoubleDamageComponent;
import it.unibo.donkeykong.model.ecs.impl.ThrowComponent;
import it.unibo.donkeykong.model.impl.EntityFactoryImpl;
import it.unibo.donkeykong.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.Type;

class BarrelTest {

    @Test
    void testGenerateNormalBarrel() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        gp.addEntity(ef.generateBarrel(new Pair<>(0f, 0f)));
        assertTrue(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.BARREL));
        assertFalse(gp.getEntities().stream().filter(e -> e.getEntityType() == Type.BARREL).findFirst().get()
                      .getComponent(DoubleDamageComponent.class).get().isDoubleDamage());
    }

    @Test
    void testGenerateDoubleDamageBarrel() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity barrel = ef.generateBarrel(new Pair<>(0f, 0f));
        barrel.getComponent(DoubleDamageComponent.class).get().setDoubleDamage(true);
        gp.addEntity(barrel);
        assertTrue(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.BARREL));
        assertTrue(gp.getEntities().stream().filter(e -> e.getEntityType() == Type.BARREL).findFirst().get()
                     .getComponent(DoubleDamageComponent.class).get().isDoubleDamage());
    }

    @Test
    void testThrowBarrel() {
        final Gameplay gp = new GameplayImpl(null);
        assertFalse(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.BARREL));
        gp.throwBarrel(new Pair<>(0f, 0f));
        assertTrue(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.BARREL));
        gp.removeAllBarrels();
        assertFalse(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.BARREL));
    }
}
