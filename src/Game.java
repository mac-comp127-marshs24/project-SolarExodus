import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.ui.Button;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;

public class Game {
    private CanvasWindow canvas;
    private static final int HEIGHT = 750;
    private static final int WIDTH = 900;
    private Spaceship spaceship;
    private ArrayList<Laser> lasers;
    private ArrayList<PlanetImage> planets;


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
        planets = new ArrayList<>();
        createLaser();
        animateGame();

        PlanetImage.SolarSystem(canvas);
        // startScreen();
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
    // public static ArrayList<PlanetImage> getPlanets(){
    //     return planets;
    // }

    public void animateGame(){
        canvas.animate(()->{
            for(Laser laser: lasers){
                laser.updatePosition();
            
                for(PlanetImage planet: planets){
                    if(planet.checkLaser(laser)){
                        planet.reflect(laser);
                    }
                }
            
        }});
    }
    public static void main(String[] args) {
        new Game();

    }
    
}
