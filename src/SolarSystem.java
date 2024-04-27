import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;

public class SolarSystem {
    private static final double EARTH_SIZE = 30;
    private static final double EARTH_RADIUS = 200;
    private static final double EARTH_SPEED = 3;

    private List<Planet> SolarSystem = new ArrayList<Planet>();;
    Planet sun, mercury, venus, earth, mars, jupiter, saturn, uranus, neptune;
    private Boolean running;

    public SolarSystem(CanvasWindow canvas) {
         sun = new Sun(EARTH_SIZE * 5, 0, 0, "planets/sun.png");
         mercury = new Planet(EARTH_SIZE * 0.5, EARTH_RADIUS * 0.5, EARTH_SPEED * 0.6, "planets/mercury.png");
         venus = new Planet(EARTH_SIZE * 0.8, EARTH_RADIUS * 0.75, EARTH_SPEED * 0.75, "planets/venus.png");
         earth = new Earth(EARTH_SIZE, EARTH_RADIUS, EARTH_SPEED, "planets/earth.png");
         mars = new Planet(EARTH_SIZE * 0.7, EARTH_RADIUS * 1.25, EARTH_SPEED * 0.5, "planets/mars1.png");
         jupiter = new Planet(EARTH_SIZE * 2.5, EARTH_RADIUS * 1.5, EARTH_SPEED * 0.4, "planets/jupiter.png");
         saturn = new Planet(EARTH_SIZE * 2.2, EARTH_RADIUS * 1.75, EARTH_SPEED * 0.3, "planets/saturn.png");
         uranus = new Planet(EARTH_SIZE * 1.5, EARTH_RADIUS * 2, EARTH_SPEED * 0.2, "planets/uranus.png");
         neptune = new Planet(EARTH_SIZE * 1.3, EARTH_RADIUS * 2.25, EARTH_SPEED * 0.1, "planets/neptune.png");

        SolarSystem.add(sun);
        SolarSystem.add(neptune);
        SolarSystem.add(uranus);
        SolarSystem.add(saturn);
        SolarSystem.add(jupiter);
        SolarSystem.add(mars);
        SolarSystem.add(earth);
        SolarSystem.add(venus);
        SolarSystem.add(mercury);

        running = true;

        for (Planet planet : SolarSystem) {
            planet.addToCanvas(canvas);
        }

        canvas.animate(() -> {
            if (running) {
                for (Planet planet : SolarSystem) {
                    planet.move(canvas);
                }
            }
        });
    }

    public void stopAnimate() {
        running = false;
    }

    public List<Planet> getSolarSystem() {
        return SolarSystem;
    }

    public Sun getSun(){
        return (Sun) sun;
    }
}
