package game_from_scratch.engine.components;

public class Colliding extends Component {
    private boolean collisionShouldBeChecked;
    private boolean collisionCheckPassed;

    public boolean isCollisionCheckPassed() {
        return collisionCheckPassed;
    }

    public void setCollisionCheckPassed(boolean collisionCheckPassed) {
        this.collisionCheckPassed = collisionCheckPassed;
    }

    public boolean collisionShouldBeChecked() {
        return collisionShouldBeChecked;
    }

    public void setCollisionShouldBeChecked(boolean collisionShouldBeChecked) {
        this.collisionShouldBeChecked = collisionShouldBeChecked;
    }

    @Override
    public String toString() {
        return "Colliding{" +
                "collisionShouldBeChecked=" + collisionShouldBeChecked +
                ", collisionCheckPassed=" + collisionCheckPassed +
                '}';
    }
}
