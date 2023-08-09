package game_from_scratch.engine.assets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssetServiceTest {

    @Test
    void getImage() {
        ImageLoader imageLoader = mock(ImageLoader.class);

        AssetService assetService = new AssetService(imageLoader);
        assetService.getImage("name");

        verify(imageLoader, times(1)).loadImage("src/main/resources/sprites/name.png");
    }
}