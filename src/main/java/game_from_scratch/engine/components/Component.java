package game_from_scratch.engine.components;

import game_from_scratch.engine.entities.Entity;

public abstract class Component {
    protected Entity entity;

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
