package solarexodus;

/**
 * Authors: Batsambuu Batbold, Yeshe Jangchup, & Nadezhda Dominguez Salinas The earth class creates
 * the Earth in the game, it extends Planet class. Help From Preceptors: Soulai, Hadley, Courtney
 */
public class Earth extends Planet {

    /**
     * Constructs an Earth object with the specified parameters of the Earth.
     *
     * @param earthSize   The size of the Earth.
     * @param earthRadius The radius of the Earth's ...
     * @param earthSpeed  The speed of the Earth.
     * @param imgPath     The image file path of the Earth to be shown.
     */
    public Earth(double earthSize, double earthRadius, double earthSpeed,
        String imgPath) {
        super(earthSize, earthRadius, earthSpeed, imgPath);
    }

    @Override
    public String getType() {
        return "Earth";
    }
}
