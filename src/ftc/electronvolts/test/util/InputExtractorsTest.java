package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.Function;
import ftc.electronvolts.util.Functions;
import ftc.electronvolts.util.InputExtractor;
import ftc.electronvolts.util.InputExtractors;

public class InputExtractorsTest {

    @Test
    public void testZero() {
        InputExtractor<Double> inputExtractor = InputExtractors.zero();
        assertEquals(0, inputExtractor.getValue(), 0);
    }

    @Test
    public void testConstant() {
        InputExtractor<Double> d = InputExtractors.constant(3.2);
        assertEquals(3.2, d.getValue(), 0);

        InputExtractor<Integer> i = InputExtractors.constant(-100);
        assertEquals(-100, i.getValue(), 0);

        InputExtractor<Long> l = InputExtractors.constant(-100000L);
        assertEquals(-100000, l.getValue(), 0);

        InputExtractor<InputExtractor<Boolean>> ie = InputExtractors.constant(InputExtractors.constant(true));
        assertEquals(true, ie.getValue().getValue());
    }
    
    @Test
    public void testLimit() {
        assertEquals(-1, InputExtractors.limit(-1, 2, InputExtractors.constant(-5.0)).getValue(), 0);
        assertEquals(-1, InputExtractors.limit(-1, 2, InputExtractors.constant(-1.0)).getValue(), 0);
        assertEquals(0, InputExtractors.limit(-1, 2, InputExtractors.constant(0.0)).getValue(), 0);
        assertEquals(1, InputExtractors.limit(-1, 2, InputExtractors.constant(1.0)).getValue(), 0);
        assertEquals(2, InputExtractors.limit(-1, 2, InputExtractors.constant(2.0)).getValue(), 0);
        assertEquals(2, InputExtractors.limit(-1, 2, InputExtractors.constant(3.0)).getValue(), 0);
    }

    @Test
    public void testMultiply() {
        InputExtractor<Double> d0, d1, d2;
        d0 = InputExtractors.constant(5.4);
        d1 = InputExtractors.constant(-3.2);

        d2 = InputExtractors.multiply(d0, -3.2);
        assertEquals(5.4 * -3.2, d2.getValue(), 0);

        d2 = InputExtractors.multiply(d0, d1);
        assertEquals(5.4 * -3.2, d2.getValue(), 0);
    }

    @Test
    public void testDivide() {
        InputExtractor<Double> d0, d1, d2;
        d0 = InputExtractors.constant(5.4);
        d1 = InputExtractors.constant(-3.2);

        d2 = InputExtractors.divide(d0, -3.2);
        assertEquals(5.4 / -3.2, d2.getValue(), 0);

        d2 = InputExtractors.divide(d0, d1);
        assertEquals(5.4 / -3.2, d2.getValue(), 0);

        d2 = InputExtractors.divide(5.4, d1);
        assertEquals(5.4 / -3.2, d2.getValue(), 0);
    }

    @Test
    public void testAdd() {
        InputExtractor<Double> d0, d1, d2;
        d0 = InputExtractors.constant(5.4);
        d1 = InputExtractors.constant(-3.2);

        d2 = InputExtractors.add(d0, -3.2);
        assertEquals(5.4 + -3.2, d2.getValue(), 0);

        d2 = InputExtractors.add(d0, d1);
        assertEquals(5.4 + -3.2, d2.getValue(), 0);
    }

    @Test
    public void testSubtract() {
        InputExtractor<Double> d0, d1, d2;
        d0 = InputExtractors.constant(5.4);
        d1 = InputExtractors.constant(-3.2);

        d2 = InputExtractors.subtract(d0, -3.2);
        assertEquals(5.4 - -3.2, d2.getValue(), 0);

        d2 = InputExtractors.subtract(d0, d1);
        assertEquals(5.4 - -3.2, d2.getValue(), 0);

        d2 = InputExtractors.subtract(5.4, d1);
        assertEquals(5.4 - -3.2, d2.getValue(), 0);
    }

    @Test
    public void testAbsolute() {
        InputExtractor<Double> d0, d1, d2;
        d0 = InputExtractors.constant(5.4);
        d1 = InputExtractors.constant(-3.2);

        d2 = InputExtractors.absolute(d0);
        assertEquals(5.4, d2.getValue(), 0);

        d2 = InputExtractors.absolute(d1);
        assertEquals(3.2, d2.getValue(), 0);
    }

    @Test
    public void testNegative() {
        InputExtractor<Double> d0, d1, d2;
        d0 = InputExtractors.constant(5.4);
        d1 = InputExtractors.constant(-3.2);

        d2 = InputExtractors.negative(d0);
        assertEquals(-5.4, d2.getValue(), 0);

        d2 = InputExtractors.negative(d1);
        assertEquals(3.2, d2.getValue(), 0);
    }

    @Test
    public void testFunction() {
        //y(x) = 3 + 2x + 1x^2
        Function f = Functions.polynomial(new double[] { 3, -2, 1 });
        InputExtractor<Double> d0 = InputExtractors.constant(2.0);
        InputExtractor<Double> d1 = InputExtractors.function(d0, f);
        assertEquals(3, d1.getValue(), 0);
    }

    @Test
    public void testNot() {
        InputExtractor<Boolean> b0, b1, b2;
        b0 = InputExtractors.constant(false);
        b1 = InputExtractors.constant(true);

        b2 = InputExtractors.not(b0);
        assertEquals(true, b2.getValue());
        
        b2 = InputExtractors.not(b1);
        assertEquals(false, b2.getValue());
    }

    @Test
    public void testAnd() {
        InputExtractor<Boolean> b0, b1, b2;
        b0 = InputExtractors.constant(false);
        b1 = InputExtractors.constant(true);

        b2 = InputExtractors.and(b0, b0);
        assertEquals(false, b2.getValue());

        b2 = InputExtractors.and(b0, b1);
        assertEquals(false, b2.getValue());

        b2 = InputExtractors.and(b1, b0);
        assertEquals(false, b2.getValue());

        b2 = InputExtractors.and(b1, b1);
        assertEquals(true, b2.getValue());
    }

    @Test
    public void testNand() {
        InputExtractor<Boolean> b0, b1, b2;
        b0 = InputExtractors.constant(false);
        b1 = InputExtractors.constant(true);

        b2 = InputExtractors.nand(b0, b0);
        assertEquals(true, b2.getValue());

        b2 = InputExtractors.nand(b0, b1);
        assertEquals(true, b2.getValue());

        b2 = InputExtractors.nand(b1, b0);
        assertEquals(true, b2.getValue());

        b2 = InputExtractors.nand(b1, b1);
        assertEquals(false, b2.getValue());
    }

    @Test
    public void testOr() {
        InputExtractor<Boolean> b0, b1, b2;
        b0 = InputExtractors.constant(false);
        b1 = InputExtractors.constant(true);

        b2 = InputExtractors.or(b0, b0);
        assertEquals(false, b2.getValue());

        b2 = InputExtractors.or(b0, b1);
        assertEquals(true, b2.getValue());

        b2 = InputExtractors.or(b1, b0);
        assertEquals(true, b2.getValue());

        b2 = InputExtractors.or(b1, b1);
        assertEquals(true, b2.getValue());
    }

    @Test
    public void testNor() {
        InputExtractor<Boolean> b0, b1, b2;
        b0 = InputExtractors.constant(false);
        b1 = InputExtractors.constant(true);

        b2 = InputExtractors.nor(b0, b0);
        assertEquals(true, b2.getValue());

        b2 = InputExtractors.nor(b0, b1);
        assertEquals(false, b2.getValue());

        b2 = InputExtractors.nor(b1, b0);
        assertEquals(false, b2.getValue());

        b2 = InputExtractors.nor(b1, b1);
        assertEquals(false, b2.getValue());
    }

    @Test
    public void testXor() {
        InputExtractor<Boolean> b0, b1, b2;
        b0 = InputExtractors.constant(false);
        b1 = InputExtractors.constant(true);

        b2 = InputExtractors.xor(b0, b0);
        assertEquals(false, b2.getValue());

        b2 = InputExtractors.xor(b0, b1);
        assertEquals(true, b2.getValue());

        b2 = InputExtractors.xor(b1, b0);
        assertEquals(true, b2.getValue());

        b2 = InputExtractors.xor(b1, b1);
        assertEquals(false, b2.getValue());
    }

    @Test
    public void testXnor() {
        InputExtractor<Boolean> b0, b1, b2;
        b0 = InputExtractors.constant(false);
        b1 = InputExtractors.constant(true);

        b2 = InputExtractors.xnor(b0, b0);
        assertEquals(true, b2.getValue());

        b2 = InputExtractors.xnor(b0, b1);
        assertEquals(false, b2.getValue());

        b2 = InputExtractors.xnor(b1, b0);
        assertEquals(false, b2.getValue());

        b2 = InputExtractors.xnor(b1, b1);
        assertEquals(true, b2.getValue());
    }
    

    @Test
    public void testFormat() {
        InputExtractor<Double> i = InputExtractors.constant(1000.234524356807456098345);
        InputExtractor<String> s = InputExtractors.format("%10f", i);
        assertEquals("1000.234524", s.getValue());
    }

}
