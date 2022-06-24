package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.Rendering;
import game_from_scratch.game.graphics.assets.AssetService;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class RenderSystem implements System {
    private Graphics graphics;
    private final AssetService assetService;

    public RenderSystem(AssetService assetService) {
        this.assetService = assetService;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void actOnOneComponent(Component component) {
        Rendering rendering = (Rendering) component;

        BufferedImage image = this.assetService.getImage(rendering.getImageName());
        int x = rendering.getX();
        int y = rendering.getY();
        int width = rendering.getWidth();
        int height = rendering.getHeight();

        this.graphics.drawImage(image, x, y, width, height, null);
    }
}
