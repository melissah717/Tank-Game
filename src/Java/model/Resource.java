package Java.model;
//DONE

import Java.controller.GameDriver;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Resource {
    private static Map<String, BufferedImage> resources;
    private static Map<String, String> sounds;

    static {
        Resource.resources = new HashMap<>();
        Resource.sounds = new HashMap<>();
        try {
            Resource.resources.put("tank1", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("simbaCat" + ".png"))));
            Resource.resources.put("tank2", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("nyanCat" + ".png"))));
            Resource.resources.put("bullet", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("shell" + ".png"))));
            Resource.resources.put("break2", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("wall-1" + ".png"))));
            Resource.resources.put("break1", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("breakableWall" + ".png"))));
            Resource.resources.put("break0", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("tile1" + ".png"))));
            Resource.resources.put("unbreak", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("unbreakableWall" + ".png"))));
            Resource.resources.put("floor1", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("tile1" + ".png"))));
            Resource.resources.put("floor2", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("tile2" + ".png"))));
            Resource.resources.put("LifeUp", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("powerup1" + ".png"))));
            Resource.resources.put("HPUp", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("powerup2" + ".png"))));
            Resource.resources.put("AttackUp", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("powerup3" + ".png"))));
            Resource.resources.put("LightShell", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("Light_Shell" + ".png"))));
            Resource.resources.put("HealthBar", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("healthBar" + ".png"))));
            Resource.resources.put("SimbaLife", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("SimbaLife" + ".png"))));
            Resource.resources.put("NyanLife", read(Objects.requireNonNull(GameDriver.class.getClassLoader().getResource("NyanLife" + ".png"))));

            Resource.sounds.put("EngineSound", "Tank_Engine" + ".wav");
            Resource.sounds.put("ShootSound", "Tank_Firing" + ".wav");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-5);
        }
    }

    public static BufferedImage getResourceImage(String key) {
        return Resource.resources.get(key);
    }

    public static String getSoundTrack(String key) {
        return Resource.sounds.get(key);
    }
}