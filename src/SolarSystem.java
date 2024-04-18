import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;

public class SolarSystem {
    private static final double EARTH_SIZE = 50;
    private static final double EARTH_RADIUS = 150;
    private static final double EARTH_SPEED = 2;

    private List<Planet> SolarSystem = new ArrayList<Planet>();;

    public SolarSystem(CanvasWindow canvas) {
        Planet mercury = new Planet(EARTH_SIZE * 0.5, EARTH_RADIUS * 0.5, EARTH_SPEED * 0.6, "planets/mercury.png");
        Planet venus = new Planet(EARTH_SIZE * 0.8, EARTH_RADIUS * 0.75, EARTH_SPEED * 0.75, "planets/venus.png");
        Planet earth = new Earth(EARTH_SIZE, EARTH_RADIUS, EARTH_SPEED, "planets/earth.png");
        Planet mars = new Planet(EARTH_SIZE * 0.7, EARTH_RADIUS * 1.25, EARTH_SPEED * 0.5, "planets/mars.png");
        Planet jupiter = new Planet(EARTH_SIZE * 2.5, EARTH_RADIUS * 1.5, EARTH_SPEED * 0.4, "planets/jupiter.png");
        Planet saturn = new Planet(EARTH_SIZE * 2.2, EARTH_RADIUS * 1.75, EARTH_SPEED * 0.3, "planets/saturn.png");
        Planet uranus = new Planet(EARTH_SIZE * 1.5, EARTH_RADIUS * 2, EARTH_SPEED * 0.2, "planets/uranus.png");
        Planet neptune = new Planet(EARTH_SIZE * 1.3, EARTH_RADIUS * 2.25, EARTH_SPEED * 0.1, "planets/neptune.png");

        SolarSystem.add(neptune);
        SolarSystem.add(uranus);
        SolarSystem.add(saturn);
        SolarSystem.add(jupiter);
        SolarSystem.add(mars);
        SolarSystem.add(earth);
        SolarSystem.add(venus);
        SolarSystem.add(mercury);


        for (Planet planet : SolarSystem) {
            planet.addToCanvas(canvas);
        }

        canvas.animate(() -> {
            for (Planet planet : SolarSystem) {
                planet.move(canvas);
            }
        });
    }

    public List<Planet> getSolarSystem() {
        return SolarSystem;
    }
}
