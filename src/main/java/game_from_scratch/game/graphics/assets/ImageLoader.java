package game_from_scratch.game.graphics.assets;

import game_from_scratch.game.logging.GameLogger;
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

    private ImageLoader(){}

    public BufferedImage loadImage(String path) {
        try {
            URL url = Paths.get(path).toUri().toURL();
            return ImageIO.read(url);
        }  catch (IOException exception) {
            LOGGER.log(Level.WARNING, "can't load image from path " + path, exception);
        }
        return null;
    }
}
