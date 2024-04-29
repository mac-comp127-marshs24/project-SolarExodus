package solarexodus;

import edu.macalester.graphics.*;
import java.awt.Color;

/**
 * Authors: Batsambuu Batbold, Yeshe Jangchup, & Nadezhda Dominguez Salinas This startscreen class
 * represents the start screen of the game. Help From Preceptors: Soulai, Hadley, Courtney
 */
public class StartScreen {
    CanvasWindow canvas;

    /**
     * Constructs a new StartScreen object including the introductory text and images of the planets on
     * the startscreen.
     * 
     * @param canvas The canvas where the start screen will be shown.
     */
    public StartScreen(CanvasWindow canvas) {
        GraphicsText scriptText = new GraphicsText(
            "Alright, listen up. Humanity's in a tight spot. We built this artificial sun to cool things down on Earth, but it's backfired \n"
                +
                "big time. The original sun's gone haywire, heating things up even more. That's where you come in. You're at the helm\n"
                +
                "of the spaceship Aurora. Your mission? Destroy that pesky original sun. But hey, it's not gonna be a walk in the park.\n"
                + 
                "\n" + "\n"
                +
                "You'll be dodging solar flares and dodging lasers bouncing off planets. Gotta keep your eyes peeled and your \n"
                + "reflexes sharp. Remember, our goal is to save Earth,so be careful notto blast our home planet to smithereens.\n"
                +
                "\n" + "\n" 
                +
                "So, strap in, commander. It's time to take the reins and lead humanity on this Solar Exodus. Good luck out there.");
        scriptText.setCenter(855, 310);
        scriptText.setFont(FontStyle.ITALIC, 14);
        scriptText.setFillColor(new Color(135, 206, 250));
        scriptText.setAlignment(TextAlignment.CENTER);
        canvas.add(scriptText);

        GraphicsText gameName = new GraphicsText("Solar Exodus");
        gameName.setFont(FontStyle.BOLD, 50);

        gameName.setFillColor(new Color(135, 206, 250));
        gameName.setCenter(450, 150);

        canvas.add(gameName);

        Image startMer = new Image("planets/mercury.png");
        planetStartUp(startMer, -770, -190, 0.03, canvas);

        Image startVenus = new Image("planets/venus.png");
        planetStartUp(startVenus, -210, 300, 0.09, canvas);

        Image startEarth = new Image("planets/earth.png");
        planetStartUp(startEarth, -165, 260, 0.09, canvas);

        Image startMars = new Image("planets/mars1.png");
        planetStartUp(startMars, -120, 230, 0.06, canvas);

        Image startJup = new Image("planets/jupiter.png");
        planetStartUp(startJup, 20, 250, 0.2, canvas);

        Image startSat = new Image("planets/saturn.png");
        planetStartUp(startSat, 170, 350, 0.3, canvas);

        Image startUra = new Image("planets/uranus.png");
        planetStartUp(startUra, 440, 340, 0.15, canvas);

        Image startNep = new Image("planets/neptune.png");
        planetStartUp(startNep, 530, 300, 0.1, canvas);
    }

    /**
     * Displays a planet image at the specified position and scale.
     * 
     * @param image  The path to the image file of the planet.
     * @param posX   The x-coordinate of the planet's position.
     * @param posY   The y-coordinate of the planet's position.
     * @param scale  The scale of the planet image.
     * @param canvas The specified canvas to be shown on.
     */
    private void planetStartUp(
        Image image,
        int posX,
        int posY,
        Double scale,
        CanvasWindow canvas) {
        image.setPosition(posX, posY);
        image.setScale(scale);
        canvas.add(image);
    }
}
