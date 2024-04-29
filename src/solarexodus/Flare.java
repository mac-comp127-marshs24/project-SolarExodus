package solarexodus;

import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import edu.macalester.graphics.Image;

/**
 * Authors: Batsambuu Batbold, Yeshe Jangchup, & Nadezhda Dominguez Salinas The flare class
 * represents a flare object that extends Image and is created. Help From Preceptors: Soulai,
 * Hadley, Courtney
 */
public class Flare extends Image {

    private double dx;
    private double dy;
    Random rand = new Random();
    double spaceshipX;
    double spaceshipY;

    /**
     * Constructs and initializes a Flare object and it's image, randomizes its initial velocity, and
     * adds audio to the flare.
     * 
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public Flare() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super(0, 0);
        setImagePath("other/flare.png");
        dx = rand.nextDouble(-2, 2);
        dy = rand.nextDouble(5, 15);
        Audio.flare();
    }

    /**
     * Updates the flare's position depending on its velocity.
     */
    public void updatePosition() {
        moveBy(dx, dy);

    }

    /**
     * Checks if the flare collides with the spaceship.
     * 
     * @param spaceship The spaceship to check the collision with.
     * @return True if the flare collides with the spaceship, or else its false.
     */
    public boolean shipCollision(Spaceship spaceship) {
        double flareX = this.getPosition().getX();
        double flareY = this.getPosition().getY();

        double spaceshipX = spaceship.getX();
        double spaceshipY = spaceship.getY();

        double spaceshipWidth = spaceship.getWidth();
        double spaceshipHeight = spaceship.getHeight();

        if (flareX >= spaceshipX && flareX <= spaceshipX + spaceshipWidth &&
            flareY >= spaceshipY && flareY <= spaceshipY + spaceshipHeight) {
            return true;
        }
        return false;
    }


}
