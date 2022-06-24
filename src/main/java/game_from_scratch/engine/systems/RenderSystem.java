package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.Rendering;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class RenderSystem implements System {
    private Graphics graphics;

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void actOnOneComponent(Component component) {
        Rendering rendering = (Rendering) component;

        BufferedImage image = rendering.getImage();
        int x = rendering.getX();
        int y = rendering.getY();
        int width = rendering.getWidth();
        int height = rendering.getHeight();

        this.graphics.drawImage(image, x, y, width, height, null);
    }
}
