package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Colliding;
import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.Moving;
import game_from_scratch.engine.entities.Entity;
import org.springframework.stereotype.Service;

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

                int x = moving.getIntendedXPosition();
                int y = moving.getIntendedYPosition();

                final Optional<Entity> optionalOtherEntityOnPosition = this.positionSystem.getEntityOnPosition(x, y);
                if (optionalOtherEntityOnPosition.isPresent()) {
                    Entity otherEntityOnPosition = optionalOtherEntityOnPosition.get();
                    Optional<Component> optionalOtherColliding = otherEntityOnPosition.getComponentOfClass(Colliding.class);
                    colliding.setCollisionCheckPassed(optionalOtherColliding.isEmpty());
                } else {
                    colliding.setCollisionCheckPassed(true);
                }
            }
            colliding.setCollisionShouldBeChecked(false);
        }
    }
}
