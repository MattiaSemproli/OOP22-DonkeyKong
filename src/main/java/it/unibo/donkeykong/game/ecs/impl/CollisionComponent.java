package it.unibo.donkeykong.game.ecs.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.ladderPadding;
import static it.unibo.donkeykong.utilities.Constants.Player.canLadderError;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Optional;
import java.util.Random;

import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Constants.Player;
import it.unibo.donkeykong.utilities.Constants.Princess;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

/**
 * Collision Component, manage collisions between objects.
 */
public class CollisionComponent extends AbstractComponent {

    private boolean barrelChangedDirection;
    private float x, y;
    private int width, height;
    private Entity entity;
    private Rectangle2D.Float hitbox;
    private Optional<Pair<Float, Float>> nextPosition = Optional.empty();
    private final Random random = new Random();

    @Override
    public final void update() {
        this.nextPosition = this.getEntity().getNextPosition();
        this.entity = this.getEntity();
        if (!this.nextPosition.isEmpty()) {
            this.hitbox.x = nextPosition.get().getX();
            this.hitbox.y = nextPosition.get().getY();
            this.checkIsEntityOnTheFloor();
            this.checkIsEntityOnLadder();
            this.checkWallCollision();
        } else {
            this.hitbox.x = this.getEntity().getPosition().getX();
            this.hitbox.y = this.getEntity().getPosition().getY();
        }
        this.checkIsCollidingWithOtherEntities();
    }

    /**
     * Constructor.
     * 
     * @param x initial x-position of entity
     * @param y initial y-position of entity
     * @param type linked entity type
     */
    public CollisionComponent(final float x, final float y, final Type type) {
        this.x = x;
        this.y = y;
        this.barrelChangedDirection = false;
        initDifferentHitbox(type);
    }

    /**
     * Get the hitbox.
     * 
     * @return defensive copy of the hitbox.
     */
    public final Rectangle2D.Float getHitbox() {
        return new Rectangle2D.Float(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
    }

    private void checkIsEntityOnTheFloor() {
        if (entity.getEntityType() == Type.PLAYER && this.nextPosition.get().getY() >= entity.getPosition().getY()) {
            if (entity.getGameplay().getEntities()
                  .stream().filter(e -> e.getEntityType() == (Type.BLOCK) 
                                        || e.getEntityType() == (Type.BLOCK_LADDER_DOWN) 
                                        || e.getEntityType() == (Type.BLOCK_LADDER_UP)
                                        || e.getEntityType() == (Type.BLOCK_LADDER_UPDOWN))
                  .filter(e -> hitbox.intersectsLine(new Line2D.Float(e.getComponent(CollisionComponent.class).get().getHitbox().x,
                                                                      e.getComponent(CollisionComponent.class).get().getHitbox().y,
                                                                      e.getComponent(CollisionComponent.class).get().getHitbox().x
                                                                      + e.getComponent(CollisionComponent.class).get().getHitbox().width,
                                                                      e.getComponent(CollisionComponent.class).get().getHitbox().y))
                               && !hitbox.intersectsLine(new Line2D.Float(e.getComponent(CollisionComponent.class).get().getHitbox().x,
                                                                         e.getComponent(CollisionComponent.class).get().getHitbox().y
                                                                         + e.getComponent(CollisionComponent.class).get().getHitbox().height,
                                                                         e.getComponent(CollisionComponent.class).get().getHitbox().x
                                                                         + e.getComponent(CollisionComponent.class).get().getHitbox().width,
                                                                         e.getComponent(CollisionComponent.class).get().getHitbox().y
                                                                         + e.getComponent(CollisionComponent.class).get().getHitbox().height))
                               && hitbox.y + hitbox.height < e.getComponent(CollisionComponent.class).get().getHitbox().y + 4)
                  .count() > 0) {
                entity.getComponent(MovementComponent.class).get().resetIsInAir();
                entity.getComponent(MovementComponent.class).get().setCanUseLadder(false);
                entity.getComponent(MovementComponent.class).get().setOnLadder(false);
            } else if (!entity.getComponent(MovementComponent.class).get().isOnLadder()) {
                entity.getComponent(MovementComponent.class).get().setIsInAir(true);
                entity.getComponent(MovementComponent.class).get().setCanUseLadder(false);
                entity.getComponent(MovementComponent.class).get().setOnLadder(false);
            }
        } else if (entity.getEntityType() == Type.BARREL) {
            if (entity.getGameplay().getEntities()
                .stream().filter(e -> e.getEntityType() == (Type.BLOCK) 
                                      || e.getEntityType() == (Type.BLOCK_LADDER_DOWN) 
                                      || e.getEntityType() == (Type.BLOCK_LADDER_UP)
                                      || e.getEntityType() == (Type.BLOCK_LADDER_UPDOWN))
                .filter(e -> hitbox.intersectsLine(new Line2D.Float(e.getComponent(CollisionComponent.class).get().getHitbox().x,
                                                                    e.getComponent(CollisionComponent.class).get().getHitbox().y,
                                                                    e.getComponent(CollisionComponent.class).get().getHitbox().x + e.getComponent(CollisionComponent.class).get().getHitbox().width,
                                                                    e.getComponent(CollisionComponent.class).get().getHitbox().y))
                        && hitbox.y + hitbox.height < e.getComponent(CollisionComponent.class).get().getHitbox().y + 4)
                .count() > 0) {
                entity.getComponent(MovementComponent.class).get().resetIsInAir();
                }  else {
                entity.getComponent(MovementComponent.class).get().setIsInAir(true);
                this.barrelChangedDirection = false;
            }
        } else if (entity.getEntityType() == Type.PRINCESS) {
            final int leftTile = (int) (Princess.levelOneStartingPrincessX / Window.SCALED_TILES_SIZE) - 1;
            final int rightTile = (int) (Princess.levelOneStartingPrincessX / Window.SCALED_TILES_SIZE) + 1;
            if (hitbox.x + hitbox.width > rightTile * Window.SCALED_TILES_SIZE + Window.SCALED_TILES_SIZE
                || hitbox.x < leftTile * Window.SCALED_TILES_SIZE) {
                final MovementComponent mc = this.entity.getComponent(MovementComponent.class).get();
                mc.moveEntity(mc.getFacing().getOppositeDirection());
            }
            entity.setPosition(new Pair<>(this.nextPosition.get().getX(), this.nextPosition.get().getY()));
        }
    }

    private void checkIsEntityOnLadder() {
        if (entity.getEntityType() == Type.PLAYER) {
            if (entity.getGameplay().getEntities()
                  .stream().filter(e -> e.getEntityType() == (Type.LADDER))
                  .filter(e -> hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox())
                               && entity.getComponent(MovementComponent.class).get().canOnLadder())
                  .findAny().isPresent()) {
                entity.getComponent(MovementComponent.class).get().setOnLadder(true);
            } else {
                if (entity.getComponent(MovementComponent.class).get().isOnLadder()) {
                    entity.getComponent(MovementComponent.class).get().setOnLadder(true);
                } else {
                    entity.getComponent(MovementComponent.class).get().setOnLadder(false);
                }
            }
        } 
    }

    private void checkWallCollision() {
        if (entity.getEntityType() == Type.PLAYER) {
            if (hitbox.x > (Window.GAME_WIDTH - hitbox.width)) {
                entity.setPosition(new Pair<>(Window.GAME_WIDTH - hitbox.width, this.nextPosition.get().getY()));
            } else if (hitbox.y < 0) {
                entity.setPosition(new Pair<>(this.nextPosition.get().getX(), 0f));
            } else if (hitbox.x < 0) {
                entity.setPosition(new Pair<>(0f, this.nextPosition.get().getY()));
            } else if (hitbox.y > Window.GAME_HEIGHT) {
                entity.getGameplay().removePlayer();
            } else {
                entity.setPosition(new Pair<>(this.nextPosition.get().getX(), this.nextPosition.get().getY()));
            }
        } else if (entity.getEntityType() == Type.BARREL) {
            final MovementComponent mc = this.entity.getComponent(MovementComponent.class).get();
            if (hitbox.x < 0 || hitbox.x + hitbox.width > Window.GAME_WIDTH) {
                mc.moveEntity(mc.getFacing().getOppositeDirection());
            } else if (hitbox.y > Window.GAME_HEIGHT) {
                this.entity.getGameplay().removeEntity(entity);
            } else {
                entity.setPosition(new Pair<>(this.nextPosition.get().getX(), this.nextPosition.get().getY()));
            }
        }
    }

    private void checkIsCollidingWithOtherEntities() {
        if (entity.getEntityType() == Type.PLAYER) {
            entity.getGameplay().getEntities().stream()
                  .filter(e -> !e.equals(entity))
                  .filter(e -> hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox()))
                  .forEach(e -> {
                        if (e.getEntityType() == Type.BARREL) {
                            if (!entity.getComponent(StarComponent.class).get().isInvincible()) {
                                if (!entity.getComponent(ShieldComponent.class).get().isShielded()) {
                                    if (e.getComponent(DoubleDamageComponent.class).get().getDoubleDamage()) {
                                        entity.getComponent(HealthComponent.class).get().setLifes(Player.doubleDamage);
                                    } else {
                                        entity.getComponent(HealthComponent.class).get().setLifes(Player.damageTaken);
                                    }
                                    entity.setPosition(new Pair<>(Player.levelOneStartingPlayerX, 
                                                                  Player.levelOneStartingPlayerY));
                                } else {
                                    if (e.getComponent(DoubleDamageComponent.class).get().getDoubleDamage()) {
                                        entity.getComponent(HealthComponent.class).get().setLifes(Player.damageTaken);
                                    }
                                    entity.getComponent(ShieldComponent.class).get().setShield(false);
                                }
                            } else {
                                entity.getComponent(ShieldComponent.class).get().setShield(false);
                            }
                            entity.getGameplay().removeEntity(e);
                        }
                        if (e.getEntityType() == Type.BLOCK
                            || e.getEntityType() == Type.BLOCK_LADDER_DOWN
                            || e.getEntityType() == Type.BLOCK_LADDER_UP
                            || e.getEntityType() == Type.BLOCK_LADDER_UPDOWN) {
                            final Rectangle2D.Float e2Hitbox = e.getComponent(CollisionComponent.class).get().getHitbox();
                            if (hitbox.y + hitbox.height > e2Hitbox.y && hitbox.y + hitbox.height < e2Hitbox.y + 4) {
                                entity.setPosition(new Pair<>(this.nextPosition.get().getX(), e2Hitbox.y - hitbox.height));
                            }
                            if (e.getEntityType() != Type.BLOCK
                                && hitbox.x > e2Hitbox.x + ladderPadding - canLadderError
                                && hitbox.x + hitbox.width > e2Hitbox.x + e2Hitbox.width - ladderPadding + canLadderError) {
                                entity.getComponent(MovementComponent.class).get().setCanUseLadder(true);
                            }
                        }
                        if (e.getEntityType() == Type.LADDER && e.getComponent(MovementComponent.class).get().isOnLadder()) {
                            entity.setPosition(new Pair<>(this.nextPosition.get().getX(), this.nextPosition.get().getY()));
                        }
                        if (e.getEntityType() == Type.PRINCESS) {
                            Gamestate.setGamestate(Gamestate.WIN);
                            entity.getGameplay().getController().stopTimer();
                        }
                        if (e.getEntityType() == Type.MONKEY) {
                            Gamestate.setGamestate(Gamestate.DEATH);
                            entity.getGameplay().getController().stopTimer();
                        }
                        if (e.getEntityType() == Type.STAR) {
                            entity.getComponent(StarComponent.class).get().setInvincible(true);
                            entity.getGameplay().removeEntity(e);
                        }
                        if (e.getEntityType() == Type.SHIELD) {
                            entity.getComponent(ShieldComponent.class).get().setShield(true);
                            entity.getGameplay().removeEntity(e);
                        }
                        if (e.getEntityType() == Type.HEART) {
                            entity.getComponent(HealthComponent.class).get().setLifes(Player.extraLife);
                            entity.getGameplay().removeEntity(e);
                        }
                        if (e.getEntityType() == Type.SNOWFLAKE) {
                            entity.getComponent(FreezeComponent.class).get().setFrozen(true);
                            entity.getGameplay().removeEntity(e);
                        }
                  });
        } else if (this.entity.getEntityType() == Type.BARREL) {
            this.entity.getGameplay().getEntities().stream()
                        .filter(e -> e.getEntityType() == Type.BLOCK
                                    || e.getEntityType() == Type.BLOCK_LADDER_DOWN
                                    || e.getEntityType() == Type.BLOCK_LADDER_UP
                                    || e.getEntityType() == Type.BLOCK_LADDER_UPDOWN)
                        .filter(e -> hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox()))
                        .forEach(e -> {
                            if (random.nextInt(Barrel.totalDirProbability) == Barrel.changeDirProbability
                                && !this.barrelChangedDirection
                                && !entity.getComponent(MovementComponent.class).get().isInAir()
                            ) {
                                entity.getComponent(MovementComponent.class).get().changeDirection();
                            }
                            this.barrelChangedDirection = true;
                        });
        }
    }

    private void initDifferentHitbox(final Type type) {
        width = Constants.Window.SCALED_TILES_SIZE;
        height = width;
        switch (type) {
            case LADDER:
                hitbox = new Rectangle2D.Float(x, y, 
                                               width - (Constants.Level.ladderPadding * 2), height);
                break;
            case BARREL:
                width = Constants.Barrel.barrelWidth;
                height = Constants.Barrel.barrelHeight;
                hitbox = new Rectangle2D.Float(x, y, width, height);
                break;
            case PLAYER:
                hitbox = new Rectangle2D.Float(x, y, width, height);
                break;
            case PRINCESS:
                height = Constants.Princess.princessHeight;
                hitbox = new Rectangle2D.Float(x, y, width, height);
                break;
            case MONKEY:
                width = Constants.Monkey.monkeyWidth;
                height = Constants.Monkey.monkeyHeight;
                hitbox = new Rectangle2D.Float(x, y, width, height);
                break;
            case HEART:
            case SNOWFLAKE:
            case STAR:
            case SHIELD:
                hitbox = new Rectangle2D.Float(x + 1, y + 1, width, height);
                break;
            case BLOCK:
            case BLOCK_LADDER_DOWN:
            case BLOCK_LADDER_UP:
            case BLOCK_LADDER_UPDOWN:
            default:
                hitbox = new Rectangle2D.Float(x, y, 
                                               width, height - Constants.Level.platformBlockPadding * 2);
                break;
        }
    }
}
