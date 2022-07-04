package game_from_scratch.engine.enums;

import java.util.Random;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    private static final Random RANDOM = new Random();

    public static Direction getRandom() {
        int randomInt = RANDOM.nextInt(4);
        return values()[randomInt];
    }
}
