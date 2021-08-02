package Java.view.UserInfo;
//DONE

import Java.GameConstants;
import Java.model.NonStationary.Tank.Tank;
import Java.model.Resource;

import java.awt.*;
import java.util.ArrayList;

public class HealthBar extends UserInfo {
    @Override
    public void drawImage(Graphics2D g2, ArrayList<Tank> tanks) {
        int tank1health = tanks.get(0).getHealth();
        for (int j = 0; j < tank1health; j++) {
            g2.drawImage(Resource.getResourceImage("HealthBar"), 20 * j + GameConstants.GAME_SCREEN_WIDTH / 8, GameConstants.GAME_SCREEN_HEIGHT + 15, null);
        }
        int tank2health = tanks.get(1).getHealth();
        for (int j = 0; j < tank2health; j++) {
            g2.drawImage(Resource.getResourceImage("HealthBar"), GameConstants.GAME_SCREEN_WIDTH / 2 + 20 * j + GameConstants.GAME_SCREEN_WIDTH / 8, GameConstants.GAME_SCREEN_HEIGHT + 15, null);
        }
    }
}
