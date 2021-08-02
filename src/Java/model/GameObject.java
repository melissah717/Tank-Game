package Java.model;
//DONE

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected int x, y;
    protected Rectangle hitBox;
    protected BufferedImage image;

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    protected void deleteHitBox() {
        this.hitBox.setRect(0, 0, 0, 0);
    }

    public abstract void reset();

    public abstract void drawImage(Graphics g);

    public abstract void update();
}
