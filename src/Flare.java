import java.util.Random;
import edu.macalester.graphics.Image;

public class Flare extends Image{
    
    private double dx;
    private double dy;
    Random rand = new Random();
    double spaceshipX;
    double spaceshipY;


    public Flare(){
        super(0,0);
        setImagePath("other/flare.JPG");
        dx = rand.nextDouble(-2, 2);
        dy = rand.nextDouble(5, 15);
    }

    public void updatePosition(){
        moveBy(dx, dy);
    
    }

    public boolean shipCollision(Spaceship spaceship) {
        double flareX = this.getPosition().getX();
        double flareY = this.getPosition().getY();

        double spaceshipX = spaceship.getX(); 
        double spaceshipY = spaceship.getY();
    
        double spaceshipWidth = spaceship.getWidth();
        double spaceshipHeight = spaceship.getHeight();

        if (flareX >= spaceshipX && flareX <= spaceshipX + spaceshipWidth &&
            flareY >= spaceshipY && flareY <= spaceshipY + spaceshipHeight) {
            return true;
        }
        return false;
    }

    
}
