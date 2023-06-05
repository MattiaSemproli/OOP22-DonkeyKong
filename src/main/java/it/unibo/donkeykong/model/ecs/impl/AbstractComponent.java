package it.unibo.donkeykong.model.ecs.impl;

import it.unibo.donkeykong.model.ecs.api.Component;
import it.unibo.donkeykong.model.ecs.api.Entity;

/**
 * AbstractComponent class, manages a component and interaction with entity.
 */
public abstract class AbstractComponent implements Component {

    private Entity entity;

    /**
     * Get the entity related to the component.
     * 
     * @return the entity.
     */
    protected Entity getEntity() {
        return this.entity;
    }

    /**
     * Set a entity to the component.
     * 
     * @param entity the entity.
     */
    protected void setEntity(final Entity entity) {
        this.entity = entity;
    }
}
