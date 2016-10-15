package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 2/2/16
 */
public class Angle {
	private static double RAD_PER_DEG = Math.PI / 180;
	private static double RAD_PER_ROT = 2 * Math.PI;

	private static double DEG_PER_RAD = 1 / RAD_PER_DEG;
	private static double ROT_PER_RAD = 1 / RAD_PER_ROT;

	private final double radians;

    /**
     * private constructor to make an Angle class from a value in radians
     *
     * @param radians the angle's value in radians
     */
	private Angle(double radians) {
		this.radians = radians;
	}
	
	public Angle abs(){
		return new Angle(Math.abs(radians));
	}
	
    public static Angle zero() {
        return new Angle(0);
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

	
    /**
     * A public method to create an Angle class from a value in radians
     *
     * @param radians the value in radians
     * @return the Angle class
     */
	public static Angle fromRadians(double v) {
		return new Angle(v);
	}

	/**
     * A public method to create an Angle class from a value in degrees
     *
     * @param degrees the value in degrees
     * @return the Angle class
     */
	public static Angle fromDegrees(double v) {
		return new Angle(v * RAD_PER_DEG);
	}

	/**
     * A public method to create an Angle class from a value in rotations
     *
     * @param degrees the value in rotations
     * @return the Angle class
     */
	public static Angle fromRotations(double v) {
		return new Angle(v * RAD_PER_ROT);
	}

	public double radians() {
		return radians;
	}

	public double degrees() {
		return radians * DEG_PER_RAD;
	}

	public double rotations() {
		return radians * ROT_PER_RAD;
	}
}
