package Java.model.NonStationary.Tank;
//DONE

import Java.model.NonStationary.NonStationary;
import Java.model.Resource;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Tank extends NonStationary {
    private int life;
    private int health;
    private int damage;
    private final int defaultDamage = 1;
    private final int defaultX;
    private final int defaultY;
    private final int defaultVx = 0;
    private final int defaultVy = 0;
    private final int defaultSpeed = 2;
    private final float defaultAngle;
    private final int defaultLives = 3;
    private final int defaultHealth = 5;
    private final float ROTATIONSPEED = 4;
    private int cooldown = 50;

    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    private final ArrayList<Shell> ammo;

    public Tank(int x, int y, float angle, BufferedImage image) {
        this.damage = defaultDamage;
        this.x = x;
        this.defaultX = x;
        this.y = y;
        this.defaultY = y;
        this.vx = defaultVx;
        this.vy = defaultVy;
        this.image = image;
        this.angle = angle;
        this.defaultAngle = this.angle;
        this.R = defaultSpeed;
        this.life = defaultLives;
        this.health = defaultHealth;
        this.ammo = new ArrayList<>();
        this.hitBox = new Rectangle(x, y, this.image.getWidth(), this.image.getHeight());
    }

    private void rotate(int direction) {
        this.angle += (direction * this.ROTATIONSPEED);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public int getDamage() {
        return this.damage;
    }

    public void incrementDamage() {
        this.damage += 2;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public int getLife() {
        return this.life;
    }

    public void incrementLife() {
        this.life++;
    }

    public boolean movingForward() {
        return UpPressed;
    }

    public boolean movingBackward() {
        return DownPressed;
    }

    public void upPressedOn() {
        this.UpPressed = true;
    }

    public void downPressedOn() {
        this.DownPressed = true;
    }

    public void rightPressOn() {
        this.RightPressed = true;
    }

    public void leftPressedOn() {
        this.LeftPressed = true;
    }

    public void shootPressedOn() {
        this.ShootPressed = true;
    }

    public void upPressedOff() {
        this.UpPressed = false;
    }

    public void downPressOff() {
        this.DownPressed = false;
    }

    public void rightPressOff() {
        this.RightPressed = false;
    }

    public void leftPressOff() {
        this.LeftPressed = false;
    }

    public void shootPressOff() {
        this.ShootPressed = false;
    }

    public ArrayList<Shell> getAmmo() {
        return this.ammo;
    }

    private int getShellX() {
        return getX() + this.image.getWidth();
    }

    private int getShellY() {
        return getY() + this.image.getHeight() / 2;
    }

    private void decrementCooldown() {
        cooldown--;
    }

    public void update() {
        if (this.UpPressed) {
            this.movement(1);
        }
        if (this.DownPressed) {
            this.movement(-1);
        }
        if (this.LeftPressed) {
            this.rotate(-1);
        }
        if (this.RightPressed) {
            this.rotate(1);
        }
        if (this.ShootPressed && cooldown % 40 == 0) {
            Shell b = new Shell(getShellX(), getShellY(), angle, Resource.getResourceImage("bullet"));
            this.ammo.add(b);
        }
        //writes and reads separate.
        this.ammo.forEach(Shell::update);

        if (this.life >= 0 && this.health <= 0) {
            respawnTanks();
        }
        decrementCooldown();
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.image.getWidth() / 2.0, this.image.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, rotation, null);
        try {
            this.ammo.forEach(shell -> shell.drawImage(g));
        } catch (ConcurrentModificationException e) {
            System.out.println(e);
        }
    }

    @Override
    public void reset() {
        softReset();
        this.life = defaultLives;
    }

    private void respawnTanks() {
        softReset();
        this.life--;
    }

    private void softReset() {
        this.setX(this.defaultX);
        this.setY(this.defaultY);
        this.setVx(this.defaultVx);
        this.setVy(this.defaultVy);
        this.damage = defaultDamage;
        this.angle = this.defaultAngle;
        this.R = this.defaultSpeed;
        this.health = defaultHealth;
        this.ammo.clear();
        this.hitBox.setLocation(this.defaultX, this.defaultY);
    }
}