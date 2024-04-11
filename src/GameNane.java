import edu.macalester.graphics.CanvasWindow;
import java.awt.Color;

public class GameNane {
    private CanvasWindow canvas;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;

    public GameNane(){
        canvas = new CanvasWindow("GameName", WIDTH, HEIGHT);
        canvas.setBackground(Color.BLACK);
        Spaceship space = new Spaceship();
        space.addToCanvas(canvas);
    }





    public static void main(String[] args) {
        new GameNane();
    }
}


