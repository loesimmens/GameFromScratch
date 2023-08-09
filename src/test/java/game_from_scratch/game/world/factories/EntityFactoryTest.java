package game_from_scratch.game.world.factories;

import game_from_scratch.engine.components.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityFactoryTest {
    @Test
    void getAIComponents() {
        List<Component> aiComponents = EntityFactory.getAiComponents(3,3);
        Position position = (Position) aiComponents.get(0);
        Moving moving = (Moving) aiComponents.get(1);
        Rendering rendering = (Rendering) aiComponents.get(2);
        AI ai = (AI) aiComponents.get(3);
        Colliding colliding = (Colliding) aiComponents.get(4);
        GetsTurns getsTurns = (GetsTurns) aiComponents.get(5);

        assertNotNull(aiComponents);
        assertEquals(3, position.getX());
        assertEquals(3, position.getY());
        assertNotNull(moving);
        assertEquals("ai", rendering.getImageName());
        assertEquals(1, rendering.getLayer());
        assertNotNull(ai);
        assertNotNull(colliding);
        assertEquals(2, getsTurns.getTurnsLeft());
    }

    @Test
    void getPlayerComponents() {
        List<Component> playerComponents = EntityFactory.getPlayerComponents(0,0);
        Position position = (Position) playerComponents.get(0);
        Moving moving = (Moving) playerComponents.get(1);
        Rendering rendering = (Rendering) playerComponents.get(2);
        TakesInput takesInput = (TakesInput) playerComponents.get(3);
        Colliding colliding = (Colliding) playerComponents.get(4);
        GetsTurns getsTurns = (GetsTurns) playerComponents.get(5);

        assertNotNull(playerComponents);
        assertEquals(0, position.getX());
        assertEquals(0, position.getY());
        assertNotNull(moving);
        assertEquals("player", rendering.getImageName());
        assertEquals(1, rendering.getLayer());
        assertNotNull(takesInput);
        assertNotNull(colliding);
        assertEquals(1, getsTurns.getTurnsLeft());
    }
}