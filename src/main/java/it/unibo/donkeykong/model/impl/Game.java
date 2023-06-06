package it.unibo.donkeykong.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Barrel.aniBarrelSpeed;
import static it.unibo.donkeykong.utilities.Constants.Monkey.aniMonkeySpeed;
import static it.unibo.donkeykong.utilities.Constants.Player.aniClimbSpeed;
import static it.unibo.donkeykong.utilities.Constants.Player.aniPlayerSpeed;
import static it.unibo.donkeykong.utilities.Constants.Princess.aniPrincessSpeed;

import it.unibo.donkeykong.model.api.ViewModel;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.DoubleDamageComponent;
import it.unibo.donkeykong.model.ecs.impl.MovementComponent;
import it.unibo.donkeykong.model.ecs.impl.ThrowComponent;
import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Constants.Monkey;
import it.unibo.donkeykong.utilities.Constants.Player;
import it.unibo.donkeykong.utilities.Constants.Princess;
import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;
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
                    return new Pair<>(mc.getFacing() == Direction.LEFT ? Player.leftAni : Player.rightAni,
                                      aniPlayerIndex);
                case FALLING:
                case JUMP:
                    return new Pair<>(mc.getFacing() == Direction.LEFT ? Player.jumpAni + Player.leftAni
                                                                       : Player.jumpAni + Player.rightAni,
                                      mc.isInAir() ? Player.midAirAni : Player.movementAni);
                case CLIMBING:
                    return new Pair<>(Player.climbAni, aniClimbIndex);
                case STOPCLIMBING:
                case STOP:
                default:
                    return new Pair<>(mc.isOnLadder() ? Player.climbAni
                                                      : mc.getFacing() == Direction.LEFT ? Player.leftAni : Player.rightAni,
                                      Player.runAni);
            }
        }
        if (entity.getEntityType() == Type.BARREL) {
            return new Pair<>(entity.getComponent(DoubleDamageComponent.class)
                                    .get().getDoubleDamage() ? Barrel.ddBarrelAni : Barrel.barrelAni, 
                              aniBarrelIndex);
        }
        if (entity.getEntityType() == Type.MONKEY) {
            if (entity.getComponent(ThrowComponent.class).get().isThrowing()) {
                return new Pair<>(Monkey.monkeyAni, aniMonkeyIndex);
            } else {
                return new Pair<>(Monkey.monkeyAni, Monkey.monkeyAni);
            }
        }
        return new Pair<>(entity.getComponent(MovementComponent.class)
                                .get().getFacing() == Direction.LEFT ? Princess.leftAni : Princess.rightAni, 
                          PlayerIdle.getPrincessIdle() == PlayerIdle.STOP ? Princess.princessAni : aniPrincessIndex);
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
        if (aniPlayerTick >= aniPlayerSpeed) {
            aniPlayerTick = 0;
            aniPlayerIndex++;
            if (aniPlayerIndex >= Player.movementAniSprites) {
                aniPlayerIndex = 0;
            }
        }
        aniClimbTick++;
        if (aniClimbTick >= aniClimbSpeed) {
            aniClimbTick = 0;
            aniClimbIndex++;
            if (aniClimbIndex >= Player.climbingAniSprites) {
                aniClimbIndex = 0;
            }
        }
    }

    /**
     * Update barrel animations' index.
     */
    private void updateBarrelAnimation() {
        aniBarrelTick++;
        if (aniBarrelTick >= aniBarrelSpeed) {
            aniBarrelTick = 0;
            aniBarrelIndex++;
            if (aniBarrelIndex >= Barrel.barrelAniSprites) {
                aniBarrelIndex = 0;
            }
        }
    }

    /**
     * Update monkey animations' index.
     */
    private void updateMonkeyAnimation() {
        aniMonkeyTick++;
        if (aniMonkeyTick >= aniMonkeySpeed) {
            aniMonkeyTick = 0;
            aniMonkeyIndex++;
            if (aniMonkeyIndex >= Monkey.monkeyAniSprites) {
                aniMonkeyIndex = 0;
            }
        }
    }

    /**
     * Update princess animations' index.
     */
    private void updatePrincessAnimation() {
        aniPrincessTick++;
        if (aniPrincessTick >= aniPrincessSpeed) {
            aniPrincessTick = 0;
            aniPrincessIndex++;
            if (aniPrincessIndex >= Princess.princessAniSprites) {
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
