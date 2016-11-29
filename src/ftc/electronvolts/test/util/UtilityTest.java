package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.Utility;

public class UtilityTest {

    @Test
    public void testLimit() {
        assertEquals(-2, Utility.limit(-5, -2, 3), 0);
        assertEquals(-2, Utility.limit(-5, 3, -2), 0);
        assertEquals(3, Utility.limit(3.2, -2, 3), 0);
        assertEquals(3, Utility.limit(3.2, 3, -2), 0);
        assertEquals(1, Utility.limit(1, -2, 3), 0);
        assertEquals(1, Utility.limit(1, 3, -2), 0);

        assertEquals(Double.NaN, Utility.limit(Double.NaN, -2, 3), 0);
    }

    @Test
    public void testMirrorLimit() {
        assertEquals(-1.5, Utility.mirrorLimit(-2, 1.5), 0);
        assertEquals(-1.5, Utility.mirrorLimit(-2, -1.5), 0);
        assertEquals(1.5, Utility.mirrorLimit(3, 1.5), 0);
        assertEquals(1.5, Utility.mirrorLimit(3, -1.5), 0);
        assertEquals(1, Utility.mirrorLimit(1, 1.5), 0);
        assertEquals(1, Utility.mirrorLimit(1, -1.5), 0);
    }

    @Test
    public void testMotorLimit() {
        assertEquals(-1, Utility.motorLimit(-1.0001), 0);
        assertEquals(-1, Utility.motorLimit(-1.5), 0);
        assertEquals(-1, Utility.motorLimit(-1), 0);
        assertEquals(-0.5, Utility.motorLimit(-0.5), 0);
        assertEquals(0, Utility.motorLimit(0), 0);
        assertEquals(0.5, Utility.motorLimit(0.5), 0);
        assertEquals(1, Utility.motorLimit(1), 0);
        assertEquals(1, Utility.motorLimit(1.5), 0);
        assertEquals(1, Utility.motorLimit(1.0001), 0);
    }

    @Test
    public void testServoLimit() {
        assertEquals(0, Utility.servoLimit(-1.5), 0);
        assertEquals(0, Utility.servoLimit(-1), 0);
        assertEquals(0, Utility.servoLimit(-0.5), 0);
        assertEquals(0, Utility.servoLimit(0), 0);
        assertEquals(0.5, Utility.servoLimit(0.5), 0);
        assertEquals(1, Utility.servoLimit(1), 0);
        assertEquals(1, Utility.servoLimit(1.5), 0);
    }

}
