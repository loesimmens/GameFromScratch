package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.AI;
import game_from_scratch.engine.components.Moving;
import game_from_scratch.engine.entities.Entity;
import game_from_scratch.engine.enums.Direction;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AISystem implements System<AI>, InputForMovementSystem {
    @Override
    public void actOnOneComponent(AI ai) {
        this.setDirectionForMoving(ai.getEntity());
    }

    @Override
    public void setDirectionForMoving(Entity entity) {
        Moving moving;
        Optional<Moving> optionalMoving = entity.getComponents().stream()
                .filter(Moving.class::isInstance)
                .map(Moving.class::cast)
                .findFirst();
        if (optionalMoving.isPresent()) {
            moving = optionalMoving.get();
            Direction direction = Direction.getRandom();
            moving.setIntendsToMove(true);
            moving.setIntendedDirection(direction);
        }
    }
}
