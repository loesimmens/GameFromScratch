package game_from_scratch.engine.entities;

import game_from_scratch.engine.components.Component;

import java.util.List;
import java.util.Optional;

public class Entity {
    private int id;
    private boolean active;
    private List<Component> components;

    public Entity(int id, List<Component> components) {
        this.id = id;
        this.active = true;
        this.components = components;
    }

    public int getId() {
        return id;
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public void removeComponent(Component component) {
        this.components.remove(component);
    }

    public Optional<Component> getComponentOfClass(Class componentClass) {
        return this.components.stream()
                .filter(componentClass::isInstance)
                .findFirst();
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

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", active=" + active +
                ", components=" + components +
                '}';
    }
}
