package solarexodus;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/** Authors: Batsambuu Batbold, Yeshe Jangchup, & Nadezhda Dominguez Salinas
 * The main class containing the Solor Exodus Game.
 * Help From Preceptors: Soulai, Hadley, Courtney
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
    private Boolean running = true, gameWon = false, gameOver = false;
    private Image cursor;
    private GraphicsGroup cooldownBar, sunBar, healthBar;
    private Random rand = new Random();
    private Flare flare;

    /**
     * Constructs a new instance of the Solar Exodus game.
     * @throws UnsupportedAudioFileException 
     * @throws IOException 
     * @throws LineUnavailableException 
     */

    public Game() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        canvas = new CanvasWindow("Solar Exodus", WIDTH, HEIGHT);
        gameBG();

        // Image cursor= new Image("other/cursor.png"); //trying to make cursor a
        // png cursor.setPosition(0, 0); canvas.add(cursor);

        startButton = new Button("Start the Mission");
        startButton.setCenter(450, 450);
        canvas.add(startButton);
        startButton.onClick(() -> startGameScreen());

        againButton = new Button("Play Again");
        againButton.setPosition(400, 450);
        againButton.onClick(() -> {
            try {
                resetGame();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });

        backButton = new Button("Back");
        backButton.setPosition(10, 10);
        backButton.onClick(() -> {
            try {
                resetGame();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });

        pauseButton = new Button("PAUSE");
        pauseButton.setPosition(20, 10);

        new StartScreen(canvas);

        spaceship = new Spaceship();
        lasers = new ArrayList<>();

        lives = 3;
        cooldown = 20;
        sunLife = 100;

        Audio.intro();
    }

    /**
     * Resets the game window for a replay.
     *
     * @throws LineUnavailableException
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    public void resetGame() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        new Game();
        canvas.closeWindow();
    }

    /**
     * Initializes and displays the game elements on the screen to be ready for play. 
     */
    public void startGameScreen() {
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
     * This handles shooting lasers on space bar press or mouse click.
     */
    public void shootLaser() {
        canvas.onCharacterTyped(event -> {
            try {
                createLaser();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });

        canvas.onClick(event -> {
            try {
                createLaser();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Animates the game by updating the game elements and checking for collision between
     * the lasers and spaceship, lasers and planets, and flares and the spaceship.
     */
    private void animateGame() {
        canvas.animate(() -> {
            if (running) {
                if (rand.nextDouble() > 0.9 && flare == null) {
                    try {
                        flare = solarSystem.getSun().shootFlare(canvas);
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    canvas.add(flare);
                }

                if (cooldown < 20) {
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
                        try {
                            Audio.hitSpaceship();
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                            e.printStackTrace();
                        }
                        updateHealthBar();
                        System.out.println("laser hit");
                        break;
                    }
                    if (lasers.get(i).outOfBounds()) {
                        canvas.remove(lasers.get(i));
                        lasers.remove(lasers.get(i));
                        i--;
                        break;
                    }
                    for (Planet planet : solarSystem.getSolarSystem()) {
                        if (planet.checkLaser(lasers.get(i))) {
                            if (planet.getType().equals("Planet")) {
                                planet.reflect(lasers.get(i));
                            } else if (planet.getType().equals("Earth")) {
                                lives--;
                                updateHealthBar();
                                try {
                                    Audio.hitEarth();
                                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                    e.printStackTrace();
                                }
                                removeLaser(lasers.get(i));
                                i--;
                            } else if (planet.getType().equals("Sun")) {
                                try {
                                    planet.shrink();
                                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                    e.printStackTrace();
                                }
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
                if (flare != null) {
                    flare.updatePosition();

                    if (flare.getPosition().getY() > canvas.getHeight()) {
                        flare = null;
                    }
                }
                if (flare != null && flare.shipCollision(spaceship)) {
                    canvas.remove(flare);
                    flare = null;
                    lives--;
                    try {
                        Audio.hitSpaceship();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    updateHealthBar();
                    System.out.println("FLARE HOT BITCH");
                    // break;
                }

                // for (int i = 0; i<lasers.size(); i++){
                // lasers.get(i).updatePosition();
                // if(lasers.get(i).shipCollision(spaceship)){
                // canvas.remove(lasers.get(i));
                // lasers.remove(lasers.get(i));
                // i--;
                // lives--;
                // System.out.println("LASER HIT BITCH");
                // break;
                // }

                // if(lasers.get(i).shipCollision(spaceship)){
                // canvas.remove(lasers.get(i));
                // lasers.remove(lasers.get(i));
                // i--;
                // lives--;
                // System.out.println("LASER HIT BITCH");
                // break;
                // }

                gameOver();
                gameWon();
            }
        });
    }

    /**
     * Updates the health bar graphics when health is impacted.
     */
    private void updateHealthBar() {
        canvas.remove(healthBar);
        healthBar = healthBar(20, 360);
        canvas.add(healthBar);
    }

    /**
     * Creates the space background image for the game.
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
     * Creates a laser when called, and if the game is running and the cooldown is above or equal to 10. 
     * Adds the laser to the canvas and to the laser list.
     * 
     * @throws LineUnavailableException
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    private void createLaser() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
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
     * Removes a laser from the canvas.
     * 
     * @param laser The laser to be removed. 
     */
    private void removeLaser(Laser laser) {
        canvas.remove(laser);
        lasers.remove(laser);
    }

    /**
     * Checks if the conditions of game over are true based on lives,
     * and updates the game accordingly.
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

            Image sadEarth = new Image("other/sadEarth.png");
            canvas.add(sadEarth);
            sadEarth.setPosition(330, 160);
            sadEarth.setScale(0.5);
        }
    }

    /**
     * Checks if the conditions for a game won are true,
     * and updates the screen accordingly.
     */
    private void gameWon() {
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

            Image happyEarth = new Image("other/happyEarth.png");
            canvas.add(happyEarth);
            happyEarth.setPosition(230, 50);
            happyEarth.setScale(0.3);
        }
    }

    /**
     * Creates a graphics group representing the cooldown bar.
     * 
     * @param xPos The x coordinate of the cooldown bar.
     * @param yPos The y coordinate of the cooldown bar.
     * @return The graphics group representing the cooldown bar.
     */
    private GraphicsGroup cooldownBar(double xPos, double yPos) {
        GraphicsGroup g = new GraphicsGroup();
        Rectangle bar = new Rectangle(xPos, yPos, 100, 20);
        bar.setStrokeColor(new Color(0, 255, 0));
        g.add(bar);
        Rectangle limit = new Rectangle(xPos, yPos, cooldown * 5, 20);
        limit.setFillColor(new Color(0, 255, 0));
        g.add(limit);
        return g;
    }

    /**
     * Creates a graphics group representing the sun's health bar.
     * 
     * @param xPos The x coordinate of the sun's health bar.
     * @param yPos The y coordinate of the sun's health bar.
     * @return The graphics group representing the sun's health bar.
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
     * Creates a graphics group representing the player's health bar.
     * 
     * @param xPos The x coordinate of the player's health bar.
     * @param yPos The y coordinate of the player's health bar.
     * @return The graphics group representing the player's health bar.
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

    /**
     * This is the main method to start the game.
     * 
     * @param args
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public static void main(String[] args) throws UnsupportedAudioFileException,
        IOException,
        LineUnavailableException {
        new Game();
    }
}
