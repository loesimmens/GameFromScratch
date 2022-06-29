package game_from_scratch.game.graphics;

import game_from_scratch.engine.ECS;
import game_from_scratch.game.graphics.display.Display;
import game_from_scratch.engine.systems.RenderSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferStrategy;

@Service
public class GraphicsService{
    private final int screenWidth;
    private final int screenHeight;

    private final Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private final RenderSystem renderSystem;
    private final ECS ecs;

    public GraphicsService(@Value("${screen.width}") int screenWidth, @Value("${screen.height}") int screenHeight,
                           Display display, RenderSystem renderSystem, ECS ecs) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.display = display;
        this.renderSystem = renderSystem;
        this.ecs = ecs;
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
        this.renderSystem.setGraphics(this.graphics);
    }

    private void clearScreen() {
        graphics.clearRect(0, 0, screenWidth, screenHeight);
    }

    private void renderGame() {
        this.ecs.tickRenderSystem();
    }

    private void stopDrawing() {
        bufferStrategy.show();
        graphics.dispose();
    }

    public Display getDisplay() {
        return display;
    }
}
