package ftc.electronvolts.test.util.units;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.units.Distance;

public class DistanceTest {

    @Test
    public void testAbs() {
        assertEquals(2, Distance.fromMeters(-2).abs().meters(), 0);
        assertEquals(1, Distance.fromMeters(-1).abs().meters(), 0);
        assertEquals(0, Distance.fromMeters(0).abs().meters(), 0);
        assertEquals(1, Distance.fromMeters(1).abs().meters(), 0);
        assertEquals(2, Distance.fromMeters(2).abs().meters(), 0);

        assertEquals(2, Distance.fromFeet(-2).abs().feet(), 1e-10);
        assertEquals(1, Distance.fromFeet(-1).abs().feet(), 1e-10);
        assertEquals(0, Distance.fromFeet(0).abs().feet(), 1e-10);
        assertEquals(1, Distance.fromFeet(1).abs().feet(), 1e-10);
        assertEquals(2, Distance.fromFeet(2).abs().feet(), 1e-10);
    }

    @Test
    public void testSignum() {
        assertEquals(-1, Distance.fromMeters(-2).signum(), 0);
        assertEquals(-1, Distance.fromMeters(-1).signum(), 0);
        assertEquals(0, Distance.fromMeters(0).signum(), 0);
        assertEquals(1, Distance.fromMeters(1).signum(), 0);
        assertEquals(1, Distance.fromMeters(2).signum(), 0);

        assertEquals(-1, Distance.fromFeet(-2).signum(), 0);
        assertEquals(-1, Distance.fromFeet(-1).signum(), 0);
        assertEquals(0, Distance.fromFeet(0).signum(), 0);
        assertEquals(1, Distance.fromFeet(1).signum(), 0);
        assertEquals(1, Distance.fromFeet(2).signum(), 0);
    }

    @Test
    public void testAdd() {
        assertEquals(3, Distance.add(Distance.fromMeters(1), Distance.fromMeters(2)).meters(), 0);
        assertEquals(-15, Distance.add(Distance.fromMeters(5), Distance.fromMeters(-20)).meters(), 0);
        assertEquals(43.5, Distance.add(Distance.fromMeters(-7.3), Distance.fromMeters(50.8)).meters(), 1e-10);
        assertEquals(15.83, Distance.add(Distance.fromMeters(6.78), Distance.fromMeters(9.05)).meters(), 1e-10);

        assertEquals(3, Distance.add(Distance.fromFeet(1), Distance.fromFeet(2)).feet(), 1e-10);
        assertEquals(-15, Distance.add(Distance.fromFeet(5), Distance.fromFeet(-20)).feet(), 1e-10);
        assertEquals(43.5, Distance.add(Distance.fromFeet(-7.3), Distance.fromFeet(50.8)).feet(), 1e-10);
        assertEquals(15.83, Distance.add(Distance.fromFeet(6.78), Distance.fromFeet(9.05)).feet(), 1e-10);

        assertEquals(3, Distance.add(Distance.fromMiles(1), Distance.fromMiles(2)).miles(), 1e-10);
        assertEquals(-15, Distance.add(Distance.fromMiles(5), Distance.fromMiles(-20)).miles(), 1e-10);
        assertEquals(43.5, Distance.add(Distance.fromMiles(-7.3), Distance.fromMiles(50.8)).miles(), 1e-10);
        assertEquals(15.83, Distance.add(Distance.fromMiles(6.78), Distance.fromMiles(9.05)).miles(), 1e-10);
    }

    @Test
    public void testSubtract() {
        assertEquals(-1, Distance.subtract(Distance.fromMeters(1), Distance.fromMeters(2)).meters(), 0);
        assertEquals(25, Distance.subtract(Distance.fromMeters(5), Distance.fromMeters(-20)).meters(), 0);
        assertEquals(-58.1, Distance.subtract(Distance.fromMeters(-7.3), Distance.fromMeters(50.8)).meters(), 1e-10);
        assertEquals(-2.27, Distance.subtract(Distance.fromMeters(6.78), Distance.fromMeters(9.05)).meters(), 1e-10);

        assertEquals(-1, Distance.subtract(Distance.fromFeet(1), Distance.fromFeet(2)).feet(), 1e-10);
        assertEquals(25, Distance.subtract(Distance.fromFeet(5), Distance.fromFeet(-20)).feet(), 1e-10);
        assertEquals(-58.1, Distance.subtract(Distance.fromFeet(-7.3), Distance.fromFeet(50.8)).feet(), 1e-10);
        assertEquals(-2.27, Distance.subtract(Distance.fromFeet(6.78), Distance.fromFeet(9.05)).feet(), 1e-10);

        assertEquals(-1, Distance.subtract(Distance.fromMiles(1), Distance.fromMiles(2)).miles(), 1e-10);
        assertEquals(25, Distance.subtract(Distance.fromMiles(5), Distance.fromMiles(-20)).miles(), 1e-10);
        assertEquals(-58.1, Distance.subtract(Distance.fromMiles(-7.3), Distance.fromMiles(50.8)).miles(), 1e-10);
        assertEquals(-2.27, Distance.subtract(Distance.fromMiles(6.78), Distance.fromMiles(9.05)).miles(), 1e-10);
    }

    @Test
    public void testMultiply() {
        assertEquals(2, Distance.multiply(Distance.fromMeters(1), 2).meters(), 0);
        assertEquals(-100, Distance.multiply(Distance.fromMeters(5), -20).meters(), 0);
        assertEquals(-370.84, Distance.multiply(Distance.fromMeters(-7.3), 50.8).meters(), 1e-10);
        assertEquals(61.359, Distance.multiply(Distance.fromMeters(6.78), 9.05).meters(), 1e-10);

        assertEquals(2, Distance.multiply(Distance.fromFeet(1), 2).feet(), 1e-10);
        assertEquals(-100, Distance.multiply(Distance.fromFeet(5), -20).feet(), 1e-10);
        assertEquals(-370.84, Distance.multiply(Distance.fromFeet(-7.3), 50.8).feet(), 1e-10);
        assertEquals(61.359, Distance.multiply(Distance.fromFeet(6.78), 9.05).feet(), 1e-10);

        assertEquals(2, Distance.multiply(Distance.fromMiles(1), 2).miles(), 1e-10);
        assertEquals(-100, Distance.multiply(Distance.fromMiles(5), -20).miles(), 1e-10);
        assertEquals(-370.84, Distance.multiply(Distance.fromMiles(-7.3), 50.8).miles(), 1e-10);
        assertEquals(61.359, Distance.multiply(Distance.fromMiles(6.78), 9.05).miles(), 1e-10);
    }

    @Test
    public void testDivide() {
        assertEquals(0.5, Distance.divide(Distance.fromMeters(1), 2).meters(), 0);
        assertEquals(-0.25, Distance.divide(Distance.fromMeters(5), -20).meters(), 0);
        assertEquals(-0.143700787402, Distance.divide(Distance.fromMeters(-7.3), 50.8).meters(), 1e-10);
        assertEquals(0.749171270718, Distance.divide(Distance.fromMeters(6.78), 9.05).meters(), 1e-10);

        assertEquals(0.5, Distance.divide(Distance.fromFeet(1), 2).feet(), 1e-10);
        assertEquals(-0.25, Distance.divide(Distance.fromFeet(5), -20).feet(), 1e-10);
        assertEquals(-0.143700787402, Distance.divide(Distance.fromFeet(-7.3), 50.8).feet(), 1e-10);
        assertEquals(0.749171270718, Distance.divide(Distance.fromFeet(6.78), 9.05).feet(), 1e-10);

        assertEquals(0.5, Distance.divide(Distance.fromMiles(1), 2).miles(), 1e-10);
        assertEquals(-0.25, Distance.divide(Distance.fromMiles(5), -20).miles(), 1e-10);
        assertEquals(-0.143700787402, Distance.divide(Distance.fromMiles(-7.3), 50.8).miles(), 1e-10);
        assertEquals(0.749171270718, Distance.divide(Distance.fromMiles(6.78), 9.05).miles(), 1e-10);
    }

    @Test
    public void testZero() {
        assertEquals(0, Distance.zero().meters(), 0);
        assertEquals(0, Distance.zero().feet(), 0);
        assertEquals(0, Distance.zero().miles(), 0);
        assertEquals(0, Distance.zero().kilometers(), 0);
        assertEquals(0, Distance.zero().nanometers(), 0);
    }

    @Test
    public void testEqualsObject() {
        assertNotEquals(Distance.zero(), null);
        assertEquals(Distance.fromMeters(1), Distance.fromCentimeters(100));
    }

    @Test
    public void testFromMeters() {
        assertEquals(3, Distance.fromMeters(3).meters(), 0);
        assertEquals(9.84251968506, Distance.fromMeters(3).feet(), 1e-10);
        assertEquals(0.001864113576, Distance.fromMeters(3).miles(), 1e-10);

        assertEquals(-2, Distance.fromMeters(-2).meters(), 0);
        assertEquals(-6.56167979004, Distance.fromMeters(-2).feet(), 1e-10);
        assertEquals(-0.001242742384, Distance.fromMeters(-2).miles(), 1e-10);
    }

    @Test
    public void testFromFeet() {
        assertEquals(0.9144, Distance.fromFeet(3).meters(), 1e-10);
        assertEquals(3, Distance.fromFeet(3).feet(), 1e-10);
        assertEquals(0.000568181818182, Distance.fromFeet(3).miles(), 1e-10);

        assertEquals(-0.6096, Distance.fromFeet(-2).meters(), 1e-10);
        assertEquals(-2, Distance.fromFeet(-2).feet(), 1e-10);
        assertEquals(-0.000378787878788, Distance.fromFeet(-2).miles(), 1e-10);
    }

    @Test
    public void testFromMiles() {
        assertEquals(4828.032, Distance.fromMiles(3).meters(), 1e-10);
        assertEquals(15840, Distance.fromMiles(3).feet(), 1e-10);
        assertEquals(3, Distance.fromMiles(3).miles(), 1e-10);

        assertEquals(-3218.688, Distance.fromMiles(-2).meters(), 1e-10);
        assertEquals(-10560, Distance.fromMiles(-2).feet(), 1e-10);
        assertEquals(-2, Distance.fromMiles(-2).miles(), 1e-10);
    }
}
