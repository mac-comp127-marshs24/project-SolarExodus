import java.util.ArrayList;

import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;

public class PlanetImage {
    private double posX;
    private double posY;
    private double angle, speed, radius;
    private Image planet;

    public PlanetImage(double size, double radius, double speed, String imgPath) {
        planet = new Image(imgPath);
        planet.setMaxHeight(size);
        planet.setMaxWidth(size);

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

    public boolean checkLaser(Laser laser){
        double laserX1 = laser.getX1();
        double laserY1 = laser.getY1();
        double laserX2 = laser.getX2();
        double laserY2 = laser.getY2();

        double planetXCord = planet.getCenter().getX();
        double planetYCord = planet.getCenter().getY();
        // double planetRadius = planet.getWidth()*0.5;

        double distanceX1Y1 = Math.sqrt(Math.pow(planetXCord-laserX1,2)+Math.pow(planetYCord-laserY1,2));
        double distanceX2Y2 = Math.sqrt(Math.pow(planetXCord-laserX2,2)+Math.pow(planetYCord-laserY2,2));
        if (distanceX1Y1 <= radius || distanceX1Y1 <= distanceX2Y2){
            return true;
        }
        return false;
        }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(planet);
    }

    public static void SolarSystem(CanvasWindow canvas) {
        double earthSize = 20;
        double earthRadius = 150;
        double earthSpeed = 2;


        PlanetImage mercury = new PlanetImage(earthSize * 0.5, earthRadius * 0.5, earthSpeed * 0.6, "planets/mercury.png");
        PlanetImage venus = new PlanetImage(earthSize * 0.8, earthRadius * 0.75, earthSpeed * 0.75, "planets/venus.png");
        PlanetImage earth = new PlanetImage(earthSize, earthRadius, earthSpeed, "planets/earth.png");
        PlanetImage mars = new PlanetImage(earthSize * 0.7, earthRadius * 1.25, earthSpeed * 0.5, "planets/mars.png");
        PlanetImage jupiter = new PlanetImage(earthSize * 2.5, earthRadius * 1.5, earthSpeed * 0.4, "planets/jupiter.png");
        PlanetImage saturn = new PlanetImage(earthSize * 2.2, earthRadius * 1.75, earthSpeed * 0.3, "planets/saturn.png");
        PlanetImage uranus = new PlanetImage(earthSize * 1.5, earthRadius * 2, earthSpeed * 0.2, "planets/uranus.png");
        PlanetImage neptune = new PlanetImage(earthSize * 1.3, earthRadius * 2.25, earthSpeed * 0.1, "planets/neptune.png");

        List<PlanetImage> planets = new ArrayList<PlanetImage>();
        planets.add(neptune);
        planets.add(uranus);
        planets.add(saturn);
        planets.add(jupiter);
        planets.add(mars);
        planets.add(earth);
        planets.add(venus);
        planets.add(mercury);


        for (PlanetImage planet : planets) {
            planet.addToCanvas(canvas);
        }

        canvas.animate(() -> {
            for (PlanetImage planet : planets) {
                planet.move(canvas);
            }
        });
    }
}
