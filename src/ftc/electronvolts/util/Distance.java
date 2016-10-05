package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 10/4/16
 */

public class Distance {
    private static final double M_PER_KM = 1e+3;
    private static final double M_PER_CM = 1e-2;
    private static final double M_PER_MM = 1e-3;
    private static final double M_PER_UM = 1e-6;
    private static final double M_PER_NM = 1e-9;

    private static final double M_PER_FT = 0.3048;
    private static final double M_PER_IN = 0.0254;

    private static final double M_PER_YD = 0.9144;

    private static final double M_PER_MI = 1609.34;
    private static final double M_PER_NAUT_MI = 1852;

    private final double meters;

    private Distance(double meters) {
        this.meters = meters;
    }

    public Distance abs() {
        return new Distance(Math.abs(meters));
    }

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


    public double meters() {
        return meters;
    }

    public double kilometers() {
        return meters / M_PER_KM;
    }

    public double centimeters() {
        return meters / M_PER_CM;
    }

    public double millimeters() {
        return meters / M_PER_MM;
    }

    public double micrometers() {
        return meters / M_PER_UM;
    }

    public double nanometers() {
        return meters / M_PER_NM;
    }

    public double feet() {
        return meters / M_PER_FT;
    }

    public double inches() {
        return meters / M_PER_IN;
    }

    public double yards() {
        return meters / M_PER_YD;
    }

    public double miles() {
        return meters / M_PER_MI;
    }

    public double nauticalMiles() {
        return meters / M_PER_NAUT_MI;
    }
}
