package game.graphics.assets;

import game.world.tiles.TileType;
import game.logging.GameLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class AssetService {
    private static final String SPRITE_SHEET_PATH = "src/main/resources/sprite_sheets";
    private static final String SPRITE_SHEET_TILES_PATH = SPRITE_SHEET_PATH + "/sprite_sheet_tiles.png";
    public static final Map<TileType, BufferedImage> TILE_IMAGE_MAP = new EnumMap<>(TileType.class);

    private final int tileWidth;
    private final int tileHeight;

    private final SpriteSheet spriteSheetTiles;

    public AssetService(@Value("${tile.width}") int tileWidth, @Value("${tile.height}") int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        spriteSheetTiles = new SpriteSheet(ImageLoader.loadImage(SPRITE_SHEET_TILES_PATH));

        Arrays.stream(TileType.values())
                .forEach(this::addTileTypeToImageMap);
    }

    private void addTileTypeToImageMap(TileType tileType) {
        final BufferedImage sprite = spriteSheetTiles.getSprite(tileType.getValue() * this.tileWidth, 0, this.tileWidth, this.tileHeight);
        TILE_IMAGE_MAP.put(tileType, sprite);
    }
}

