package game;

import game.graphics.GraphicsService;
import game.input.InputService;
import game.logging.GameLogger;
import game.world.GameMap;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class Game implements Ticking {
    private static final Logger LOGGER = GameLogger.getLogger();

    private final InputService inputService;
    private final GraphicsService graphicsService;
    private final GameMap gameMap;

    private boolean running = false;

    public Game(InputService inputService, GraphicsService graphicsService, GameMap gameMap) {
        this.inputService = inputService;
        this.graphicsService = graphicsService;
        this.graphicsService.getDisplay().getFrame().addKeyListener(inputService);
        this.gameMap = gameMap;
    }

    public void start() {
        if(!running) {
            running = true;
            LOGGER.info("Game has started");

            gameLoop();

            stop(); //todo: never reached?
        }
    }

    public void stop() {
        if(running) {
            running = false;
            LOGGER.info("Game has stopped");
        }
    }

    private void gameLoop() {
        var fps = 60;
        double timePerTick = (double) 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long timer = 0;
        long now;
        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                delta--;
            }

            if(timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    @Override
    public void tick() {
        inputService.tick();
    }

    public void render() {
        graphicsService.render();
    }
}
