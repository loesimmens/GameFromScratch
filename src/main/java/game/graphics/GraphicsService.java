package game.graphics;

import game.graphics.display.Display;
import game.graphics.rendering.RenderService;
import game.logging.GameLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.logging.Logger;

@Service
public class GraphicsService{
    private static final Logger LOGGER = GameLogger.getLogger();

    private final int screenWidth;
    private final int screenHeight;

    private final Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private final RenderService renderService;

    public GraphicsService(@Value("${screen.width}") int screenWidth, @Value("${screen.height}") int screenHeight,
                           Display display, RenderService renderService) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.display = display;
        this.renderService = renderService;
        initGraphics();
    }

    public void render() {
        initGraphics();
        clearScreen();
        renderGame();
        stopDrawing();
    }

    private void initGraphics() {
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if(bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            bufferStrategy = display.getCanvas().getBufferStrategy();
        }
        graphics = bufferStrategy.getDrawGraphics();
    }

    private void clearScreen() {
        graphics.clearRect(0, 0, screenWidth, screenHeight);
    }

    private void renderGame() {
        renderService.render(graphics);
    }

    private void stopDrawing() {
        bufferStrategy.show();
        graphics.dispose();
    }

    public Display getDisplay() {
        return display;
    }
}
