package ftc.electronvolts.util.units;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 10/5/16
 */

public class Time {
    /*
     * Constants to relate various units to seconds
     * The reciprocal of each constant is calculated once to be used multiple
     * times later
     */
    private static final double S_PER_NS = 1e-9;
    private static final double S_PER_US = 1e-6;
    private static final double S_PER_MS = 0.001;
    private static final double S_PER_MIN = 60;
    private static final double S_PER_HR = 3600;
    private static final double S_PER_DAY = 86400;
    private static final double S_PER_WEEK = 604800;
    private static final double S_PER_MONTH = 2.628e+6;
    private static final double S_PER_YEAR = 3.154e+7;

    private static final double NS_PER_S = 1 / S_PER_NS;
    private static final double US_PER_S = 1 / S_PER_US;
    private static final double MS_PER_S = 1 / S_PER_MS;
    private static final double MIN_PER_S = 1 / S_PER_MIN;
    private static final double HR_PER_S = 1 / S_PER_HR;
    private static final double DAY_PER_S = 1 / S_PER_DAY;
    private static final double WEEK_PER_S = 1 / S_PER_WEEK;
    private static final double MONTH_PER_S = 1 / S_PER_MONTH;
    private static final double YEAR_PER_S = 1 / S_PER_YEAR;

    private static final Time zero = new Time(0);

    // The time in seconds
    private final double seconds;

    /**
     * private constructor to create a Time object from a value in seconds
     * 
     * @param seconds the time in seconds
     */
    private Time(double seconds) {
        this.seconds = seconds;
    }

    /**
     * 
     * @return the absolute value of the time
     */
    public Time abs() {
        return new Time(Math.abs(seconds));
    }

    /**
     * 
     * @return the sign of the angle
     */
    public double signum() {
        return Math.signum(seconds);
    }

    /**
     * Adds two Times together
     * 
     * @param time1 the first Time object
     * @param time2 the second Time object
     * @return the resulting Time
     */
    public static Time add(Time time1, Time time2) {
        return new Time(time1.seconds + time2.seconds);
    }

    /**
     * Subtracts time2 from time1
     * 
     * @param time1 the first Time object
     * @param time2 the second Time object
     * @return the resulting Time
     */
    public static Time subtract(Time time1, Time time2) {
        return new Time(time1.seconds - time2.seconds);
    }

    /**
     * Multiplies a Time object by a number
     * 
     * @param time the Time object
     * @param number the number to multiply by
     * @return the resulting Time
     */
    public static Time multiply(Time time, double number) {
        return new Time(time.seconds * number);
    }

    /**
     * Divides a Time object by a number
     * 
     * @param time the Time object
     * @param number the number to divide by
     * @return the resulting Time
     */
    public static Time divide(Time time, double number) {
        return new Time(time.seconds / number);
    }

    /**
     * Create a Time that has a value of 0
     * 
     * @return the created Time
     */
    public static Time zero() {
        return zero;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(seconds);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Time other = (Time) obj;
        if (Double.doubleToLongBits(seconds) != Double.doubleToLongBits(other.seconds)) return false;
        return true;
    }

    @Override
    public String toString() {
        return seconds + " seconds";
    }

    // create Time objects from various units
    public static Time fromSeconds(double v) {
        return new Time(v);
    }

    public static Time fromNanoseconds(double v) {
        return new Time(v * S_PER_NS);
    }

    public static Time fromMicroseconds(double v) {
        return new Time(v * S_PER_US);
    }

    public static Time fromMilliseconds(double v) {
        return new Time(v * S_PER_MS);
    }

    public static Time fromMinutes(double v) {
        return new Time(v * S_PER_MIN);
    }

    public static Time fromHours(double v) {
        return new Time(v * S_PER_HR);
    }

    public static Time fromDays(double v) {
        return new Time(v * S_PER_DAY);
    }

    public static Time fromWeeks(double v) {
        return new Time(v * S_PER_WEEK);
    }

    public static Time fromMonths(double v) {
        return new Time(v * S_PER_MONTH);
    }

    public static Time fromYears(double v) {
        return new Time(v * S_PER_YEAR);
    }

    // get time in various units
    public double seconds() {
        return seconds;
    }

    public double nanoseconds() {
        return seconds * NS_PER_S;
    }

    public double microseconds() {
        return seconds * US_PER_S;
    }

    public double milliseconds() {
        return seconds * MS_PER_S;
    }

    public double minutes() {
        return seconds * MIN_PER_S;
    }

    public double hours() {
        return seconds * HR_PER_S;
    }

    public double days() {
        return seconds * DAY_PER_S;
    }

    public double weeks() {
        return seconds * WEEK_PER_S;
    }

    public double months() {
        return seconds * MONTH_PER_S;
    }

    public double years() {
        return seconds * YEAR_PER_S;
    }
}
