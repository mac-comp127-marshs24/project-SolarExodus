import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.SortingFocusTraversalPolicy;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;

/**
 * 
 */
public class Game {
    private CanvasWindow canvas;
    private static final int HEIGHT = 750, WIDTH = 900;
    private Spaceship spaceship;
    private ArrayList<Laser> lasers;
    private SolarSystem solarSystem;
    private int lives, sunLife;
    private double cooldown;
    private Button startButton, againButton, backButton, pauseButton;
    private Boolean running = true, gameWin = false, gameOver = false;
    private Image cursor;
    private GraphicsGroup cooldownBar, sunBar, healthBar;
    private Random rand = new Random();
    private Flare flare;

    public Game() {
        canvas = new CanvasWindow("Solar Exodus", WIDTH, HEIGHT);
        gameBG();

        // Image cursor= new Image("other/cursor.png"); //trying to make cursor a
        // png cursor.setPosition(0, 0); canvas.add(cursor);

        startButton = new Button("Start the Mission");
        startButton.setCenter(450, 450);
        canvas.add(startButton);
        startButton.onClick(() -> call());

        againButton = new Button("Play Again");
        againButton.setPosition(400, 450);
        againButton.onClick(() -> reSet());

        backButton = new Button("Back");
        backButton.setPosition(10,10);
        backButton.onClick(()->reSet());

        pauseButton = new Button("PAUSE");
        pauseButton.setPosition(20, 10);

        new StartScreen(canvas);

        spaceship = new Spaceship();
        lasers = new ArrayList<>();

        lives = 3;
        cooldown = 50;
        sunLife = 100;

    }

    /**
     * Resets the game window for a replay
     */
    public void reSet() {
        new Game();
        canvas.closeWindow();
    }

    /**
     * Calls all the relevant game elements on the screen to be ready for play
     */
    public void call() {
        canvas.remove(startButton);
        gameBG();

        canvas.add(backButton);

        spaceship.addToCanvas(canvas);
        spaceship.moveShip(canvas);

        shootLaser();
        animateGame();

        solarSystem = new SolarSystem(canvas);

        cooldownBar = cooldownBar(770, 700);
        canvas.add(cooldownBar);

        healthBar = healthBar(20, 360);
        canvas.add(healthBar);

        sunBar = sunBar(770, 20);
        canvas.add(sunBar);        

    }


    /**
     * Shoots the laser on space bar press or on click
     */
    public void shootLaser() {
        canvas.onCharacterTyped(event -> {
            createLaser();
        });

        canvas.onClick(event -> {
            createLaser();
        });
    }

    

    private void animateGame() {
        canvas.animate(() -> {
            if (running) {
                if(rand.nextDouble() > 0.9 && flare==null){
                    flare = solarSystem.getSun().shootFlare(canvas);
                    canvas.add(flare);
                }
                
                if (cooldown < 50) {
                    cooldown += 0.2;
                    canvas.remove(cooldownBar);
                    cooldownBar = cooldownBar(770, 700);
                    canvas.add(cooldownBar);
                }
                for (int i = 0; i < lasers.size(); i++) {
                    lasers.get(i).updatePosition();
                    if (lasers.get(i).shipCollision(spaceship)) {
                        canvas.remove(lasers.get(i));
                        lasers.remove(lasers.get(i));
                        i--;
                        lives--;
                        System.out.println("laser hit");
                        break;
                    }
                    if(lasers.get(i).outOfBounds()){
                        canvas.remove(lasers.get(i));
                        lasers.remove(lasers.get(i));
                        break;
                    }
                    for (Planet planet : solarSystem.getSolarSystem()) {
                        if (planet.checkLaser(lasers.get(i))) {
                            if (planet.getType().equals("Planet")) {
                                planet.reflect(lasers.get(i));
                            } else if (planet.getType().equals("Earth")) {
                                lives--;
                                canvas.remove(healthBar);
                                healthBar = healthBar(20, 360);
                                canvas.add(healthBar);
                                removeLaser(lasers.get(i));
                                i--;
                            } else if (planet.getType().equals("Sun")) {
                                planet.shrink();
                                sunLife -= 10;
                                canvas.remove(sunBar);
                                sunBar = sunBar(770, 20);
                                canvas.add(sunBar);
                                removeLaser(lasers.get(i));
                                i--;
                            }

                            break;
                        }
                    }
                }
                if(flare != null){
                    flare.updatePosition();

                    if(flare.getPosition().getY() > canvas.getHeight()){
                        flare = null;
                    }
                }
                if(flare!=null &&flare.shipCollision(spaceship)){
                    canvas.remove(flare);
                    flare=null;
                    lives--;
                    System.out.println("FLARE HOT BITCH");
                    // break;
                }

            
                // for (int i = 0; i<lasers.size(); i++){
                //     lasers.get(i).updatePosition();
                //     if(lasers.get(i).shipCollision(spaceship)){
                //         canvas.remove(lasers.get(i));
                //         lasers.remove(lasers.get(i));
                //         i--;
                //         lives--;
                //         System.out.println("LASER HIT BITCH");
                //         break;
                //     }
                    
                        // if(lasers.get(i).shipCollision(spaceship)){
                        //     canvas.remove(lasers.get(i));
                        //     lasers.remove(lasers.get(i));
                        //     i--;
                        //     lives--;
                        //     System.out.println("LASER HIT BITCH");
                        //     break;
                        // }

            
               
                
                gameOver();
                gameWin();
            }
        });
    }

    /**
     * Creates the overall gameBG for use in multiple instances
     */
    private void gameBG() {
        Image spaceBG = new Image("other/spaceBG.png");
        spaceBG.setMaxWidth(WIDTH);
        spaceBG.setScale(2);
        spaceBG.setMaxHeight(HEIGHT);
        spaceBG.setPosition(0, 0);
        canvas.add(spaceBG);
    }

    /**
     * 
     */
    private void createLaser() {
        if (!gameOver && cooldown >= 10) {
            double x1 = spaceship.getX() + 25;
            double x2 = spaceship.getX() + 25;
            double y1 = spaceship.getY() - 10;
            double y2 = spaceship.getY() - 5;
            Laser lasershot = new Laser(x1, y1, x2, y2);
            lasershot.setStrokeColor(new Color(0, 255, 0));

            canvas.add(lasershot);
            lasers.add(lasershot);

            cooldown -= 10;
        }
    }

    /**
     * 
     * @param laser
     */
    private void removeLaser(Laser laser) {
        canvas.remove(laser);
        lasers.remove(laser);
    }


    /**
     * Checks if the conditions for a Game Over are true and sets up the screen 
     */
    private void gameOver() {
        if (lives == 0) {
            running = false;
            gameOver = true;

            canvas.removeAll();
            gameBG();
            canvas.add(againButton);

            GraphicsText over = new GraphicsText("GAME OVER");
            over.setFillColor(Color.PINK);
            over.setFontStyle(FontStyle.BOLD_ITALIC);
            over.setFontSize(20);
            canvas.add(over, 400, 400);

            Image sadEarth = new Image("other/sadEarth.JPG");
            canvas.add(sadEarth);
            sadEarth.setPosition(330, 160);
            sadEarth.setScale(0.5);
        }
    }

    /**
     * Checks if the conditions for a Game Win are true and sets up the screen
     */
    private void gameWin() {
        if (sunLife == 0) {
            running = false;

            canvas.removeAll();
            gameBG();
            canvas.add(againButton);

            GraphicsText win = new GraphicsText("GAME WIN");
            win.setFillColor(Color.PINK);
            win.setFontStyle(FontStyle.BOLD_ITALIC);
            win.setFontSize(20);
            canvas.add(win, 400, 400);

            Image happyEarth = new Image("other/happyEarth.JPG");
            canvas.add(happyEarth);
            happyEarth.setPosition(230, 50);
            happyEarth.setScale(0.3);

        }

    }

    /**
     * 
     * @param xPos
     * @param yPos
     * @return
     */
    private GraphicsGroup cooldownBar(double xPos, double yPos) {
        GraphicsGroup g = new GraphicsGroup();
        Rectangle bar = new Rectangle(xPos, yPos, 100, 20);
        bar.setStrokeColor(new Color(0, 255, 0));
        g.add(bar);
        Rectangle limit = new Rectangle(xPos, yPos, cooldown * 2, 20);
        limit.setFillColor(new Color(0, 255, 0));
        g.add(limit);
        return g;
    }

    /**
     * 
     * @param xPos
     * @param yPos
     * @return
     */
    private GraphicsGroup sunBar(double xPos, double yPos) {
        GraphicsGroup g = new GraphicsGroup();
        Rectangle bar = new Rectangle(xPos, yPos, 100, 20);
        bar.setStrokeColor(Color.RED);
        g.add(bar);
        Rectangle limit = new Rectangle(xPos, yPos, sunLife, 20);
        limit.setFillColor(new Color(255, 206, 23, 255));
        g.add(limit);
        return g;
    }

    /**
     * 
     * @param xPos
     * @param yPos
     * @return
     */
    private GraphicsGroup healthBar(double xPos, double yPos) {
        GraphicsGroup g = new GraphicsGroup(xPos, yPos);
        for (int i = 0; i < lives; i++) {
            Image heart = new Image("other/heart.png");
            heart.setMaxHeight(20);
            heart.setMaxWidth(20);
            g.add(heart, i * 20, yPos);
        }
        return g;
    }

    public static void main(String[] args) {
        new Game();
    }
}
