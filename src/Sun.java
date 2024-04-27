import edu.macalester.graphics.CanvasWindow;
import java.io.IOException;
import java.util.List;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sun extends Planet {
    // private Image sunflare;
    List<Flare> flares;

    public Sun(double size, double radius, double speed, String imgPath) {
        super(size, radius, speed, imgPath);
    }

    public Flare shootFlare(CanvasWindow canvas)
        throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        Flare sunFlare = new Flare();
        sunFlare.setPosition((canvas.getCenter().getX()) - 120, 100);
        sunFlare.setScale(0.15);
        return sunFlare;
    }

    // public void createsunflare(){
    // // sunflare = new Image(200, 400);
    // // sunflare.setImagePath("other/flare.JPG");
    // // sunflare.setMaxWidth(20);
    // // sunflare.setMaxHeight(20);
    // Image sunflare = new Image("other/flare.JPG");
    // sunflare.setMaxWidth(20);
    // sunflare.setMaxHeight(20);
    // sunflare.setPosition()
    // // Image sunflare = new Image("other/flare.JPG");

    // //use random num generator if odd create if even then dont create flare;

    // }
    // public void addToCanvas(CanvasWindow canvas) {
    // canvas.add(sunflare);
    // }
    @Override
    public String getType() {
        return "Sun";
    }
}
