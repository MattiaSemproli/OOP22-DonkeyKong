package it.unibo.donkeykong.model.impl;

import static it.unibo.donkeykong.utilities.ModelConstants.Barrel.ANI_BARREL_SPEED;
import static it.unibo.donkeykong.utilities.ModelConstants.Monkey.ANI_MONKEY_SPEED;
import static it.unibo.donkeykong.utilities.ModelConstants.Player.ANI_CLIMB_SPEED;
import static it.unibo.donkeykong.utilities.ModelConstants.Player.ANI_PLAYER_SPEED;
import static it.unibo.donkeykong.utilities.ModelConstants.Princess.ANI_PRINCESS_SPEED;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.model.api.ViewModel;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.DoubleDamageComponent;
import it.unibo.donkeykong.model.ecs.impl.MovementComponent;
import it.unibo.donkeykong.model.ecs.impl.ThrowComponent;
import it.unibo.donkeykong.utilities.ModelConstants.Barrel;
import it.unibo.donkeykong.utilities.ModelConstants.Monkey;
import it.unibo.donkeykong.utilities.ModelConstants.Player;
import it.unibo.donkeykong.utilities.ModelConstants.Princess;
import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.PlayerIdle;
import it.unibo.donkeykong.utilities.Type;

/**
 * Game model, manages game logics.
 */
public class Game implements ViewModel {

    private int aniPrincessTick, aniPrincessIndex;
    private int aniMonkeyTick, aniMonkeyIndex;
    private int aniPlayerTick, aniPlayerIndex;
    private int aniClimbTick, aniClimbIndex;
    private int aniBarrelTick, aniBarrelIndex;

    /**
     * Constructor.
     */
    public Game() {
        this.aniPrincessTick = 0;
        this.aniPrincessIndex = 0;
        this.aniMonkeyTick = 0;
        this.aniMonkeyIndex = 0;
        this.aniPlayerTick = 0;
        this.aniPlayerIndex = 0;
        this.aniBarrelTick = 0;
        this.aniBarrelIndex = 0;
        this.aniClimbTick = 0;
        this.aniClimbIndex = 0;
    }

    /**
     * Get player's data to be drawn.
     * 
     * @param entity the entity.
     * @return a pair of animation and animation's index.
     */
    public final Pair<Integer, Integer> getIdle(final Entity entity) {
        if (entity.getEntityType() == Type.PLAYER) {
            final MovementComponent mc = entity.getComponent(MovementComponent.class).get();
            switch (PlayerIdle.getPlayerIdle()) {
                case RUN:
                    return new Pair<>(mc.getFacing() == Direction.LEFT ? Player.LEFT_ANI : Player.RIGHT_ANI,
                                      aniPlayerIndex);
                case FALLING:
                case JUMP:
                    return new Pair<>(mc.getFacing() == Direction.LEFT ? Player.JUMP_ANI + Player.LEFT_ANI
                                                                       : Player.JUMP_ANI + Player.RIGHT_ANI,
                                      mc.isInAir() ? Player.MID_AIR_ANI : Player.MOVEMENT_ANI);
                case CLIMBING:
                    return new Pair<>(Player.CLIMB_ANI, aniClimbIndex);
                case STOPCLIMBING:
                case STOP:
                default:
                    return new Pair<>(mc.isOnLadder() ? Player.CLIMB_ANI
                                                      : mc.getFacing() == Direction.LEFT ? Player.LEFT_ANI : Player.RIGHT_ANI,
                                      Player.RUN_ANI);
            }
        }
        if (entity.getEntityType() == Type.BARREL) {
            return new Pair<>(entity.getComponent(DoubleDamageComponent.class)
                                    .get().isDoubleDamage() ? Barrel.DD_BARREL_ANI : Barrel.BARREL_ANI, 
                              aniBarrelIndex);
        }
        if (entity.getEntityType() == Type.MONKEY) {
            final ThrowComponent tc = entity.getComponent(ThrowComponent.class).get();
            if (tc.isThrowing()) {
                return new Pair<>(Monkey.MONKEY_ANI, tc.isFreezed() ? Monkey.MONKEY_ANI : aniMonkeyIndex);
            } else {
                return new Pair<>(Monkey.MONKEY_ANI, Monkey.MONKEY_ANI);
            }
        }
        return new Pair<>(entity.getComponent(MovementComponent.class)
                                .get().getFacing() == Direction.LEFT ? Princess.LEFT_ANI : Princess.RIGHT_ANI, 
                          PlayerIdle.getPrincessIdle() == PlayerIdle.STOP ? Princess.PRINCESS_ANI : aniPrincessIndex);
    }

    /**
     * Update all the animations' indexes.
     */
    public final void updateAnimations() {
        this.updatePlayerAnimation();
        this.updateBarrelAnimation();
        this.updateMonkeyAnimation();
        this.updatePrincessAnimation();
    }

    /**
     * Update player animations' index.
     */
    private void updatePlayerAnimation() {
        aniPlayerTick++;
        if (aniPlayerTick >= ANI_PLAYER_SPEED) {
            aniPlayerTick = 0;
            aniPlayerIndex++;
            if (aniPlayerIndex >= Player.MOVEMENT_ANI_SPRITES) {
                aniPlayerIndex = 0;
            }
        }
        aniClimbTick++;
        if (aniClimbTick >= ANI_CLIMB_SPEED) {
            aniClimbTick = 0;
            aniClimbIndex++;
            if (aniClimbIndex >= Player.CLIMB_ANI_SPRITES) {
                aniClimbIndex = 0;
            }
        }
    }

    /**
     * Update barrel animations' index.
     */
    private void updateBarrelAnimation() {
        aniBarrelTick++;
        if (aniBarrelTick >= ANI_BARREL_SPEED) {
            aniBarrelTick = 0;
            aniBarrelIndex++;
            if (aniBarrelIndex >= Barrel.BARREL_ANI_SPRITES) {
                aniBarrelIndex = 0;
            }
        }
    }

    /**
     * Update monkey animations' index.
     */
    private void updateMonkeyAnimation() {
        aniMonkeyTick++;
        if (aniMonkeyTick >= ANI_MONKEY_SPEED) {
            aniMonkeyTick = 0;
            aniMonkeyIndex++;
            if (aniMonkeyIndex >= Monkey.MONKEY_ANI_SPRITES) {
                aniMonkeyIndex = 0;
            }
        }
    }

    /**
     * Update princess animations' index.
     */
    private void updatePrincessAnimation() {
        aniPrincessTick++;
        if (aniPrincessTick >= ANI_PRINCESS_SPEED) {
            aniPrincessTick = 0;
            aniPrincessIndex++;
            if (aniPrincessIndex >= Princess.PRINCESS_ANI_SPRITES) {
                aniPrincessIndex = 0;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyGamestate(final Gamestate gamestate) {
        Gamestate.setGamestate(gamestate);
    }
}
