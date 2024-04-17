import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Rectangle;

public class Laser extends Line {
    private double velocityX;
    private double velocityY;
    public Laser(double x1, double y1, double x2, double y2) {
        super(x1, y2, x2, y2);
        this.setStrokeWidth(3);
        velocityX = -0.1;
        velocityY = -0.2;

    }
    public void changeDirection(){
        velocityX = -velocityX;
        velocityY=-velocityY;
    }

    public void updatePosition(){
        this.moveBy(velocityX, velocityY);
        // if (laser)

    }

}
