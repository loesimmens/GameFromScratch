package game_from_scratch.engine.components;

public abstract class Component {
    protected long entityId;

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }
}
