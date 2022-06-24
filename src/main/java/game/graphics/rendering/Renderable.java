package game.graphics.rendering;

import java.awt.*;

public interface Renderable {
    void render(Graphics graphics, int x, int y, int width, int height);
}
