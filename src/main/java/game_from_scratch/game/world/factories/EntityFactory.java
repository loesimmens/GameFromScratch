package game_from_scratch.game.world.factories;

import game_from_scratch.engine.components.*;

import java.util.ArrayList;
import java.util.List;

public class EntityFactory {
    private EntityFactory(){}

    public static List<Component> getAiComponents(int xPosition, int yPosition) {
        return List.of(
                new Position(xPosition, yPosition),
                new Moving(),
                new Rendering("ai", 1),
                new AI(),
                new Colliding(),
                new GetsTurns(2)
        );
    }

    public static List<Component> getPlayerComponents(int xPosition, int yPosition) {
        return List.of(
                new Position(xPosition, yPosition),
                new Moving(),
                new Rendering("player", 1),
                new TakesInput(),
                new Colliding(),
                new GetsTurns(1)
        );
    }

    public static List<Component> getTileComponents(int xPosition, int yPosition, String name, boolean colliding) {
        List<Component> components = new ArrayList<>();
        components.add(new Position(xPosition, yPosition));
        components.add(new Rendering(name, 0));

        if(colliding) {
            components.add(new Colliding());
        }
        return components;
    }
}
