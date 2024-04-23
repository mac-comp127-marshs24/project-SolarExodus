
import java.awt.Color;

import edu.macalester.graphics.*;

public class Planet {
    private double posX;
    private double posY;
    private double size, angle, speed, radius;
    private Image planet;
    private Ellipse bound;
    private GraphicsGroup group;

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
        bound.setCenter(posX, posY);
        angle = angle % 360 + speed;
    }

    public void reflect(Laser laser) {
        laser.changeDirection(this);
    }

    public void hit(Game game) {
    }

    public String getType() {
        return "Planet";
    }

    public void shrink() {
        planet.setScale(0.9);
        bound.setScale(0.9);
    }

    public Point getCenter() {
        return bound.getCenter();
    }

    public int checkLaser(Laser laser) {
        double laserX1 = laser.getX1();
        double laserY1 = laser.getY1();
        double laserX2 = laser.getX2();
        double laserY2 = laser.getY2();

        double planetXCord = bound.getCenter().getX();
        double planetYCord = bound.getCenter().getY();

        double distanceX1Y1 = Math.sqrt(Math.pow(planetXCord - laserX1, 2) + Math.pow(planetYCord - laserY1, 2));
        double distanceX2Y2 = Math.sqrt(Math.pow(planetXCord - laserX2, 2) + Math.pow(planetYCord - laserY2, 2));
        if (distanceX1Y1 <= size / 2) {
            return 1;
        } else if (distanceX2Y2 <= size / 2) {
            return 2;
        }
        return 0;
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(group);
    }
}
