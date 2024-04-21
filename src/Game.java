import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.ui.Button;

import java.awt.Color;
import java.util.ArrayList;

public class Game {
    private CanvasWindow canvas;
    private static final int HEIGHT = 750;
    private static final int WIDTH = 900;
    private Spaceship spaceship;
    private ArrayList<Laser> lasers;
    private SolarSystem solarSystem;
    private int lives;
    private Button startButton;
    private GraphicsText livesText;
    Boolean running = true;


    public Game() {
        canvas = new CanvasWindow("Game Screen", WIDTH, HEIGHT);

        Image spaceBG = new Image("planets/spaceBG2.png");
        spaceBG.setMaxWidth(WIDTH);
        spaceBG.setScale(2);

        spaceBG.setMaxHeight(HEIGHT);
        spaceBG.setPosition(0, 0);
        canvas.add(spaceBG);

        startButton = new Button("Start Game");
        startButton.setPosition(400, 400);
        canvas.add(startButton);

        graphics();

        startButton.onClick(() -> call());

        spaceship = new Spaceship();
        lasers = new ArrayList<>();
        lives = 3;

        livesText = new GraphicsText("Lives Left: " + getLives());
    }

    public void call() {
        canvas.remove(startButton);
        gameBG();

        spaceship.addToCanvas(canvas);
        spaceship.moveShip(canvas);

        createLaser();
        animateGame();
        solarSystem = new SolarSystem(canvas);
        livesTxt();
    }


    public void createLaser() {
        canvas.onCharacterTyped(event -> {
            double x1 = spaceship.getX() + 25;
            double x2 = spaceship.getX() + 25;
            double y1 = spaceship.getY() - 15;
            double y2 = spaceship.getY() - 5;
            Laser lasershot = new Laser(x1, y1, x2, y2);
            lasershot.setStrokeColor(Color.PINK);

            canvas.add(lasershot);
            lasers.add(lasershot);
        });

        canvas.onClick(event -> {
            double x1 = spaceship.getX() + 25;
            double x2 = spaceship.getX() + 25;
            double y1 = spaceship.getY() - 15;
            double y2 = spaceship.getY() - 5;
            Laser lasershot = new Laser(x1, y1, x2, y2);
            lasershot.setStrokeColor(Color.PINK);

            canvas.add(lasershot);
            lasers.add(lasershot);
        });
    }

    public void animateGame() {
        canvas.animate(() -> {
            if (running) {
                for (Laser laser : lasers) {
                    laser.updatePosition();
                    if (laser.collisionSS(spaceship)) {
                        canvas.remove(laser);
                        lasers.remove(laser);
                        System.out.println("laser hit");
                    }
                    for (Planet planet : solarSystem.getSolarSystem()) {
                        if (planet.checkLaser(laser)) {
                            if (planet.getType().equals("Planet")) {
                                planet.reflect(laser);
                            } else if (planet.getType().equals("Earth")) {
                                planet.hit(this);
                                System.out.println(getLives());
                                livesTxt();
                            } else if (planet.getType().equals("Sun")) {
                                planet.shrink();
                            }
                        }
                    }

                }
                gameOver();
            }
        });
    }

    public void gameBG() {
        Image spaceBG = new Image("planets/spaceBG2.png");
        spaceBG.setMaxWidth(WIDTH);
        spaceBG.setScale(2);
        spaceBG.setMaxHeight(HEIGHT);
        spaceBG.setPosition(0, 0);
        canvas.add(spaceBG);
    }

    public void graphics() {
        GraphicsText scriptText = new GraphicsText(
            "Oh no! There are two suns threatening to overheat the Earth! Shoot down one of these suns to save Earth!");
        scriptText.setPosition(80, 310);
        scriptText.setFontStyle(FontStyle.ITALIC);
        scriptText.setFillColor(Color.PINK);
        canvas.add(scriptText);

        GraphicsText gameName = new GraphicsText("Saving Earth");
        gameName.setPosition(300, 200);
        gameName.setFontSize(50);
        gameName.setFontStyle(FontStyle.BOLD_ITALIC);
        gameName.setFillColor(Color.PINK);
        canvas.add(gameName);

        Image startMer = new Image("planets/mercury.png");
        planetStartUp(startMer, -770, -190, 0.03);

        Image startVenus = new Image("planets/venus.png");
        planetStartUp(startVenus, -210, 300, 0.09);

        Image startEarth = new Image("planets/earth.png");
        planetStartUp(startEarth, -165, 260, 0.09);

        Image startMars = new Image("planets/mars1.png");
        planetStartUp(startMars, -120, 230, 0.06);

        Image startJup = new Image("planets/jupiter.png");
        planetStartUp(startJup, 20, 250, 0.2);

        Image startSat = new Image("planets/saturn.png");
        planetStartUp(startSat, 170, 350, 0.3);

        Image startUra = new Image("planets/uranus.png");
        planetStartUp(startUra, 440, 340, 0.15);

        Image startNep = new Image("planets/neptune.png");
        planetStartUp(startNep, 530, 300, 0.1);
    }

    public void planetStartUp(Image image, int posX, int posY, Double scale) {
        image.setPosition(posX, posY);
        image.setScale(scale);
        canvas.add(image);
    }

    public int getLives() {
        return lives;
    }

    public void decLives() {
        if (lives > 0) {
            lives--;
        }
    }

    public void gameOver() {
        if (lives == 0) {
            running = false;
            GraphicsText over = new GraphicsText("GAME OVER");

            over.setFillColor(Color.PINK);
            over.setFontStyle(FontStyle.BOLD);
            over.setFontSize(20);
            canvas.add(over, canvas.getWidth() / 2, canvas.getHeight() / 2);

            solarSystem.stopAnimate();
        }
    }

    public void livesTxt() {
        livesText.setText("Lives Left: " + getLives());
        livesText.setPosition(20, 30);
        livesText.setFontStyle(FontStyle.BOLD);
        livesText.setFillColor(Color.PINK);
        if (getLives() == 3) {
            canvas.add(livesText);
        }
    }

    public static void main(String[] args) {
        new Game();
    }

}
