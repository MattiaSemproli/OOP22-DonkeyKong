package it.unibo.donkeykong.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.impl.GameplayImpl;
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
}
