package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Colliding;
import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.Moving;
import game_from_scratch.engine.entities.Entity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollisionSystem implements System<Colliding> {
    private final PositionSystem positionSystem;

    public CollisionSystem(PositionSystem positionSystem) {
        this.positionSystem = positionSystem;
    }

    @Override
    public void actOnOneComponent(Colliding colliding) {
        if(colliding.collisionShouldBeChecked()) {
            Entity entity = colliding.getEntity();
            Optional<Component> optionalMoving = entity.getComponentOfClass(Moving.class);
            if (optionalMoving.isPresent()) {
                Moving moving = (Moving) optionalMoving.get();
                this.checkForCollision(colliding, entity, moving);
            }
            colliding.setCollisionShouldBeChecked(false);
        }
    }

    private void checkForCollision(Colliding colliding, Entity entity, Moving moving) {
        int x = moving.getIntendedXPosition();
        int y = moving.getIntendedYPosition();

        List<Entity> entitiesOnPosition = this.positionSystem.getEntitiesOnPosition(x, y);
        final boolean otherCollidingsOnThisPosition = entitiesOnPosition.stream()
                .filter(otherEntity -> !otherEntity.equals(entity))
                .anyMatch(otherEntity -> otherEntity.hasComponentOfClass(Colliding.class));
        colliding.setCollisionCheckPassed(!otherCollidingsOnThisPosition);
    }
}
