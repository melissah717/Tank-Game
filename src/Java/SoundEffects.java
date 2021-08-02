package Java;
//DONE

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class SoundEffects {
    SoundEffects() {
    }

    public static void playOnce(String fileName) {
        URL sound = SoundEffects.class.getClassLoader().getResource(fileName);
        try {
            Clip clip = AudioSystem.getClip();
            assert sound != null;
            clip.open(AudioSystem.getAudioInputStream(sound));
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-50.0f);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
