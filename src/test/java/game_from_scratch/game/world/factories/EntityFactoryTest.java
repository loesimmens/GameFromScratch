package game_from_scratch.game.world.factories;

import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.GetsTurns;
import game_from_scratch.engine.components.Position;
import game_from_scratch.engine.components.Rendering;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityFactoryTest {
    @Test
    void getAIComponents() {
        List<Component> aiComponents = EntityFactory.getAiComponents(3,3);
        Position position = (Position) aiComponents.get(0);
        Rendering rendering = (Rendering) aiComponents.get(2);
        GetsTurns getsTurns = (GetsTurns) aiComponents.get(5);

        assertNotNull(aiComponents);
        assertEquals(3, position.getX());
        assertEquals(3, position.getY());
        assertEquals("ai", rendering.getImageName());
        assertEquals(1, rendering.getLayer());
        assertEquals(2, getsTurns.getTurnsLeft());
    }

    @Test
    void getPlayerComponents() {
        List<Component> playerComponents = EntityFactory.getPlayerComponents(0,0);
        Position position = (Position) playerComponents.get(0);
        Rendering rendering = (Rendering) playerComponents.get(2);
        GetsTurns getsTurns = (GetsTurns) playerComponents.get(5);

        assertNotNull(playerComponents);
        assertEquals(0, position.getX());
        assertEquals(0, position.getY());
        assertEquals("player", rendering.getImageName());
        assertEquals(1, rendering.getLayer());
        assertEquals(1, getsTurns.getTurnsLeft());
    }
}