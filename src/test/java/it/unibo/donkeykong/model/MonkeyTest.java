package it.unibo.donkeykong.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.api.EntityFactory;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.ThrowComponent;
import it.unibo.donkeykong.model.impl.EntityFactoryImpl;
import it.unibo.donkeykong.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.Type;

class MonkeyTest {

    @Test
    void testGenerateMonkey() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        gp.addEntity(ef.generateMonkey(new Pair<>(0f, 0f)));
        assertTrue(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.MONKEY));
    }

    @Test
    void testMonkeyThrowBarrelWhileNotFreezed() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity monkey = ef.generateMonkey(new Pair<>(0f, 0f));
        gp.addEntity(monkey);
        assertFalse(monkey.getComponent(ThrowComponent.class).get().isFreezed());
        assertFalse(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.BARREL));
        monkey.getComponent(ThrowComponent.class).get().update();
        assertTrue(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.BARREL));
    }

    @Test
    void testMonkeyNotThrowBarrelWhileFreezed() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity monkey = ef.generateMonkey(new Pair<>(0f, 0f));
        monkey.getComponent(ThrowComponent.class).get().setFreezed(true);
        gp.addEntity(monkey);
        assertTrue(monkey.getComponent(ThrowComponent.class).get().isFreezed());
        assertFalse(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.BARREL));
        monkey.getComponent(ThrowComponent.class).get().update();
        assertFalse(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.BARREL));
    }
}
