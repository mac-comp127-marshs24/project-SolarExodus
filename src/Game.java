import edu.macalester.graphics.CanvasWindow;
import java.awt.Color;

public class Game {
    private CanvasWindow canvas;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;
    private Spaceship spaceship;

    public Game() {
        canvas = new CanvasWindow("GameName", WIDTH, HEIGHT);
        // canvas.setBackground(new Color(10, 0, 20));

        PlanetImage spaceBG = new PlanetImage("res/spaceBG.jpeg");
        canvas.setBackground(spaceBG);

        spaceship = new Spaceship();
        spaceship.addToCanvas(canvas);
        spaceship.moveShip(canvas);
    }

    public static void main(String[] args) {
        new Game();
    }
    
}
