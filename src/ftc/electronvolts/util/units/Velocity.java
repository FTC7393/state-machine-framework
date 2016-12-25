package ftc.electronvolts.util.units;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 10/5/16
 */

public class Velocity {
    private static final Velocity zero = new Velocity(Distance.zero());

    private final Distance distance;
    private final Time time;

    /**
     * Distance per Time
     * 
     * @param distance the Distance object
     * @param time the Time object
     */
    public Velocity(Distance distance, Time time) {
        this.distance = distance;
        this.time = time;
    }

    /**
     * Distance per 1 second
     * 
     * @param distance the Distance object
     */
    private Velocity(Distance distance) {
        this(distance, Time.fromSeconds(1));
    }

    /**
     * @return a Velocity with a value of 0
     */
    public static Velocity zero() {
        return zero;
    }

    public Distance getDistance(Time time) {
        // distance = velocity * time
        return Distance.fromMeters(metersPerSecond() * time.seconds());
    }

    public Time getTime(Distance distance) {
        // time = distance / velocity
        return Time.fromSeconds(distance.meters() / metersPerSecond());
    }

    public AngularVelocity getAngularVelocity(Distance radius) {
        //angular velocity = velocity / radius
        return new AngularVelocity(Angle.fromRadians(metersPerSecond() / radius.meters()), Time.fromSeconds(1));
    }

    public Velocity abs() {
        return new Velocity(distance.abs(), time.abs());
    }

    public double signum() {
        return Math.signum(metersPerSecond());
    }

    /**
     * Adds two Velocities together
     * 
     * @param velocity1 the first Velocity
     * @param velocity2 the second Velocity
     * @return the resulting Velocity
     */
    public static Velocity add(Velocity velocity1, Velocity velocity2) {
        return new Velocity(Distance.fromMeters(velocity1.metersPerSecond() + velocity2.metersPerSecond()));
    }

    /**
     * Subtracts velocity2 from velocity1
     * 
     * @param velocity1 the first Velocity
     * @param velocity2 the second Velocity
     * @return the resulting Velocity
     */
    public static Velocity subtract(Velocity velocity1, Velocity velocity2) {
        return new Velocity(Distance.fromMeters(velocity1.metersPerSecond() - velocity2.metersPerSecond()));
    }

    /**
     * Multiplies a Velocity by a number
     * 
     * @param velocity the Velocity
     * @param number the number to multiply by
     * @return the resulting Velocity
     */
    public static Velocity multiply(Velocity velocity, double number) {
        return new Velocity(Distance.fromMeters(velocity.metersPerSecond() * number));
    }

    /**
     * Divides a Velocity by a number
     * 
     * @param velocity the Velocity
     * @param number the number to divide by
     * @return the resulting Velocity
     */
    public static Velocity divide(Velocity velocity, double number) {
        return new Velocity(Distance.fromMeters(velocity.metersPerSecond() * number));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(metersPerSecond());
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Velocity other = (Velocity) obj;
        if (Double.doubleToLongBits(metersPerSecond()) != Double.doubleToLongBits(other.metersPerSecond())) return false;
        return true;
    }

    @Override
    public String toString() {
        return metersPerSecond() + " meters/second";
    }

    // get velocity in various units
    public double metersPerNanosecond() {
        return distance.meters() / time.nanoseconds();
    }

    public double metersPerMicrosecond() {
        return distance.meters() / time.microseconds();
    }

    public double metersPerMillisecond() {
        return distance.meters() / time.milliseconds();
    }

    public double metersPerSecond() {
        return distance.meters() / time.seconds();
    }

    public double metersPerMinute() {
        return distance.meters() / time.minutes();
    }

    public double metersPerHour() {
        return distance.meters() / time.hours();
    }

    public double metersPerDay() {
        return distance.meters() / time.days();
    }

    public double metersPerWeek() {
        return distance.meters() / time.weeks();
    }

    public double metersPerMonth() {
        return distance.meters() / time.months();
    }

    public double metersPerYear() {
        return distance.meters() / time.years();
    }

    public double kilometersPerNanosecond() {
        return distance.kilometers() / time.nanoseconds();
    }

    public double kilometersPerMicrosecond() {
        return distance.kilometers() / time.microseconds();
    }

    public double kilometersPerMillisecond() {
        return distance.kilometers() / time.milliseconds();
    }

    public double kilometersPerSecond() {
        return distance.kilometers() / time.seconds();
    }

    public double kilometersPerMinute() {
        return distance.kilometers() / time.minutes();
    }

    public double kilometersPerHour() {
        return distance.kilometers() / time.hours();
    }

    public double kilometersPerDay() {
        return distance.kilometers() / time.days();
    }

    public double kilometersPerWeek() {
        return distance.kilometers() / time.weeks();
    }

    public double kilometersPerMonth() {
        return distance.kilometers() / time.months();
    }

    public double kilometersPerYear() {
        return distance.kilometers() / time.years();
    }

    public double centimetersPerNanosecond() {
        return distance.centimeters() / time.nanoseconds();
    }

    public double centimetersPerMicrosecond() {
        return distance.centimeters() / time.microseconds();
    }

    public double centimetersPerMillisecond() {
        return distance.centimeters() / time.milliseconds();
    }

    public double centimetersPerSecond() {
        return distance.centimeters() / time.seconds();
    }

    public double centimetersPerMinute() {
        return distance.centimeters() / time.minutes();
    }

    public double centimetersPerHour() {
        return distance.centimeters() / time.hours();
    }

    public double centimetersPerDay() {
        return distance.centimeters() / time.days();
    }

    public double centimetersPerWeek() {
        return distance.centimeters() / time.weeks();
    }

    public double centimetersPerMonth() {
        return distance.centimeters() / time.months();
    }

    public double centimetersPerYear() {
        return distance.centimeters() / time.years();
    }

    public double millimetersPerNanosecond() {
        return distance.millimeters() / time.nanoseconds();
    }

    public double millimetersPerMicrosecond() {
        return distance.millimeters() / time.microseconds();
    }

    public double millimetersPerMillisecond() {
        return distance.millimeters() / time.milliseconds();
    }

    public double millimetersPerSecond() {
        return distance.millimeters() / time.seconds();
    }

    public double millimetersPerMinute() {
        return distance.millimeters() / time.minutes();
    }

    public double millimetersPerHour() {
        return distance.millimeters() / time.hours();
    }

    public double millimetersPerDay() {
        return distance.millimeters() / time.days();
    }

    public double millimetersPerWeek() {
        return distance.millimeters() / time.weeks();
    }

    public double millimetersPerMonth() {
        return distance.millimeters() / time.months();
    }

    public double millimetersPerYear() {
        return distance.millimeters() / time.years();
    }

    public double micrometersPerNanosecond() {
        return distance.micrometers() / time.nanoseconds();
    }

    public double micrometersPerMicrosecond() {
        return distance.micrometers() / time.microseconds();
    }

    public double micrometersPerMillisecond() {
        return distance.micrometers() / time.milliseconds();
    }

    public double micrometersPerSecond() {
        return distance.micrometers() / time.seconds();
    }

    public double micrometersPerMinute() {
        return distance.micrometers() / time.minutes();
    }

    public double micrometersPerHour() {
        return distance.micrometers() / time.hours();
    }

    public double micrometersPerDay() {
        return distance.micrometers() / time.days();
    }

    public double micrometersPerWeek() {
        return distance.micrometers() / time.weeks();
    }

    public double micrometersPerMonth() {
        return distance.micrometers() / time.months();
    }

    public double micrometersPerYear() {
        return distance.micrometers() / time.years();
    }

    public double nanometersPerNanosecond() {
        return distance.nanometers() / time.nanoseconds();
    }

    public double nanometersPerMicrosecond() {
        return distance.nanometers() / time.microseconds();
    }

    public double nanometersPerMillisecond() {
        return distance.nanometers() / time.milliseconds();
    }

    public double nanometersPerSecond() {
        return distance.nanometers() / time.seconds();
    }

    public double nanometersPerMinute() {
        return distance.nanometers() / time.minutes();
    }

    public double nanometersPerHour() {
        return distance.nanometers() / time.hours();
    }

    public double nanometersPerDay() {
        return distance.nanometers() / time.days();
    }

    public double nanometersPerWeek() {
        return distance.nanometers() / time.weeks();
    }

    public double nanometersPerMonth() {
        return distance.nanometers() / time.months();
    }

    public double nanometersPerYear() {
        return distance.nanometers() / time.years();
    }

    public double feetPerNanosecond() {
        return distance.feet() / time.nanoseconds();
    }

    public double feetPerMicrosecond() {
        return distance.feet() / time.microseconds();
    }

    public double feetPerMillisecond() {
        return distance.feet() / time.milliseconds();
    }

    public double feetPerSecond() {
        return distance.feet() / time.seconds();
    }

    public double feetPerMinunte() {
        return distance.feet() / time.minutes();
    }

    public double feetPerHour() {
        return distance.feet() / time.hours();
    }

    public double feetPerDay() {
        return distance.feet() / time.days();
    }

    public double feetPerWeek() {
        return distance.feet() / time.weeks();
    }

    public double feetPerMonth() {
        return distance.feet() / time.months();
    }

    public double feetPerYear() {
        return distance.feet() / time.years();
    }

    public double inchesPerNanosecond() {
        return distance.inches() / time.nanoseconds();
    }

    public double inchesPerMicrosecond() {
        return distance.inches() / time.microseconds();
    }

    public double inchesPerMillisecond() {
        return distance.inches() / time.milliseconds();
    }

    public double inchesPerSecond() {
        return distance.inches() / time.seconds();
    }

    public double inchesPerMinute() {
        return distance.inches() / time.minutes();
    }

    public double inchesPerHour() {
        return distance.inches() / time.hours();
    }

    public double inchesPerDay() {
        return distance.inches() / time.days();
    }

    public double inchesPerWeek() {
        return distance.inches() / time.weeks();
    }

    public double inchesPerMonth() {
        return distance.inches() / time.months();
    }

    public double inchesPerYear() {
        return distance.inches() / time.years();
    }

    public double yardsPerNanosecond() {
        return distance.yards() / time.nanoseconds();
    }

    public double yardsPerMicrosecond() {
        return distance.yards() / time.microseconds();
    }

    public double yardsPerMillisecond() {
        return distance.yards() / time.milliseconds();
    }

    public double yardsPerSecond() {
        return distance.yards() / time.seconds();
    }

    public double yardsPerMinute() {
        return distance.yards() / time.minutes();
    }

    public double yardsPerHour() {
        return distance.yards() / time.hours();
    }

    public double yardsPerDay() {
        return distance.yards() / time.days();
    }

    public double yardsPerWeek() {
        return distance.yards() / time.weeks();
    }

    public double yardsPerMonth() {
        return distance.yards() / time.months();
    }

    public double yardsPerYear() {
        return distance.yards() / time.years();
    }

    public double milesPerNanosecond() {
        return distance.miles() / time.nanoseconds();
    }

    public double milesPerMicrosecond() {
        return distance.miles() / time.microseconds();
    }

    public double milesPerMillisecond() {
        return distance.miles() / time.milliseconds();
    }

    public double milesPerSecond() {
        return distance.miles() / time.seconds();
    }

    public double milesPerMinute() {
        return distance.miles() / time.minutes();
    }

    public double milesPerHour() {
        return distance.miles() / time.hours();
    }

    public double milesPerDay() {
        return distance.miles() / time.days();
    }

    public double milesPerWeek() {
        return distance.miles() / time.weeks();
    }

    public double milesPerMonth() {
        return distance.miles() / time.months();
    }

    public double milesPerYear() {
        return distance.miles() / time.years();
    }

    public double nauticalMilesPerNanosecond() {
        return distance.nauticalMiles() / time.nanoseconds();
    }

    public double nauticalMilesPerMicrosecond() {
        return distance.nauticalMiles() / time.microseconds();
    }

    public double nauticalMilesPerMillisecond() {
        return distance.nauticalMiles() / time.milliseconds();
    }

    public double nauticalMilesPerSecond() {
        return distance.nauticalMiles() / time.seconds();
    }

    public double nauticalMilesPerMinute() {
        return distance.nauticalMiles() / time.minutes();
    }

    public double nauticalMilesPerHour() {
        return distance.nauticalMiles() / time.hours();
    }

    public double nauticalMilesPerDay() {
        return distance.nauticalMiles() / time.days();
    }

    public double nauticalMilesPerWeek() {
        return distance.nauticalMiles() / time.weeks();
    }

    public double nauticalMilesPerMonth() {
        return distance.nauticalMiles() / time.months();
    }

    public double nauticalMilesPerYear() {
        return distance.nauticalMiles() / time.years();
    }
}
