package it.unibo.donkeykong.game.ecs.impl;

import java.awt.geom.Rectangle2D;

import it.unibo.donkeykong.game.model.api.Entity;
import it.unibo.donkeykong.game.model.impl.AbstractComponent;
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
    private Rectangle2D.Float hitbox;

    @Override
    public final void update() {
        hitbox.x = this.getEntity().getPosition().getX();
        hitbox.y = this.getEntity().getPosition().getY();
        checkOutField();
        checkCollisions();
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
        this.x = (int) x;
        this.y = (int) y;
        this.isSolid = isSolid;
        initDifferentHitbox(type);
    }

    /**
     * Get the hitbox.
     * 
     * @return defensive copy of the hitbox.
     */
    public final Rectangle2D getHitbox() {
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

    private void checkCollisions() {
        final Entity entity = this.getEntity();
        if (entity.getEntityType().equals(Type.PLAYER)) {
            entity.getGameplay()
                  .getEntities()
                  .stream()
                  .filter(e -> !e.equals(entity))
                  .filter(e -> hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox()))
                  .forEach(e -> {
                    final Rectangle2D eHitbox = entity.getComponent(CollisionComponent.class).get().getHitbox();
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
                    if ((e.getEntityType().equals(Type.BLOCK)
                        || e.getEntityType().equals(Type.BLOCK_LADDER_DOWN)
                        || e.getEntityType().equals(Type.BLOCK_LADDER_UP)
                        || e.getEntityType().equals(Type.BLOCK_LADDER_UPDOWN))
                        && checkWall((float) eHitbox.getMaxY(), (float) e2Hitbox.getY()) >= 0) {
                            entity.setPosition(new Pair<>(entity.getPosition().getX(), 
                                                          (float) e2Hitbox.getY() - entity.getHeight()));
                    }
                    if (e.getEntityType().equals(Type.LADDER)) {
                        e.getGameplay().removeEntity(e);
                    }
                  });
        }
    }

    private void checkOutField() {
        final Entity entity = this.getEntity();
        Pair<Float, Float> nextPos = null;
        float check;
        if (entity.getEntityType() == Type.PLAYER) {
            if ((check = checkWall(hitbox.x + hitbox.width, Window.GAME_WIDTH)) > 0) {
                nextPos = new Pair<>(entity.getPosition().getX() - check, entity.getPosition().getY());
            } else if ((check = checkWall(hitbox.x, 0)) < 0) {
                nextPos = new Pair<>(entity.getPosition().getX() - check, entity.getPosition().getY());
            } else if ((check = checkWall(hitbox.y, 0)) < 0) {
                nextPos = new Pair<>(entity.getPosition().getX(), entity.getPosition().getY() - check);
            } else if (checkWall(hitbox.y, Window.GAME_HEIGHT) > 0) {
                entity.getGameplay().removeEntity(entity);
            }
            if (nextPos != null) {
                entity.setPosition(nextPos);
            }
        } else if (entity.getEntityType() == Type.BARREL) {
            final MovementComponent movementComponent = entity.getComponent(MovementComponent.class).get();
            if (checkWall(hitbox.x + hitbox.width, Window.GAME_WIDTH) > 0) {
                movementComponent.moveEntity(movementComponent.getFacing().getOppositeDirection());
            } else if (checkWall(hitbox.x, 0) < 0) {
                movementComponent.moveEntity(movementComponent.getFacing().getOppositeDirection());
            } else if (checkWall(hitbox.y, Window.GAME_HEIGHT) > 0) {
                entity.getGameplay().removeEntity(entity);
            }
        }
    }

    private float checkWall(final float val, final float wall) {
        return val - wall;
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
