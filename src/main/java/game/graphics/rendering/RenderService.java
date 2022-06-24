package game.graphics.rendering;

import game.converters.ConverterService;
import game.graphics.rendering.renderables.world.Tile;
import game.world.GameMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class RenderService implements Rendering {
    private final int gameMapWidth;
    private final int gameMapHeight;
    private final int tileWidth;
    private final int tileHeight;

    private final GameMap gameMap;

    private final ConverterService converterService;

    public RenderService(@Value("${gamemap.width}") int gameMapWidth, @Value("${gamemap.height}") int gameMapHeight,
                         @Value("${tile.width}") int tileWidth, @Value("${tile.height}") int tileHeight,
                         GameMap gameMap, ConverterService converterService) {
        this.gameMapWidth = gameMapWidth;
        this.gameMapHeight = gameMapHeight;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.gameMap = gameMap;
        this.converterService = converterService;
    }

    @Override
    public void render(Graphics graphics) {
        renderGameMap(graphics);
    }

    private void renderGameMap(Graphics graphics) {
        for(int y = 0; y < this.gameMapHeight; y ++) {
            for(int x = 0; x < this.gameMapWidth; x ++) {
                renderTile(gameMap.getMap()[x][y], graphics, x * this.tileWidth, y * this.tileHeight);
            }
        }
    }

    private void renderTile(game.world.tiles.Tile gameTile, Graphics graphics, int x, int y) {
        Tile graphicsTile = this.converterService.getTileConverter().toRenderable(gameTile);
        graphicsTile.render(graphics, x, y, this.tileWidth, this.tileHeight);
    }
}
