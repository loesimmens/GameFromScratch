package game.graphics.assets;

import game.logging.GameLogger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageLoader {
    private static final Logger LOGGER = GameLogger.getLogger();

    private ImageLoader(){}

    public static BufferedImage loadImage(String path) {

        try {
            URL url = Paths.get(path).toUri().toURL();
            return ImageIO.read(url);
        }  catch (IOException exception) {
            LOGGER.log(Level.WARNING, "can't load image from path " + path, exception);
        }
        return null;
    }
}
