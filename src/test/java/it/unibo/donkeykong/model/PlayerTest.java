package it.unibo.donkeykong.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.api.EntityFactory;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.CollisionComponent;
import it.unibo.donkeykong.model.ecs.impl.HealthComponent;
import it.unibo.donkeykong.model.ecs.impl.InputsComponent;
import it.unibo.donkeykong.model.ecs.impl.MovementComponent;
import it.unibo.donkeykong.model.ecs.impl.StarComponent;
import it.unibo.donkeykong.model.ecs.impl.ThrowComponent;
import it.unibo.donkeykong.model.impl.EntityFactoryImpl;
import it.unibo.donkeykong.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.PlayerIdle;

class PlayerTest {

    private static final float TEST_PLAYER_X = 100f;
    private static final float TEST_PLAYER_Y = 100f;

    private final Gameplay gp = new GameplayImpl(null);
    private final EntityFactory ef = new EntityFactoryImpl(gp);
    private final Entity player = ef.generatePlayer(new Pair<>(TEST_PLAYER_X, TEST_PLAYER_Y));

    @Test
    void testInitPlayerAndPosition() {
        assertNotNull(this.player);
        assertEquals(new Pair<>(TEST_PLAYER_X, TEST_PLAYER_Y), this.player.getPosition());
        assertEquals(TEST_PLAYER_X, this.player.getPosition().getX());
        assertNotEquals(new Pair<>(0f, 0f), this.player.getPosition());
        assertNotEquals(0f, this.player.getPosition().getX());
    }

    @Test
    void testPlayerGetSomeComponentsAndArePresent() {
        assertTrue(this.player.getComponent(InputsComponent.class).isPresent());
        assertTrue(InputsComponent.class.isInstance(this.player.getComponent(InputsComponent.class).get()));
        assertTrue(this.player.getComponent(MovementComponent.class).isPresent());
        assertTrue(MovementComponent.class.isInstance(this.player.getComponent(MovementComponent.class).get()));
        assertTrue(this.player.getComponent(HealthComponent.class).isPresent());
        assertTrue(HealthComponent.class.isInstance(this.player.getComponent(HealthComponent.class).get()));
        assertTrue(this.player.getComponent(StarComponent.class).isPresent());
        assertTrue(StarComponent.class.isInstance(this.player.getComponent(StarComponent.class).get()));
    }

    @Test
    void testPlayerGetComponentNotPresent() {
        assertFalse(this.player.getComponent(ThrowComponent.class).isPresent());
    }

    @Test
    void testMovePlayer() {
        assertEquals(new Pair<>(TEST_PLAYER_X, TEST_PLAYER_Y), this.player.getPosition());
        final MovementComponent mc = this.player.getComponent(MovementComponent.class).get();
        mc.moveEntity(Direction.RIGHT);
        mc.update();
        this.player.getComponent(CollisionComponent.class).get().update();
        assertNotEquals(new Pair<>(TEST_PLAYER_X, TEST_PLAYER_Y), this.player.getPosition());
    }

    @Test
    void testJumpPlayer() {
        assertEquals(new Pair<>(TEST_PLAYER_X, TEST_PLAYER_Y), this.player.getPosition());
        final MovementComponent mc = this.player.getComponent(MovementComponent.class).get();
        assertFalse(mc.isInAir());
        assertEquals(PlayerIdle.STOP, PlayerIdle.getPlayerIdle());
        mc.jump();
        mc.update();
        this.player.getComponent(CollisionComponent.class).get().update();
        assertTrue(mc.isInAir());
        assertNotEquals(PlayerIdle.STOP, PlayerIdle.getPlayerIdle());
        assertEquals(PlayerIdle.JUMP, PlayerIdle.getPlayerIdle());
        assertNotEquals(new Pair<>(TEST_PLAYER_X, TEST_PLAYER_Y), this.player.getPosition());
    }
}
