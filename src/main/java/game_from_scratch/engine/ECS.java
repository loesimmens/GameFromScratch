package game_from_scratch.engine;

import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.Rendering;
import game_from_scratch.engine.entities.Entity;
import game_from_scratch.engine.systems.rendering.RenderSystem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ECS {
    private final List<Entity> entities;
    protected List<Component> components;

    private final RenderSystem renderSystem;

    public ECS(RenderSystem renderSystem) {
        this.entities = new ArrayList<>();
        this.components = new ArrayList<>();

        this.renderSystem = renderSystem;
    }

    public void tickRenderSystem() {
        List<Component> renderingComponents =  this.components.stream()
                .filter(Rendering.class::isInstance)
                .collect(Collectors.toList());
        this.renderSystem.actOnAllComponents(renderingComponents);
    }

    public void addEntity(List<Component> components) {
        int id = this.entities.size();
        Entity entity = new Entity(id, components);
        this.entities.add(entity);
        components.forEach(component -> component.setEntityId(id));
        this.components.addAll(components);
    }

    public void activateEntity(Entity entity) {
        entity.activate();
    }

    public  void deactivateEntity(Entity entity) {
        entity.deactivate();
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }
}
