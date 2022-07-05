package game_from_scratch.engine;

import game_from_scratch.engine.components.*;
import game_from_scratch.engine.entities.Entity;
import game_from_scratch.engine.systems.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ECS {
    private final List<Entity> entities;
    protected List<Component> components;

    private final TurnSystem turnSystem;
    private final RenderSystem renderSystem;
    private final InputSystem inputSystem;
    private final AISystem aiSystem;
    private final MovingSystem intendToMoveSystem;
    private final MovingSystem executeMoveSystem;
    private final PositionSystem positionSystem;
    private final CollisionSystem collisionSystem;

    public ECS(TurnSystem turnSystem, RenderSystem renderSystem, InputSystem inputSystem, AISystem aiSystem, IntendToMoveSystem intendToMoveSystem,
               ExecuteMoveSystem executeMoveSystem, PositionSystem positionSystem, CollisionSystem collisionSystem) {
        this.turnSystem = turnSystem;
        this.aiSystem = aiSystem;
        this.positionSystem = positionSystem;
        this.renderSystem = renderSystem;
        this.inputSystem = inputSystem;
        this.intendToMoveSystem = intendToMoveSystem;
        this.executeMoveSystem = executeMoveSystem;
        this.collisionSystem = collisionSystem;

        this.entities = new ArrayList<>();
        this.components = new ArrayList<>();
    }

    public void tickTurnSystem() {
        this.turnSystem.actOnAllComponents(this.components, GetsTurns.class);
    }

    public void tickRenderSystem() {
        this.renderSystem.actOnAllComponents(this.components, Rendering.class);
    }

    public void tickInputSystem() {
        this.inputSystem.actOnAllComponents(this.components, TakesInput.class);
    }

    public void tickAISystem() {
        this.aiSystem.actOnAllComponents(this.components, AI.class);
    }

    public void tickIntendToMoveSystem() {
        this.intendToMoveSystem.actOnAllComponents(this.components, Moving.class);
    }

    public void tickExecuteMoveSystem() {
        this.executeMoveSystem.actOnAllComponents(this.components, Moving.class);
    }

    public void tickPositionSystem() {
        this.positionSystem.actOnAllComponents(this.components, Position.class);
    }

    public void tickCollisionSystem() {
        this.collisionSystem.actOnAllComponents(this.components, Colliding.class);
    }

    public void addEntity(List<Component> components) {
        int id = this.entities.size();
        Entity entity = new Entity(id, components);
        this.entities.add(entity);
        components.forEach(component -> component.setEntity(entity));
        this.components.addAll(components);
    }

    public void activateEntity(Entity entity) {
        entity.activate();
    }

    public  void deactivateEntity(Entity entity) {
        entity.deactivate();
    }

    public List<Component> getComponents() {
        return components;
    }

    public TurnSystem getTurnSystem() {
        return turnSystem;
    }
}
