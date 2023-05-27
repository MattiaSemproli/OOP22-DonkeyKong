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
