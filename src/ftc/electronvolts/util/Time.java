package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 10/5/16
 */

public class Time {
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

    
    private final double seconds;
    
    private Time(double seconds) {
        this.seconds = seconds;
    }

    public Time abs() {
        return new Time(Math.abs(seconds));
    }

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
