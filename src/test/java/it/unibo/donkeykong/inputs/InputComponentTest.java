package it.unibo.donkeykong.inputs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.api.EntityFactory;
import it.unibo.donkeykong.model.api.Gameplay;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.InputsComponent;
import it.unibo.donkeykong.model.ecs.impl.MovementComponent;
import it.unibo.donkeykong.model.impl.EntityFactoryImpl;
import it.unibo.donkeykong.model.impl.GameplayImpl;
import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.ModelConstants;

class InputComponentTest {

    private static final int WRONG_CODE_TEST = 75;

    @Test
    void testInputComponentWithWrongCode() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity player = ef.generatePlayer(new Pair<>(0f, 0f));
        gp.addEntity(player);
        gp.updateKeyPressed(ModelConstants.Action.MOVE_RIGHT);
        assertTrue(gp.getInputs().contains(ModelConstants.Action.MOVE_RIGHT));
        gp.updateKeyPressed(WRONG_CODE_TEST);
        assertFalse(gp.getInputs().contains(WRONG_CODE_TEST));
        gp.resetKeys();
        assertTrue(gp.getInputs().isEmpty());
    }

    @Test
    void testInputComponentProcessCode() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity player = ef.generatePlayer(new Pair<>(0f, 0f));
        gp.addEntity(player);
        final InputsComponent ic = player.getComponent(InputsComponent.class).get();
        ic.update();
        assertTrue(player.getNextPosition().isEmpty());
        gp.updateKeyPressed(ModelConstants.Action.MOVE_RIGHT);
        assertTrue(gp.getInputs().contains(ModelConstants.Action.MOVE_RIGHT));
        ic.update();
        player.getComponent(MovementComponent.class).get().update();
        assertEquals(player.getPosition().getX() + Direction.RIGHT.getX() * player.getSpeed(), 
                     player.getNextPosition().get().getX());
    }

    @Test
    void testInputComponentProcessJump() {
        final Gameplay gp = new GameplayImpl(null);
        final EntityFactory ef = new EntityFactoryImpl(gp);
        final Entity player = ef.generatePlayer(new Pair<>(0f, 0f));
        gp.addEntity(player);
        gp.updateKeyPressed(ModelConstants.Action.JUMP);
        assertFalse(player.getComponent(MovementComponent.class).get().isInAir());
        final InputsComponent ic = player.getComponent(InputsComponent.class).get();
        ic.update();
        assertTrue(player.getComponent(MovementComponent.class).get().isInAir());
    }
}
