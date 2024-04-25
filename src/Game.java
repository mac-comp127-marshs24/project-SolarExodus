import edu.macalester.graphics.*;
import edu.macalester.graphics.events.MouseMotionEvent;
import edu.macalester.graphics.ui.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
// import org.w3c.dom.events.MouseEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Game {
    private CanvasWindow canvas;
    private static final int HEIGHT = 750;
    private static final int WIDTH = 900;
    private Spaceship spaceship;
    private ArrayList<Laser> lasers;
    private SolarSystem solarSystem;
    private int lives;
    private double cooldown;
    private int sunLife;
    private Button startButton;
    private Button againButton;
    private Boolean running = true;
    private boolean gameOver = false;
    private Image cursor;
    private GraphicsGroup cooldownBar;
    private GraphicsGroup healthBar;
    private GraphicsGroup sunBar;

    public Game() {
        canvas = new CanvasWindow("Solar Exodus", WIDTH, HEIGHT);

        // Image spaceBG = new Image("planets/spaceBG2.png");
        // spaceBG.setMaxWidth(WIDTH);
        // spaceBG.setScale(2);

        // spaceBG.setMaxHeight(HEIGHT);
        // spaceBG.setPosition(0, 0);
        // canvas.add(spaceBG);
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

        graphicsStartUp();

        spaceship = new Spaceship();
        lasers = new ArrayList<>();

        lives = 5;
        cooldown = 50;
        sunLife = 100;

    }

    public void reSet() {
        new Game();
        canvas.closeWindow();
    }

    public void call() {
        canvas.remove(startButton);
        gameBG();

        spaceship.addToCanvas(canvas);
        spaceship.moveShip(canvas);

        createLaser();
        animateGame();

        solarSystem = new SolarSystem(canvas);

        cooldownBar = cooldownBar(770, 700);
        canvas.add(cooldownBar);

        healthBar = healthBar(20, 10);
        canvas.add(healthBar);

        sunBar = sunBar(770, 20);
        canvas.add(sunBar);
    }

    public void createLaser() {
        canvas.onCharacterTyped(event -> {
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
        });

        canvas.onClick(event -> {
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
        });
    }

    public void animateGame() {
        canvas.animate(() -> {

            if (running) {
                canvas.remove(cooldownBar);
                cooldownBar = cooldownBar(770, 700);
                canvas.add(cooldownBar);
                if (cooldown < 50) {
                    cooldown += 0.2;
                }
                for (int i = 0; i < lasers.size(); i++) {
                    lasers.get(i).updatePosition();
                    // if (lasers.get(i).collisionSS(spaceship)) {
                    // canvas.remove(lasers.get(i));
                    // lasers.remove(lasers.get(i));
                    // i--;
                    // System.out.println("laser hit");
                    // }
                    for (Planet planet : solarSystem.getSolarSystem()) {
                        if (planet.checkLaser(lasers.get(i))) {
                            if (planet.getType().equals("Planet")) {
                                planet.reflect(lasers.get(i));
                            } else if (planet.getType().equals("Earth")) {
                                lives--;
                                canvas.remove(healthBar);
                                healthBar = healthBar(20, 10);
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
                gameOver();
            }
        });
    }

    private void removeLaser(Laser laser) {
        canvas.remove(laser);
        lasers.remove(laser);
    }

    public void gameBG() {
        Image spaceBG = new Image("other/spaceBG.png");
        spaceBG.setMaxWidth(WIDTH);
        spaceBG.setScale(2);
        spaceBG.setMaxHeight(HEIGHT);
        spaceBG.setPosition(0, 0);
        canvas.add(spaceBG);
    }

    public void graphicsStartUp() {
        GraphicsText scriptText = new GraphicsText(
            "Alright, listen up. Humanity's in a tight spot. We built this artificial sun to cool things down on Earth, but it's backfired big time.\n"
                +
                "The original sun's gone haywire, heating things up even more. That's where you come in.\n"
                + "You're at the helm of the spaceship Aurora. Your mission? Destroy that pesky original sun.\n"
                + "\n"
                + "But hey, it's not gonna be a walk in the park. You'll be dodging solar flares and dodging lasers bouncing off planets.\n"
                + "Gotta keep your eyes peeled and your reflexes sharp.\n"
                + "Remember, our goal is to save Earth, so be careful not to blast our home planet to smithereens.\n"
                + "\n"
                + "So, strap in, commander. It's time to take the reins and lead humanity on this Solar Exodus. Good luck out there.");
        scriptText.setCenter(420, 300);
        scriptText.setFont(FontStyle.PLAIN, 16);
        scriptText.setFillColor(new Color(135, 206, 250));
        canvas.add(scriptText);

        GraphicsText gameName = new GraphicsText("Solar Exodus");
        gameName.setFont(FontStyle.BOLD, 50);

        gameName.setFillColor(new Color(135, 206, 250));
        gameName.setCenter(450, 150);

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

    public void gameOver() {
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
        }
    }

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
