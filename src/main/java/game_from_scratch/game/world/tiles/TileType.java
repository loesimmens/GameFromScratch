package game_from_scratch.game.world.tiles;

public enum TileType {
    WALL(0),
    FLOOR(1),
    DOOR(2),
    EMPTY(3),
    LADDER(4);

    private final int value;

    TileType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isSolid() {
        switch(this.value) {
            case 0: return true;
            case 1:
            case 2:
            case 3:
            case 4:
            default: return false;
        }
    }
}
