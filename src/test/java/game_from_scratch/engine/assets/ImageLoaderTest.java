package game_from_scratch.engine.assets;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class ImageLoaderTest {

    @Test
    void loadImage() {
        ImageLoader imageLoader = new ImageLoader();
        final String path = "src/main/resources/sprites/ai.png";

        BufferedImage image = imageLoader.loadImage(path);

        assertNotNull(image);
    }
}