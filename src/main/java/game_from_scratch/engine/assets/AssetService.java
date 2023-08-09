package game_from_scratch.engine.assets;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class AssetService {
    private final ImageLoader imageLoader;

    public AssetService(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public BufferedImage getImage(String path, String name) {
        return this.imageLoader.loadImage(path + "/" + name + ".png");
    }
}

