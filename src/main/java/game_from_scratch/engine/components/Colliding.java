package game_from_scratch.engine.components;

public class Colliding extends Component {
    private boolean collisionCheckPassed;

    public Colliding() {
        this.collisionCheckPassed = true;
    }

    public boolean isCollisionCheckPassed() {
        return collisionCheckPassed;
    }

    public void setCollisionCheckPassed(boolean collisionCheckPassed) {
        this.collisionCheckPassed = collisionCheckPassed;
    }

    @Override
    public String toString() {
        return "Colliding{" +
                "collisionCheckPassed=" + collisionCheckPassed +
                ", entity=" + entity +
                '}';
    }
}
