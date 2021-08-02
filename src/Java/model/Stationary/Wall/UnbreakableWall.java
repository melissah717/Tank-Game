package Java.model.Stationary.Wall;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnbreakableWall extends Wall {

    public UnbreakableWall(int x, int y, BufferedImage wallImage) {
        super(x, y, wallImage);
        this.hitBox = new Rectangle(x, y, this.image.getWidth(), this.image.getHeight());
    }

    @Override
    public void reset() {
        //none
    }
}
