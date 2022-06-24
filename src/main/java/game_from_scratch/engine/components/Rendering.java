package game_from_scratch.engine.components;

public class Rendering extends Component {
    private String imageName;
    private int x;
    private int y;
    private int layer;

    public Rendering(String imageName, int x, int y, int layer) {
        this.imageName = imageName;
        this.x = x;
        this.y = y;
        this.layer = layer;
    }

    public String getImageName() {
        return imageName;
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
