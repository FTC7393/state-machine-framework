package ftc.electronvolts.util;

import java.util.Objects;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 10/4/16
 */

public class Distance {
    /*
     * Constants to relate various units to meters
     * The reciprocal of each constant is calculated once to be used multiple
     * times later
     */
    private static final double M_PER_KM = 1e+3;
    private static final double M_PER_CM = 1e-2;
    private static final double M_PER_MM = 1e-3;
    private static final double M_PER_UM = 1e-6;
    private static final double M_PER_NM = 1e-9;
    private static final double M_PER_FT = 0.3048;
    private static final double M_PER_IN = 0.0254;
    private static final double M_PER_YD = 0.9144;
    private static final double M_PER_MI = 1609.344;
    private static final double M_PER_NAUT_MI = 1852;

    private static final double KM_PER_M = 1 / M_PER_KM;
    private static final double CM_PER_M = 1 / M_PER_CM;
    private static final double MM_PER_M = 1 / M_PER_MM;
    private static final double UM_PER_M = 1 / M_PER_UM;
    private static final double NM_PER_M = 1 / M_PER_NM;
    private static final double FT_PER_M = 1 / M_PER_FT;
    private static final double IN_PER_M = 1 / M_PER_IN;
    private static final double YD_PER_M = 1 / M_PER_YD;
    private static final double MI_PER_M = 1 / M_PER_MI;
    private static final double NAUT_MI_PER_M = 1 / M_PER_NAUT_MI;

    // The distance in meters
    private final double meters;

    /**
     * Private constructor to create a Distance object from meters
     * 
     * @param meters the number of meters
     */
    private Distance(double meters) {
        this.meters = meters;
    }

    /**
     * 
     * @return the absolute value of the distance
     */
    public Distance abs() {
        return new Distance(Math.abs(meters));
    }

    /**
     * 
     * @return the sign of the distance
     */
    public double signum() {
        return Math.signum(meters);
    }

    /**
     * Adds two Distances together
     * 
     * @param distance1 the first Distance
     * @param distance2 the second Distance
     * @return the resulting Distance
     */
    public static Distance add(Distance distance1, Distance distance2) {
        return new Distance(distance1.meters + distance2.meters);
    }

    /**
     * Subtracts distance2 from distance1
     * 
     * @param distance1 the first Distance
     * @param distance2 the second Distance
     * @return the resulting Distance
     */
    public static Distance subtract(Distance distance1, Distance distance2) {
        return new Distance(distance1.meters - distance2.meters);
    }

    /**
     * Multiplies a Distance by a number
     * 
     * @param distance the Distance
     * @param number the number to multiply by
     * @return the resulting Distance
     */
    public static Distance multiply(Distance distance, double number) {
        return new Distance(distance.meters * number);
    }

    /**
     * Divides a Distance by a number
     * 
     * @param distance the Distance
     * @param number the number to divide by
     * @return the resulting Distance
     */
    public static Distance divide(Distance distance, double number) {
        return new Distance(distance.meters / number);
    }

    /**
     * Create a Distance that has a value of 0
     * 
     * @return the created Distance
     */
    public static Distance zero() {
        return new Distance(0);
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
        Distance distance = (Distance) o;
        // field comparison
        return Objects.equals(meters, distance.meters);
    }

    // Create Distance objects from various units
    public static Distance fromMeters(double v) {
        return new Distance(v);
    }

    public static Distance fromKilometers(double v) {
        return new Distance(v * M_PER_KM);
    }

    public static Distance fromCentimeters(double v) {
        return new Distance(v * M_PER_CM);
    }

    public static Distance fromMillimeters(double v) {
        return new Distance(v * M_PER_MM);
    }

    public static Distance fromMicrometers(double v) {
        return new Distance(v * M_PER_UM);
    }

    public static Distance fromNanoMeters(double v) {
        return new Distance(v * M_PER_NM);
    }

    public static Distance fromFeet(double v) {
        return new Distance(v * M_PER_FT);
    }

    public static Distance fromInches(double v) {
        return new Distance(v * M_PER_IN);
    }

    public static Distance fromYards(double v) {
        return new Distance(v * M_PER_YD);
    }

    public static Distance fromMiles(double v) {
        return new Distance(v * M_PER_MI);
    }

    public static Distance fromNauticalMiles(double v) {
        return new Distance(v * M_PER_NAUT_MI);
    }

    // get distance in various units
    public double meters() {
        return meters;
    }

    public double kilometers() {
        return meters * KM_PER_M;
    }

    public double centimeters() {
        return meters * CM_PER_M;
    }

    public double millimeters() {
        return meters * MM_PER_M;
    }

    public double micrometers() {
        return meters * UM_PER_M;
    }

    public double nanometers() {
        return meters * NM_PER_M;
    }

    public double feet() {
        return meters * FT_PER_M;
    }

    public double inches() {
        return meters * IN_PER_M;
    }

    public double yards() {
        return meters * YD_PER_M;
    }

    public double miles() {
        return meters * MI_PER_M;
    }

    public double nauticalMiles() {
        return meters * NAUT_MI_PER_M;
    }
}
