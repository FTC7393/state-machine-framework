package ftc.electronvolts.util;

import java.util.Objects;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 2/2/16
 */
public class Angle {
    /*
     * Constants to relate various units to radians
     * The reciprocal of each constant is calculated once to be used multiple
     * times later
     */
    private static double RAD_PER_DEG = Math.PI / 180;
    private static double RAD_PER_ROT = 2 * Math.PI;

    private static double DEG_PER_RAD = 1 / RAD_PER_DEG;
    private static double ROT_PER_RAD = 1 / RAD_PER_ROT;

    // holds the angle's value in radians
    private final double radians;

    /**
     * private constructor to make an Angle object from a value in radians
     *
     * @param radians the angle's value in radians
     */
    private Angle(double radians) {
        this.radians = radians;
    }

    /**
     * 
     * @return the absolute value of the angle
     */
    public Angle abs() {
        return new Angle(Math.abs(radians));
    }

    /**
     * 
     * @return the sign of the angle
     */
    public double signum() {
        return Math.signum(radians);
    }

    /**
     * Adds two Angles together
     *
     * @param angle1 one Angle
     * @param angle2 another Angle
     * @return the resulting Angle
     */
    public static Angle add(Angle angle1, Angle angle2) {
        return new Angle(angle1.radians + angle2.radians);
    }

    /**
     * Subtracts angle2 from angle1
     * 
     * @param angle1 one Angle
     * @param angle2 another Angle
     * @return the resulting Angle
     */
    public static Angle subtract(Angle angle1, Angle angle2) {
        return new Angle(angle1.radians - angle2.radians);
    }

    /**
     * Multiplies an Angle by a number
     * 
     * @param angle the Angle to be multiplied
     * @param number the number to multiply by
     * @return the resulting Angle
     */
    public static Angle multiply(Angle angle, double number) {
        return new Angle(angle.radians * number);
    }

    /**
     * Divides an Angle by a number
     * 
     * @param angle the Angle to be multiplied
     * @param number the number to divided by
     * @return the resulting Angle
     */
    public static Angle divide(Angle angle, double number) {
        return new Angle(angle.radians / number);
    }

    /**
     * Creates an Angle that has a value of 0 (in all units)
     * 
     * @return the created Angle
     */
    public static Angle zero() {
        return new Angle(0);
    }

    /**
     * custom equals() method to compare values
     * https://www.sitepoint.com/implement-javas-equals-method-correctly/
     */
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Angle angle = (Angle) o;
        // field comparison
        return Objects.equals(radians, angle.radians);
    }

    /**
     * Creates an Angle class from a value in radians
     *
     * @param v the value in radians
     * @return the Angle class
     */
    public static Angle fromRadians(double v) {
        return new Angle(v);
    }

    /**
     * Creates an Angle class from a value in degrees
     *
     * @param v the value in degrees
     * @return the Angle class
     */
    public static Angle fromDegrees(double v) {
        return new Angle(v * RAD_PER_DEG);
    }

    /**
     * Creates an Angle class from a value in rotations
     *
     * @param v the value in rotations
     * @return the Angle class
     */
    public static Angle fromRotations(double v) {
        return new Angle(v * RAD_PER_ROT);
    }

    /**
     * 
     * @return the value of the Angle in radians
     */
    public double radians() {
        return radians;
    }

    /**
     * 
     * @return the value of the Angle in degrees
     */
    public double degrees() {
        return radians * DEG_PER_RAD;
    }

    /**
     * 
     * @return the value of the Angle in rotations
     */
    public double rotations() {
        return radians * ROT_PER_RAD;
    }
}
