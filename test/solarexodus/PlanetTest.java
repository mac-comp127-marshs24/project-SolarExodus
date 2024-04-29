package solarexodus;

import edu.macalester.graphics.CanvasWindow;
import java.awt.Color;

public class PlanetTest {
    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("PlanetTest", 1200, 1600);
        canvas.setBackground(new Color(10, 0, 20));

        new SolarSystem(canvas);
    }
}
