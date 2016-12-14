package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testJoinListOfTString() {
        List<String> list = new ArrayList<>();
        assertEquals("", Utility.join(list, "djhfaejlaekwrbfjladjv"));
        list.add("item 1");
        list.add("item 2");
        list.add("item 3");
        assertEquals("item 1, item 2, item 3", Utility.join(list, ", "));

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        assertEquals("1;2;3", Utility.join(list1, ";"));
    }

    @Test
    public void testJoinBooleanArrayString() {
        assertEquals("", Utility.join(new boolean[] {}, "32458y"));
        assertEquals("true", Utility.join(new boolean[] { true }, "34857028"));
        assertEquals("true-false-true-true", Utility.join(new boolean[] { true, false, true, true }, "-"));
    }

    @Test
    public void testJoinByteArrayString() {
        assertEquals("", Utility.join(new byte[] {}, "32458y"));
        assertEquals("100", Utility.join(new byte[] { 100 }, "34857028"));
        assertEquals("100 + 20 + -3 + -5 + -100", Utility.join(new byte[] { 100, 20, -3, -5, -100 }, " + "));
    }

    @Test
    public void testJoinCharArrayString() {
        assertEquals("", Utility.join(new char[] {}, "32458y"));
        assertEquals("a", Utility.join(new char[] { 'a' }, "34857028"));
        assertEquals("q//w//e//r//t//y", Utility.join(new char[] { 'q', 'w', 'e', 'r', 't', 'y' }, "//"));
    }

    @Test
    public void testJoinShortArrayString() {
        assertEquals("", Utility.join(new short[] {}, "32458y"));
        assertEquals("800", Utility.join(new short[] { 800 }, "34857028"));
        assertEquals("1 Mississippi 2 Mississippi 3 Mississippi 4 Mississippi 5", Utility.join(new short[] { 1, 2, 3, 4, 5 }, " Mississippi "));
    }

    @Test
    public void testJoinIntArrayString() {
        assertEquals("", Utility.join(new int[] {}, "32458y"));
        assertEquals("200000", Utility.join(new int[] { 200000 }, "34857028"));
        assertEquals("2345~567~123~678~43~67~789", Utility.join(new int[] { 2345, 567, 123, 678, 43, 67, 789 }, "~"));
    }

    @Test
    public void testJoinLongArrayString() {
        assertEquals("", Utility.join(new long[] {}, "32458y"));
        assertEquals("492843750", Utility.join(new long[] { 492843750 }, "34857028"));
        assertEquals("44#55#66", Utility.join(new long[] { 44, 55, 66 }, "#"));
    }

    @Test
    public void testJoinFloatArrayString() {
        assertEquals("", Utility.join(new float[] {}, "32458y"));
        assertEquals("1.23456", Utility.join(new float[] { 1.23456f }, "34857028"));
        assertEquals("3.3<>400.0<>123.123<>456.789", Utility.join(new float[] { 3.3f, 400f, 123.123f, 456.789f }, "<>"));
    }

    @Test
    public void testJoinDoubleArrayString() {
        assertEquals("", Utility.join(new double[] {}, "32458y"));
        assertEquals("55.1", Utility.join(new double[] { 55.1 }, "34857028"));
        assertEquals("123.0&33.5&10005.0&234.555", Utility.join(new double[] { 123, 33.5, 10005, 234.555 }, "&"));
    }
}
