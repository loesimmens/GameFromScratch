package game.converters;

import game.graphics.rendering.renderables.world.Tile;
import org.springframework.stereotype.Service;

@Service
public class TileConverter {
    public Tile toRenderable(game.world.tiles.Tile tile) {
        return new Tile(tile.getTileType());
    }
}
