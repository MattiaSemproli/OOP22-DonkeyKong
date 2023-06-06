package it.unibo.donkeykong.model.ecs.impl;

import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Constants.Monkey;
import it.unibo.donkeykong.utilities.Pair;

/**
 * Throw Component, manages barrel's throw.
 */
public class ThrowComponent extends AbstractComponent {

    private boolean isFreezed;
    private boolean isThrowing;

    private int timeElapsed = Barrel.spawnDelay;

    /**
     * Constructor.
     */
    public ThrowComponent() {
        this.isFreezed = false;
        this.isThrowing = false;
    }

    @Override
    public final void update() {
        this.timeElapsed++;
        if (!isFreezed() && this.timeElapsed > Barrel.spawnDelay) {
            this.getEntity().getGameplay().throwBarrel(this.getBarrelStartingPosition());
            this.timeElapsed = 0;
            this.isThrowing = false;
        } else if (!isFreezed 
                   && this.timeElapsed < Barrel.spawnDelay 
                   && this.timeElapsed > Barrel.spawnDelay - Monkey.throwAnimationTime) {
            this.isThrowing = true;
        }
    }

    private Pair<Float, Float> getBarrelStartingPosition() {
        return new Pair<>(this.getEntity().getPosition().getX() + this.getEntity().getWidth(), 
                          this.getEntity().getPosition().getY() + this.getEntity().getHeight() - Barrel.barrelHeight);
    }

    /**
     * Check if Donkey Kong is throwing.
     * 
     * @return true if Donkey Kong is throwing.
     */
    public final boolean isThrowing() {
        return isThrowing;
    }

    /**
     * Check if Donkey Kong is freezed.
     * 
     * @return true if Donkey Kong is freezed.
     */
    public final boolean isFreezed() {
        return isFreezed;
    }

    /**
     * Set new Donkey Kong's state.
     * 
     * @param isFreezed the new Donkey Kong's state.
     */
    public final void setFreezed(final boolean isFreezed) {
        this.isFreezed = isFreezed;
    }
}