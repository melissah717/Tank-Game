package Java.model.Stationary;
//DONE

import java.awt.image.BufferedImage;

public class Floor extends Stationary {

    public Floor(int x, int y, BufferedImage floorImage) {
        super(x, y, floorImage);
    }

    @Override
    public void reset() {
        //none
    }
}
