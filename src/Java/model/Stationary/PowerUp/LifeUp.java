package Java.model.Stationary.PowerUp;
//DONE

import Java.model.NonStationary.Tank.Tank;

import java.awt.image.BufferedImage;

public class LifeUp extends PowerUp {

    public LifeUp(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    @Override
    public void applyPowerUp(Tank tank) {
        tank.incrementLife();
        this.used = true;
        this.deleteHitBox();
    }
}
