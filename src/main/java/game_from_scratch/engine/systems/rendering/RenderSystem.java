package game_from_scratch.engine.systems.rendering;

import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.Rendering;
import game_from_scratch.engine.systems.System;
import game_from_scratch.engine.systems.rendering.assets.AssetService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;

@Service
public class RenderSystem implements System {
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
    public void actOnAllComponents(List<Component> components) {

        components.stream()
                .map(Rendering.class::cast)
                .sorted(Comparator.comparing(Rendering::getLayer))
                .forEach(this::actOnOneComponent);
    }

    @Override
    public void actOnOneComponent(Component component) {
        Rendering rendering = (Rendering) component;

        BufferedImage image = this.assetService.getImage(rendering.getImageName());
        int x = rendering.getPosition().getX();
        int y = rendering.getPosition().getY();

        this.graphics.drawImage(image, x * this.tileWidth, y * this.tileHeight, this.tileWidth, this.tileHeight, null);
    }
}
