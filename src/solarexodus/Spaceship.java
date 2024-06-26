package solarexodus;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.events.Key;

/** Authors: Batsambuu Batbold, Yeshe Jangchup, & Nadezhda Dominguez Salinas
 * This class represents the spaceship in the game.
 * Help From Preceptors: Soulai, Hadley, Courtney
 */
public class Spaceship {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private Image spaceship;

    /**
     * Constructs a new spaceship object.
     */
    public Spaceship() {
        spaceship = new Image(375, 700);
        spaceship.setImagePath("other/spaceship1.png");
        spaceship.setMaxWidth(WIDTH);
        spaceship.setMaxHeight(HEIGHT);
    }

    /**
     * Moves the spaceship based on the mouse movement and/or keyboard trigger.
     * 
     * @param canvas The canvas the spaceship is displayed on.
     */
    public void moveShip(CanvasWindow canvas) {
        canvas.onMouseMove(event -> {
            double mouseX = event.getPosition().getX();
            double spaceshipX = mouseX - WIDTH / 2;
            if (spaceshipX < 0) {
                spaceshipX = 0;
            } else if (spaceshipX + WIDTH > canvas.getWidth()) {
                spaceshipX = canvas.getWidth() - WIDTH;
            }
            spaceship.setPosition(spaceshipX, spaceship.getY());
        });

        canvas.onKeyDown(event -> {
            double spaceshipX = spaceship.getX();
            if (spaceshipX < 0) {
                spaceshipX = 0;
            } else if (spaceshipX + WIDTH > canvas.getWidth()) {
                spaceshipX = canvas.getWidth() - WIDTH;
            }
            spaceship.setPosition(spaceshipX, spaceship.getY());

            if (event.getKey() == Key.LEFT_ARROW) {
                spaceship.moveBy(-5, 0);
            } else if (event.getKey() == Key.RIGHT_ARROW) {
                spaceship.moveBy(5, 0);
            }
        });
    }

    /**
     * Adds the spaceship to the specified canvas.
     */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(spaceship);
    }

    /**
     * Returns the x-coordinate of the spaceship.
     */
    public double getX() {
        return spaceship.getX();
    }

    /**
     * Returns the y-coordinate of the spaceship.
     */
    public double getY() {
        return spaceship.getY();
    }

    /**
     * Returns the center point of the spaceship.
     */
    public Point getCenter() {
        return spaceship.getCenter();
    }

    /**
     * Returns the width of the spaceship.
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * Returns the height of the spaceship.
     */
    public int getHeight() {
        return HEIGHT;
    }
}
