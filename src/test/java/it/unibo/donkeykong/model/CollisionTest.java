package it.unibo.donkeykong.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.api.EntityFactory;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.CollisionComponent;
import it.unibo.donkeykong.model.ecs.impl.MovementComponent;
import it.unibo.donkeykong.model.impl.EntityFactoryImpl;
import it.unibo.donkeykong.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.ModelConstants;

class CollisionTest {

    private static final float TEST_PLAYER_X = 0f;
    private static final float TEST_PLAYER_Y = 0f;
    private static final float TEST_ONE_TO_DROP = ModelConstants.Application.GAME_HEIGHT - 1;

    private final Gameplay gp = new GameplayImpl(null);
    private final EntityFactory ef = new EntityFactoryImpl(gp);

    @Test
    void testPlayerCollisionWithWall() {
        final Entity player = ef.generatePlayer(new Pair<>(TEST_PLAYER_X, TEST_PLAYER_Y));
        assertEquals(new Pair<>(TEST_PLAYER_X, TEST_PLAYER_Y), player.getPosition());
        final MovementComponent mc = player.getComponent(MovementComponent.class).get();
        mc.moveEntity(Direction.LEFT);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        mc.resetInAir();
        assertEquals(new Pair<>(TEST_PLAYER_X, TEST_PLAYER_Y), player.getPosition());
        mc.moveEntity(Direction.RIGHT);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        assertNotEquals(new Pair<>(TEST_PLAYER_X, TEST_PLAYER_Y), player.getPosition());
    }

    @Test
    void testPlayerFallenInVoid() {
        final Entity player = ef.generatePlayer(new Pair<>(TEST_PLAYER_X, TEST_ONE_TO_DROP));
        assertNotEquals(Gamestate.getGamestate(), Gamestate.DEATH);
        final MovementComponent mc = player.getComponent(MovementComponent.class).get();
        mc.moveEntity(Direction.DOWN);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        assertEquals(Gamestate.getGamestate(), Gamestate.DEATH);
    }

    @Test
    void testPlayerTouchesPrincess() {
        final Entity player = ef.generatePlayer(new Pair<>(TEST_PLAYER_X, TEST_PLAYER_X));
        gp.addEntity(ef.generatePrincess(new Pair<>((float) (ModelConstants.Player.PLAYER_DIMENSION), TEST_PLAYER_Y)));
        gp.addEntity(ef.generateBlock(new Pair<>(0f, (float) (ModelConstants.Player.PLAYER_DIMENSION))));
        gp.addEntity(ef.generateBlock(new Pair<>((float) (ModelConstants.Player.PLAYER_DIMENSION),
                                                 (float) (ModelConstants.Player.PLAYER_DIMENSION))));
        assertNotEquals(Gamestate.getGamestate(), Gamestate.WIN);
        final MovementComponent mc = player.getComponent(MovementComponent.class).get();
        mc.moveEntity(Direction.RIGHT);
        mc.update();
        player.getComponent(CollisionComponent.class).get().update();
        assertEquals(Gamestate.getGamestate(), Gamestate.WIN);
    }
}
