package game_from_scratch.engine.entities;

import game_from_scratch.engine.components.Component;

import java.util.List;

public class Entity {
    private long id;
    private boolean active;
    private List<Component> components;

    public Entity(long id, List<Component> components) {
        this.id = id;
        this.active = true;
        this.components = components;
    }

    public long getId() {
        return id;
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public void removeComponent(Component component) {
        this.components.remove(component);
    }

    public boolean hasComponent(Component component) {
        return this.components.contains(component);
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public List<Component> getComponents() {
        return components;
    }
}
