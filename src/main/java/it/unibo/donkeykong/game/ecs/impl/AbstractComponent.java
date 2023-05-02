package it.unibo.donkeykong.game.ecs.impl;

import it.unibo.donkeykong.game.ecs.api.Component;
import it.unibo.donkeykong.game.model.api.Entity;

/**
 * Defines the relationship between component and related entity.
 */
public abstract class AbstractComponent implements Component {

    private Entity entity;

    /**
     * @return entity related to the component.
     */
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * @param entity entity to set to the component.
     */
    public void setEntity(final Entity entity) {
        this.entity = entity;
    }
}
