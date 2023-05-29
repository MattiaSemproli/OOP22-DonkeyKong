package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.utilities.Type;

public class FreezeComponent extends AbstractComponent{
    
    private boolean freezer;
    private int timeElapsed;

    public FreezeComponent() {
        this.freezer = false;
        this.timeElapsed = 0;
    }

    @Override
    public void update() {
        this.timeElapsed++;
        if (this.freezer && this.timeElapsed > 600) {
            this.freezer = false;
            this.setMonkeyFreezer(freezer);
        }
    }

    public final boolean isFrozen() {
        return this.freezer;
    }

    public final void setFrozen(final boolean freezer) {
        this.freezer = freezer;
        if (freezer) {
            this.timeElapsed = 0;
            this.setMonkeyFreezer(freezer);
        }
    }

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
