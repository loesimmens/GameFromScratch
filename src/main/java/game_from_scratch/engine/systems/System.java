package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Component;

import java.util.List;

public interface System<T extends Component> {
    default void actOnAllComponents(List<Component> components, Class<T> type) {
        components.stream()
                .filter(type::isInstance)
                .map(component -> (T) component)
                .forEach(this::actOnOneComponent);
    }

    void actOnOneComponent(T component);
}
