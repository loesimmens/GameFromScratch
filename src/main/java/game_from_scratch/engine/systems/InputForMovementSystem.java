package game_from_scratch.engine.systems;
import game_from_scratch.engine.entities.Entity;

public interface InputForMovementSystem {
    void setDirectionForMoving(Entity entity);
}
