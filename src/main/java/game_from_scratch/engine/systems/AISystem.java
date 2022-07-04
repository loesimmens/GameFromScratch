package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.AI;
import game_from_scratch.engine.components.Moving;
import game_from_scratch.engine.entities.Entity;
import game_from_scratch.engine.enums.Direction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AISystem implements System<AI>, InputForMovementSystem {
    private final int fps;
    private int moveTimer;

    public AISystem(@Value("${fps}") int fps) {
        this.fps = fps;
    }

    @Override
    public void actOnOneComponent(AI ai) {
        if(this.moveTimer >= this.fps) {
            this.setDirectionForMoving(ai.getEntity());
            this.moveTimer = 0;
        }
        this.moveTimer += ai.getSpeed();
    }

    @Override
    public void setDirectionForMoving(Entity entity) {
        Moving moving;
        Optional<Moving> optionalMoving = entity.getComponents().stream()
                .filter(Moving.class::isInstance)
                .map(Moving.class::cast)
                .findFirst();
        if(optionalMoving.isPresent()) {
            moving = optionalMoving.get();
            Direction direction = Direction.getRandom();
            moving.setIntendsToMove(true); moving.setIntendedDirection(direction);
        }
    }
}
