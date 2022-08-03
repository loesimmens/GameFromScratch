package game_from_scratch.game.world;

import game_from_scratch.engine.ECS;
import game_from_scratch.engine.components.*;
import game_from_scratch.game.world.factories.EntityFactory;
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
        this.edgesOfGameMap();
        this.addTiles();
        this.addPlayer();
        this.addAI();
    }

    private void addAI() {
        this.ecs.addEntity(List.of(
                new Position(3, 3),
                new Moving(),
                new Rendering("ai", 1),
                new AI(),
                new Colliding(),
                new GetsTurns(2)
        ));
    }

    private void edgesOfGameMap() {
        for(int y = -1; y <= this.mapHeight; y++) {
            for (int x = -1; x <= this.mapWidth; x++) {
                if(x == -1
                || x == this.mapWidth
                || y == -1
                || y == this.mapHeight) {
                    this.ecs.addEntity(List.of(
                            new Position(x, y),
                            new Colliding()));
                }
            }
        }
    }

    private void addTiles() {
        for(int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapWidth; x++) {
                if(y == this.mapHeight / 2 && x != this.mapWidth / 2) {
                    this.ecs.addEntity(List.of(
                            new Position(x, y),
                            new Rendering("wall", 0),
                            new Colliding()));
                } else {
                    this.ecs.addEntity(List.of(
                            new Position(x, y),
                            new Rendering("floor", 0)));
                }
            }
        }
    }

    private void addPlayer() {
        this.ecs.addEntity(EntityFactory.getPlayer());
    }
}
