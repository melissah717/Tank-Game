package Java.model.NonStationary.Tank;
//DONE

import Java.model.NonStationary.NonStationary;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Shell extends NonStationary {
    public Shell(int x, int y, float angle, BufferedImage shellImage) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.image = shellImage;
        this.R = 6;
        this.hitBox = new Rectangle(x, y, this.image.getWidth(), this.image.getHeight());
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.image.getWidth() / 2.0, this.image.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, rotation, null);
        g2d.setColor(Color.BLACK);
    }


    public void update() {
        movement(1);
    }

    @Override
    public void reset() {
        //none
    }

}
