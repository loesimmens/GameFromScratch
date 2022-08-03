package game_from_scratch.game.world.factories;

import game_from_scratch.engine.components.*;

import java.util.List;

public class EntityFactory {
    private EntityFactory(){}

    public static List<Component> getPlayerComponents() {
        return List.of(
                new Position(0, 0),
                new Moving(),
                new Rendering("player", 1),
                new TakesInput(),
                new Colliding(),
                new GetsTurns(1)
        );
    }
}
