package ftc.electronvolts.test.util.units;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.units.Angle;

public class AngleTest {

    @Test
    public void testAbs() {
        assertEquals(2, Angle.fromRadians(-2).abs().radians(), 0);
        assertEquals(1, Angle.fromRadians(-1).abs().radians(), 0);
        assertEquals(0, Angle.fromRadians(0).abs().radians(), 0);
        assertEquals(1, Angle.fromRadians(1).abs().radians(), 0);
        assertEquals(2, Angle.fromRadians(2).abs().radians(), 0);

        assertEquals(2, Angle.fromDegrees(-2).abs().degrees(), 0);
        assertEquals(1, Angle.fromDegrees(-1).abs().degrees(), 0);
        assertEquals(0, Angle.fromDegrees(0).abs().degrees(), 0);
        assertEquals(1, Angle.fromDegrees(1).abs().degrees(), 0);
        assertEquals(2, Angle.fromDegrees(2).abs().degrees(), 0);
    }

    @Test
    public void testSignum() {
        assertEquals(-1, Angle.fromRadians(-2).signum(), 0);
        assertEquals(-1, Angle.fromRadians(-1).signum(), 0);
        assertEquals(0, Angle.fromRadians(0).signum(), 0);
        assertEquals(1, Angle.fromRadians(1).signum(), 0);
        assertEquals(1, Angle.fromRadians(2).signum(), 0);

        assertEquals(-1, Angle.fromDegrees(-2).signum(), 0);
        assertEquals(-1, Angle.fromDegrees(-1).signum(), 0);
        assertEquals(0, Angle.fromDegrees(0).signum(), 0);
        assertEquals(1, Angle.fromDegrees(1).signum(), 0);
        assertEquals(1, Angle.fromDegrees(2).signum(), 0);
    }

    @Test
    public void testAdd() {
        assertEquals(3, Angle.add(Angle.fromRadians(1), Angle.fromRadians(2)).radians(), 0);
        assertEquals(6, Angle.add(Angle.fromRadians(8), Angle.fromRadians(-2)).radians(), 0);
        assertEquals(97, Angle.add(Angle.fromRadians(-3), Angle.fromRadians(100)).radians(), 0);
        assertEquals(-15, Angle.add(Angle.fromRadians(5), Angle.fromRadians(-20)).radians(), 0);
        assertEquals(43, Angle.add(Angle.fromRadians(-7), Angle.fromRadians(50)).radians(), 0);

        assertEquals(43.5, Angle.add(Angle.fromRadians(-7.3), Angle.fromRadians(50.8)).radians(), 1e-10);
        assertEquals(15.83, Angle.add(Angle.fromRadians(6.78), Angle.fromRadians(9.05)).radians(), 1e-10);

        assertEquals(3, Angle.add(Angle.fromRotations(1), Angle.fromRotations(2)).rotations(), 1e-10);
        assertEquals(6, Angle.add(Angle.fromRotations(8), Angle.fromRotations(-2)).rotations(), 1e-10);

        assertEquals(97, Angle.add(Angle.fromDegrees(-3), Angle.fromDegrees(100)).degrees(), 1e-10);
        assertEquals(-15, Angle.add(Angle.fromDegrees(5), Angle.fromDegrees(-20)).degrees(), 1e-10);
        assertEquals(43, Angle.add(Angle.fromDegrees(-7), Angle.fromDegrees(50)).degrees(), 1e-10);

        assertEquals(43.5, Angle.add(Angle.fromDegrees(-7.3), Angle.fromDegrees(50.8)).degrees(), 1e-10);
        assertEquals(15.83, Angle.add(Angle.fromDegrees(6.78), Angle.fromDegrees(9.05)).degrees(), 1e-10);
    }

    @Test
    public void testSubtract() {
        assertEquals(-1, Angle.subtract(Angle.fromRadians(1), Angle.fromRadians(2)).radians(), 0);
        assertEquals(10, Angle.subtract(Angle.fromRadians(8), Angle.fromRadians(-2)).radians(), 0);
        assertEquals(-103, Angle.subtract(Angle.fromRadians(-3), Angle.fromRadians(100)).radians(), 0);
        assertEquals(25, Angle.subtract(Angle.fromRadians(5), Angle.fromRadians(-20)).radians(), 0);
        assertEquals(-57, Angle.subtract(Angle.fromRadians(-7), Angle.fromRadians(50)).radians(), 0);

        assertEquals(-58.1, Angle.subtract(Angle.fromRadians(-7.3), Angle.fromRadians(50.8)).radians(), 1e-10);
        assertEquals(-2.27, Angle.subtract(Angle.fromRadians(6.78), Angle.fromRadians(9.05)).radians(), 1e-10);

        assertEquals(-1, Angle.subtract(Angle.fromRotations(1), Angle.fromRotations(2)).rotations(), 1e-10);
        assertEquals(10, Angle.subtract(Angle.fromRotations(8), Angle.fromRotations(-2)).rotations(), 1e-10);

        assertEquals(-103, Angle.subtract(Angle.fromDegrees(-3), Angle.fromDegrees(100)).degrees(), 1e-10);
        assertEquals(25, Angle.subtract(Angle.fromDegrees(5), Angle.fromDegrees(-20)).degrees(), 1e-10);
        assertEquals(-57, Angle.subtract(Angle.fromDegrees(-7), Angle.fromDegrees(50)).degrees(), 1e-10);

        assertEquals(-58.1, Angle.subtract(Angle.fromDegrees(-7.3), Angle.fromDegrees(50.8)).degrees(), 1e-10);
        assertEquals(-2.27, Angle.subtract(Angle.fromDegrees(6.78), Angle.fromDegrees(9.05)).degrees(), 1e-10);
    }

    @Test
    public void testMultiply() {
        assertEquals(2, Angle.multiply(Angle.fromRadians(1), 2).radians(), 0);
        assertEquals(-16, Angle.multiply(Angle.fromRadians(8), -2).radians(), 0);
        assertEquals(-300, Angle.multiply(Angle.fromRadians(-3), 100).radians(), 0);
        assertEquals(-100, Angle.multiply(Angle.fromRadians(5), -20).radians(), 0);
        assertEquals(-350, Angle.multiply(Angle.fromRadians(-7), 50).radians(), 0);

        assertEquals(-370.84, Angle.multiply(Angle.fromRadians(-7.3), 50.8).radians(), 1e-10);
        assertEquals(61.359, Angle.multiply(Angle.fromRadians(6.78), 9.05).radians(), 1e-10);

        assertEquals(2, Angle.multiply(Angle.fromRotations(1), 2).rotations(), 0);
        assertEquals(-16, Angle.multiply(Angle.fromRotations(8), -2).rotations(), 0);

        assertEquals(-300, Angle.multiply(Angle.fromDegrees(-3), 100).degrees(), 0);
        assertEquals(-100, Angle.multiply(Angle.fromDegrees(5), -20).degrees(), 0);
        assertEquals(-350, Angle.multiply(Angle.fromDegrees(-7), 50).degrees(), 0);

        assertEquals(-370.84, Angle.multiply(Angle.fromDegrees(-7.3), 50.8).degrees(), 1e-10);
        assertEquals(61.359, Angle.multiply(Angle.fromDegrees(6.78), 9.05).degrees(), 1e-10);
    }

    @Test
    public void testDivide() {
        assertEquals(0.5, Angle.divide(Angle.fromRadians(1), 2).radians(), 0);
        assertEquals(-4, Angle.divide(Angle.fromRadians(8), -2).radians(), 0);
        assertEquals(-0.03, Angle.divide(Angle.fromRadians(-3), 100).radians(), 0);
        assertEquals(-0.25, Angle.divide(Angle.fromRadians(5), -20).radians(), 0);
        assertEquals(-0.14, Angle.divide(Angle.fromRadians(-7), 50).radians(), 0);

        assertEquals(-0.143700787402, Angle.divide(Angle.fromRadians(-7.3), 50.8).radians(), 1e-10);
        assertEquals(0.749171270718, Angle.divide(Angle.fromRadians(6.78), 9.05).radians(), 1e-10);

        assertEquals(0.5, Angle.divide(Angle.fromRotations(1), 2).rotations(), 1e-10);
        assertEquals(-4, Angle.divide(Angle.fromRotations(8), -2).rotations(), 1e-10);

        assertEquals(-0.03, Angle.divide(Angle.fromDegrees(-3), 100).degrees(), 1e-10);
        assertEquals(-0.25, Angle.divide(Angle.fromDegrees(5), -20).degrees(), 1e-10);
        assertEquals(-0.14, Angle.divide(Angle.fromDegrees(-7), 50).degrees(), 1e-10);

        assertEquals(-0.143700787402, Angle.divide(Angle.fromDegrees(-7.3), 50.8).degrees(), 1e-10);
        assertEquals(0.749171270718, Angle.divide(Angle.fromDegrees(6.78), 9.05).degrees(), 1e-10);
    }

    @Test
    public void testZero() {
        assertEquals(0, Angle.zero().radians(), 0);
        assertEquals(0, Angle.zero().degrees(), 0);
        assertEquals(0, Angle.zero().rotations(), 0);
    }

    @Test
    public void testEqualsObject() {
        assertNotEquals(Angle.zero(), null);
        assertEquals(Angle.fromRadians(Math.PI), Angle.fromDegrees(180));
    }

    @Test
    public void testFromRadians() {
        assertEquals(Math.PI, Angle.fromRadians(Math.PI).radians(), 0);
        assertEquals(180, Angle.fromRadians(Math.PI).degrees(), 0);
        assertEquals(0.5, Angle.fromRadians(Math.PI).rotations(), 0);

        assertEquals(2 * Math.PI, Angle.fromRadians(2 * Math.PI).radians(), 0);
        assertEquals(360, Angle.fromRadians(2 * Math.PI).degrees(), 0);
        assertEquals(1, Angle.fromRadians(2 * Math.PI).rotations(), 0);

        assertEquals(Math.PI / 4, Angle.fromRadians(Math.PI / 4).radians(), 0);
        assertEquals(45, Angle.fromRadians(Math.PI / 4).degrees(), 0);
        assertEquals(0.125, Angle.fromRadians(Math.PI / 4).rotations(), 0);
    }

    @Test
    public void testFromDegrees() {
        assertEquals(Math.PI, Angle.fromDegrees(180).radians(), 0);
        assertEquals(180, Angle.fromDegrees(180).degrees(), 0);
        assertEquals(0.5, Angle.fromDegrees(180).rotations(), 0);

        assertEquals(2 * Math.PI, Angle.fromDegrees(360).radians(), 0);
        assertEquals(360, Angle.fromDegrees(360).degrees(), 0);
        assertEquals(1, Angle.fromDegrees(360).rotations(), 0);

        assertEquals(Math.PI / 4, Angle.fromDegrees(45).radians(), 0);
        assertEquals(45, Angle.fromDegrees(45).degrees(), 0);
        assertEquals(0.125, Angle.fromDegrees(45).rotations(), 0);
    }

    @Test
    public void testFromRotations() {
        assertEquals(Math.PI, Angle.fromRotations(0.5).radians(), 0);
        assertEquals(180, Angle.fromRotations(0.5).degrees(), 0);
        assertEquals(0.5, Angle.fromRotations(0.5).rotations(), 0);

        assertEquals(2 * Math.PI, Angle.fromRotations(1).radians(), 0);
        assertEquals(360, Angle.fromRotations(1).degrees(), 0);
        assertEquals(1, Angle.fromRotations(1).rotations(), 0);

        assertEquals(Math.PI / 4, Angle.fromRotations(0.125).radians(), 0);
        assertEquals(45, Angle.fromRotations(0.125).degrees(), 0);
        assertEquals(0.125, Angle.fromRotations(0.125).rotations(), 0);
    }

}
