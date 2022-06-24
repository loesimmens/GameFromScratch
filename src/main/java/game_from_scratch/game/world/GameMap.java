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
    private final int tileWidth;
    private final int tileHeight;

    public GameMap(ECS ecs, @Value("${gamemap.width}") int mapWidth, @Value("${gamemap.height}") int mapHeight,
                   @Value("${tile.width}") int tileWidth, @Value("${tile.height}") int tileHeight) {
        this.ecs = ecs;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.addTilesAsEntities();
    }

    private void addTilesAsEntities() {
        for(int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapWidth; x++) {
                this.ecs.addEntity(List.of(new Rendering("wall", x * this.tileWidth, y * this.tileHeight, this.tileWidth, this.tileHeight)));
            }
        }
    }
}
