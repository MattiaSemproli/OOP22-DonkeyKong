package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Barrel.aniBarrelSpeed;
import static it.unibo.donkeykong.utilities.Constants.Monkey.aniMonkeySpeed;
import static it.unibo.donkeykong.utilities.Constants.Player.aniClimbSpeed;
import static it.unibo.donkeykong.utilities.Constants.Player.aniPlayerSpeed;
import static it.unibo.donkeykong.utilities.Constants.Princess.aniPrincessSpeed;
import static it.unibo.donkeykong.utilities.Constants.Window.SCALED_TILES_SIZE;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.game.ecs.impl.DoubleDamageComponent;
import it.unibo.donkeykong.game.ecs.impl.MovementComponent;
import it.unibo.donkeykong.game.ecs.impl.ThrowComponent;
import it.unibo.donkeykong.game.model.api.Level;
import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Constants.Monkey;
import it.unibo.donkeykong.utilities.Constants.Player;
import it.unibo.donkeykong.utilities.Constants.Princess;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.PlayerIdle;
import it.unibo.donkeykong.utilities.Type;

/**
 * Game model, manages game logics.
 */
public class Game {

    private final Level level;
    private final Map<Rectangle, BufferedImage> dataLevel = new HashMap<>();
    private BufferedImage[][] playerMovementAni = new BufferedImage[Player.numMovementAni][Player.movementAniSprites];
    private BufferedImage[] monkeyAni = new BufferedImage[Monkey.monkeyAniSprites];
    private BufferedImage[][] barrelAni = new BufferedImage[Barrel.numBarrel][Barrel.barrelAniSprites];
    private BufferedImage[][] princessAni = new BufferedImage[Princess.numPrincessAni][Princess.princessAniSprites];
    private int aniPrincessTick, aniPrincessIndex;
    private int aniMonkeyTick, aniMonkeyIndex;
    private int aniPlayerTick, aniPlayerIndex;
    private int aniClimbTick, aniClimbIndex;
    private int aniBarrelTick, aniBarrelIndex;

    /**
     * Constructor.
     */
    public Game() {
        this.level = new LevelImpl();
        this.mapDataLevel();

        this.bufferAnimations();
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

    private void bufferAnimations() {
        for (int r = 0; r < Player.numMovementAni - 1; r++) {
            for (int c = 0; c < Player.movementAniSprites; c++) {
                playerMovementAni[r][c] = Player.getPlayerSources()
                                                .get(Player.movementAni)
                                                .getSubimage(c * Player.spriteDimension, 
                                                             r * Player.spriteDimension, 
                                                             Player.spriteDimension, 
                                                             Player.spriteDimension);
            }
        }
        for (int c = 0; c < Player.climbingAniSprites; c++) {
            playerMovementAni[Player.climbAni][c] = Player.getPlayerSources()
                                                          .get(Player.climbingAni)
                                                          .getSubimage(c * Player.spriteDimension, 
                                                                       0, 
                                                                       Player.spriteDimension, 
                                                                       Player.spriteDimension);
        }
        for (int c = 0; c < monkeyAni.length; c++) {
            monkeyAni[c] = Monkey.getMonkeySources()
                                 .get(Monkey.monkeyAni)
                                 .getSubimage(c * Monkey.spriteWidth, 
                                              0, 
                                              Monkey.spriteWidth, 
                                              Monkey.spriteHeight);
        }
        for (int c = 0; c < Barrel.barrelAniSprites; c++) {
            barrelAni[Barrel.barrelAni][c] = Barrel.getBarrelSources()
                                                   .get(Barrel.barrelAni)
                                                   .getSubimage(c * Barrel.spriteWidth,
                                                                0, 
                                                                Barrel.spriteWidth, 
                                                                Barrel.spriteHeight);
            barrelAni[Barrel.ddBarrelAni][c] = Barrel.getBarrelSources()
                                                     .get(Barrel.ddBarrelAni)
                                                     .getSubimage(c * Barrel.spriteWidth,
                                                                  0, 
                                                                  Barrel.spriteWidth, 
                                                                  Barrel.spriteHeight);
        }
        for (int r = 0; r < Princess.numPrincessAni; r++) {
            for (int c = 0; c < Princess.princessAniSprites; c++) {
                princessAni[r][c] = Princess.getPrincessSources()
                                         .get(Princess.princessAni)
                                         .getSubimage(c * Princess.spriteWidth,
                                                      r * Princess.spriteHeight, 
                                                      Princess.spriteWidth, 
                                                      Princess.spriteHeight);
            }
        }
    }

    private void mapDataLevel() {
        final Map<Pair<Integer, Integer>, Integer> lvl = this.level.getLevelData();
        for (int r = 0; r < Window.TILES_IN_HEIGHT; r++) {
            for (int c = 0; c < Window.TILES_IN_WIDTH; c++) {
                this.dataLevel.put(new Rectangle(SCALED_TILES_SIZE * r, 
                                                 SCALED_TILES_SIZE * c, 
                                                 SCALED_TILES_SIZE, 
                                                 SCALED_TILES_SIZE),
                                   this.level.getLevelSprite(lvl.get(new Pair<>(r, c))));
            }
        }
    }

    /**
     * Get level's data to be drawn.
     * 
     * @return a level's data map.
     */
    public Map<Rectangle, BufferedImage> getDataLevel() {
        return new HashMap<>(this.dataLevel);
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
     * Get entity's animation from row and col.
     * 
     * @param type the entity's type.
     * @param row animation's col to get.
     * @param col animation's row to get.
     * @return entity's animation sprite.
     */
    public final BufferedImage getEntityAni(final Type type, final int row, final int col) {
        if (type == Type.PLAYER) {
            return this.playerMovementAni[row][col];
        }
        if (type == Type.MONKEY) {
            return this.monkeyAni[col];
        }
        if (type == Type.BARREL) {
            return this.barrelAni[row][col];
        }
        return this.princessAni[row][col];
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
}
