package game.graphics.rendering.renderables.world;

import game.graphics.assets.AssetService;
import game.graphics.rendering.Renderable;
import game.world.tiles.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile implements Renderable {
    private final TileType tileType;

    public Tile(TileType tileType) {
        this.tileType = tileType;
    }

    @Override
    public void render(Graphics graphics, int x, int y, int width, int height) {
        final BufferedImage image = AssetService.TILE_IMAGE_MAP.get(this.tileType);
        graphics.drawImage(image, x, y, width, height, null);
    }
}
