import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;


public class AudioTest {
    Clip clip;

    public AudioTest(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(path);
        AudioInputStream audioInputStream = AudioSystem
            .getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        // clip.start();
    }

    public void play() {
        clip.start();
    }

    public static void main(String[] args)
        throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

        AudioTest test = new AudioTest("res/sound/laser.wav");
        test.play();

        Thread.sleep(1000);
    }
}
