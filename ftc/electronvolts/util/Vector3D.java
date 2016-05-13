package ftc.electronvolts.util;

/**
 * Created by vandejd1 on 12/27/15.
 * FTC Team EV 7393
 */
public class Vector3D {
    //the 3 components of the vector
    private final double x;
    private final double y;
    private final double z;

    /**
     * create a vector using polar coordinates with z = 0
     * @param magnitude the magnitude of the 2-D vector
     * @param angle the direction of the 2-D vector
     * @return the created vector
     */
    public static Vector3D fromPolar2D(double magnitude, Angle angle){
        double angleRads = angle.getValueRadians();
        double x = magnitude*Math.cos(angleRads);
        double y = magnitude*Math.sin(angleRads);
        return new Vector3D(x, y, 0);
    }

    /**
     * create a vector using x, y, and z
     * @param x x component
     * @param y y component
     * @param z z component
     */
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    //

    /**
     * uses the Pythagorean theorem
     * @return the length or magnitude of the vector
     */
    public double getLength(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    //

    /**
     * Order matters for the cross product
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the first vector crossed with the second vector
     */
    public static Vector3D crossProduct(Vector3D v1, Vector3D v2){
        double x = v1.y*v2.z - v1.z*v2.y;
        double y = v1.z*v2.x - v1.x*v2.z;
        double z = v1.x*v2.y - v1.y*v2.x;
        return new Vector3D(x, y, z);
    }

    //

    /**
     * The order does not matter for the cross product
     * @param v1 one vector
     * @param v2 another vector
     * @return the dot product of the two vectors
     */
    public static double dotProduct(Vector3D v1, Vector3D v2){
        return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
    }


    private final static double closeToZero = 1.0e-3;

    /**
     * This helps when using a gyro sensor or any type of 360 degree rotation mechanism
     * @param ref the reference direction, assumed to have no z component
     * @param vector the vector to be measured against the reference to find the angular separation, also assumed to have no z component
     * @return angular separation between -pi and pi. If vector is to the right of reference, then it is positive.
     */
    public static Angle signedAngularSeparation(Vector3D ref, Vector3D vector) {
        Vector3D cross = crossProduct(ref, vector);
        // If the vectors are too closely aligned, then return zero for separation.
        if (Math.abs(cross.z) < closeToZero) {
            return Angle.fromRadians(0);
        }
        // To get the angle:
        // a dot b = a * b * cos(angle)
        // so angle = acos[ (a dot b) / (a * b) ]
        // Make sure a * b is not too close to zero 0
        double lengths = ref.getLength() * vector.getLength();
        if (lengths < closeToZero) {
            // this is really an error, but to keep the robot from crashing, just return 0
            return Angle.fromRadians(0);
        }
        double dot = dotProduct(ref, vector);

        return Angle.fromRadians(Math.signum(cross.z) * Math.acos(dot / lengths));
    }
}