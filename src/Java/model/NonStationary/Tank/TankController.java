package Java.model.NonStationary.Tank;

import Java.model.Resource;
import Java.SoundEffects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankController implements KeyListener {
    private final Tank tank;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;

    public TankController(Tank tank, int up, int down, int left, int right, int shoot) {
        this.tank = tank;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
    }

    @Override
    public void keyPressed(KeyEvent key) {
        int keyPressed = key.getKeyCode();
        if (keyPressed == up) {
            this.tank.upPressedOn();
        }
        if (keyPressed == down) {
            this.tank.downPressedOn();
        }
        if (keyPressed == left) {
            this.tank.leftPressedOn();
        }
        if (keyPressed == right) {
            this.tank.rightPressOn();
        }
        if (keyPressed == shoot) {
            SoundEffects.playOnce(Resource.getSoundTrack("ShootSound"));
            this.tank.shootPressedOn();
        }
        if (keyPressed != shoot) {
            SoundEffects.playOnce(Resource.getSoundTrack("EngineSound"));
        }
    }

    @Override
    public void keyReleased(KeyEvent key) {
        int keyReleased = key.getKeyCode();
        if (keyReleased == up) {
            this.tank.upPressedOff();
        }
        if (keyReleased == down) {
            this.tank.downPressOff();
        }
        if (keyReleased == left) {
            this.tank.leftPressOff();
        }
        if (keyReleased == right) {
            this.tank.rightPressOff();
        }
        if (keyReleased == shoot) {
            this.tank.shootPressOff();
        }
    }


    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
