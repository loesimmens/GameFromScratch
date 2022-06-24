package game_from_scratch.engine.components;

public class Rendering extends Component {
    private String imageName;

    private Position position;

    private int layer;

    public Rendering(String imageName, Position position, int layer) {
        this.imageName = imageName;
        this.position = position;
        this.layer = layer;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }
}
