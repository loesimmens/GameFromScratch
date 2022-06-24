package game_from_scratch.engine.components;

public class Rendering extends Component {
    private String imageName;

    private int layer;

    public Rendering(String imageName, int layer) {
        this.imageName = imageName;
        this.layer = layer;
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
