
import java.awt.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Line;

public class Laser extends Line {
    private double velocityX;
    private double velocityY;

    public Laser(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
        this.setStrokeWidth(3);
        velocityX = 0;
        velocityY = -3;

    }

    public void updatePosition() {
        this.moveBy(velocityX, velocityY);
    }

    public void changeDirection() {
        velocityX = -velocityX;
        velocityY = -velocityY;
        this.moveBy(0, velocityY * 6);

    }

    public boolean collisionSS(Spaceship spaceship) {
        double spaceX = spaceship.getX();
        double spaceY = spaceship.getY();
        double distanceX1Y1 = Math.sqrt(Math.pow(spaceX - this.getX1(), 2) + Math.pow(spaceY - this.getY1(), 2));
        double distanceX2Y2 = Math.sqrt(Math.pow(spaceX - this.getX2(), 2) + Math.pow(spaceY - this.getY2(), 2));
        // double maxSpace = spaceship.getY();

        if (distanceX1Y1 <= 0 && distanceX2Y2 <= 0) {
            return true;
        }
        return false;


    }
    // public boolean (Spaceship spaceship){
    // double x = spaceship.getX();
    // double y = spaceship.getY();

    // GraphicsObject topLeftCorner = canvas.getElementAt(x,y);
    // GraphicsObject topRightCorner = canvas.getElementAt(x+2*BALLRADIUS,y);
    // GraphicsObject bottomLeftCorner = canvas.getElementAt(x, y + 2 * BALLRADIUS);
    // GraphicsObject bottomRightCorner = canvas.getElementAt(x + 2*BALLRADIUS,y+2*BALLRADIUS);
    // if (topLeftCorner != null){
    // return topRightCorner;
    // }
    // if (topRightCorner != null){
    // return topRightCorner;
    // }
    // if (bottomLeftCorner != null){
    // return bottomLeftCorner;
    // }
    // if (bottomRightCorner != null){
    // return bottomRightCorner;
    // }
    // return null;
    // }
    // }


}
