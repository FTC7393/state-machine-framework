package ftc.electronvolts.test.util.units;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.units.Distance;
import ftc.electronvolts.util.units.Time;
import ftc.electronvolts.util.units.Velocity;

public class VelocityTest {

    private static final Velocity fiveMetersPerSecond = new Velocity(Distance.fromMeters(5), Time.fromSeconds(1));
    private static final Velocity minusFiveMetersPerSecond = new Velocity(Distance.fromMeters(-5), Time.fromSeconds(1));

    private static final Velocity fiveFeetPerSecond = new Velocity(Distance.fromFeet(5), Time.fromSeconds(1));
    private static final Velocity minusFiveFeetPerSecond = new Velocity(Distance.fromFeet(-5), Time.fromSeconds(1));

    private static final Velocity fiveMilesPerHour = new Velocity(Distance.fromMiles(5), Time.fromHours(1));

    @Test
    public void testZero() {
        assertEquals(0, Velocity.zero().centimetersPerDay(), 0);
        assertEquals(0, Velocity.zero().feetPerSecond(), 0);
        assertEquals(0, Velocity.zero().inchesPerMinute(), 0);
        assertEquals(0, Velocity.zero().kilometersPerMonth(), 0);
        assertEquals(0, Velocity.zero().metersPerSecond(), 0);
        assertEquals(0, Velocity.zero().yardsPerYear(), 0);
        assertEquals(0, Velocity.zero().nauticalMilesPerMonth(), 0);
    }

    @Test
    public void testGetDistance() {
        assertEquals(7.5, fiveMetersPerSecond.getDistance(Time.fromSeconds(1.5)).meters(), 0);
        assertEquals(0, fiveMetersPerSecond.getDistance(Time.fromSeconds(0)).meters(), 0);
    }

    @Test
    public void testGetTime() {
        assertEquals(3, fiveMetersPerSecond.getTime(Distance.fromMeters(15)).seconds(), 0);
        assertEquals(0, fiveMetersPerSecond.getTime(Distance.fromMeters(0)).seconds(), 0);
    }

    @Test
    public void testAbs() {
        assertEquals(5, minusFiveMetersPerSecond.abs().metersPerSecond(), 0);
        assertEquals(0, Velocity.zero().abs().metersPerSecond(), 0);
        assertEquals(5, fiveMetersPerSecond.abs().metersPerSecond(), 0);

        assertEquals(5, minusFiveFeetPerSecond.abs().feetPerSecond(), 1e-10);
        assertEquals(0, Velocity.zero().abs().feetPerSecond(), 1e-10);
        assertEquals(5, fiveFeetPerSecond.abs().feetPerSecond(), 1e-10);
    }

    @Test
    public void testSignum() {
        assertEquals(-1, minusFiveMetersPerSecond.signum(), 0);
        assertEquals(0, Velocity.zero().signum(), 0);
        assertEquals(1, fiveMetersPerSecond.signum(), 0);

        assertEquals(-1, minusFiveFeetPerSecond.signum(), 0);
        assertEquals(0, Velocity.zero().signum(), 0);
        assertEquals(1, fiveFeetPerSecond.signum(), 0);
    }

    @Test
    public void testAdd() {
        assertEquals(3, Time.add(Time.fromSeconds(1), Time.fromSeconds(2)).seconds(), 0);
        assertEquals(-15, Time.add(Time.fromSeconds(5), Time.fromSeconds(-20)).seconds(), 0);
        assertEquals(43.5, Time.add(Time.fromSeconds(-7.3), Time.fromSeconds(50.8)).seconds(), 1e-10);
        assertEquals(15.83, Time.add(Time.fromSeconds(6.78), Time.fromSeconds(9.05)).seconds(), 1e-10);

        assertEquals(3, Time.add(Time.fromMinutes(1), Time.fromMinutes(2)).minutes(), 1e-10);
        assertEquals(-15, Time.add(Time.fromMinutes(5), Time.fromMinutes(-20)).minutes(), 1e-10);
        assertEquals(43.5, Time.add(Time.fromMinutes(-7.3), Time.fromMinutes(50.8)).minutes(), 1e-10);
        assertEquals(15.83, Time.add(Time.fromMinutes(6.78), Time.fromMinutes(9.05)).minutes(), 1e-10);

        assertEquals(3, Time.add(Time.fromHours(1), Time.fromHours(2)).hours(), 1e-10);
        assertEquals(-15, Time.add(Time.fromHours(5), Time.fromHours(-20)).hours(), 1e-10);
        assertEquals(43.5, Time.add(Time.fromHours(-7.3), Time.fromHours(50.8)).hours(), 1e-10);
        assertEquals(15.83, Time.add(Time.fromHours(6.78), Time.fromHours(9.05)).hours(), 1e-10);
    }

    @Test
    public void testSubtract() {
        assertEquals(-1, Time.subtract(Time.fromSeconds(1), Time.fromSeconds(2)).seconds(), 0);
        assertEquals(25, Time.subtract(Time.fromSeconds(5), Time.fromSeconds(-20)).seconds(), 0);
        assertEquals(-58.1, Time.subtract(Time.fromSeconds(-7.3), Time.fromSeconds(50.8)).seconds(), 1e-10);
        assertEquals(-2.27, Time.subtract(Time.fromSeconds(6.78), Time.fromSeconds(9.05)).seconds(), 1e-10);

        assertEquals(-1, Time.subtract(Time.fromMinutes(1), Time.fromMinutes(2)).minutes(), 1e-10);
        assertEquals(25, Time.subtract(Time.fromMinutes(5), Time.fromMinutes(-20)).minutes(), 1e-10);
        assertEquals(-58.1, Time.subtract(Time.fromMinutes(-7.3), Time.fromMinutes(50.8)).minutes(), 1e-10);
        assertEquals(-2.27, Time.subtract(Time.fromMinutes(6.78), Time.fromMinutes(9.05)).minutes(), 1e-10);

        assertEquals(-1, Time.subtract(Time.fromHours(1), Time.fromHours(2)).hours(), 1e-10);
        assertEquals(25, Time.subtract(Time.fromHours(5), Time.fromHours(-20)).hours(), 1e-10);
        assertEquals(-58.1, Time.subtract(Time.fromHours(-7.3), Time.fromHours(50.8)).hours(), 1e-10);
        assertEquals(-2.27, Time.subtract(Time.fromHours(6.78), Time.fromHours(9.05)).hours(), 1e-10);
    }

    @Test
    public void testMultiply() {
        assertEquals(2, Time.multiply(Time.fromSeconds(1), 2).seconds(), 0);
        assertEquals(-100, Time.multiply(Time.fromSeconds(5), -20).seconds(), 0);
        assertEquals(-370.84, Time.multiply(Time.fromSeconds(-7.3), 50.8).seconds(), 1e-10);
        assertEquals(61.359, Time.multiply(Time.fromSeconds(6.78), 9.05).seconds(), 1e-10);

        assertEquals(2, Time.multiply(Time.fromMinutes(1), 2).minutes(), 1e-10);
        assertEquals(-100, Time.multiply(Time.fromMinutes(5), -20).minutes(), 1e-10);
        assertEquals(-370.84, Time.multiply(Time.fromMinutes(-7.3), 50.8).minutes(), 1e-10);
        assertEquals(61.359, Time.multiply(Time.fromMinutes(6.78), 9.05).minutes(), 1e-10);

        assertEquals(2, Time.multiply(Time.fromHours(1), 2).hours(), 1e-10);
        assertEquals(-100, Time.multiply(Time.fromHours(5), -20).hours(), 1e-10);
        assertEquals(-370.84, Time.multiply(Time.fromHours(-7.3), 50.8).hours(), 1e-10);
        assertEquals(61.359, Time.multiply(Time.fromHours(6.78), 9.05).hours(), 1e-10);
    }

    @Test
    public void testDivide() {
        assertEquals(0.5, Time.divide(Time.fromSeconds(1), 2).seconds(), 0);
        assertEquals(-0.25, Time.divide(Time.fromSeconds(5), -20).seconds(), 0);
        assertEquals(-0.143700787402, Time.divide(Time.fromSeconds(-7.3), 50.8).seconds(), 1e-10);
        assertEquals(0.749171270718, Time.divide(Time.fromSeconds(6.78), 9.05).seconds(), 1e-10);

        assertEquals(0.5, Time.divide(Time.fromMinutes(1), 2).minutes(), 1e-10);
        assertEquals(-0.25, Time.divide(Time.fromMinutes(5), -20).minutes(), 1e-10);
        assertEquals(-0.143700787402, Time.divide(Time.fromMinutes(-7.3), 50.8).minutes(), 1e-10);
        assertEquals(0.749171270718, Time.divide(Time.fromMinutes(6.78), 9.05).minutes(), 1e-10);

        assertEquals(0.5, Time.divide(Time.fromHours(1), 2).hours(), 1e-10);
        assertEquals(-0.25, Time.divide(Time.fromHours(5), -20).hours(), 1e-10);
        assertEquals(-0.143700787402, Time.divide(Time.fromHours(-7.3), 50.8).hours(), 1e-10);
        assertEquals(0.749171270718, Time.divide(Time.fromHours(6.78), 9.05).hours(), 1e-10);
    }

    @Test
    public void testEqualsObject() {
        assertNotEquals(fiveMetersPerSecond, null);
        assertNotEquals(null, fiveMetersPerSecond);
        assertEquals(fiveMetersPerSecond, fiveMetersPerSecond);
        assertEquals(fiveMetersPerSecond, new Velocity(Distance.fromMeters(5), Time.fromSeconds(1)));
        assertEquals(fiveMetersPerSecond, new Velocity(Distance.fromMeters(10), Time.fromSeconds(2)));
    }

    @Test
    public void testMetersPerSecond() {
        assertEquals(5, fiveMetersPerSecond.metersPerSecond(), 0);
        assertEquals(16.4041994751, fiveMetersPerSecond.feetPerSecond(), 1e-10);
        assertEquals(11.1847, fiveMetersPerSecond.milesPerHour(), 1e-4);

        assertEquals(-5, minusFiveMetersPerSecond.metersPerSecond(), 0);
        assertEquals(-16.4041994751, minusFiveMetersPerSecond.feetPerSecond(), 1e-10);
        assertEquals(-11.1847, minusFiveMetersPerSecond.milesPerHour(), 1e-4);
    }

    @Test
    public void testFeetPerSecond() {
        assertEquals(1.524, fiveFeetPerSecond.metersPerSecond(), 0);
        assertEquals(5, fiveFeetPerSecond.feetPerSecond(), 1e-10);
        assertEquals(3.40909, fiveFeetPerSecond.milesPerHour(), 1e-4);

        assertEquals(-1.524, minusFiveFeetPerSecond.metersPerSecond(), 0);
        assertEquals(-5, minusFiveFeetPerSecond.feetPerSecond(), 1e-10);
        assertEquals(-3.40909, minusFiveFeetPerSecond.milesPerHour(), 1e-4);
    }

    @Test
    public void testMilesPerHour() {
        assertEquals(2.2352, fiveMilesPerHour.metersPerSecond(), 0);
        assertEquals(7.33333333333, fiveMilesPerHour.feetPerSecond(), 1e-10);
        assertEquals(5, fiveMilesPerHour.milesPerHour(), 0);
    }
    
    @Test
    public void testGetAngularVelocity(){
        assertEquals(0.5, fiveMetersPerSecond.getAngularVelocity(Distance.fromMeters(10)).radiansPerSecond(), 0);
    }
    

    @Test
    public void testNanometersPerYear() {
    	Velocity v = new Velocity(Distance.fromMiles(600), Time.fromHours(1));
    	assertEquals(8.45978496E18, v.nanometersPerYear(), 0);
    }
}
