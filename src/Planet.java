import edu.macalester.graphics.*;
import java.awt.Color;


public class Planet {
    private double posX;
    private double posY;
    private double radius;
    private Ellipse planet;
    private double speed;
    private double angle;

    public Planet(double size, Color color, double radius, double speed) {
        planet = new Ellipse(0, 0, size, size);
        planet.setFillColor(color);
        planet.setStrokeColor(Color.BLACK);

        this.speed = speed;
        this.angle = 0;
        this.radius = radius;
    }

    public void move(CanvasWindow canvas) {
        double angleRad = Math.toRadians(angle);
        double xCoor = radius * Math.cos(angleRad);
        double yCoor = radius * Math.sin(angleRad);
        posX = canvas.getWidth() / 2 + xCoor;
        posY = canvas.getHeight() / 2 + yCoor;
        planet.setCenter(posX, posY);
        angle = angle % 360 + speed;
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(planet);
    }
}
