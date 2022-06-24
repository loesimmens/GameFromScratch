package game_from_scratch.game.graphics.assets;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class AssetService {
    private static final String SPRITE_SHEET_PATH = "src/main/resources/sprite_sheets";

    private final ImageLoader imageLoader;

    public AssetService(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public BufferedImage getImage(String name) {
        return this.imageLoader.loadImage(SPRITE_SHEET_PATH + "/" + name + ".png");
    }
}

