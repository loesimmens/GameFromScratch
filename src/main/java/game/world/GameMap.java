package game.world;

import game.logging.GameLogger;
import game.world.tiles.Tile;
import game.world.tiles.TileType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class GameMap {
    private static final Logger LOGGER = GameLogger.getLogger();

    private final Tile[][] map;
    private final int width;
    private final int height;

    public GameMap(@Value("${gamemap.width}") int width, @Value("${gamemap.height}")int height) {
        this.width = width;
        this.height = height;
        this.map = new Tile[width][height];
        this.initializeGameMap();
    }

    private void initializeGameMap() {
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                this.map[x][y] = new Tile(TileType.FLOOR);
            }
        }
        this.map[3][3] = new Tile(TileType.WALL);
        LOGGER.info("gameMap initialized with WIDTH=" + this.width + " and HEIGHT=" + this.height);
    }

    public Tile[][] getMap() {
        return map;
    }
}
