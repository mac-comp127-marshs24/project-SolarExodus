import edu.macalester.graphics.*;

public class Laser extends Line {
    private double velocityX;
    private double velocityY;
    private Point p1, p2;

    public Laser(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
        this.setStrokeWidth(3);
        velocityX = 0;
        velocityY = -6;
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
    }

    public boolean shipCollision(Spaceship spaceship) {
        double laserX1 = this.getX1();
        double laserY1 = this.getY1();
        double laserX2 = this.getX2();
        double laserY2 = this.getY2();

        double shipX = spaceship.getCenter().getX();
        double shipY = spaceship.getCenter().getY();

        double distanceX1Y1 = Math.sqrt(Math.pow(shipX - laserX1, 2) + Math.pow(shipY - laserY1, 2));
        double distanceX2Y2 = Math.sqrt(Math.pow(shipX - laserX2, 2) + Math.pow(shipY - laserY2, 2));
        if (distanceX1Y1 <= 50 / 2 || distanceX2Y2 <= 50 / 2) {
            return true;
        }
        return false;
    }


    public void updatePosition() {
        this.moveBy(velocityX, velocityY);
        p1 = new Point(this.getX1(), this.getY1());
        p2 = new Point(this.getX2(), this.getY2());
    }

    public boolean outOfBounds() {
        double x = this.getX1();
        double y = this.getY1();
        if (x > 900 || x < 0 || y > 750 || y < 0) {
            return true;
        }
        return false;
    }


    public void changeDirection(Planet planet) {
        double angle = angle(p1, p2, planet.getCenter());
        double rotateAngle = 2 * (180 - angle);
        double slope = slope(planet.getCenter(), p1);
        double speed = velocityX * velocityX + velocityY * velocityY;

        if (velocityX == 0) {
            if ((planet.getCenter().getX() - this.getX1()) * (planet.getCenter().getY() - this.getY1()) >= 0) {
                this.setStartPosition(
                    newP1(Math.toRadians(-rotateAngle), this.getX2(), this.getY2(), this.getX1(), this.getY1()));
            } else {
                this.setStartPosition(
                    newP1(Math.toRadians(rotateAngle), this.getX2(), this.getY2(), this.getX1(), this.getY1()));
            }
        } else if (Math.abs(slope) >= Math.abs(velocityY / velocityX)) {
            this.setStartPosition(
                newP1(Math.toRadians(-rotateAngle), this.getX2(), this.getY2(), this.getX1(), this.getY1()));
        } else {
            this.setStartPosition(
                newP1(Math.toRadians(rotateAngle), this.getX2(), this.getY2(), this.getX1(), this.getY1()));
        }

        this.setEndPosition(p1);

        velocityX = (this.getX1() - this.getX2()) * Math.sqrt(speed) / 5;
        velocityY = (this.getY1() - this.getY2()) * Math.sqrt(speed) / 5;

        this.moveBy(velocityX * 5, velocityY * 5);

        p1 = new Point(this.getX1(), this.getY1());
        p2 = new Point(this.getX2(), this.getY2());
    }

    private double angle(Point center, Point point1, Point point2) {
        double c = distance(point1, point2);
        double a = distance(point2, center);
        double b = distance(point1, center);
        double cosAngle = (a * a + b * b - c * c) / (2 * a * b);
        return Math.toDegrees(Math.acos(cosAngle));
    }

    private Point newP1(double angle, double x1, double y1, double centerX, double centerY) {
        double x2 = (x1 - centerX) * Math.cos(angle) - (y1 - centerY) * Math.sin(angle) + centerX;
        double y2 = (x1 - centerX) * Math.sin(angle) + (y1 - centerY) * Math.cos(angle) + centerY;
        return new Point(x2, y2);
    }

    private double distance(Point a, Point b) {
        double ax = a.getX();
        double ay = a.getY();
        double bx = b.getX();
        double by = b.getY();

        return Math.sqrt(Math.pow(ax - bx, 2) + Math.pow(ay - by, 2));
    }

    private double slope(Point a, Point b) {
        return (a.getY() - b.getY()) / (a.getX() - b.getX());
    }

    public boolean collisionSS(Spaceship spaceship) {
        double spaceX = spaceship.getX();
        double spaceY = spaceship.getY();
        double distanceX1Y1 = Math.sqrt(Math.pow(spaceX - this.getX1(), 2) + Math.pow(spaceY - this.getY1(), 2));
        double distanceX2Y2 = Math.sqrt(Math.pow(spaceX - this.getX2(), 2) + Math.pow(spaceY - this.getY2(), 2));

        if (distanceX1Y1 <= 0 && distanceX2Y2 <= 0) {
            return true;
        }
        return false;


    }
}