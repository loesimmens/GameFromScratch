package game_from_scratch.engine.assets;

import game_from_scratch.game.logging.GameLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ImageLoader {
    private static final Logger LOGGER = GameLogger.getLogger();
    private BufferedImage defaultImage;

    public ImageLoader(@Value("${sprites.path.default.image}") String defaultImageWholePath) {
        try {
            defaultImage = this.loadImage(defaultImageWholePath);
        } catch(IOException exception) {
            LOGGER.log(Level.WARNING, exception, () -> "Default image could not be loaded from path: " + defaultImageWholePath);
        }
    }

    public BufferedImage loadImage(String path) throws IOException {
        final URL url = Paths.get(path).toUri().toURL();
        return ImageIO.read(url);
    }

    public BufferedImage getDefaultImage() {
        return defaultImage;
    }
}
