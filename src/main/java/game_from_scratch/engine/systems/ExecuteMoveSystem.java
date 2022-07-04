package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Colliding;
import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.Moving;
import game_from_scratch.engine.components.Position;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecuteMoveSystem extends MovingSystem {

    @Override
    public void actOnOneComponent(Moving moving) {
        if(moving.intendsToMove()) {
            Optional<Component> optionalPosition = moving.getEntity().getComponentOfClass(Position.class);
            if (optionalPosition.isPresent()) {
                this.position = (Position) optionalPosition.get();
                if (this.collisionCheckPassed(moving)) {
                    this.move(moving);
                }
            }
        }
        this.resetIntendedMove(moving);
    }

    private boolean collisionCheckPassed(Moving moving) {
        Optional<Component> optionalColliding = moving.getEntity().getComponentOfClass(Colliding.class);
        if(optionalColliding.isPresent()) {
            Colliding colliding = (Colliding) optionalColliding.get();
            return colliding.isCollisionCheckPassed();
        }
        return true;
    }

    private void move(Moving moving) {
        position.updateX(moving.getIntendedXMove());
        position.updateY(moving.getIntendedYMove());
    }

    private void resetIntendedMove(Moving moving) {
        moving.resetIntendedMove();
    }
}
