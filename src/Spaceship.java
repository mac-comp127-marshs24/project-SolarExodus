import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class Spaceship {
    private double positionx;
    private double positiony;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private Image spaceship; 



    public Spaceship(){
        spaceship = new Image(400,400);
        spaceship.setImagePath("res/shippp.png");
        spaceship.setMaxWidth(WIDTH);
        spaceship.setMaxHeight(HEIGHT);


        
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(spaceship);
    } 

}
