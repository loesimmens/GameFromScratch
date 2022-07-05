package game_from_scratch.game;

import game_from_scratch.engine.ECS;
import game_from_scratch.engine.enums.WhoseTurn;
import game_from_scratch.engine.systems.InputSystem;
import game_from_scratch.game.graphics.GraphicsService;
import game_from_scratch.game.logging.GameLogger;
import game_from_scratch.game.world.GameMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class Game {
    private static final Logger LOGGER = GameLogger.getLogger();

    private final int fps;
    private int ticks;
    private final GraphicsService graphicsService;
    private final GameMap gameMap;
    private final ECS ecs;

    private boolean running = false;

    public Game(InputSystem inputSystem, @Value("${fps}") int fps, GraphicsService graphicsService, GameMap gameMap, ECS ecs) {
        this.fps = fps;
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
        double timePerTick = (double) 1000000000 / this.fps;
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
                render();
                tick();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void tick() {
        WhoseTurn whoseTurn = this.ecs.getTurnSystem().getWhoseTurn();
        if(whoseTurn == WhoseTurn.AI) {
            if(this.ticks % 10 == 0) {
                this.ecs.tickAISystem();
            }
        } else {
            this.ecs.tickInputSystem();
        }
        this.ecs.tickIntendToMoveSystem();
        this.ecs.tickPositionSystem();
        this.ecs.tickCollisionSystem();
        this.ecs.tickExecuteMoveSystem();
        this.ecs.tickTurnSystem();
    }

    public void render() {
        this.graphicsService.render();
    }
}
