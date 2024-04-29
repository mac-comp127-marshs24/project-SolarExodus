package solarexodus;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Authors: Batsambuu Batbold, Yeshe Jangchup, & Nadezhda Dominguez Salinas The audio class imports
 * audio and controls the audio aspects of the game. Help From Preceptors: Soulai, Hadley, Courtney
 */
public class Audio {
    private Clip clip;

    public Audio(String path) throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        File file = new File(path);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public static void laser() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        new Audio("res/sound/laser.wav");
    }

    public static void flare() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        new Audio("res/sound/flare.wav");
    }
}
