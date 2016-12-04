package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.ControlLoop;
import ftc.electronvolts.util.PIDController;

public class PIDControllerTest {

    public static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPIDController() {
        new PIDController(0.1, 0, 0, 1);
        new PIDController(0.1, 0.1, 0.1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPIDController1() {
        new PIDController(-0.1, -0.1, -0.1, 10);
    }

    @Test
    public void testComputeCorrection() {
        ControlLoop pid;

        pid = new PIDController(0.1, 0, 0, 1);
        assertEquals(0, pid.computeCorrection(0, 0), 0);
        delay(10);
        assertTrue(pid.computeCorrection(0, -1) > 0);
        delay(10);
        assertTrue(pid.computeCorrection(0, 1) < 0);

        pid = new PIDController(0.1, 0.1, 0.1, 1);
        assertEquals(0, pid.computeCorrection(0, 0), 0);
        delay(10);
        assertTrue(pid.computeCorrection(0, -1) > 0);
        delay(10);
        assertTrue(pid.computeCorrection(0, 1) < 0);
        delay(10);
        assertTrue(pid.computeCorrection(0, 1) < 0);
        delay(10);
        assertTrue(pid.computeCorrection(0, 0) < 0);
    }

    @Test
    public void testInitialize() {
        ControlLoop pid;

        pid = new PIDController(0.1, 0.1, 0.1, 1);
        assertEquals(0, pid.computeCorrection(0, 0), 0);
        delay(10);
        assertTrue(pid.computeCorrection(0, -1) > 0);
        delay(10);
        assertTrue(pid.computeCorrection(0, 1) < 0);
        pid.initialize();
        delay(10);
        assertTrue(pid.computeCorrection(0, 0) == 0);
    }

}
