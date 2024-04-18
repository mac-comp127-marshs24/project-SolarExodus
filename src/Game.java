import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

import java.awt.Color;
import java.util.ArrayList;

public class Game {
    private CanvasWindow canvas;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 900;
    private Spaceship spaceship;
    private ArrayList<Laser> lasers;


    public Game() {
        canvas = new CanvasWindow("Game Screen", WIDTH, HEIGHT);

        Image spaceBG = new Image("planets/spaceBG2.png");
        spaceBG.setMaxWidth(WIDTH);
        spaceBG.setScale(2);
        spaceBG.setMaxHeight(HEIGHT);
        spaceBG.setPosition(0,0);
        canvas.add(spaceBG);


        spaceship = new Spaceship();
        spaceship.addToCanvas(canvas);
        spaceship.moveShip(canvas);
        lasers = new ArrayList <> ();
        createLaser();
        animateGame();

        PlanetImage.SolarSystem(canvas);
}


    public void createLaser(){
        canvas.onCharacterTyped(event-> {
            double x1 = spaceship.getX()+23;
            double x2 = spaceship.getX()+25;
            double y1 = spaceship.getY()-40;
            double y2 = spaceship.getY()-5;
            Laser lasershot = new Laser(x1,y1,x2,y2);
            lasershot.setStrokeColor(Color.PINK);

            canvas.add(lasershot);
            lasers.add(lasershot);
        });

        canvas.onClick(event-> {
            double x1 = spaceship.getX()+23;
            double x2 = spaceship.getX()+25;
            double y1 = spaceship.getY()-40;
            double y2 = spaceship.getY()-5;
            Laser lasershot = new Laser(x1,y1,x2,y2);
            lasershot.setStrokeColor(Color.PINK);

            canvas.add(lasershot);
            lasers.add(lasershot);
    });}

    public void animateGame(){
        canvas.animate(()->{
            for(Laser laser: lasers){
                laser.updatePosition();
            }
        });
    }
    
    public static void main(String[] args) {
        new Game();

    }
    
}
