import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class Spaceship {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private Image spaceship;

    public Spaceship() {
        spaceship = new Image(375, 700);
        spaceship.setImagePath("spaceship1.png");
        spaceship.setMaxWidth(WIDTH);
        spaceship.setMaxHeight(HEIGHT);
    }

    public void moveShip(CanvasWindow canvas) {
        canvas.onMouseMove(event -> {
            double mouseX = event.getPosition().getX();
            double spaceshipX = mouseX - WIDTH / 2;
            if (spaceshipX < 0) {
                spaceshipX = 0;
            } else if (spaceshipX + WIDTH > canvas.getWidth()) {
                spaceshipX = canvas.getWidth() - WIDTH;
            }
            spaceship.setPosition(spaceshipX, spaceship.getY());
        });
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(spaceship);
    }
    // public boolean collisionLaser(Laser laser){
    //     double x = spaceship.getX();
    //     double y = spaceship.getY();


    //     double topLeftX = x;
    //     double topRightX = x+WIDTH;
    //     double topLeftY = y;
    //     double topRightY = y+HEIGHT;

    //     double bottomLeftX = x;
    //     double bottomRightX = x+WIDTH;
    //     double bottomLeftY = y+HEIGHT;
    //     double bottomRightY = y+HEIGHT;

    //     if (topLeftX < laser.getX
    //     // GraphicsObject  bottomLeftCorner = canvas.getElementAt(x, y + 2 * BALLRADIUS);
    //     // GraphicsObject bottomRightCorner = canvas.getElementAt(x + 2*BALLRADIUS,y+2*BALLRADIUS);
    //     // if (topLeftCorner != null){
    //     //     return topRightCorner;
    //     // }
    //     // if (topRightCorner != null){
    //     //     return topRightCorner;
    //     // }
    //     // if (bottomLeftCorner != null){
    //     //     return bottomLeftCorner;
    //     // }
    //     // if (bottomRightCorner != null){
    //     //     return bottomRightCorner;
    //     // }
    //     // return null;
    // }
    // }

    public double getX() {
        return spaceship.getX();
    }
    // public double getCenter(){
    //     return spaceship.getCenter();
    // }

    public double getY() {
        return spaceship.getY();
    }

}
