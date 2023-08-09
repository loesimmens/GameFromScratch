package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.*;
import game_from_scratch.game.logging.GameLogger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecuteMoveSystem implements MovingSystem {
    private Position position;

    @Override
    public void actOnOneComponent(Moving moving) {
        if(moving.intendsToMove()) {
            Optional<Component> optionalPosition = moving.getEntity().getComponentOfClass(Position.class);
            if (optionalPosition.isPresent()) {
                this.position = (Position) optionalPosition.get();
                if (this.collisionCheckPassed(moving)) {
                    this.move(moving);
                    this.decreaseTurnsLeft(moving);
                }
            }
        }
        this.resetIntendedMove(moving);
    }

    private void decreaseTurnsLeft(Moving moving) {
        final Optional<Component> optionalGetsTurns = moving.getEntity().getComponentOfClass(GetsTurns.class);
        if(optionalGetsTurns.isPresent()) {
            GetsTurns getsTurns = (GetsTurns) optionalGetsTurns.get();
            getsTurns.decreaseTurnsLeft();
        }
    }

    private void move(Moving moving) {
        position.updateX(moving.getIntendedXMove());
        position.updateY(moving.getIntendedYMove());
    }

    private void resetIntendedMove(Moving moving) {
        moving.resetIntendedMove();
    }
}
