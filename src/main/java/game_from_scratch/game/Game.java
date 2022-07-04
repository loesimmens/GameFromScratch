package game_from_scratch.game;

import game_from_scratch.engine.ECS;
import game_from_scratch.engine.systems.InputSystem;
import game_from_scratch.game.graphics.GraphicsService;
import game_from_scratch.game.logging.GameLogger;
import game_from_scratch.game.world.GameMap;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class Game {
    private static final Logger LOGGER = GameLogger.getLogger();

    private final GraphicsService graphicsService;
    private final GameMap gameMap;
    private final ECS ecs;

    private boolean running = false;

    public Game(InputSystem inputSystem, GraphicsService graphicsService, GameMap gameMap, ECS ecs) {
        this.graphicsService = graphicsService;
        this.ecs = ecs;
        this.graphicsService.getDisplay().getFrame().addKeyListener(inputSystem);
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

    public void tick() {
        this.ecs.tickInputSystem();
        this.ecs.tickIntendToMoveSystem();
        this.ecs.tickPositionSystem();
        this.ecs.tickCollisionSystem();
        this.ecs.tickExecuteMoveSystem();
    }

    public void render() {
        this.graphicsService.render();
    }
}
