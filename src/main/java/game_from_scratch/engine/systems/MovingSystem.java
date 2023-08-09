package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Colliding;
import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.Moving;

import java.util.Optional;

public interface MovingSystem extends System<Moving> {
    default boolean collisionCheckPassed(Moving moving) {
        Optional<Component> optionalColliding = moving.getEntity().getComponentOfClass(Colliding.class);
        if(optionalColliding.isPresent()) {
            Colliding colliding = (Colliding) optionalColliding.get();
            return colliding.isCollisionCheckPassed();
        }
        return true;
    }
}
