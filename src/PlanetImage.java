import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class PlanetImage {
    private double posX;
    private double posY;
    private double angle, speed, radius;
    private Image planet;

    public PlanetImage(double size, double radius, double speed, String imgPath) {
        planet = new Image(imgPath);
        planet.setMaxHeight(size);
        planet.setMaxWidth(size);

        this.angle = 0;
        this.radius = radius;
        this.speed = speed;
    }

    public void move(CanvasWindow canvas) {
        double angleRad = Math.toRadians(angle);
        double xCoor = radius * Math.cos(angleRad);
        double yCoor = radius * Math.sin(angleRad);
        posX = canvas.getWidth() * 0.5 + xCoor;
        posY = canvas.getHeight() * 0.25 + yCoor;
        planet.setCenter(posX, posY);
        angle = angle % 360 + speed;
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(planet);
    }
}
