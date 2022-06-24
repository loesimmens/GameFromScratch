package game_from_scratch.engine.components;

public class Rendering extends Component {
    private String imageName;
    private int x;
    private int y;
    private int width;
    private int height;

    public Rendering(String imageName, int x, int y, int width, int height) {
        this.imageName = imageName;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
