package ftc.electronvolts.util;

/**
 * Created by vandejd1 on 12/27/15.
 * FTC Team EV 7393
 */
public class Vector3D {
    private final double x;
    private final double y;
    private final double z;

    public static Vector3D fromPolar2D(double distance, Angle angle){
        double angleRads = angle.getValueRadians();
        double x = distance*Math.cos(angleRads);
        double y = distance*Math.sin(angleRads);
        return new Vector3D(x, y, 0);
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getLength(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public static Vector3D crossProduct(Vector3D v1, Vector3D v2){
//        double x = v1.y*v2.z - v1.z*v2.x; //this line was wrong!!
        double x = v1.y*v2.z - v1.z*v2.y;
        double y = v1.z*v2.x - v1.x*v2.z;
        double z = v1.x*v2.y - v1.y*v2.x;
        return new Vector3D(x, y, z);
    }

    public static double dotProduct(Vector3D v1, Vector3D v2){
        return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
    }

    private final static double closeToZero = 1.0e-3;

    /**
     *
     * @param ref the reference direction, assumed to have no z component
     * @param vector the vector to be measured against the reference to find the angular separation, also assumed to have no z component
     * @return angular separation in radians between -pi and pi. If vector is to the right of reference, then it is positive.
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