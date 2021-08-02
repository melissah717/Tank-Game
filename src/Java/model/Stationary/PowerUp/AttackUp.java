package Java.model.Stationary.PowerUp;
//DONE

import Java.model.NonStationary.Tank.Tank;

import java.awt.image.BufferedImage;

public class AttackUp extends PowerUp {

    public AttackUp(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    @Override
    public void applyPowerUp(Tank tank) {
        tank.incrementDamage();
        used = true;
        this.deleteHitBox();
    }
}
