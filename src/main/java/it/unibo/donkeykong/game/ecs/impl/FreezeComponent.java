package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Player;

/**
 * Freeze component, manages a freeze power up.
 */
public class FreezeComponent extends AbstractComponent {

    private boolean freezer;
    private int timeElapsed;

    /**
     * Constructor.
     */
    public FreezeComponent() {
        this.freezer = false;
        this.timeElapsed = 0;
    }

    @Override
    public final void update() {
        this.timeElapsed++;
        if (this.freezer && this.timeElapsed > Player.freezeDuration) {
            this.freezer = false;
            this.setMonkeyFreezer(freezer);
        }
    }

    /**
     * Checks if freeze effect is active.
     * 
     * @return true if is active.
     */
    public final boolean isFrozen() {
        return this.freezer;
    }

    /**
     * Set new freeze's state.
     * 
     * @param freezer the new freeze's state.
     */
    public final void setFrozen(final boolean freezer) {
        this.freezer = freezer;
        if (freezer) {
            this.timeElapsed = 0;
            this.setMonkeyFreezer(freezer);
        }
    }

    /**
     * Set freeze state to monkey.
     * 
     * @param freezer the freeze's state.
     */
    private void setMonkeyFreezer(final boolean freezer) {
        this.getEntity()
            .getGameplay()
            .getEntities()
            .stream()
            .filter(e -> e.getEntityType() == Type.MONKEY)
            .findFirst()
            .ifPresent(e -> e.getComponent(ThrowComponent.class).get().setFreezed(freezer));
    }
}
