



import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.MouseButtonEvent;

import java.awt.Color;
import java.awt.Graphics;


public class Start {
    private CanvasWindow canvas;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 900;

    public Start(){

        canvas = new CanvasWindow("Start Screen", WIDTH, HEIGHT);

        Image spaceBG = new Image("planets/spaceBG2.png");
        spaceBG.setMaxWidth(WIDTH);
        spaceBG.setScale(2);
        spaceBG.setMaxHeight(HEIGHT);
        spaceBG.setPosition(0,0);
        canvas.add(spaceBG);

        Rectangle startButton = new Rectangle((WIDTH/2)-50,(HEIGHT/2)-30,100,50);
        startButton.setFillColor(Color.BLACK);
        startButton.setStrokeColor(Color.PINK);
        // startButton.onMousePressed(event->{startGame(event)});
        // canvas.onClick(event->{
        //     double x = event.getPosition().getX();
        //     double y = event.getPosition().getY();
            
        //     if (x >= 150 && x <= 250 && y >= 100 && y <= 150) {
        //         startGame(event);
        //     }
        // });
        
        canvas.add(startButton);

        GraphicsText startText = new GraphicsText("START");
        startText.setPosition((WIDTH/2)-35,(HEIGHT/2));
        startText.setFillColor(Color.PINK);
        startText.setFontSize(20);
        startText.setFontStyle(FontStyle.BOLD);
        canvas.add(startText);
  

        // Rectangle scriptBox = new Rectangle(70, 500, 750, 50);
        // scriptBox.setFillColor(Color.BLACK);
        // scriptBox.setStrokeColor(Color.PINK);
        // canvas.add(scriptBox);

        GraphicsText scriptText = new GraphicsText("Oh no! There are two suns threatening to overheat the Earth! Shoot down one of these suns to save Earth!");
        scriptText.setPosition(80,310);
        scriptText.setFontStyle(FontStyle.ITALIC);
        scriptText.setFillColor(Color.PINK);
        canvas.add(scriptText);

        GraphicsText gameName = new GraphicsText("Saving Earth");
        gameName.setPosition(300,200);
        gameName.setFontSize(50);
        gameName.setFontStyle(FontStyle.BOLD_ITALIC);
        gameName.setFillColor(Color.PINK);
        canvas.add(gameName);

        

        Image startMer = new Image("planets/mercury.png");
        startMer.setPosition(-770,-190);
        startMer.setScale(0.03);
        canvas.add(startMer);

        Image startVenus = new Image("planets/venus.png");
        startVenus.setPosition(-210,300);
        startVenus.setScale(0.09);
        canvas.add(startVenus);

        Image startEarth = new Image("planets/earth.png");
        startEarth.setPosition(-165,260);
        startEarth.setScale(0.09);
        canvas.add(startEarth);

        Image startMars = new Image("planets/mars1.png");
        startMars.setPosition(-120,230);
        startMars.setScale(0.06);
        canvas.add(startMars);

        Image startJup = new Image("planets/jupiter.png");
        startJup.setPosition(20,250);
        startJup.setScale(0.2);
        canvas.add(startJup);

        Image startSat = new Image("planets/saturn.png");
        startSat.setPosition(170,350);
        startSat.setScale(0.3);
        canvas.add(startSat);

        Image startUra = new Image("planets/uranus.png");
        startUra.setPosition(440,340);
        startUra.setScale(0.15);
        canvas.add(startUra);

        Image startNep = new Image("planets/neptune.png");
        startNep.setPosition(530,300);
        startNep.setScale(0.1);
        canvas.add(startNep);



   
        
    }

    // private void startGame(){
    //     canvas.removeAll();
    //     new Game(canvas);
    //     // MainGame mainGame = new MainGame(canvas);


    // }

    public static void main(String[]args){
        new Start();
    }
}
