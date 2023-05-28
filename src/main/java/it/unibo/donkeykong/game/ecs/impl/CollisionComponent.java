package it.unibo.donkeykong.game.ecs.impl;

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
    private Type eType;
    private Rectangle2D.Float hitbox;
    private Optional<Pair<Float, Float>> nextPosition = Optional.empty();
    private final Random random = new Random();

    @Override
    public final void update() {
        this.nextPosition = this.getEntity().getNextPosition();
        this.entity = this.getEntity();
        this.eType = this.getEntity().getEntityType();
        if (this.nextPosition.isPresent()) {
            this.hitbox.x = nextPosition.get().getX();
            this.hitbox.y = nextPosition.get().getY();
            if (eType == Type.BARREL) {
                this.checkBarrelInAir();
                this.checkBarrelCollision();
            }
        } else {
            this.nextPosition = Optional.of(this.getEntity().getPosition());
            this.hitbox.x = this.getEntity().getPosition().getX();
            this.hitbox.y = this.getEntity().getPosition().getY();
        }
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

    private boolean checkIsNotBlock(final Type type) {
        return type != Type.BLOCK
            && type != Type.BLOCK_LADDER_DOWN
            && type != Type.BLOCK_LADDER_UP
            && type != Type.BLOCK_LADDER_UPDOWN;
    }

    private void checkBarrelInAir() {
        if (entity.getGameplay().getEntities()
                                .stream().filter(e -> !this.checkIsNotBlock(e.getEntityType()))
                                .anyMatch(e -> {
                                    Rectangle2D.Float e2hitbox = e.getComponent(CollisionComponent.class).get().getHitbox();
                                    if (hitbox.intersectsLine(new Line2D.Float(e2hitbox.x,
                                                                               e2hitbox.y,
                                                                               e2hitbox.x + e2hitbox.width,
                                                                               e2hitbox.y))
                                        && hitbox.y + hitbox.height < e2hitbox.y + 4) {
                                            return true;
                                        }
                                        return false;
                                })) {
                                entity.getComponent(MovementComponent.class).get().resetIsInAir();
                            }  else {
                                entity.getComponent(MovementComponent.class).get().setIsInAir(true);
                                this.barrelChangedDirection = false;
                            }
    }

    private void checkBarrelCollision() {
        final MovementComponent mc = this.entity.getComponent(MovementComponent.class).get();
        this.entity.getGameplay().getEntities().stream()
                        .filter(e -> !this.checkIsNotBlock(e.getEntityType()))
                        .filter(e -> hitbox.intersects(e.getComponent(CollisionComponent.class).get().getHitbox()))
                        .forEach(e -> {
                            if (random.nextInt(Barrel.totalDirProbability) == Barrel.changeDirProbability
                                && !this.barrelChangedDirection
                                && !mc.isInAir()
                            ) {
                                mc.changeDirection();
                            }
                            this.barrelChangedDirection = true;
                        });
        if (hitbox.x < 0 || hitbox.x + hitbox.width > Window.GAME_WIDTH) {
            mc.moveEntity(mc.getFacing().getOppositeDirection());
        } else if (hitbox.y > Window.GAME_HEIGHT) {
            this.entity.getGameplay().removeEntity(entity);
        } else {
            entity.setPosition(new Pair<>(this.nextPosition.get().getX(), this.nextPosition.get().getY()));
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
