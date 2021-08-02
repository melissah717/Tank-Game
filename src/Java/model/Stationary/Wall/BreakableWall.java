package Java.model.Stationary.Wall;

import Java.model.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableWall extends Wall {
    public int health = 2; // 2 = full health, 1 = damaged, 0 = broken

    public BreakableWall(int x, int y, BufferedImage wallImage) {
        super(x, y, wallImage);
        this.hitBox = new Rectangle(x, y, this.image.getWidth(), this.image.getHeight());
    }

    public void takeDamage() {
        health--;
    }

    @Override
    public void reset() {
        health = 2;
        this.hitBox.setRect(x, y, this.image.getWidth(), this.image.getHeight());
    }

    @Override
    public void drawImage(Graphics graphics) {
        if (health == 2) {
            graphics.drawImage(this.image, x, y, null);
        } else if (health == 1) {
            graphics.drawImage(Resource.getResourceImage("break1"), x, y, null);
        } else {
            graphics.drawImage(Resource.getResourceImage("break0"), x, y, null);
            deleteHitBox();
        }
    }
}
