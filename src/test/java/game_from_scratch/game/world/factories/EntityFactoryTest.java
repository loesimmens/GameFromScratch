package game_from_scratch.game.world.factories;

import game_from_scratch.engine.components.Component;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityFactoryTest {

    @Test
    void getPlayerComponents() {
        List<Component> playerComponents = EntityFactory.getPlayerComponents(0,0);
        assertNotNull(playerComponents);
    }

    @Test
    void getAIComponents() {
        List<Component> aiComponents = EntityFactory.getAiComponents(3,3);
        assertNotNull(aiComponents);
    }
}