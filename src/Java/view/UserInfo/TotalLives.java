package Java.view.UserInfo;
//DONE

import Java.GameConstants;
import Java.model.NonStationary.Tank.Tank;
import Java.model.Resource;

import java.awt.*;
import java.util.ArrayList;

public class TotalLives extends UserInfo {
    @Override
    public void drawImage(Graphics2D g2, ArrayList<Tank> tanks) {
        int tank1lives = tanks.get(0).getLife();
        for (int i = 0; i < tank1lives; i++) {
            g2.drawImage(Resource.getResourceImage("SimbaLife"), 10 * i, GameConstants.GAME_SCREEN_HEIGHT + 15, null);
        }
        int tank2lives = tanks.get(1).getLife();
        for (int j = 0; j < tank2lives; j++) {
            g2.drawImage(Resource.getResourceImage("NyanLife"), GameConstants.GAME_SCREEN_WIDTH / 2 + 10 * j, GameConstants.GAME_SCREEN_HEIGHT + 15, null);
        }
    }
}
