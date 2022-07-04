package game_from_scratch.engine.components;

import java.awt.image.BufferedImage;

public class Rendering extends Component {
    private final String imageName;
    private BufferedImage image;
    private final int layer;

    public Rendering(String imageName, int layer) {
        this.imageName = imageName;
        this.layer = layer;
    }

    public String getImageName() {
        return imageName;
    }

    public Integer getLayer() {
        return layer;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Rendering{" +
                ", imageName='" + imageName + '\'' +
                ", image=" + image +
                ", layer=" + layer +
                '}';
    }
}
