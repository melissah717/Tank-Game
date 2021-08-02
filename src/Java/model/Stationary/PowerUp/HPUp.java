package Java.model.Stationary.PowerUp;
//DONE

import Java.model.NonStationary.Tank.Tank;

import java.awt.image.BufferedImage;

public class HPUp extends PowerUp {

    public HPUp(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    @Override
    public void applyPowerUp(Tank tank) {
        tank.setHealth(tank.getHealth() + 3);
        used = true;
        this.deleteHitBox();
    }
}
