package Java.view;
//DONE

import Java.model.GameObject;
import Java.model.Resource;
import Java.model.Stationary.Floor;
import Java.model.Stationary.PowerUp.AttackUp;
import Java.model.Stationary.PowerUp.HPUp;
import Java.model.Stationary.PowerUp.LifeUp;
import Java.model.Stationary.Wall.BreakableWall;
import Java.model.Stationary.Wall.UnbreakableWall;
import Java.controller.GameDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class MapLoader {
    public static void gameInitialize(ArrayList<GameObject> gameObjects, ArrayList<Floor> floors) {
        try {
            /*
             * note class loaders read files from the out folder (build folder in Netbeans) and not the
             * current working directory.
             */
            InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(GameDriver.class.getClassLoader().getResourceAsStream("maps/map1.txt")));
            BufferedReader mapReader = new BufferedReader(isr);
            String row = mapReader.readLine();

            if (row == null) throw new IOException("No data in file");

            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for (int curRow = 0; curRow < numRows; curRow++) {
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for (int curCol = 0; curCol < numCols; curCol++) {
                    switch (mapInfo[curCol]) {
                        case "0" -> floors.add(new Floor(curCol * 30, curRow * 30, Resource.getResourceImage("floor1")));
                        case "1" -> floors.add(new Floor(curCol * 30, curRow * 30, Resource.getResourceImage("floor2")));
                        case "3" -> gameObjects.add(new AttackUp(curCol * 30, curRow * 30, Resource.getResourceImage("AttackUp")));
                        case "4" -> gameObjects.add(new LifeUp(curCol * 30, curRow * 30, Resource.getResourceImage("LifeUp")));
                        case "5" -> gameObjects.add(new HPUp(curCol * 30, curRow * 30, Resource.getResourceImage("HPUp")));
                        case "8" -> gameObjects.add(new BreakableWall(curCol * 30, curRow * 30, Resource.getResourceImage("break2")));
                        case "9" -> gameObjects.add(new UnbreakableWall(curCol * 30, curRow * 30, Resource.getResourceImage("unbreak")));
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
