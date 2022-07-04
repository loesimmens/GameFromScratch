package game_from_scratch.engine.components;

public class AI extends Component {
    private int speed;

    public AI(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "AI{" +
                "speed=" + speed +
                '}';
    }
}
