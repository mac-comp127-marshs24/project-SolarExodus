
public class Sun extends Planet {
    public Sun(double size, double radius, double speed, String imgPath) {
        super(size, radius, speed, imgPath);
    }

    @Override
    public String getType() {
        return "Sun";
    }
}
