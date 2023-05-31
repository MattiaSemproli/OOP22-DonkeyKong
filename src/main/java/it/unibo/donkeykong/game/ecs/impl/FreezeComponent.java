package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Player;

/**
 * Component that represents the freeze effect.
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
     * Checks if the freeze effect is currently active.
     * 
     * @return true if the freeze effect is active, false otherwise.
     */
    public final boolean isFrozen() {
        return this.freezer;
    }

    /**
     * Sets the state of the freeze effect.
     * 
     * @param freezer true to activate the freeze effect, false to disable it.
     */
    public final void setFrozen(final boolean freezer) {
        this.freezer = freezer;
        if (freezer) {
            this.timeElapsed = 0;
            this.setMonkeyFreezer(freezer);
        }
    }

    /**
     * Sets the freeze state of the monkey entity.
     * 
     * @param freezer true to freeze the monkey entity, false to unfreeze it.
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
