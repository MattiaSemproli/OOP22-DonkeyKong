package it.unibo.donkeykong.game.ecs.impl;

import java.awt.geom.Rectangle2D;

import it.unibo.donkeykong.game.model.api.Entity;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

public class CollisionComponent extends AbstractComponent {

    private final boolean isSolid;
    private float x, y;
    private int width, height;
    private Rectangle2D.Float hitbox;

    @Override
    public void update() {
        hitbox.x = this.getEntity().getPosition().getX()*Window.TILES_DEFAULT_SIZE;
        hitbox.y = this.getEntity().getPosition().getY()*Window.TILES_DEFAULT_SIZE;
        checkOutField();
        checkCollisions();
    }

    public CollisionComponent(final float x, final float y, final boolean isSolid) {
        this.x = (int) (x * Window.TILES_DEFAULT_SIZE);
        this.y = (int) (y * Window.TILES_DEFAULT_SIZE);
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
    }

    private void initHitbox() {
        this.width = (int)Window.TILES_DEFAULT_SIZE;
        this.height = this.width;
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }
}
