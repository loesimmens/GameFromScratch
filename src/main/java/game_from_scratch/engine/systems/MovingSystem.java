package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Colliding;
import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.Moving;
import game_from_scratch.engine.components.Position;
import game_from_scratch.engine.enums.Direction;
import game_from_scratch.game.logging.GameLogger;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MovingSystem implements System<Moving> {
    private static final Logger LOGGER = GameLogger.getLogger();

    private Position position;

    @Override
    public void actOnOneComponent(Moving moving) {
        if(moving.intendsToMove()) {
            Optional<Component> optionalPosition = moving.getEntity().getComponentOfClass(Position.class);
            if(optionalPosition.isPresent()) {
                position = (Position) optionalPosition.get();
                this.setIntendedMove(moving);
                if (collisionCheckPassed(moving)) {
                    this.move(moving);
                }
            } else {
                LOGGER.log(Level.WARNING,() -> moving.getEntity() + " doesn't have a position component, so it won't move, even though it wants to.");
            }
        }
        this.resetIntendedMove(moving);
    }

    private void setIntendedMove(Moving moving) {
        Direction intendedDirection = moving.getIntendedDirection();
        switch (intendedDirection) {
            case UP:
                moving.setIntendedMoveY(position.getY(), -1);
                break;
            case DOWN:
                moving.setIntendedMoveY(position.getY(), 1);
                break;
            case LEFT:
                moving.setIntendedMoveX(position.getX(), -1);
                break;
            case RIGHT:
                moving.setIntendedMoveX(position.getX(), 1);
                break;
        }
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
