package it.unibo.donkeykong.game.ecs.impl;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Optional;

import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

/**
 * Collision Component, manage collisions between objects.
 */
public class CollisionComponent extends AbstractComponent {

    private final boolean isSolid;
    private float x, y;
    private int width, height;
    private Entity entity;
    private Rectangle2D.Float hitbox;
    private Optional<Pair<Float, Float>> nextPosition = Optional.empty();

    @Override
    public final void update() {
        this.nextPosition = this.getEntity().getNextPosition();
        if(!this.nextPosition.isEmpty()) {
            this.entity = this.getEntity();
            this.hitbox.x = nextPosition.get().getX();
            this.hitbox.y = nextPosition.get().getY();
            this.checkIsEntityOnTheFloor();
            this.checkWallCollision();
            this.checkIsCollidingWithOtherEntities();
        } else {
            this.hitbox.x = this.getEntity().getPosition().getX();
            this.hitbox.y = this.getEntity().getPosition().getY();
        }
    }

    /**
     * Constructor.
     * 
     * @param x initial x-position of entity
     * @param y initial y-position of entity
     * @param isSolid declare if entity can go through
     * @param type linked entity type
     */
    public CollisionComponent(final float x, final float y, final boolean isSolid, final Type type) {
        this.x = x;
        this.y = y;
        this.isSolid = isSolid;
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

    /**
     * Check if entity can go through things.
     * 
     * @return true if entity can go through things, false otherwise.
     */
    public final boolean isSolid() {
        return this.isSolid;
    }
    
    private void checkIsEntityOnTheFloor() {
        if (entity.getEntityType() == Type.PLAYER && (this.nextPosition.get().getY() >= entity.getPosition().getY())) {
            if (entity.getGameplay().getEntities()
                  .stream().filter(e -> e.getEntityType() == (Type.BLOCK) 
                                        || e.getEntityType() == (Type.BLOCK_LADDER_DOWN) 
                                        || e.getEntityType() == (Type.BLOCK_LADDER_UP)
                                        || e.getEntityType() == (Type.BLOCK_LADDER_UPDOWN))
                  .filter(e -> hitbox.intersectsLine(new Line2D.Float(e.getComponent(CollisionComponent.class).get().getHitbox().x,
                                                                      e.getComponent(CollisionComponent.class).get().getHitbox().y,
                                                                      e.getComponent(CollisionComponent.class).get().getHitbox().x + e.getComponent(CollisionComponent.class).get().getHitbox().width,
                                                                      e.getComponent(CollisionComponent.class).get().getHitbox().y))
                               && !hitbox.intersectsLine(new Line2D.Float(e.getComponent(CollisionComponent.class).get().getHitbox().x,
                                                                         e.getComponent(CollisionComponent.class).get().getHitbox().y + e.getComponent(CollisionComponent.class).get().getHitbox().height,
                                                                         e.getComponent(CollisionComponent.class).get().getHitbox().x + e.getComponent(CollisionComponent.class).get().getHitbox().width,
                                                                         e.getComponent(CollisionComponent.class).get().getHitbox().y + e.getComponent(CollisionComponent.class).get().getHitbox().height))
                               && hitbox.y + hitbox.height < e.getComponent(CollisionComponent.class).get().getHitbox().y + 4)
                  .count() > 0) {
                entity.getComponent(MovementComponent.class).get().resetIsInAir();
            } else {
                entity.getComponent(MovementComponent.class).get().setIsInAir(true);
            }
        }
    }

    private void checkWallCollision() {
        if (entity.getEntityType() == Type.PLAYER) {
            if(hitbox.x > (Window.GAME_WIDTH - hitbox.width)) {
                entity.setPosition(new Pair<>((Window.GAME_WIDTH - hitbox.width), this.nextPosition.get().getY()));
            } else if(hitbox.y < 0) {
                entity.setPosition(new Pair<>(this.nextPosition.get().getX(), 0f));
            } else if(hitbox.x < 0) {
                entity.setPosition(new Pair<>(0f, this.nextPosition.get().getY()));
            } else if(hitbox.y > Window.GAME_HEIGHT) {
                entity.getGameplay().removeEntity(entity);
            } else {
                entity.setPosition(new Pair<>(this.nextPosition.get().getX(), this.nextPosition.get().getY()));
            }
        } else if (entity.getEntityType() == Type.BARREL) {
            final MovementComponent mc = this.entity.getComponent(MovementComponent.class).get();
            if(hitbox.x < 0 || hitbox.x + hitbox.width > Window.GAME_WIDTH) {
                mc.moveEntity(mc.getFacing().getOppositeDirection());
            } else if (hitbox.y > Window.GAME_HEIGHT) {
                this.entity.getGameplay().removeEntity(entity);
            } else {
                entity.setPosition(new Pair<>(this.nextPosition.get().getX(), this.nextPosition.get().getY()));
            }
        }
    }

    /**
     * TO DO
     */
    private void checkIsCollidingWithOtherEntities() {
        if (entity.getEntityType() == Type.PLAYER) {
            entity.getGameplay().getEntities().stream()
                  .filter(e -> !e.equals(entity))
                  .filter(e -> hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox()))
                  .forEach(e -> {
                        if (e.getEntityType() == Type.BARREL) {
                            entity.getGameplay().removeEntity(entity);
                        }
                        if (e.getEntityType() == Type.BLOCK
                            || e.getEntityType() == Type.BLOCK_LADDER_DOWN
                            || e.getEntityType() == Type.BLOCK_LADDER_UP
                            || e.getEntityType() == Type.BLOCK_LADDER_UPDOWN) {
                            final Rectangle2D.Float e2Hitbox = e.getComponent(CollisionComponent.class).get().getHitbox();
                            if (hitbox.y + hitbox.height > e2Hitbox.y && hitbox.y + hitbox.height < e2Hitbox.y + 4) {
                                entity.setPosition(new Pair<>(this.nextPosition.get().getX(), e2Hitbox.y - hitbox.height));
                            }
                        }
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
            case POWER_UP:
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
