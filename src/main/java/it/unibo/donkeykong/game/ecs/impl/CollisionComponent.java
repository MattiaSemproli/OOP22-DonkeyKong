package it.unibo.donkeykong.game.ecs.impl;

import java.awt.geom.Rectangle2D;

import javax.swing.text.Position;

import com.google.common.base.Optional;

import it.unibo.donkeykong.game.model.api.Entity;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

public class CollisionComponent extends AbstractComponent {

    private final boolean isSolid;
    private float x, y;
    private int width, height;
    private Rectangle2D.Float hitbox;

    @Override
    public void update() {
        hitbox.x = this.getEntity().getPosition().getX();
        hitbox.y = this.getEntity().getPosition().getY();
        checkOutField();
        checkCollisions();
    }

    public CollisionComponent(final float x, final float y, final boolean isSolid) {
        this.x = (int) x;
        this.y = (int) y;
        this.isSolid = isSolid;
        initHitbox();
    }

    public Rectangle2D getHitbox() {
        return this.hitbox;
    }

    public boolean isSolid() {
        return this.isSolid;
    }

    private void checkCollisions() {
    }

    private void checkOutField() {
        final Entity entity = this.getEntity();
        Pair<Float,Float> nextPos = null;
        float check = 0;
        if (entity.getEntityType() == Type.PLAYER) {
            if ((check = checkWall(hitbox.x + hitbox.width, Window.GAME_WIDTH)) > 0) {
                nextPos = new Pair<>(entity.getPosition().getX() - check, entity.getPosition().getY());
            } else if ((check = checkWall(hitbox.x, 0)) < 0) {
                nextPos = new Pair<>(entity.getPosition().getX() - check, entity.getPosition().getY());
            } else if ((check = checkWall(hitbox.y, 0)) < 0) {
                nextPos = new Pair<>(entity.getPosition().getX(), entity.getPosition().getY() - check);
            } else if ((check = checkWall(hitbox.y, Window.GAME_HEIGHT)) > 0) {
                entity.getGameplay().removeEntity(entity);
            }
            if (nextPos != null) {
                entity.setPosition(nextPos);
            }
        } else if (entity.getEntityType() == Type.BARREL) {
            final MovementComponent movementComponent = entity.getComponent(MovementComponent.class).get();
            if ((check = checkWall(hitbox.x + hitbox.width, Window.GAME_WIDTH)) > 0) {
                movementComponent.moveEntity(movementComponent.getFacing().getOppositeDirection());
            } else if ((check = checkWall(hitbox.x, 0)) < 0) {
                movementComponent.moveEntity(movementComponent.getFacing().getOppositeDirection());
            } else if ((check = checkWall(hitbox.y, Window.GAME_HEIGHT)) > 0) {
                entity.getGameplay().removeEntity(entity);
            }
        }
    }

    private float checkWall(final float val, final float wall) {
        return val - wall;
    }

    private void initHitbox() {
        this.width = this.getEntity().getWidth();
        this.height = this.getEntity().getHeight();
        initDifferentHitbox(this.getEntity().getEntityType());
    }

    private void initDifferentHitbox(final Type type) {
        switch (type) {
            case LADDER:
                hitbox = new Rectangle2D.Float(x + Constants.Level.ladderPadding, y, 
                                               width - (Constants.Level.ladderPadding * 2), height);
                break;
            case BARREL:
            case MONKEY:
            case PLAYER:
            case PRINCESS:
                hitbox = new Rectangle2D.Float(x, y, width, height);
                break;
            case POWER_UP:
                hitbox = new Rectangle2D.Float(x, y, width, height);
                break;
            case BLOCK:
            case BLOCK_LADDER_DOWN:
            case BLOCK_LADDER_UP:
            case BLOCK_LADDER_UPDOWN:
            default:
                hitbox = new Rectangle2D.Float(x, y + Constants.Level.platformBlockPadding, 
                                               width, height - (Constants.Level.platformBlockPadding * 2));
                break;

        }
    }
}
