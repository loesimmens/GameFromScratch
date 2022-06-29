package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.Position;
import game_from_scratch.engine.entities.Entity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PositionSystem implements System<Position> {
    private List<Position> positions;

    public PositionSystem() {
        this.positions = new ArrayList<>();
    }

    @Override
    public void actOnOneComponent(Position position) {
        if(!this.positions.contains(position)) {
            this.positions.add(position);
        }
    }

    public Optional<Entity> getEntityOnPosition(int x, int y) {
        return this.positions.stream()
                .filter(position -> position.getX() == x && position.getY() == y)
                .map(Component::getEntity)
                .findFirst();
    }
}
