import java.awt.Color;

import edu.macalester.graphics.*;

public class StartScreen {
    CanvasWindow canvas;

    public StartScreen(CanvasWindow canvas) {
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
        scriptText.setCenter(850, 300);
        scriptText.setFont(FontStyle.ITALIC, 14);
        scriptText.setFillColor(new Color(135, 206, 250));
        scriptText.setAlignment(TextAlignment.CENTER);
        canvas.add(scriptText);
        // moveText(scriptText, 5, 5);        



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

    private void planetStartUp(Image image, int posX, int posY, Double scale, CanvasWindow canvas) {
        image.setPosition(posX, posY);
        image.setScale(scale);
        canvas.add(image);
    }

    public void moveText(GraphicsText script, double dx, double dy){
        canvas.animate(()->{
            if(script.getX()+script.getWidth()+50<canvas.getWidth()){
                script.moveBy(dx,dy);
            }
        });
    }
}
