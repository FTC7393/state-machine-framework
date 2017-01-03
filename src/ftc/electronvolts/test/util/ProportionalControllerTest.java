package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.ControlLoop;
import ftc.electronvolts.util.ProportionalController;

public class ProportionalControllerTest {

    @Test
    public void testProportionalController() {
        new ProportionalController(0.1, 0.3, 0.5, 0.7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProportionalController1() {
        new ProportionalController(-0.1, 0.3, 0.5, 0.7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProportionalController2() {
        new ProportionalController(0.1, -0.3, 0.5, 0.7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProportionalController3() {
        new ProportionalController(0.1, 0.3, -0.5, 0.7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProportionalController4() {
        new ProportionalController(0.1, 0.3, 0.5, -0.7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProportionalController5() {
        new ProportionalController(0.1, 0.3, 0.5, 0.4);
    }

    @Test
    public void testProportionalController6() {
        new ProportionalController(0.1, 0.3, 0.4, 0.5, 0.7);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testProportionalController7() {
        new ProportionalController(0.1, 0.3, -0.4, 0.5, 0.7);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testProportionalController8() {
        new ProportionalController(0.1, 0.4, 0.3, 0.5, 0.7);
    }

    @Test
    public void testComputeCorrection() {
        ControlLoop c = new ProportionalController(0.1, 3.1, 0.5, 0.7);
        assertEquals(0, c.computeCorrection(0, 1), 0);
        assertEquals(0, c.computeCorrection(0, 2), 0);
        assertEquals(0, c.computeCorrection(0, 3), 0);
        assertEquals(-0.5, c.computeCorrection(0, 4), 0);
        assertEquals(-0.5, c.computeCorrection(0, 5), 0);
        assertEquals(-0.6, c.computeCorrection(0, 6), 1e-10);
        assertEquals(-0.7, c.computeCorrection(0, 7), 0);
        assertEquals(-0.7, c.computeCorrection(0, 8), 0);
        assertEquals(-0.7, c.computeCorrection(0, 9), 0);

        assertEquals(0, c.computeCorrection(0, -1), 0);
        assertEquals(0, c.computeCorrection(0, -2), 0);
        assertEquals(0, c.computeCorrection(0, -3), 0);
        assertEquals(0.5, c.computeCorrection(0, -4), 0);
        assertEquals(0.5, c.computeCorrection(0, -5), 0);
        assertEquals(0.6, c.computeCorrection(0, -6), 1e-10);
        assertEquals(0.7, c.computeCorrection(0, -7), 0);
        assertEquals(0.7, c.computeCorrection(0, -8), 0);
        assertEquals(0.7, c.computeCorrection(0, -9), 0);
    }

    @Test
    public void testComputeCorrection1() {
        ControlLoop c = new ProportionalController(0.1, 3.1, 6, 0.5, 0.7);
        assertEquals(0, c.computeCorrection(0, 1), 0);
        assertEquals(0, c.computeCorrection(0, 2), 0);
        assertEquals(0, c.computeCorrection(0, 3), 0);
        assertEquals(-0.5, c.computeCorrection(0, 4), 0);
        assertEquals(-0.5, c.computeCorrection(0, 5), 0);
        assertEquals(-0.7, c.computeCorrection(0, 6), 1e-10);
        assertEquals(-0.7, c.computeCorrection(0, 7), 0);
        assertEquals(-0.7, c.computeCorrection(0, 8), 0);
        assertEquals(-0.7, c.computeCorrection(0, 9), 0);

        assertEquals(0, c.computeCorrection(0, -1), 0);
        assertEquals(0, c.computeCorrection(0, -2), 0);
        assertEquals(0, c.computeCorrection(0, -3), 0);
        assertEquals(0.5, c.computeCorrection(0, -4), 0);
        assertEquals(0.5, c.computeCorrection(0, -5), 0);
        assertEquals(0.7, c.computeCorrection(0, -6), 1e-10);
        assertEquals(0.7, c.computeCorrection(0, -7), 0);
        assertEquals(0.7, c.computeCorrection(0, -8), 0);
        assertEquals(0.7, c.computeCorrection(0, -9), 0);
    }

}
