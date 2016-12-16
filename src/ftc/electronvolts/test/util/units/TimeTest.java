package ftc.electronvolts.test.util.units;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.units.Time;

public class TimeTest {

    @Test
    public void testAbs() {
        assertEquals(2, Time.fromSeconds(-2).abs().seconds(), 0);
        assertEquals(1, Time.fromSeconds(-1).abs().seconds(), 0);
        assertEquals(0, Time.fromSeconds(0).abs().seconds(), 0);
        assertEquals(1, Time.fromSeconds(1).abs().seconds(), 0);
        assertEquals(2, Time.fromSeconds(2).abs().seconds(), 0);

        assertEquals(2, Time.fromMinutes(-2).abs().minutes(), 1e-10);
        assertEquals(1, Time.fromMinutes(-1).abs().minutes(), 1e-10);
        assertEquals(0, Time.fromMinutes(0).abs().minutes(), 1e-10);
        assertEquals(1, Time.fromMinutes(1).abs().minutes(), 1e-10);
        assertEquals(2, Time.fromMinutes(2).abs().minutes(), 1e-10);
    }

    @Test
    public void testSignum() {
        assertEquals(-1, Time.fromSeconds(-2).signum(), 0);
        assertEquals(-1, Time.fromSeconds(-1).signum(), 0);
        assertEquals(0, Time.fromSeconds(0).signum(), 0);
        assertEquals(1, Time.fromSeconds(1).signum(), 0);
        assertEquals(1, Time.fromSeconds(2).signum(), 0);

        assertEquals(-1, Time.fromMinutes(-2).signum(), 0);
        assertEquals(-1, Time.fromMinutes(-1).signum(), 0);
        assertEquals(0, Time.fromMinutes(0).signum(), 0);
        assertEquals(1, Time.fromMinutes(1).signum(), 0);
        assertEquals(1, Time.fromMinutes(2).signum(), 0);
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
    public void testZero() {
        assertEquals(0, Time.zero().seconds(), 0);
        assertEquals(0, Time.zero().minutes(), 0);
        assertEquals(0, Time.zero().hours(), 0);
        assertEquals(0, Time.zero().days(), 0);
        assertEquals(0, Time.zero().nanoseconds(), 0);
    }

    @Test
    public void testEqualsObject() {
        assertNotEquals(Time.zero(), null);
        assertEquals(Time.fromMinutes(2), Time.fromSeconds(120));
    }

    @Test
    public void testFromSeconds() {
        assertEquals(9, Time.fromSeconds(9).seconds(), 0);
        assertEquals(0.15, Time.fromSeconds(9).minutes(), 0);
        assertEquals(0.0025, Time.fromSeconds(9).hours(), 0);
        
        assertEquals(-18, Time.fromSeconds(-18).seconds(), 0);
        assertEquals(-0.3, Time.fromSeconds(-18).minutes(), 0);
        assertEquals(-0.005, Time.fromSeconds(-18).hours(), 0);
    }

    @Test
    public void testFromMinutes() {
        assertEquals(540, Time.fromMinutes(9).seconds(), 0);
        assertEquals(9, Time.fromMinutes(9).minutes(), 0);
        assertEquals(0.15, Time.fromMinutes(9).hours(), 0);
        
        assertEquals(-1080, Time.fromMinutes(-18).seconds(), 0);
        assertEquals(-18, Time.fromMinutes(-18).minutes(), 0);
        assertEquals(-0.3, Time.fromMinutes(-18).hours(), 0);
    }

    @Test
    public void testFromHours() {
        assertEquals(32400, Time.fromHours(9).seconds(), 0);
        assertEquals(540, Time.fromHours(9).minutes(), 0);
        assertEquals(9, Time.fromHours(9).hours(), 0);
        
        assertEquals(-64800, Time.fromHours(-18).seconds(), 0);
        assertEquals(-1080, Time.fromHours(-18).minutes(), 0);
        assertEquals(-18, Time.fromHours(-18).hours(), 0);
    }

}
