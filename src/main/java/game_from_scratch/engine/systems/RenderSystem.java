package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.Position;
import game_from_scratch.engine.components.Rendering;
import game_from_scratch.engine.entities.Entity;
import game_from_scratch.engine.exceptions.RenderingException;
import game_from_scratch.engine.assets.AssetService;
import game_from_scratch.game.logging.GameLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RenderSystem implements System<Rendering> {
    private static final Logger LOGGER = GameLogger.getLogger();

    private Graphics graphics;
    private final AssetService assetService;
    private final int tileWidth;
    private final int tileHeight;

    public RenderSystem(AssetService assetService,
                        @Value("${tile.width}") int tileWidth, @Value("${tile.height}") int tileHeight) {
        this.assetService = assetService;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void actOnAllComponents(List<Component> components, Class<Rendering> type) {
        components.stream()
                .filter(type::isInstance)
                .map(Rendering.class::cast)
                .sorted(Comparator.comparing(Rendering::getLayer))
                .forEach(this::actOnOneComponent);
    }

    //todo unit test voor RenderingException
    @Override
    public void actOnOneComponent(Rendering rendering) {
        if(rendering.getImage() == null) {
            rendering.setImage(this.assetService.getImage(rendering.getImageName()));
        }
        Entity entity = rendering.getEntity();

        try {
            Position position = (Position) entity.getComponents().stream().filter(Position.class::isInstance).findFirst().orElseThrow(RenderingException::new);
            int x = position.getX();
            int y = position.getY();

            this.graphics.drawImage(rendering.getImage(), x * this.tileWidth, y * this.tileHeight, this.tileWidth, this.tileHeight, null);
        } catch (RenderingException renderingException) {
            LOGGER.log(Level.WARNING, "Rendering entity " + entity.getId() + "has no position!", renderingException);
        }
    }
}
