package it.unibo.donkeykong.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.api.EntityFactory;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.HealthComponent;
import it.unibo.donkeykong.model.impl.EntityFactoryImpl;
import it.unibo.donkeykong.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.ModelConstants;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.view.api.Level;
import it.unibo.donkeykong.view.impl.LevelImpl;

class GameplayTest {

    @Test
    void testInitGameplay() {
        final Level l = new LevelImpl();
        final Gameplay gp = new GameplayImpl(null);
        assertTrue(gp.getEntities().isEmpty());
        gp.initializeGame(l.getLevelData());
        assertFalse(gp.getEntities().isEmpty());
        assertTrue(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.PLAYER));
        assertTrue(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.PRINCESS));
        assertTrue(gp.getEntities().stream().anyMatch(e -> e.getEntityType() == Type.MONKEY));
        assertFalse(gp.isOver());
        assertFalse(gp.isWin());
    }

    @Test
    void testWin() {
        final Gameplay gp = new GameplayImpl(null);
        assertFalse(gp.isWin());
        gp.setWin();
        assertTrue(gp.isWin());
    }

    @Test
    void testLosePlayerRemoved() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        gp.addEntity(ef.generatePlayer(new Pair<>(0f, 0f)));
        assertFalse(gp.isOver());
        gp.removePlayer();
        gp.checkIsOver();
        assertTrue(gp.isOver());
    }

    @Test
    void testLoseWithNoLives() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity player = ef.generatePlayer(new Pair<>(0f, 0f));
        gp.addEntity(player);
        player.getComponent(HealthComponent.class).get().setLifes(-ModelConstants.Player.NUM_LIVES);
        gp.checkIsOver();
        assertTrue(gp.isOver());
    }
}
