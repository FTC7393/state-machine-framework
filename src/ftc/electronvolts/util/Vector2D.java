package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 10/1/16
 */

public class Vector2D {
    //the 2 components of the vector
    private final double x;
    private final double y;

    /**
     * create a vector using polar coordinates
     *
     * @param magnitude the magnitude of the 2-D vector
     * @param angle     the direction of the 2-D vector
     * @return the created vector
     */
    public static Vector2D fromPolar2D(double magnitude, Angle angle) {
        double angleRads = angle.radians();
        double x = magnitude * Math.cos(angleRads);
        double y = magnitude * Math.sin(angleRads);
        return new Vector2D(x, y);
    }

    /**
     * create a vector using x and y
     *
     * @param x x component
     * @param y y component
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * uses the Pythagorean theorem
     *
     * @return the length or magnitude of the vector
     */
    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * The order does not matter for the cross product
     *
     * @param v1 one vector
     * @param v2 another vector
     * @return the dot product of the two vectors
     */
    public static double dotProduct(Vector2D v1, Vector2D v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public Angle getDirection() {
        return Angle.fromRadians(Math.atan2(y, x));
    }
}
