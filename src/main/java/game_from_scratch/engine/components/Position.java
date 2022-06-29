package game_from_scratch.engine.components;

public class Position extends Component{
    private int x;
    private int y;

    //todo check of positie binnen gamemap past
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updateX(int intendedMoveX) {
        this.x += intendedMoveX;
    }

    public void updateY(int intendedMoveY) {
        this.y += intendedMoveY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "entity=" + entity +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
