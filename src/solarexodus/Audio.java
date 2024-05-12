package solarexodus;

import java.io.File;
import java.io.IOException;
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

    /**
     *
     * @param path : The path to the audio file
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public Audio(String path) throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        File file = new File(path);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    /**
     * Stops the sound
     */
    public void stop() {
        clip.stop();
    }

    /**
     * Plays the laser sound once
     *
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public static void laser() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        new Audio("res/sound/laser.wav");
    }

    /**
     * Plays the flare sound once
     *
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public static void flare() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        new Audio("res/sound/flare.wav");
    }

    public static void loseLife() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        new Audio("res/sound/lose life.wav");
    }
}
