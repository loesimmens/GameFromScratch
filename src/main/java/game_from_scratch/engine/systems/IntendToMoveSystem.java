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
public class IntendToMoveSystem implements MovingSystem {
    private Position position;
    private static final Logger LOGGER = GameLogger.getLogger();

    @Override
    public void actOnOneComponent(Moving moving) {
        if(moving.intendsToMove()) {
            Optional<Component> optionalPosition = moving.getEntity().getComponentOfClass(Position.class);
            if (optionalPosition.isPresent()) {
                this.position = (Position) optionalPosition.get();
                this.setIntendedMove(moving);
                if (this.movingIsColliding(moving)) {
                    this.setCollisionToBeChecked(moving);
                }
            } else {
                LOGGER.log(Level.WARNING, () -> moving.getEntity() + " doesn't have a position component, so it won't move, even though it wants to.");
            }
        }
    }

    private boolean movingIsColliding(Moving moving) {
        Optional<Component> optionalColliding = moving.getEntity().getComponentOfClass(Colliding.class);
        return optionalColliding.isPresent();
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

    private void setCollisionToBeChecked(Moving moving) {
        Optional<Component> optionalColliding = moving.getEntity().getComponentOfClass(Colliding.class);
        if(optionalColliding.isPresent()) {
            Colliding colliding = (Colliding) optionalColliding.get();
            colliding.setCollisionShouldBeChecked(true);
        }
    }
}
