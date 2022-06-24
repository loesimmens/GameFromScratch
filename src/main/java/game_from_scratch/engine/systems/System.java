package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Component;

import java.util.List;

public interface System {
    default void actOnAllComponents(List<Component> components) {
        components.forEach(this::actOnOneComponent);
    }

    void actOnOneComponent(Component component);
}
