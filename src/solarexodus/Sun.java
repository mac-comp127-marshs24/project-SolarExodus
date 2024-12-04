package solarexodus;

import edu.macalester.graphics.CanvasWindow;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Authors: Batsambuu Batbold, Yeshe Jangchup, & Nadezhda Dominguez Salinas 
 * The class represents the Sun in the game, this class extends the Planet class. 
 * Help From Preceptors: Soulai, Hadley,Courtney
 */
public class Sun extends Planet {

    /**
     * Constructs a Sun object with specified parameters.
     * 
     * @param size    The size of the Sun.
     * @param radius  The radius of the Sun's orbit.
     * @param speed   The speed of the Sun's movement.
     * @param imgPath The file path to the image of the Sun.
     */
    public Sun(double size, double radius, double speed, String imgPath) {
        super(size, radius, speed, imgPath);
    }

    /**
     * Creates and shoots a solar flare from the Sun's position.
     * 
     * @param canvas The canvas which the flares will be shown on.
     * @return The flare object created.
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public Flare shootFlare(CanvasWindow canvas)
        throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        Flare sunFlare = new Flare();
        sunFlare.setPosition((canvas.getCenter().getX()) - 120, 100);
        sunFlare.setScale(0.15);
        return sunFlare;
    }

    @Override
    public String getType() {
        return "Sun";
    }
}
