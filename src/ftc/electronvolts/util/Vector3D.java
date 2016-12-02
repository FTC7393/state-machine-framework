package ftc.electronvolts.util;

import ftc.electronvolts.util.units.Angle;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 9/30/16
 */
public class Vector3D {
    /*
     * the 3 components of the vector
     */
    private final double x;
    private final double y;
    private final double z;

    /*
     * the spherical coordinates
     */
    private final double l;
    private final Angle theta;
    private final Angle phi;

    /**
     * create a vector using polar coordinates with z = 0
     *
     * @param magnitude the magnitude of the Vector2D
     * @param theta the direction of the Vector2D
     * @return the created Vector3D
     */
    public static Vector3D fromPolar2D(double magnitude, Angle theta) {
        return from2D(new Vector2D(magnitude, theta));
    }

    /**
     * create a vector from a Vector2D with z = 0
     * 
     * @param vector2D the 2D vector to use
     * @return the created Vector3D
     */
    public static Vector3D from2D(Vector2D vector2D) {
        return new Vector3D(vector2D.getX(), vector2D.getY(), 0);
    }

    /**
     * Create a vector using spherical coordinates
     * 
     * @param magnitude the magnitude of the 3D vector
     * @param theta the direction in the x-y plane
     * @param phi the z direction
     * @return
     */
    public Vector3D(double magnitude, Angle theta, Angle phi) {
        double thetaRads = theta.radians();
        double phiRads = phi.radians();

        // http://mathinsight.org/spherical_coordinates
        // x = ρ sinϕ cosθ
        // y = ρ sinϕ sinθ
        // z = ρ cosϕ
        
        this.x = magnitude * Math.sin(phiRads) * Math.cos(thetaRads);
        this.y = magnitude * Math.sin(phiRads) * Math.sin(thetaRads);
        this.z = magnitude * Math.cos(phiRads);
        
        this.l = magnitude;
        this.theta = theta;
        this.phi = phi;
    }

    /**
     * create a vector using x, y, and z
     *
     * @param x x component
     * @param y y component
     * @param z z component
     */
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        
        //Pythagorean theorem
        this.l = Math.sqrt(x * x + y * y + z * z);
        
        //compute spherical coordinates
        phi = Angle.fromRadians(Math.acos(z / l));
        theta = Angle.fromRadians(Math.atan2(y, x));
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
     * @return the z component of the vector
     */
    public double getZ() {
        return z;
    }

    /**
     * @return the length or magnitude of the vector
     */
    public double getLength() {
        return l;
    }

    /**
     * @return the z direction
     */
    public Angle getPhi() {
        return phi;
    }

    /**
     * @return the x-y direction
     */
    public Angle getTheta() {
        return theta;
    }

    /**
     * @return a new vector that is normalized (length = 1)
     */
    public Vector3D normalized() {
        return new Vector3D(x / l, y / l, z / l);
    }

    /**
     * Order matters for the cross product
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the first vector crossed with the second vector
     */
    public static Vector3D crossProduct(Vector3D v1, Vector3D v2) {
        double x = v1.y * v2.z - v1.z * v2.y;
        double y = v1.z * v2.x - v1.x * v2.z;
        double z = v1.x * v2.y - v1.y * v2.x;
        return new Vector3D(x, y, z);
    }

    /**
     * The order does not matter for the dot product
     *
     * @param v1 one vector
     * @param v2 another vector
     * @return the dot product of the two vectors
     */
    public static double dotProduct(Vector3D v1, Vector3D v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }
    
    /**
     * 
     * @param v1 one vector
     * @param v2 another vector
     * @return the angle between the two vectors
     */
    public static Angle angularSeparation(Vector3D v1, Vector3D v2){
        //             a dot b
        //cos theta = ---------
        //            |a| * |b|
        return Angle.fromRadians(Math.acos(dotProduct(v1, v2) / (v1.l * v2.l)));
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
        temp = Double.doubleToLongBits(z);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Vector3D other = (Vector3D) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) return false;
        if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

}