package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 2/2/16
 */
public class Angle {
    private final double radians;

    /**
     * private constructor to make an Angle class from a value in radians
     *
     * @param radians the angle's value in radians
     */
    private Angle(double radians) {
        this.radians = radians;
    }

    /**
     * A public method to create an Angle class from a value in radians
     *
     * @param radians the value in radians
     * @return the Angle class
     */
    public static Angle fromRadians(double radians) {
        return new Angle(radians);
    }

    /**
     * A public method to create an Angle class from a value in degrees
     *
     * @param degrees the value in degrees
     * @return the Angle class
     */
    public static Angle fromDegrees(double degrees) {
        return new Angle(Math.toRadians(degrees));
    }

    public static Angle zero() {
        return new Angle(0);
    }

    /**
     * @return the value in degrees
     */
    public double getValueDegrees() {
        return Math.toDegrees(radians);
    }

    /**
     * @return the value in radians
     */
    public double getValueRadians() {
        return radians;
    }

    /**
     * Adds two Angles together
     *
     * @param angle1 one Angle
     * @param angle2 the other Angle
     * @return the resulting Angle
     */
    public static Angle sum(Angle angle1, Angle angle2) {
        return new Angle(angle1.radians + angle2.radians);
    }
}
