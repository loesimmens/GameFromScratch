package game_from_scratch.game.world;

import game_from_scratch.engine.ECS;
import game_from_scratch.engine.components.Rendering;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameMap {
    private final ECS ecs;

    private final int mapWidth;
    private final int mapHeight;

    public GameMap(ECS ecs, @Value("${gamemap.width}") int mapWidth, @Value("${gamemap.height}") int mapHeight) {
        this.ecs = ecs;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.addTiles();
        this.addPlayer();
    }

    private void addTiles() {
        for(int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapWidth; x++) {
                this.ecs.addEntity(List.of(new Rendering("wall", x, y, 0)));
            }
        }
    }

    private void addPlayer() {
        this.ecs.addEntity(List.of(
                new Rendering("player", 0, 0, 1)
        ));
    }
}
