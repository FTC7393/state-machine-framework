package ftc.electronvolts.test.util.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import ftc.electronvolts.util.units.Angle;
import ftc.electronvolts.util.units.AngularVelocity;
import ftc.electronvolts.util.units.Distance;
import ftc.electronvolts.util.units.Time;

public class AngularVelocityTest {

    private static final AngularVelocity fiveRadiansPerSecond = new AngularVelocity(Angle.fromRadians(5), Time.fromSeconds(1));
    private static final AngularVelocity minusFiveRadiansPerSecond = new AngularVelocity(Angle.fromRadians(-5), Time.fromSeconds(1));

    private static final AngularVelocity fiveDegreesPerSecond = new AngularVelocity(Angle.fromDegrees(5), Time.fromSeconds(1));
    private static final AngularVelocity minusFiveDegreesPerSecond = new AngularVelocity(Angle.fromDegrees(-5), Time.fromSeconds(1));

    private static final AngularVelocity fiveRotationsPerMinute = new AngularVelocity(Angle.fromRotations(5), Time.fromMinutes(1));
    private static final AngularVelocity minusFiveRotationsPerMinute = new AngularVelocity(Angle.fromRotations(-5), Time.fromMinutes(1));

    @Test
    public void testZero() {
        assertEquals(0, AngularVelocity.zero().radiansPerDay(), 0);
        assertEquals(0, AngularVelocity.zero().radiansPerMinute(), 0);
        assertEquals(0, AngularVelocity.zero().radiansPerSecond(), 0);

        assertEquals(0, AngularVelocity.zero().degreesPerSecond(), 0);
        assertEquals(0, AngularVelocity.zero().degreesPerMonth(), 0);
        assertEquals(0, AngularVelocity.zero().degreesPerYear(), 0);

        assertEquals(0, AngularVelocity.zero().rotationsPerMillisecond(), 0);
        assertEquals(0, AngularVelocity.zero().rotationsPerWeek(), 0);
        assertEquals(0, AngularVelocity.zero().rotationsPerMicrosecond(), 0);
    }

    @Test
    public void testGetDistance() {
        assertEquals(7.5, fiveRadiansPerSecond.getAngle(Time.fromSeconds(1.5)).radians(), 0);
        assertEquals(0, fiveRadiansPerSecond.getAngle(Time.fromSeconds(0)).radians(), 0);
    }

    @Test
    public void testGetTime() {
        assertEquals(3, fiveRadiansPerSecond.getTime(Angle.fromRadians(15)).seconds(), 0);
        assertEquals(0, fiveRadiansPerSecond.getTime(Angle.fromRadians(0)).seconds(), 0);
    }

    @Test
    public void testAbs() {
        assertEquals(5, minusFiveRadiansPerSecond.abs().radiansPerSecond(), 0);
        assertEquals(0, AngularVelocity.zero().abs().radiansPerSecond(), 0);
        assertEquals(5, fiveRadiansPerSecond.abs().radiansPerSecond(), 0);

        assertEquals(5, minusFiveDegreesPerSecond.abs().degreesPerSecond(), 1e-10);
        assertEquals(0, AngularVelocity.zero().abs().degreesPerSecond(), 1e-10);
        assertEquals(5, fiveDegreesPerSecond.abs().degreesPerSecond(), 1e-10);
    }

    @Test
    public void testSignum() {
        assertEquals(-1, minusFiveRadiansPerSecond.signum(), 0);
        assertEquals(0, AngularVelocity.zero().signum(), 0);
        assertEquals(1, fiveRadiansPerSecond.signum(), 0);

        assertEquals(-1, minusFiveDegreesPerSecond.signum(), 0);
        assertEquals(0, AngularVelocity.zero().signum(), 0);
        assertEquals(1, fiveDegreesPerSecond.signum(), 0);
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
        assertNotEquals(fiveRadiansPerSecond, null);
        assertNotEquals(null, fiveRadiansPerSecond);
        assertEquals(fiveRadiansPerSecond, fiveRadiansPerSecond);
        assertEquals(fiveRadiansPerSecond, new AngularVelocity(Angle.fromRadians(5), Time.fromSeconds(1)));
        assertEquals(fiveRadiansPerSecond, new AngularVelocity(Angle.fromRadians(10), Time.fromSeconds(2)));
    }

    @Test
    public void testRadiansPerSecond() {
        assertEquals(5, fiveRadiansPerSecond.radiansPerSecond(), 0);
        assertEquals(5.0 * 180 / Math.PI, fiveRadiansPerSecond.degreesPerSecond(), 1e-9);
        assertEquals(5.0 * 180 / Math.PI / 360 * 60, fiveRadiansPerSecond.rotationsPerMinute(), 1e-9);

        assertEquals(-5, minusFiveRadiansPerSecond.radiansPerSecond(), 0);
        assertEquals(-5.0 * 180 / Math.PI, minusFiveRadiansPerSecond.degreesPerSecond(), 1e-9);
        assertEquals(-5.0 * 180 / Math.PI / 360 * 60, minusFiveRadiansPerSecond.rotationsPerMinute(), 1e-9);
    }

    @Test
    public void testDegreesPerSecond() {
        assertEquals(5.0 / 180 * Math.PI, fiveDegreesPerSecond.radiansPerSecond(), 1e-9);
        assertEquals(5, fiveDegreesPerSecond.degreesPerSecond(), 0);
        assertEquals(0.833333333333334, fiveDegreesPerSecond.rotationsPerMinute(), 1e-9);

        assertEquals(-5.0 / 180 * Math.PI, minusFiveDegreesPerSecond.radiansPerSecond(), 1e-9);
        assertEquals(-5, minusFiveDegreesPerSecond.degreesPerSecond(), 0);
        assertEquals(-0.833333333333334, minusFiveDegreesPerSecond.rotationsPerMinute(), 1e-9);
    }

    @Test
    public void testRotationsPerMinute() {
        assertEquals(0.523598775, fiveRotationsPerMinute.radiansPerSecond(), 1e-9);
        assertEquals(30, fiveRotationsPerMinute.degreesPerSecond(), 1e-9);
        assertEquals(5, fiveRotationsPerMinute.rotationsPerMinute(), 0);

        assertEquals(-0.523598775, minusFiveRotationsPerMinute.radiansPerSecond(), 1e-9);
        assertEquals(-30, minusFiveRotationsPerMinute.degreesPerSecond(), 1e-9);
        assertEquals(-5, minusFiveRotationsPerMinute.rotationsPerMinute(), 0);
    }
    
    @Test
    public void testGetVelocity(){
        assertEquals(20, fiveRadiansPerSecond.getVelocity(Distance.fromMeters(4)).metersPerSecond(), 0);
    }
}
