package game_from_scratch.engine.assets;

import game_from_scratch.game.logging.GameLogger;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AssetService {
    private static final Logger LOGGER = GameLogger.getLogger();

    private final ImageLoader imageLoader;

    public AssetService(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public BufferedImage getImage(String path, String name) {
        BufferedImage image;
        final String wholePath = path + "/" + name + ".png";
        try {
            image = this.imageLoader.loadImage(wholePath);
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, exception, () -> wholePath + " does not contain a valid image");
            image = null;
        }
        return image;
    }
}

