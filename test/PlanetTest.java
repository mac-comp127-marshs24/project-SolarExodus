import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;

public class PlanetTest {
    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("PlanetTest", 800, 800);
        Planet planet = new Planet(25, Color.RED, 100, 0.3);
        PlanetImage planet2 = new PlanetImage(50, 200, 0.5, "planets/mars.png");
        planet.addToCanvas(canvas);
        planet2.addToCanvas(canvas);
        canvas.animate(() -> {
            planet.move(canvas);
            planet2.move(canvas);
        });
    }
}
