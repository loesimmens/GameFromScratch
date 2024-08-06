package game_from_scratch.game;

import game_from_scratch.engine.ECS;
import game_from_scratch.engine.enums.WhoseTurn;
import game_from_scratch.engine.systems.InputSystem;
import game_from_scratch.game.graphics.GraphicsService;
import game_from_scratch.game.logging.GameLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class Game {
    private static final Logger LOGGER = GameLogger.getLogger();
    private static final double ONE_SECOND = 1000000000.0;

    private final int fps;
    private int ticks;
    private double ticksSinceLastTime;
    private long timePassed;

    private final GraphicsService graphicsService;
    private final ECS ecs;

    private boolean running = false;

    public Game(InputSystem inputSystem, @Value("${fps}") int fps, GraphicsService graphicsService, ECS ecs) {
        this.fps = fps;
        this.graphicsService = graphicsService;
        this.ecs = ecs;
        this.graphicsService.getDisplay().getFrame().addKeyListener(inputSystem);
    }

    public void start() {
        if(!running) {
            running = true;
            LOGGER.info("Game has started");

            gameLoop();
        }
    }

    private void gameLoop() {
        double timePerTick = ONE_SECOND / this.fps;
        long lastTime = System.nanoTime();
        long now;
        while(running) {
            now = System.nanoTime();
            long timePassedSinceLastTime = now - lastTime;
            ticksSinceLastTime += (timePassedSinceLastTime) / timePerTick;
            timePassed += timePassedSinceLastTime;
            lastTime = now;

            // for every tick
            if(ticksSinceLastTime >= 1) {
                tickActions();
            }

            if(timePassed >= ONE_SECOND) {
                timePassed = 0;
            }
        }
    }

    private void tickActions() {
        render();
        tick();
        ticks++;
        ticksSinceLastTime--;
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

    public void stop() {
        if(running) {
            running = false;
            LOGGER.info("Game has stopped");
        }
    }
}
