package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Moving;
import game_from_scratch.engine.components.Position;
import game_from_scratch.game.logging.GameLogger;
import java.util.logging.Logger;

public abstract class MovingSystem implements System<Moving> {
    protected static final Logger LOGGER = GameLogger.getLogger();

    protected Position position;
}
