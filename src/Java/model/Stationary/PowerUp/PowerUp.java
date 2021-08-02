package Java.model.Stationary.PowerUp;
//DONE

import Java.model.NonStationary.Tank.Tank;
import Java.model.Resource;
import Java.model.Stationary.Stationary;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PowerUp extends Stationary {
    protected boolean used = false;

    public PowerUp(int x, int y, BufferedImage image) {
        super(x, y, image);
        this.hitBox = new Rectangle(x, y, this.image.getWidth(), this.image.getHeight());
    }

    public abstract void applyPowerUp(Tank tank);

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (!used) {
            g2.drawImage(this.image, x, y, null);
        } else if (used) {
            g2.drawImage(Resource.getResourceImage("floor1"), x, y, null);
        }
    }

    @Override
    public void reset() {
        used = false;
        this.hitBox.setRect(x, y, this.image.getWidth(), this.image.getHeight());
    }
}
