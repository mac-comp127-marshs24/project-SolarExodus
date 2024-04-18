public class Earth extends Planet {

    public Earth(double earthSize, double earthRadius, double earthSpeed, String imgPath) {
        super(20, 150, 2, "planets/earth.png");
    }

    @Override
    public void hit(Game game) {
        game.decLives();
    }


    @Override
    public String getType() {
        return "Earth";
    }
}
