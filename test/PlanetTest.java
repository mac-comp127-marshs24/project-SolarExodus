import edu.macalester.graphics.CanvasWindow;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class PlanetTest {
    public static void main(String[] args) {
        double earthSize = 20;
        double earthRadius = 300;
        double earthSpeed = 2;
        CanvasWindow canvas = new CanvasWindow("PlanetTest", 1200, 1200);
        canvas.setBackground(new Color(10, 0, 20));

        PlanetImage mercury = new PlanetImage(earthSize * 0.5, earthRadius * 0.5, earthSpeed * 0.6, "planets/mercury.png");
        PlanetImage venus = new PlanetImage(earthSize * 0.8, earthRadius * 0.75, earthSpeed * 0.75, "planets/venus.png");
        PlanetImage earth = new PlanetImage(earthSize, earthRadius, earthSpeed, "planets/earth.png");
        PlanetImage mars = new PlanetImage(earthSize * 0.7, earthRadius * 1.25, earthSpeed * 0.5, "planets/mars.png");
        PlanetImage jupiter = new PlanetImage(earthSize * 2.5, earthRadius * 1.5, earthSpeed * 0.4, "planets/jupiter.png");
        PlanetImage saturn = new PlanetImage(earthSize * 2.2, earthRadius * 1.75, earthSpeed * 0.3, "planets/saturn.png");
        PlanetImage uranus = new PlanetImage(earthSize * 1.5, earthRadius * 2, earthSpeed * 0.2, "planets/uranus.png");
        PlanetImage neptune = new PlanetImage(earthSize * 1.3, earthRadius * 2.75, earthSpeed * 0.1, "planets/neptune.png");

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
