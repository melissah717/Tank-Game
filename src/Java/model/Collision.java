package Java.model;
//DONE

import Java.model.NonStationary.Tank.Tank;
import Java.model.Stationary.PowerUp.PowerUp;
import Java.model.Stationary.Wall.BreakableWall;
import Java.model.Stationary.Wall.UnbreakableWall;

import java.util.ArrayList;

public class Collision {
    public static void collide(ArrayList<GameObject> gameObjects, ArrayList<Tank> tanks) {
        for (Tank tank : tanks) {
            for (GameObject other : gameObjects) {
                if (tank.equals(other)) {
                    continue;
                }
                direction(tank, other);
                bulletCollision(tank, other);
                if (other instanceof PowerUp) {
                    powerUpCollision(tank, (PowerUp) other);
                }
            }
        }
    }

    private static void powerUpCollision(Tank tank, PowerUp powerUp) {
        if (tank.getHitBox().intersects(powerUp.getHitBox())) {
            powerUp.applyPowerUp(tank);
        }
    }

    private static void bulletCollision(Tank tank, GameObject other) {
        for (int i = 0; i < tank.getAmmo().size(); i++) {
            if ((tank.getAmmo().get(i).getHitBox().intersects(other.getHitBox()))) {
                if (other instanceof BreakableWall) {
                    ((BreakableWall) other).takeDamage();
                    tank.getAmmo().remove(i--);
                } else if (other instanceof UnbreakableWall) {//pass over powerUps
                    tank.getAmmo().remove(i--);
                } else if (other instanceof Tank) {
                    ((Tank) other).takeDamage(tank.getDamage());
                    tank.getAmmo().remove(i--);
                }
            }
        }
    }

    private static void direction(Tank tank, GameObject other) {
        if (tank.getHitBox().intersects(other.getHitBox()) &&
                !(other instanceof PowerUp)) {
            if (tank.movingForward()) {
                tank.setX(tank.getX() - tank.getVx());
                tank.setY(tank.getY() - tank.getVy());
            } else if (tank.movingBackward()) {
                tank.setX(tank.getX() + tank.getVx());
                tank.setY(tank.getY() + tank.getVy());
            }
        }
    }
}
