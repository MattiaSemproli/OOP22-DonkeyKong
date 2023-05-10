package it.unibo.donkeykong.game.ecs.impl;

import java.awt.geom.Rectangle2D;

import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.Constants.Window;

public class CollisionComponent extends AbstractComponent {

    private Rectangle2D.Float hitbox;
    private float x, y;
    private int width, height;

    public CollisionComponent(final float x, final float y) {
        this.x = (int) (x * Window.TILES_DEFAULT_SIZE);
        this.y = (int) (y * Window.TILES_DEFAULT_SIZE);
        initHitbox();
    }

    @Override
    public void update() {
    }

    private void initHitbox() {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }
}
