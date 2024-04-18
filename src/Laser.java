
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Line;

public class Laser extends Line {
    private double velocityX;
    private double velocityY;
    public Laser(double x1, double y1, double x2, double y2) {
        super(x1, y2, x2, y2);
        this.setStrokeWidth(3);
        velocityX = -0.1;
        velocityY = -3;

    }
    public void changeDirection(){
        velocityX = -velocityX;
        velocityY=-velocityY;
    }

    public void updatePosition(){
        this.moveBy(velocityX, velocityY);
        // if (laser)

    }
    // public GraphicsObject checkforCollision(CanvasWindow canvas){
    //     double x = this.getX();
    //     double y = this.getY();

    //     GraphicsObject laserDetect = canvas.getElementAt(x,y);

    //     if(laserDetect != null){
    //         return laserDetect;
    //     }
    //     return null;
    // }
    // public void collisionPlanets(CanvasWindow canvas, ArrayList<PlanetImage> PlanetImage ){
    //     if(checkforCollision(canvas) instanceof Planet){
    //         // Planet planet = (Planet) checkforCollision(canvas);
    //         velocityY=-velocityY;
    //     }

    // }

}
