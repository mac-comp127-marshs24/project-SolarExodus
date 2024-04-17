import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Rectangle;

public class Laser extends Line {
    // private final int velocityX=1;
    // private final int velocityY=1;

    public Laser(double x1, double y1, double x2, double y2) {
        super(x1, y2, x2, y2);
        this.setStrokeWidth(3);
    }

}
