import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.events.Key;

public class Spaceship {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private Image spaceship;

    public Spaceship() {
        spaceship = new Image(375, 700);
        spaceship.setImagePath("other/spaceship1.png");
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

        
        canvas.onKeyDown(event -> {
            double spaceshipX = spaceship.getX();
            if (spaceshipX < 0) {
                spaceshipX = 0;
            } else if (spaceshipX + WIDTH > canvas.getWidth()) {
                spaceshipX = canvas.getWidth() - WIDTH;
            }
            spaceship.setPosition(spaceshipX, spaceship.getY());
        
            if (event.getKey() == Key.LEFT_ARROW) {
                spaceship.moveBy(-5, 0);
            } else if (event.getKey() == Key.RIGHT_ARROW) {
                spaceship.moveBy(5, 0);
            }
    
        });
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(spaceship);
    }

    public double getX() {
        return spaceship.getX();
    }

    public double getY() {
        return spaceship.getY();
    }

    public Point getCenter() {
        return spaceship.getCenter();
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

}
