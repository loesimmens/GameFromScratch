package game_from_scratch.engine.assets;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssetServiceTest {

    @Test
    void getImage() throws IOException {
        ImageLoader imageLoader = mock(ImageLoader.class);
        final BufferedImage expectedImage = new BufferedImage(100, 100, 1);
        when(imageLoader.loadImage(anyString())).thenReturn(expectedImage);
        final AssetService assetService = new AssetService(imageLoader);
        final String basePath = "src/main/resources/sprites";
        final String name = "not_found";
        final String wholePath = basePath + "/" + name + ".png";

        final BufferedImage image = assetService.getImage(basePath, name);

        verify(imageLoader, times(1)).loadImage(wholePath);
        assertNotNull(image);
        assertEquals(image, expectedImage);
    }

    @Test
    void getImageInvalidPath() throws IOException {
        ImageLoader imageLoader = mock(ImageLoader.class);
        when(imageLoader.loadImage(anyString())).thenReturn(null);
        final String basePath = "src/main/resources/sprites";
        final String name = "name";
        final String wholePath = basePath + "/" + name + ".png";

        AssetService assetService = new AssetService(imageLoader);
        final BufferedImage image = assetService.getImage(basePath, name);

        verify(imageLoader, times(1)).loadImage(wholePath);
        assertNull(image);
    }
}