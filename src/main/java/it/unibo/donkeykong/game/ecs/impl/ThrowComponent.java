package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Pair;

/**
 * Throw Component, manage barrel's throw from entity monkey.
 */
public class ThrowComponent extends AbstractComponent {

    private boolean isFreezed = false;
    private int timeElapsed = 360;

    @Override
    public void update() {
        this.timeElapsed++;
        if (!isFreezed() && this.timeElapsed > 360) {
            this.getEntity().getGameplay().throwBarrel(this.getBarrelStartingPosition());
            this.timeElapsed = 0;
        }
    }

    private Pair<Float,Float> getBarrelStartingPosition() {
        return new Pair<>(this.getEntity().getPosition().getX() + this.getEntity().getWidth(), 
                          this.getEntity().getPosition().getY() + this.getEntity().getHeight() - Barrel.barrelDimension);
    }


    public boolean isFreezed() {
        return isFreezed;
    }

    public void setFreezed(boolean isFreezed) {
        this.isFreezed = isFreezed;
    }
}
