package it.unibo.donkeykong.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.api.EntityFactory;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.CollisionComponent;
import it.unibo.donkeykong.model.ecs.impl.DoubleDamageComponent;
import it.unibo.donkeykong.model.ecs.impl.FreezeComponent;
import it.unibo.donkeykong.model.ecs.impl.HealthComponent;
import it.unibo.donkeykong.model.ecs.impl.MovementComponent;
import it.unibo.donkeykong.model.ecs.impl.ShieldComponent;
import it.unibo.donkeykong.model.ecs.impl.StarComponent;
import it.unibo.donkeykong.model.impl.EntityFactoryImpl;
import it.unibo.donkeykong.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.ModelConstants;
import it.unibo.donkeykong.utilities.Type;

class PowerupTest {

    private static final int TEST_LIFE_1 = -3;
    private static final int TEST_LIFE_2 = 5;
    private static final int TEST_LIFE_3 = 7;

    @Test
    void testGeneratePowerup() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        assertFalse(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.STAR
                                                            || e.getEntityType() == Type.HEART
                                                            || e.getEntityType() == Type.SHIELD
                                                            || e.getEntityType() == Type.SNOWFLAKE));
        gp.addEntity(ef.generateHeartPowerUp(new Pair<>(0f, 0f)));
        gp.addEntity(ef.generateShieldPowerUp(new Pair<>(0f, 0f)));
        gp.addEntity(ef.generateSnowflakePowerUp(new Pair<>(0f, 0f)));
        gp.addEntity(ef.generateStarPowerUp(new Pair<>(0f, 0f)));
        assertTrue(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.STAR
                                                            || e.getEntityType() == Type.HEART
                                                            || e.getEntityType() == Type.SHIELD
                                                            || e.getEntityType() == Type.SNOWFLAKE));
    }

    @Test
    void testStarPowerup() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity player = ef.generatePlayer(new Pair<>(0f, 0f));
        gp.addEntity(player);
        final StarComponent sc = player.getComponent(StarComponent.class).get();
        assertFalse(sc.isInvincible());
        gp.addEntity(ef.generateStarPowerUp(new Pair<>((float) ModelConstants.Player.PLAYER_DIMENSION, 0f)));
        final MovementComponent mc = player.getComponent(MovementComponent.class).get();
        mc.moveEntity(Direction.RIGHT);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        assertTrue(sc.isInvincible());
        for (int i = 0; i <= ModelConstants.Player.STAR_DURATION; i++) {
            sc.update();
        }
        assertFalse(sc.isInvincible());
    }

    @Test
    void testShieldPowerup() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity player = ef.generatePlayer(new Pair<>(0f, 0f));
        gp.addEntity(player);
        final ShieldComponent sc = player.getComponent(ShieldComponent.class).get();
        assertFalse(sc.isShielded());
        gp.addEntity(ef.generateShieldPowerUp(new Pair<>((float) ModelConstants.Player.PLAYER_DIMENSION, 0f)));
        final MovementComponent mc = player.getComponent(MovementComponent.class).get();
        mc.moveEntity(Direction.RIGHT);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        assertTrue(sc.isShielded());
        gp.addEntity(ef.generateBarrel(new Pair<>((float) ModelConstants.Player.PLAYER_DIMENSION, 0f)));
        mc.moveEntity(Direction.RIGHT);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        assertFalse(sc.isShielded());
    }

    @Test
    void testBarrelHitWithShield() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity player = ef.generatePlayer(new Pair<>(0f, 0f));
        player.getComponent(ShieldComponent.class).get().setShield(true);
        gp.addEntity(player);
        gp.addEntity(ef.generateBarrel(new Pair<>((float) ModelConstants.Player.PLAYER_DIMENSION, 0f)));
        final MovementComponent mc = player.getComponent(MovementComponent.class).get();
        mc.moveEntity(Direction.RIGHT);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        assertFalse(player.getComponent(ShieldComponent.class).get().isShielded());
        assertEquals(ModelConstants.Player.NUM_LIVES, player.getComponent(HealthComponent.class).get().getLives());
        player.getComponent(ShieldComponent.class).get().setShield(true);
        final Entity ddBarrel = ef.generateBarrel(new Pair<>((float) ModelConstants.Player.PLAYER_DIMENSION, 0f));
        ddBarrel.getComponent(DoubleDamageComponent.class).get().setDoubleDamage(true);
        gp.addEntity(ddBarrel);
        mc.moveEntity(Direction.RIGHT);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        assertFalse(player.getComponent(ShieldComponent.class).get().isShielded());
        assertEquals(ModelConstants.Player.NUM_LIVES - 1, player.getComponent(HealthComponent.class).get().getLives());
    }

    @Test
    void testFreezePowerup() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity player = ef.generatePlayer(new Pair<>(0f, 0f));
        gp.addEntity(player);
        final FreezeComponent fc = player.getComponent(FreezeComponent.class).get();
        assertFalse(fc.isFrozen());
        gp.addEntity(ef.generateSnowflakePowerUp(new Pair<>((float) ModelConstants.Player.PLAYER_DIMENSION, 0f)));
        final MovementComponent mc = player.getComponent(MovementComponent.class).get();
        mc.moveEntity(Direction.RIGHT);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        assertTrue(fc.isFrozen());
        for (int i = 0; i <= ModelConstants.Player.FREEZE_DURATION; i++) {
            fc.update();
        }
        assertFalse(fc.isFrozen());
    }

    @Test
    void testHeartPowerup() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity player = ef.generatePlayer(new Pair<>(0f, 0f));
        gp.addEntity(player);
        final HealthComponent hc = player.getComponent(HealthComponent.class).get();
        assertEquals(ModelConstants.Player.NUM_LIVES, hc.getLives());
        gp.addEntity(ef.generateHeartPowerUp(new Pair<>((float) ModelConstants.Player.PLAYER_DIMENSION, 0f)));
        final MovementComponent mc = player.getComponent(MovementComponent.class).get();
        mc.moveEntity(Direction.RIGHT);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        assertTrue(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.HEART));
        assertEquals(ModelConstants.Player.NUM_LIVES, hc.getLives());
        hc.setLifes(-1);
        assertEquals(ModelConstants.Player.NUM_LIVES - 1, hc.getLives());
        mc.moveEntity(Direction.RIGHT);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        assertEquals(ModelConstants.Player.NUM_LIVES, hc.getLives());
        assertFalse(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.HEART));
    }

    @Test
    void testHealthComponent() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity player = ef.generatePlayer(new Pair<>(0f, 0f));
        gp.addEntity(player);
        final HealthComponent hc = player.getComponent(HealthComponent.class).get();
        assertEquals(ModelConstants.Player.NUM_LIVES, hc.getLives());
        hc.setLifes(-1);
        assertEquals(ModelConstants.Player.NUM_LIVES - 1, hc.getLives());
        hc.setLifes(TEST_LIFE_1);
        assertEquals(0, hc.getLives());
        assertNotEquals(ModelConstants.Player.NUM_LIVES - 4, hc.getLives());
        hc.setLifes(2);
        assertEquals(2, hc.getLives());
        hc.setLifes(TEST_LIFE_2);
        assertEquals(ModelConstants.Player.NUM_LIVES, hc.getLives());
        assertNotEquals(TEST_LIFE_3, hc.getLives());
    }
}
