package game_from_scratch.engine.components;

import java.awt.image.BufferedImage;

public class Rendering extends Component {
    private String imageName;
    private BufferedImage image;

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

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Rendering{" +
                "entity=" + entity +
                ", imageName='" + imageName + '\'' +
                ", image=" + image +
                ", layer=" + layer +
                '}';
    }
}
