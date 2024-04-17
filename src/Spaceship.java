import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class Spaceship {
    private double positionx;
    private double positiony;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private Image spaceship;

    public Spaceship() {
        spaceship = new Image(375, 700);
        spaceship.setImagePath("spaceship1.png");
        spaceship.setMaxWidth(WIDTH);
        spaceship.setMaxHeight(HEIGHT);
    }
        
    public void moveShip(CanvasWindow canvas){
        canvas.onMouseMove(event -> {
            double mouseX = event.getPosition().getX();
            double spaceshipX = mouseX - WIDTH /2;
            if (spaceshipX<0){
                spaceshipX = 0;
            } else if (spaceshipX + WIDTH > canvas.getWidth()){
                spaceshipX = canvas.getWidth() - WIDTH;
            }
            spaceship.setPosition(spaceshipX, spaceship.getY());
        });
            }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(spaceship);
    }

    public double getX(){
        return spaceship.getX();
    }
    public double getY(){
        return spaceship.getY();
    }

}
