import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;


public class AudioTest {
    public void playSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("res/sound/laser.wav");
        AudioInputStream audioInputStream = AudioSystem
            .getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public static void main(String[] args)
        throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

        AudioTest test = new AudioTest();
        test.playSound();

        Thread.sleep(1000);
    }
}
