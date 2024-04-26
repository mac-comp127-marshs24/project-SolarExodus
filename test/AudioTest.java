import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AudioTest {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File soundFile = new File("sound//laser.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile.getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

}
