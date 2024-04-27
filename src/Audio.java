import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
    private Clip clip;

    public Audio(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(path);
        AudioInputStream audioInputStream = AudioSystem
            .getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public static void laser() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new Audio("res/sound/laser.wav");
    }

    public static void hitEarth(){

    }

    public static void hitSun(){

    }

    public static void hitSpaceship(){

    }

    public static void flare(){

    }
}
