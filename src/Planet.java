import java.util.ArrayList;

import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;

public class Planet {
    private double posX;
    private double posY;
    private double size, angle, speed, radius;
    private Image planet;

    public Planet(double size, double radius, double speed, String imgPath) {
        planet = new Image(imgPath);
        planet.setMaxHeight(size);
        planet.setMaxWidth(size);

        this.size = size;
        this.angle = 0;
        this.radius = radius;
        this.speed = speed;
    }

    public void move(CanvasWindow canvas) {
        double angleRad = Math.toRadians(angle);
        double xCoor = radius * Math.cos(angleRad);
        double yCoor = radius * Math.sin(angleRad);
        posX = canvas.getWidth() * 0.5 + xCoor;
        posY = canvas.getHeight() * 0.25 + yCoor;
        planet.setCenter(posX, posY);
        angle = angle % 360 + speed;
    }

    public void reflect(Laser laser) {
        laser.changeDirection();
    }

    public void hit(Game game) {
    }

    public String getType() {
        return "Planet";
    }

    public boolean checkLaser(Laser laser) {
        double laserX1 = laser.getX1();
        double laserY1 = laser.getY1();
        double laserX2 = laser.getX2();
        double laserY2 = laser.getY2();

        double planetXCord = planet.getCenter().getX();
        double planetYCord = planet.getCenter().getY();
        double planetBound = planet.getHeight() * 0.5;

        double distanceX1Y1 = Math.sqrt(Math.pow(planetXCord - laserX1, 2) + Math.pow(planetYCord - laserY1, 2));
        double distanceX2Y2 = Math.sqrt(Math.pow(planetXCord - laserX2, 2) + Math.pow(planetYCord - laserY2, 2));
        if (distanceX1Y1 <= planetBound && distanceX2Y2 <= planetBound) {
            return true;
        }
        return false;
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(planet);
    }
}
