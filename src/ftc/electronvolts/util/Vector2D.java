package ftc.electronvolts.util;

import ftc.electronvolts.util.units.Angle;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 10/1/16
 */

public class Vector2D {
    /*
     * the 2 components of the vector
     */
    private final double x;
    private final double y;
    
    /*
     * the polar coordinates
     */
    private final double l;
    private final Angle theta;

    /**
     * create a vector using polar coordinates
     *
     * @param magnitude the magnitude of the 2-D vector
     * @param theta the direction of the 2-D vector
     * @return the created vector
     */
    public Vector2D(double magnitude, Angle theta) {
        double thetaRads = theta.radians();
        this.x = magnitude * Math.cos(thetaRads);
        this.y = magnitude * Math.sin(thetaRads);
        
        this.l = magnitude;
        this.theta = theta;
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
        
        //Pythagorean theorem
        this.l = Math.sqrt(x * x + y * y);
        this.theta = Angle.fromRadians(Math.atan2(y, x));
    }

    /**
     * @return the x component of the vector
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y component of the vector
     */
    public double getY() {
        return y;
    }

    /**
     * @return the length or magnitude of the vector
     */
    public double getLength() {
        return l;
    }

    /**
     * @return a new vector that is normalized (length = 1)
     */
    public Vector2D normalized() {
        return new Vector2D(x / l, y / l);
    }

    /**
     * The order does not matter for the dot product
     *
     * @param v1 one vector
     * @param v2 another vector
     * @return the dot product of the two vectors
     */
    public static double dotProduct(Vector2D v1, Vector2D v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    /**
     * @return the direction of the vector
     */
    public Angle getDirection() {
        return theta;
    }
    

    private final static double closeToZero = 1.0e-3;

    /**
     * This helps when using a gyro sensor or any type of 360 degree rotation
     * mechanism
     *
     * @param ref the reference direction, assumed to have no z component
     * @param vector the vector to be measured against the reference to find the
     *            angular separation, also assumed to have no z component
     * @return angular separation between -pi and pi. If vector is to the right
     *         of reference, then it is positive.
     */
    public static Angle signedAngularSeparation(Vector2D ref, Vector2D vector) {
        Vector3D ref3D = Vector3D.from2D(ref);
        Vector3D vector3D = Vector3D.from2D(vector);
        
        Vector3D cross = Vector3D.crossProduct(ref3D, vector3D);
        // If the vectors are too closely aligned, then return zero for
        // separation.
        if (Math.abs(cross.getZ()) < closeToZero) {
            return Angle.zero();
        }
        // To get the angle:
        // a dot b = a * b * cos(angle)
        // so angle = acos[ (a dot b) / (a * b) ]
        // Make sure a * b is not too close to zero 0
        double lengths = ref3D.getLength() * vector3D.getLength();
        if (lengths < closeToZero) {
            // this is really an error, but to keep the robot from crashing,
            // just return 0
            return Angle.zero();
        }
        double dot = Vector3D.dotProduct(ref3D, vector3D);

        return Angle.fromRadians(Math.signum(cross.getZ()) * Math.acos(dot / lengths));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Vector2D other = (Vector2D) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
