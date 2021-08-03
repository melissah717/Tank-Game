package Java.controller;
//TODO

import Java.GameConstants;
import Java.model.Collision;
import Java.model.GameObject;
import Java.model.NonStationary.Tank.*;
import Java.model.Resource;
import Java.model.Stationary.Floor;
import Java.view.UserInfo.HealthBar;
import Java.view.UserInfo.TotalLives;
import Java.Launcher;
import Java.view.MapLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author anthony-pc
 */
public class GameDriver extends JPanel implements Runnable {
    private BufferedImage world;
    private Graphics2D buffer;
    private final Launcher launcher;
    ArrayList<GameObject> gameObjects;
    ArrayList<Tank> tanks;
    ArrayList<Floor> floors;

    public GameDriver(Launcher launcher) {
        this.launcher = launcher;
    }

    /**
     * Load all resources for Tank Wars Game. Set all Game Objects to their
     * initial state as well.
     */
    public void gameInitialize() {
        this.world = new BufferedImage(GameConstants.GAME_WORLD_WIDTH,
                GameConstants.GAME_WORLD_HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        this.floors = new ArrayList<>();
        this.gameObjects = new ArrayList<>();
        this.tanks = new ArrayList<>();



        MapLoader.gameInitialize(gameObjects, floors);

        Tank t1 = new Tank(120, 160, (short) 0, Resource.getResourceImage("tank1"));
        Tank t2 = new Tank(GameConstants.GAME_WORLD_WIDTH - 120, GameConstants.GAME_WORLD_HEIGHT - 160, (short) 180, Resource.getResourceImage("tank2"));

        TankController tc1 = new TankController(
                t1,
                KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_A,
                KeyEvent.VK_D,
                KeyEvent.VK_SPACE);
        TankController tc2 = new TankController(
                t2,
                KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_CONTROL);


        this.gameObjects.add(t1);
        this.gameObjects.add(t2);

        this.tanks.add(t1);
        this.tanks.add(t2);


        this.launcher.getjFrame().addKeyListener(tc1);
        this.launcher.getjFrame().addKeyListener(tc2);
    }

    @Override
    public void run() {
        try {
            this.resetGame();
            while (true) {
                this.gameObjects.forEach(GameObject::update);
                this.repaint();

                Collision.collide(gameObjects, tanks);

                Thread.sleep(1000 / 144);
                for (GameObject gameObject : gameObjects) {
                    if (gameObject instanceof Tank && ((Tank) gameObject).getLife() == 0) {
                        this.launcher.setFrame("end");
                        return;
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    /**
     * Reset game to its initial state.
     */
    public void resetGame() {
        for (GameObject gameObjects : gameObjects) {
            gameObjects.reset();
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        TotalLives totalLives = new TotalLives();
        HealthBar healthBar = new HealthBar();
        super.paintComponent(g2);
        buffer = world.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, GameConstants.GAME_WORLD_WIDTH, GameConstants.GAME_WORLD_HEIGHT - 100);
        this.floors.forEach(floor -> floor.drawImage(buffer));
        this.gameObjects.forEach(gameObject -> gameObject.drawImage(buffer));

        ArrayList<Tank> tanks = new ArrayList<>();
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Tank) {
                tanks.add((Tank) gameObject);
                gameObject.drawImage(buffer);
            }
        }

        totalLives.drawImage(g2, tanks);
        healthBar.drawImage(g2, tanks);

        BufferedImage leftHalf = world.getSubimage(
                (tanks.get(0).getX() - GameConstants.GAME_SCREEN_WIDTH / 4 > 0) ?
                        Math.min(tanks.get(0).getX() - GameConstants.GAME_SCREEN_WIDTH / 4, GameConstants.GAME_WORLD_WIDTH - (GameConstants.GAME_SCREEN_WIDTH / 2)) :
                        Math.max(tanks.get(0).getX() - GameConstants.GAME_SCREEN_WIDTH / 4, 0),
                (tanks.get(0).getY() - GameConstants.GAME_SCREEN_HEIGHT / 2 > 0) ?
                        Math.min(tanks.get(0).getY() - GameConstants.GAME_SCREEN_HEIGHT / 2, GameConstants.GAME_WORLD_HEIGHT - GameConstants.GAME_SCREEN_HEIGHT) :
                        Math.max(tanks.get(0).getY() - (GameConstants.GAME_SCREEN_HEIGHT / 2), 0),
                GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT);

        BufferedImage rightHalf = world.getSubimage(
                (tanks.get(1).getX() - GameConstants.GAME_SCREEN_WIDTH / 4 > 0) ?
                        Math.min(tanks.get(1).getX() - GameConstants.GAME_SCREEN_WIDTH / 4, GameConstants.GAME_WORLD_WIDTH - (GameConstants.GAME_SCREEN_WIDTH / 2)) :
                        Math.max(tanks.get(1).getX() - GameConstants.GAME_SCREEN_WIDTH / 4, 0),
                (tanks.get(1).getY() - GameConstants.GAME_SCREEN_HEIGHT / 2 > 0) ?
                        Math.min(tanks.get(1).getY() - GameConstants.GAME_SCREEN_HEIGHT / 2, GameConstants.GAME_WORLD_HEIGHT - GameConstants.GAME_SCREEN_HEIGHT) :
                        Math.max(tanks.get(1).getY() - (GameConstants.GAME_SCREEN_HEIGHT / 2), 0),
                GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT);


        Image minimap = world.getSubimage(0, 0, GameConstants.GAME_WORLD_WIDTH, GameConstants.GAME_WORLD_HEIGHT).getScaledInstance(100, 100, 0);
        g2.drawImage(leftHalf, 0, 0, null);
        g2.drawImage(rightHalf, GameConstants.GAME_SCREEN_WIDTH / 2 + 5, 0, null);
        g2.drawImage(minimap, GameConstants.GAME_SCREEN_WIDTH - 200, 50, null);

    }
}
