public class Earth extends Planet {

    public Earth(double earthSize, double earthRadius, double earthSpeed,
        String imgPath) {
        super(earthSize, earthRadius, earthSpeed, imgPath);
    }

    @Override
    public String getType() {
        return "Earth";
    }
}
