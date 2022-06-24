package game_from_scratch.engine.components;

public abstract class Component {
    protected int entityId;

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
}
