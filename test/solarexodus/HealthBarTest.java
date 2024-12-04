package solarexodus;

import edu.macalester.graphics.*;

public class HealthBarTest {
    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("ss", 500, 600);
        GraphicsGroup group = new GraphicsGroup();

        int i = 10;
        while (i > 0) {
            Image heart = new Image("planets/neptune.png");
            heart.setMaxHeight(20);
            heart.setMaxWidth(20);
            canvas.add(heart, i * 20, 10);
            i--;
        }
        canvas.add(group);
    }
}
