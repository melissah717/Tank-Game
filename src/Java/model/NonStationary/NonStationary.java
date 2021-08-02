package Java.model.NonStationary;

import Java.model.GameObject;


public abstract class NonStationary extends GameObject {
    protected int vx, vy, R;
    protected float angle;

    protected void movement(int direction) {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += (direction) * vx;
        y += (direction) * vy;
        moveHitBox();
    }

    protected void moveHitBox() {
        this.hitBox.setLocation(x, y);
    }

}
