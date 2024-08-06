package game_from_scratch.engine.assets;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageLoaderTest {

    @Test
    void loadImage() throws IOException {
        ImageLoader imageLoader = new ImageLoader("src/main/resources/sprites/not_found.png");
        final String path = "src/main/resources/sprites/ai.png";

        BufferedImage image = imageLoader.loadImage(path);

        assertNotNull(image);
    }

    @Test
    void loadImageThrowsIOException() throws IOException {
        final String notFoundPath = "src/main/resources/sprites/not_found.png";
        ImageLoader imageLoader = new ImageLoader(notFoundPath);
        final String path = "src/main/resources/sprites/BLABLA.png";

        assertThrows(IOException.class, () -> imageLoader.loadImage(path));
    }
}