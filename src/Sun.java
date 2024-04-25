
public class Sun extends Planet {
    public Sun(double size, double radius, double speed, String imgPath) {
        super(size, radius, speed, imgPath);
    }

    public void sunFlare(){
        //use random num generator if odd create if even then dont create flare;
        
    }

    @Override
    public String getType() {
        return "Sun";
    }
}
