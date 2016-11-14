package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.DeadZones;
import ftc.electronvolts.util.Function;
import ftc.electronvolts.util.Functions;

public class FunctionsTest {

    @Test
    public void testConstant() {
        for (int c = -10; c < 10; c++) {
            Function f = Functions.constant(c);
            for (int x = -10; x < 10; x++) {
                assertEquals(c, f.f(x), 0);
            }
        }
    }

    @Test
    public void testLinear() {
        // y(x) = -3x + 4.5
        Function f = Functions.linear(-3, 4.5);
        assertEquals(13.5, f.f(-3), 0);
        assertEquals(10.5, f.f(-2), 0);
        assertEquals(9, f.f(-1.5), 0);
        assertEquals(7.5, f.f(-1), 0);
        assertEquals(4.5, f.f(0), 0);
        assertEquals(1.5, f.f(1), 0);
        assertEquals(0, f.f(1.5), 0);
        assertEquals(-1.5, f.f(2), 0);
        assertEquals(-4.5, f.f(3), 0);
    }

    @Test
    public void testQuadratic() {
        Function f = Functions.quadratic(-2, 3, 4);
        assertEquals(-226, f.f(-10), 0);
        assertEquals(-1, f.f(-1), 0);
        assertEquals(4, f.f(0), 0);
        assertEquals(5, f.f(1), 0);
        assertEquals(-166, f.f(10), 0);
    }

    @Test
    public void testCubic() {
        Function f = Functions.cubic(1, -2, 3, 4);
        assertEquals(-1226, f.f(-10), 0);
        assertEquals(-2, f.f(-1), 0);
        assertEquals(4, f.f(0), 0);
        assertEquals(6, f.f(1), 0);
        assertEquals(1000 - 166, f.f(10), 0);
    }

    @Test
    public void testPolynomial() {
        Function f;

        // y(x) = 0
        f = Functions.polynomial(new double[] {});
        assertEquals(0, f.f(-10), 0);
        assertEquals(0, f.f(-1), 0);
        assertEquals(0, f.f(0), 0);
        assertEquals(0, f.f(1), 0);
        assertEquals(0, f.f(10), 0);

        // y(x) = 5
        f = Functions.polynomial(new double[] { 5 });
        assertEquals(5, f.f(-10), 0);
        assertEquals(5, f.f(-1), 0);
        assertEquals(5, f.f(0), 0);
        assertEquals(5, f.f(1), 0);
        assertEquals(5, f.f(10), 0);

        // y(x) = 2x + 1
        f = Functions.polynomial(new double[] { 1, 2 });
        assertEquals(-19, f.f(-10), 0);
        assertEquals(-1, f.f(-1), 0);
        assertEquals(1, f.f(0), 0);
        assertEquals(3, f.f(1), 0);
        assertEquals(21, f.f(10), 0);

        // y(x) = x^5 + 4
        f = Functions.polynomial(new double[] { 4, 0, 0, 0, 0, 1 });
        assertEquals(-100000 + 4, f.f(-10), 0);
        assertEquals(3, f.f(-1), 0);
        assertEquals(4, f.f(0), 0);
        assertEquals(5, f.f(1), 0);
        assertEquals(100000 + 4, f.f(10), 0);
    }

    @Test
    public void testSquared() {
        // note: the sign is retained
        Function f = Functions.squared();
        assertEquals(-9, f.f(-3), 0);
        assertEquals(0, f.f(0), 0);
        assertEquals(1, f.f(1), 0);
        assertEquals(4, f.f(2), 0);
    }

    @Test
    public void testCubed() {
        Function f = Functions.cubed();
        assertEquals(-27, f.f(-3), 0);
        assertEquals(0, f.f(0), 0);
        assertEquals(1, f.f(1), 0);
        assertEquals(8, f.f(2), 0);
    }

    @Test
    public void testDeadzone() {
        Function f = Functions.deadzone(DeadZones.deltaDeadZone(0, 0.1));
        assertEquals(-3, f.f(-3), 0);
        assertEquals(0, f.f(-0.1), 0);
        assertEquals(0, f.f(-0.05), 0);
        assertEquals(0, f.f(0), 0);
        assertEquals(0, f.f(0.05), 0);
        assertEquals(0, f.f(0.1), 0);
        assertEquals(2, f.f(2), 0);

    }

    @Test
    public void testLimit() {
        Function f = Functions.limit(-3, -2);
        assertEquals(-3, f.f(-3.9), 0);
        assertEquals(-3, f.f(-3), 0);
        assertEquals(-2.5, f.f(-2.5), 0);
        assertEquals(-2, f.f(-2), 0);
        assertEquals(-2, f.f(-1.1), 0);
    }

    @Test
    public void testComposite() {
        Function f = Functions.deadzone(DeadZones.deltaDeadZone(0, 0.1));
        Function g = Functions.limit(-1, 1);

        Function y = Functions.composite(f, g);

        assertEquals(-1, y.f(-2.1), 0);
        assertEquals(-1, y.f(-1), 0);
        assertEquals(-0.5, y.f(-0.5), 0);
        assertEquals(0, y.f(-0.1), 0);
        assertEquals(0, y.f(0), 0);
        assertEquals(0, y.f(0.1), 0);
        assertEquals(0.5, y.f(0.5), 0);
        assertEquals(1, y.f(1), 0);
        assertEquals(1, y.f(5), 0);

        // f(x) = 2x^2 - 3x + 0.5
        f = Functions.quadratic(2, -3, 0.5);
        // g(x) = -2x + 5
        g = Functions.linear(-2, 5);

        // y(x) = g(f(x))
        y = Functions.composite(f, g);
        // y(x) = -2f(x) + 5
        // y(x) = -2(2x^2 - 3x + 0.5) + 5
        // y(x) = -4x^2 + 6x - 1 + 5
        // y(x) = -4x^2 + 6x + 4
        Function test = Functions.quadratic(-4, 6, 4);

        for (int x = -10; x < 10; x++) {
            assertEquals(test.f(x), y.f(x), 0);
        }
    }

    @Test
    public void testNone() {
        Function f = Functions.none();
        assertEquals(-3.9, f.f(-3.9), 0);
        assertEquals(-3, f.f(-3), 0);
        assertEquals(-2.5, f.f(-2.5), 0);
        assertEquals(-2, f.f(-2), 0);
        assertEquals(-1.1, f.f(-1.1), 0);
        assertEquals(1000, f.f(1000), 0);
    }

    @Test
    public void testLinear2() {
        // y(x) = -0.2x
        Function f = Functions.linear(-0.2);
        assertEquals(0.4, f.f(-2), 0);
        assertEquals(0, f.f(0), 0);
        assertEquals(-1, f.f(5), 0);
        assertEquals(-20, f.f(100), 0);
    }

    @Test
    public void testLogarithmic() {
        for (int i = 0; i < 10; i++) {
            testIncreasing(Functions.logarithmic(i));
        }
    }

    private void testIncreasing(Function f) {
        assertEquals(-1, f.f(-1), 0);
        assertEquals(0, f.f(0), 0);
        assertEquals(1, f.f(1), 0);
        double last = -1;
        for (int i = -9; i < 10; i++) {
            double x = i / 10.0;
            double y = f.f(x);
            // System.out.println(x + "\t" + y);
            assertTrue(y > last);
            if (x > 0) {
                assertEquals(0.5, y, 0.5); // between 0 and 1
            } else {
                assertEquals(-0.5, y, 0.5); // between -1 and 0
            }
            last = y;
        }
    }

    @Test
    public void testPiecewise() {
        for (int i = 0; i < 10; i++) {
            testIncreasing(Functions.piecewise(i / 10.0, 0.2, 1));
        }
    }

    @Test
    public void testEBased() {
        for (int i = -10; i < 10; i++) {
            testIncreasing(Functions.eBased(i / 2.0));
        }
    }

}
