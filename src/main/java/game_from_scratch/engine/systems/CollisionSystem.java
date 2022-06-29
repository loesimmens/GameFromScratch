package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Moving;
import org.springframework.stereotype.Service;

@Service
public class CollisionSystem implements System<Moving> {
    private final PositionSystem positionSystem;

    public CollisionSystem(PositionSystem positionSystem) {
        this.positionSystem = positionSystem;
    }

    @Override
    public void actOnOneComponent(Moving moving) {
        if(moving.intendsToMove()) {
            this.tryToMove(moving);
        }
    }

    private void tryToMove(Moving moving) {
//        int intendedX = moving.
        //use positionsystem and solid to check for collision (only on active entities)

    }
}
