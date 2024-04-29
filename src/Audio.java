
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/** Authors: Batsambuu Batbold, Yeshe Jangchup, & Nadezhda Dominguez Salinas
 * The audio class imports audio and controls the audio aspects of the game.
 * Help From Preceptors: Soulai, Hadley, Courtney
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

    public static void hitEarth() throws UnsupportedAudioFileException,
        IOException, LineUnavailableException {
        List<String> statements = List.of(
            "res/sound/hitting earth/1.wav", "res/sound/hitting earth/2.wav",
            "res/sound/hitting earth/3.wav", "res/sound/hitting earth/4.wav",
            "res/sound/hitting earth/5.wav");
        Random random = new Random();
        int ind = random.nextInt(statements.size());
        new Audio(statements.get(ind));
    }

    public static void hitSun() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        List<String> statements = List.of("res/sound/hitting sun/1.wav", "res/sound/hitting sun/2.wav",
            "res/sound/hitting sun/3.wav", "res/sound/hitting sun/4.wav",
            "res/sound/hitting sun/5.wav");
        Random random = new Random();
        int ind = random.nextInt(statements.size());
        new Audio(statements.get(ind));
    }

    public static void hitSpaceship() throws UnsupportedAudioFileException,
        IOException,
        LineUnavailableException {
        List<String> statements = List.of(
            "res/sound/hit spaceship/1.wav", "res/sound/hit spaceship/2.wav",
            "res/sound/hit spaceship/3.wav", "res/sound/hit spaceship/4.wav",
            "res/sound/hit spaceship/5.wav");
        Random random = new Random();
        int ind = random.nextInt(statements.size());
        new Audio(statements.get(ind));
    }

    public static void flare() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        new Audio("res/sound/flare.wav");
    }

    public static void intro() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        new Audio("res/sound/intro.wav");
    }

    // public static void stopIntro() throws UnsupportedAudioFileException,
    // IOException, LineUnavailableException { Audio intro = new
    // Audio("res/sound/intro.wav"); intro.stop();
    // }
}
