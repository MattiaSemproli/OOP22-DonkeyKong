package it.unibo.donkeykong.game.ecs.impl;

import java.awt.geom.Rectangle2D;
import java.util.Optional;

import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.Gamestate;
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
        if(this.nextPosition.isPresent()) {
            entity = this.getEntity();
            hitbox.x = nextPosition.get().getX();
            hitbox.y = nextPosition.get().getY();
            checkOutField();
            checkCollisions();
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
    public final Rectangle2D getHitbox() {
        return this.hitbox;
    }

    /**
     * Check if entity can go through things.
     * 
     * @return true if entity can go through things, false otherwise.
     */
    public final boolean isSolid() {
        return this.isSolid;
    }

    private void checkCollisions() {
        if (entity.getEntityType().equals(Type.PLAYER)) {
            entity.getGameplay()
                  .getEntities()
                  .stream()
                  .filter(e -> !e.equals(entity))
                  .filter(e -> hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox()));
            /*entity.getGameplay()
                  .getEntities()
                  .stream()
                  .filter(e -> !e.equals(entity))
                  .filter(e -> hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox()))
                  .forEach(e -> {
                    final Rectangle2D e2Hitbox = e.getComponent(CollisionComponent.class).get().getHitbox();
                    if (e.getEntityType().equals(Type.BARREL)) {
                        entity.getGameplay().removeEntity(entity);
                    }
                    if (e.getEntityType().equals(Type.MONKEY)) {
                        entity.getGameplay().removeEntity(entity);
                    }
                    if (e.getEntityType().equals(Type.PRINCESS)) {
                        Gamestate.setGamestate(Gamestate.WIN);
                    }
                    if (e.getEntityType().equals(Type.BLOCK)
                        || e.getEntityType().equals(Type.BLOCK_LADDER_DOWN)
                        || e.getEntityType().equals(Type.BLOCK_LADDER_UP)
                        || e.getEntityType().equals(Type.BLOCK_LADDER_UPDOWN)) {
                        if ((hitbox.y + hitbox.height > e2Hitbox.getY()
                             && hitbox.y + hitbox.height < e2Hitbox.getY() + 4)
                            && (hitbox.x + hitbox.width > e2Hitbox.getX() 
                                && hitbox.x < e2Hitbox.getMaxX())) {
                            entity.getComponent(MovementComponent.class).get().resetIsInAir();
                            entity.getComponent(MovementComponent.class).get().setFalling(false);
                            entity.setPosition(new Pair<>(this.nextPosition.get().getX(), (float) e2Hitbox.getY() - hitbox.height));
                        }
                    }
                    if (e.getEntityType().equals(Type.LADDER)) {
                        e.getGameplay().removeEntity(e);
                    }
                  });*/
        } else if (entity.getEntityType().equals(Type.BARREL)) {

        }
    }

    private void checkOutField() {
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
