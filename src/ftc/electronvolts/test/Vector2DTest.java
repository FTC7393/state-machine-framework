package ftc.electronvolts.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.Angle;
import ftc.electronvolts.util.Vector2D;

public class Vector2DTest {

    @Test
    public void testFromPolar2D() {
        Vector2D v;

        v = Vector2D.fromPolar2D(10, Angle.fromDegrees(30));
        assertEquals(5 * Math.sqrt(3), v.getX(), 1e-10);
        assertEquals(5, v.getY(), 1e-10);

        v = Vector2D.fromPolar2D(10, Angle.fromDegrees(90));
        assertEquals(0, v.getX(), 1e-10);
        assertEquals(10, v.getY(), 1e-10);
    }

    @Test
    public void testVector2D() {
        new Vector2D(3, 4);
    }

    @Test
    public void testGetX() {
        assertEquals(-21, new Vector2D(-21, 5).getX(), 0);
        assertEquals(0, new Vector2D(0, 8).getX(), 0);
        assertEquals(15, new Vector2D(15, -300).getX(), 0);
        assertEquals(4.3, new Vector2D(4.3, 0.5).getX(), 0);
    }

    @Test
    public void testGetY() {
        assertEquals(5, new Vector2D(-21, 5).getY(), 0);
        assertEquals(8, new Vector2D(0, 8).getY(), 0);
        assertEquals(-300, new Vector2D(15, -300).getY(), 0);
        assertEquals(0.5, new Vector2D(4.3, 0.5).getY(), 0);
    }

    @Test
    public void testGetLength() {
        assertEquals(Math.sqrt(466), new Vector2D(-21, 5).getLength(), 0);
        assertEquals(8, new Vector2D(0, 8).getLength(), 0);
        assertEquals(Math.sqrt(90225), new Vector2D(15, -300).getLength(), 0);
        assertEquals(Math.sqrt(18.74), new Vector2D(4.3, 0.5).getLength(), 0);
    }

    @Test
    public void testDotProduct() {
        assertEquals(5, Vector2D.dotProduct(new Vector2D(1, 2), new Vector2D(1,
                2)), 0);
        assertEquals(-35, Vector2D.dotProduct(new Vector2D(-1.5, 5),
                new Vector2D(100, 23)), 0);
    }

    @Test
    public void testGetDirection() {
        assertEquals(45, new Vector2D(1, 1).getDirection().degrees(), 0);
        assertEquals(135, new Vector2D(-1, 1).getDirection().degrees(), 0);
        assertEquals(-30, new Vector2D(Math.sqrt(3), -1).getDirection()
                .degrees(), 1e-10);
        assertEquals(0, new Vector2D(9, 0).getDirection().degrees(), 0);
        assertEquals(90, new Vector2D(0, 13).getDirection().degrees(), 0);
        assertEquals(180, new Vector2D(-9, 0).getDirection().degrees(), 0);
        assertEquals(-90, new Vector2D(0, -13).getDirection().degrees(), 0);
    }

}
