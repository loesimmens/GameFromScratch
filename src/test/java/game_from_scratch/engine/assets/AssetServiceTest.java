package game_from_scratch.engine.assets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssetServiceTest {

    @Test
    void getImage() {
        ImageLoader imageLoader = mock(ImageLoader.class);
        final String basePath = "src/main/resources/sprites";
        final String name = "name";
        final String wholePath = basePath + "/" + name + ".png";

        AssetService assetService = new AssetService(imageLoader);
        assetService.getImage(basePath, name);

        verify(imageLoader, times(1)).loadImage(wholePath);
    }
}