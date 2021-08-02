package Java.model.Stationary.Wall;

import Java.model.Stationary.Stationary;
import java.awt.image.BufferedImage;

public abstract class Wall extends Stationary {
    public Wall(int x, int y, BufferedImage image) {
        super(x, y, image);
    }
}
