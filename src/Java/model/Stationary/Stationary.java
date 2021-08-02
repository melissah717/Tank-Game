package Java.model.Stationary;
//DONE

import Java.model.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Stationary extends GameObject {

    public Stationary(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    @Override
    public void update() {
        //none
    }

    @Override
    public void drawImage(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(this.image, x, y, null);
    }
}
