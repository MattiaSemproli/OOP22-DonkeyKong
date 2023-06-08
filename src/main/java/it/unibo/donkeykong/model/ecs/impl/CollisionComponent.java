package it.unibo.donkeykong.model.ecs.impl;

import java.util.Optional;
import java.util.Random;

import it.unibo.donkeykong.utilities.ModelConstants.Barrel;
import it.unibo.donkeykong.utilities.ModelConstants.Level;
import it.unibo.donkeykong.utilities.ModelConstants.Monkey;
import it.unibo.donkeykong.utilities.ModelConstants.Player;
import it.unibo.donkeykong.utilities.ModelConstants.Powerup;
import it.unibo.donkeykong.utilities.ModelConstants.Princess;
import it.unibo.donkeykong.utilities.ModelConstants.Application;
import it.unibo.donkeykong.common.Line;
import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.common.Rectangle;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.utilities.CurrentLevel;
import it.unibo.donkeykong.utilities.Type;

/**
 * Collision component, manages collisions between objects.
 */
public class CollisionComponent extends AbstractComponent {

    private boolean barrelChangedDirection;
    private final float x, y;
    private Entity entity;
    private Rectangle hitbox;
    private Optional<Pair<Float, Float>> nextPosition = Optional.empty();
    private final Random random = new Random();

    @Override
    public final void update() {
        this.nextPosition = this.getEntity().getNextPosition();
        this.entity = this.getEntity();
        final Type eType = this.getEntity().getEntityType();
        if (this.nextPosition.isPresent()) {
            this.hitbox.setX(nextPosition.get().getX());
            this.hitbox.setY(nextPosition.get().getY());
            if (eType == Type.PLAYER) {
                this.checkPlayerPlatformCollision();
                this.checkPlayerLadderCollision();
                this.checkPlayerWallCollision();
                this.checkPlayerState();
            } else if (eType == Type.BARREL) {
                this.checkBarrelInAir();
                this.checkBarrelCollision();
            } else if (eType == Type.PRINCESS) {
                this.checkPrincessCollision();
            }
        } else {
            this.nextPosition = Optional.of(this.getEntity().getPosition());
            this.hitbox.setX(this.getEntity().getPosition().getX());
            this.hitbox.setY(this.getEntity().getPosition().getY());
        }
        if (eType == Type.PLAYER) {
            this.checkIsCollidingWithOtherEntities();
        }
    }

    /**
     * Constructor.
     * 
     * @param x the initial entity's x-position.
     * @param y the initial entity's y-position.
     * @param type the entity's type.
     */
    public CollisionComponent(final float x, final float y, final Type type) {
        this.x = x;
        this.y = y;
        this.barrelChangedDirection = false;
        initDifferentHitbox(type);
    }

    /**
     * Get entity's hitbox.
     * 
     * @return a defensive copy of the hitbox.
     */
    public final Rectangle getHitbox() {
        return new Rectangle(this.hitbox.getX(), this.hitbox.getY(), this.hitbox.getWidth(), this.hitbox.getHeight());
    }

    private void checkPlayerPlatformCollision() {
        final MovementComponent mc = entity.getComponent(MovementComponent.class).get();
        if (this.nextPosition.get().getY() > entity.getPosition().getY()) {
            entity.getGameplay().getEntities().stream()
                  .filter(e -> !this.checkIsNotBlock(e.getEntityType()))
                  .filter(e -> {
                    final Rectangle e2Hitbox = e.getComponent(CollisionComponent.class).get().getHitbox();
                    if (hitbox.intersectsLine(new Line(e2Hitbox.getX(),
                                                       e2Hitbox.getY(),
                                                       e2Hitbox.getMaxX(),
                                                       e2Hitbox.getY()))
                       && !hitbox.intersectsLine(new Line(e2Hitbox.getX(),
                                                          e2Hitbox.getMaxY(),
                                                          e2Hitbox.getMaxX(),
                                                          e2Hitbox.getMaxY()))
                       && hitbox.getMaxY() < e2Hitbox.getY() + 8) {
                        return true;
                    }
                    return false;
            }).forEach(eBlock -> {
                this.nextPosition = Optional.of(new Pair<>(this.nextPosition.get().getX(),
                                                           eBlock.getPosition().getY() - hitbox.getHeight()));
                mc.resetInAir();
                mc.setOnLadder(false);
            });
        }
    }

    private void checkPlayerLadderCollision() {
        final MovementComponent mc = entity.getComponent(MovementComponent.class).get();
        if (!mc.isInAir()) {
            if (mc.isOnFloor()) {
                entity.getGameplay().getEntities().stream()
                      .filter(e -> e.getEntityType() == Type.LADDER
                                   && hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox()))
                      .findAny().ifPresentOrElse(e -> mc.setCanUseLadder(true),
                                                 () -> mc.setCanUseLadder(false));
            } else {
                entity.getGameplay().getEntities().stream()
                      .filter(e -> e.getEntityType() == Type.LADDER
                                   && hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox()))
                      .findAny().ifPresentOrElse(e -> {
                            mc.setOnLadder(true);
                            this.nextPosition = Optional.of(new Pair<>(e.getPosition().getX() - Level.LADDER_PADDING,
                                                                       this.nextPosition.get().getY()));
                            },
                            () -> {
                               if (entity.getGameplay().getEntities().stream()
                                         .filter(e -> (e.getEntityType() == Type.BLOCK_LADDER_DOWN
                                                      || e.getEntityType() == Type.BLOCK_LADDER_UPDOWN)
                                                      && hitbox.intersects(e.getComponent(CollisionComponent.class)
                                                                            .get().getHitbox()))
                                         .findAny().isEmpty()) {
                                   mc.setInAir(true);
                               }
                            });
            }
        }
    }

    private void checkPlayerWallCollision() {
        if (hitbox.getY() > Application.GAME_HEIGHT) {
            entity.getGameplay().removePlayer();
        } else if (hitbox.getX() > (Application.GAME_WIDTH - hitbox.getWidth())) {
            entity.setPosition(new Pair<>(Application.GAME_WIDTH - hitbox.getWidth(), this.nextPosition.get().getY()));
        } else if (hitbox.getY() < 0) {
            entity.setPosition(new Pair<>(this.nextPosition.get().getX(), 0f));
        } else if (hitbox.getX() < 0) {
            entity.setPosition(new Pair<>(0f, this.nextPosition.get().getY()));
        } else {
            entity.setPosition(new Pair<>(this.nextPosition.get().getX(), this.nextPosition.get().getY()));
        }
    }

    private void checkPlayerState() {
        final MovementComponent mc = entity.getComponent(MovementComponent.class).get();
            if (entity.getGameplay().getEntities().stream()
                      .filter(e -> !this.checkIsNotBlock(e.getEntityType()))
                      .anyMatch(e -> {
                          final Rectangle e2Hitbox = e.getComponent(CollisionComponent.class).get().getHitbox();
                          if (hitbox.intersectsLine(new Line(e2Hitbox.getX(),
                                                             e2Hitbox.getY(),
                                                             e2Hitbox.getMaxX(),
                                                             e2Hitbox.getY()))
                             && !hitbox.intersectsLine(new Line(e2Hitbox.getX(),
                                                                e2Hitbox.getMaxY(),
                                                                e2Hitbox.getMaxX(),
                                                                e2Hitbox.getMaxY()))) {
                              return true;
                          }
                          return false;
                       })) {
                if (mc.isOnFloor()) {
                    mc.resetInAir();
                }
            } else {
                if (!mc.isOnLadder()) {
                    mc.setInAir(true);
                }
                mc.setOnFloor(false);
            }
    }

    private void checkIsCollidingWithOtherEntities() {
        entity.getGameplay().getEntities().stream()
              .filter(e -> !e.equals(entity) && this.checkIsNotBlock(e.getEntityType()))
              .filter(e -> hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox()))
              .forEach(e -> {
                    final StarComponent starC = entity.getComponent(StarComponent.class).get();
                    final ShieldComponent shieldC = entity.getComponent(ShieldComponent.class).get();
                    final HealthComponent healthC = entity.getComponent(HealthComponent.class).get();
                    final MovementComponent movementC = entity.getComponent(MovementComponent.class).get();
                    final FreezeComponent freezeC = entity.getComponent(FreezeComponent.class).get();
                    if (e.getEntityType() == Type.BARREL) {
                        if (!starC.isInvincible()) {
                            if (!shieldC.isShielded()) {
                                if (e.getComponent(DoubleDamageComponent.class).get().isDoubleDamage()) {
                                    healthC.setLifes(Player.DOUBLE_DAMAGE);
                                } else {
                                    healthC.setLifes(Player.DAMAGE_TAKEN);
                                }
                                this.resetPlayer(movementC);
                            } else {
                                if (e.getComponent(DoubleDamageComponent.class).get().isDoubleDamage()) {
                                    healthC.setLifes(Player.DAMAGE_TAKEN);
                                }
                                shieldC.setShield(false);
                            }
                        } else {
                            shieldC.setShield(false);
                        }
                        entity.getGameplay().removeEntity(e);
                    }
                    if (e.getEntityType() == Type.PRINCESS && movementC.isOnFloor()) {
                        entity.getGameplay().setWin();
                    }
                    if (e.getEntityType() == Type.MONKEY) {
                        entity.getGameplay().removePlayer();
                    }
                    if (e.getEntityType() == Type.STAR) {
                        starC.setInvincible(true);
                        entity.getGameplay().removeEntity(e);
                    }
                    if (e.getEntityType() == Type.SHIELD) {
                        shieldC.setShield(true);
                        entity.getGameplay().removeEntity(e);
                    }
                    if (e.getEntityType() == Type.HEART && healthC.getLives() != Player.NUM_LIVES) {
                        healthC.setLifes(Player.EXTRA_LIFE);
                        entity.getGameplay().removeEntity(e);
                    }
                    if (e.getEntityType() == Type.SNOWFLAKE) {
                        freezeC.setFrozen(true);
                        entity.getGameplay().removeEntity(e);
                    }
              });
    }

    private boolean checkIsNotBlock(final Type type) {
        return type != Type.BLOCK
            && type != Type.BLOCK_LADDER_DOWN
            && type != Type.BLOCK_LADDER_UP
            && type != Type.BLOCK_LADDER_UPDOWN;
    }

    private void resetPlayer(final MovementComponent mc) {
        switch (CurrentLevel.getCurrentLevel()) {
            case ONE:
                entity.setPosition(new Pair<>(Player.LEVEL_ONE_STARTING_X, Player.LEVEL_ONE_STARTING_Y));
                break;
            case TWO:
                entity.setPosition(new Pair<>(Player.LEVEL_TWO_STARTING_X, Player.LEVEL_TWO_STARTING_Y));
                break;
            case THREE:
                entity.setPosition(new Pair<>(Player.LEVEL_THREE_STARTING_X, Player.LEVEL_THREE_STARTING_Y));
                break;
            case FOUR:
            default:
                entity.setPosition(new Pair<>(Player.LEVEL_FOUR_STARTING_X, Player.LEVEL_FOUR_STARTING_Y));
                break;
        }
        mc.resetInAir();
        mc.setCanUseLadder(false);
        mc.setOnLadder(false);
        entity.getGameplay().removeAllBarrels();
    }

    private void checkBarrelInAir() {
        if (entity.getGameplay().getEntities().stream()
                  .filter(e -> !this.checkIsNotBlock(e.getEntityType()))
                  .anyMatch(e -> {
                            final Rectangle e2hitbox = e.getComponent(CollisionComponent.class).get().getHitbox();
                            if (hitbox.intersectsLine(new Line(e2hitbox.getX(),
                                                               e2hitbox.getY(),
                                                               e2hitbox.getMaxX(),
                                                               e2hitbox.getY()))
                                && hitbox.getMaxY() < e2hitbox.getY() + Barrel.BARREL_FLOOR_ERROR) {
                                    this.nextPosition = Optional.of(new Pair<>(this.nextPosition.get().getX(),
                                                                               e2hitbox.getY() - hitbox.getHeight()));
                                    return true;
                                }
                                return false;
                        })) {
                        entity.getComponent(MovementComponent.class).get().resetInAir();
                    }  else {
                        entity.getComponent(MovementComponent.class).get().setInAir(true);
                        this.barrelChangedDirection = false;
                    }
    }

    private void checkBarrelCollision() {
        final MovementComponent mc = this.entity.getComponent(MovementComponent.class).get();
        this.entity.getGameplay().getEntities().stream()
                   .filter(e -> !this.checkIsNotBlock(e.getEntityType()))
                   .filter(e -> hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox()))
                   .forEach(e -> {
                       if (random.nextInt(Barrel.TOTAL_DIR_PROBABILITY) == Barrel.CHANGE_DIR_INDEX
                           && !this.barrelChangedDirection
                           && !mc.isInAir()
                       ) {
                           mc.changeDirection();
                       }
                       this.barrelChangedDirection = true;
                   });
        if (hitbox.getX() < 0 || hitbox.getMaxX() > Application.GAME_WIDTH) {
            mc.moveEntity(mc.getFacing().getOppositeDirection());
        } else if (hitbox.getY() > Application.GAME_HEIGHT) {
            this.entity.getGameplay().removeEntity(entity);
        } else {
            entity.setPosition(new Pair<>(this.nextPosition.get().getX(), this.nextPosition.get().getY()));
        }
   }

    private void checkPrincessCollision() {
        final int leftTile = (int) (Princess.LEVEL_ONE_STARTING_X / Application.SCALED_TILES_SIZE) - 1;
        final int rightTile = (int) (Princess.LEVEL_ONE_STARTING_X / Application.SCALED_TILES_SIZE) + 1;
        if (hitbox.getMaxX() > rightTile * Application.SCALED_TILES_SIZE + Application.SCALED_TILES_SIZE
            || hitbox.getX() < leftTile * Application.SCALED_TILES_SIZE) {
            final MovementComponent mc = this.entity.getComponent(MovementComponent.class).get();
            mc.moveEntity(mc.getFacing().getOppositeDirection());
        }
        entity.setPosition(new Pair<>(this.nextPosition.get().getX(), this.nextPosition.get().getY()));
    }

    private void initDifferentHitbox(final Type type) {
        int width = Application.SCALED_TILES_SIZE;
        int height = width;
        switch (type) {
            case BARREL:
                width = Barrel.BARREL_WIDTH;
                height = Barrel.BARREL_HEIGHT;
                hitbox = new Rectangle(x, y, width, height);
                break;
            case PLAYER:
                hitbox = new Rectangle(x, y, width, height);
                break;
            case PRINCESS:
                width = Princess.PRINCESS_WIDTH;
                height = Princess.PRINCESS_HEIGHT;
                hitbox = new Rectangle(x, y, width, height);
                break;
            case MONKEY:
                width = Monkey.MONKEY_WIDTH;
                height = Monkey.MONKEY_HEIGHT;
                hitbox = new Rectangle(x, y, width, height);
                break;
            case HEART:
                width = Powerup.HEART_WIDTH;
                height = Powerup.HEART_HEIGHT;
                hitbox = new Rectangle(x, y, width, height);
                break;
            case SNOWFLAKE:
                width = Powerup.FREEZE_DIMENSION;
                height = Powerup.FREEZE_DIMENSION;
                hitbox = new Rectangle(x, y, width, height);
                break;
            case STAR:
                width = Powerup.STAR_DIMENSION;
                height = Powerup.STAR_DIMENSION;
                hitbox = new Rectangle(x, y, width, height);
                break;
            case SHIELD:
                width = Powerup.SHIELD_WIDTH;
                height = Powerup.SHIELD_HEIGHT;
                hitbox = new Rectangle(x, y, width, height);
                break;
            case LADDER:
                hitbox = new Rectangle(x, y, width - (Level.LADDER_PADDING * 2), height);
                break;
            case BLOCK:
            case BLOCK_LADDER_DOWN:
            case BLOCK_LADDER_UP:
            case BLOCK_LADDER_UPDOWN:
            default:
                hitbox = new Rectangle(x, y, width, height - Level.PLATFORM_BLOCK_PADDING * 2);
                break;
        }
    }
}
