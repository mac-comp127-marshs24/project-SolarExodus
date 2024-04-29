package solarexodus;

import edu.macalester.graphics.*;
import java.awt.Color;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/** Authors: Batsambuu Batbold, Yeshe Jangchup, & Nadezhda Dominguez Salinas
 * The planet class represents a planet in the solar system and its shape and movement.
 * Help From Preceptors: Soulai, Hadley, Courtney
 */
public class Planet {
    private double posX;
    private double posY;
    private double size, angle, speed, radius;
    private Image planet;
    private Ellipse bound;
    private GraphicsGroup group;
    private double scale;
    private double initialSize;

    /**
     * Constructs a Planet object using the following parameters.
     * 
     * @param size The size of the planet.
     * @param radius The radius of the orbit.
     * @param speed The speed of the planet's movement.
     * @param imgPath The image's file path of the planet.
     */
    public Planet(double size, double radius, double speed, String imgPath) {
        group = new GraphicsGroup();

        planet = new Image(imgPath);
        planet.setMaxHeight(size);
        planet.setMaxWidth(size);

        bound = new Ellipse(0, 0, size, size);
        bound.setStrokeColor(Color.RED);
        bound.setStrokeWidth(1);
        bound.setCenter(planet.getCenter());

        group.add(planet);
        group.add(bound);

        this.size = size;
        initialSize = size;
        this.angle = 0;
        this.radius = radius;
        this.speed = speed;

        scale = 1;
    }

    /**
     * Moves the planet on the created orbit.
     * 
     * @param canvas The canvas where the planet is shown.
     */
    public void move(CanvasWindow canvas) {
        double angleRad = Math.toRadians(angle);
        double xCoor = radius * Math.cos(angleRad);
        double yCoor = radius * Math.sin(angleRad);
        posX = canvas.getWidth() * 0.5 + xCoor;
        posY = canvas.getHeight() * 0.25 + yCoor;
        planet.setCenter(posX, posY);
        bound.setCenter(posX, posY);
        angle = angle % 360 + speed;
    }

    /**
     * Reflects a laser off the planet's surface.
     * 
     * @param laser The laser object that will be reflected.
     */
    public void reflect(Laser laser) {
        laser.changeDirection(this);
    }

    /**
     * Shrinks the planet's size according to scale.
     * 
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public void shrink() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        scale -= 0.1;
        planet.setScale(scale);
        bound.setScale(scale);
        size -= initialSize / 10;
        Audio.hitSun();
    }

    /**
     * Returns the type of the planet.
     */
    public String getType() {
        return "Planet";
    }

    /**
     * Returns the center point of the planet's bounding ellipse.
     */
    public Point getCenter() {
        return bound.getCenter();
    }

    /**
     * Checks if a laser intersects with the planet.
     * 
     * @param laser The laser to check.
     * @return True if the laser intersects with the planet, or else false.
     */
    public boolean checkLaser(Laser laser) {
        double laserX1 = laser.getX1();
        double laserY1 = laser.getY1();
        double laserX2 = laser.getX2();
        double laserY2 = laser.getY2();

        double planetXCord = bound.getCenter().getX();
        double planetYCord = bound.getCenter().getY();

        double distanceX1Y1 = Math.sqrt(Math.pow(planetXCord - laserX1, 2) +
            Math.pow(planetYCord - laserY1, 2));
        double distanceX2Y2 = Math.sqrt(Math.pow(planetXCord - laserX2, 2) +
            Math.pow(planetYCord - laserY2, 2));
        if (distanceX1Y1 <= size / 2 || distanceX2Y2 <= size / 2) {
            return true;
        }
        return false;
    }

    /**
     * Adds the planet to the specified canvas. 
     * 
     * @param canvas The canvas where the planet will be shown.
     */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(group);
    }
}
