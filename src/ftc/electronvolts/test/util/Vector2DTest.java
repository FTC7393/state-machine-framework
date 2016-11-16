package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.Vector2D;
import ftc.electronvolts.util.units.Angle;

public class Vector2DTest {

    @Test
    public void testVector2DDoubleAngle() {
        Vector2D v;

        v = new Vector2D(10, Angle.fromDegrees(30));
        assertEquals(5 * Math.sqrt(3), v.getX(), 1e-10);
        assertEquals(5, v.getY(), 1e-10);

        v = new Vector2D(10, Angle.fromDegrees(90));
        assertEquals(0, v.getX(), 1e-10);
        assertEquals(10, v.getY(), 1e-10);
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
    public void testNormalized() {
        assertEquals(1, new Vector2D(1, -2).normalized().getLength(), 1e-10);
        assertEquals(1, new Vector2D(-4.3, 5).normalized().getLength(), 1e-10);
        assertEquals(1, new Vector2D(6.8, Angle.fromDegrees(10)).normalized().getLength(), 1e-10);
    }

    @Test
    public void testDotProduct() {
        assertEquals(5, Vector2D.dotProduct(new Vector2D(1, 2), new Vector2D(1, 2)), 0);
        assertEquals(-35, Vector2D.dotProduct(new Vector2D(-1.5, 5), new Vector2D(100, 23)), 0);
    }

    @Test
    public void testGetDirection() {
        assertEquals(45, new Vector2D(1, 1).getDirection().degrees(), 0);
        assertEquals(135, new Vector2D(-1, 1).getDirection().degrees(), 0);
        assertEquals(-30, new Vector2D(Math.sqrt(3), -1).getDirection().degrees(), 1e-10);
        assertEquals(0, new Vector2D(9, 0).getDirection().degrees(), 0);
        assertEquals(90, new Vector2D(0, 13).getDirection().degrees(), 0);
        assertEquals(180, new Vector2D(-9, 0).getDirection().degrees(), 0);
        assertEquals(-90, new Vector2D(0, -13).getDirection().degrees(), 0);
    }

    @Test
    public void testSignedAngularSeparation() {
        Vector2D v1;
        Vector2D v2;

        v1 = new Vector2D(1, 2);
        v2 = new Vector2D(1, 2);
        assertEquals(0, Vector2D.signedAngularSeparation(v1, v2).degrees(), 1e-10);

        v1 = new Vector2D(0, 2);
        v2 = new Vector2D(2, 0);
        assertEquals(-90, Vector2D.signedAngularSeparation(v1, v2).degrees(), 1e-10);

        v1 = new Vector2D(1, Angle.zero());
        v2 = new Vector2D(2, Angle.fromDegrees(50));
        assertEquals(50, Vector2D.signedAngularSeparation(v1, v2).degrees(), 1e-10);

        v1 = new Vector2D(1, Angle.zero());
        v2 = new Vector2D(2, Angle.fromRadians(2));
        assertEquals(2, Vector2D.signedAngularSeparation(v1, v2).radians(), 1e-10);

    }
    
    @Test
    public void testToString(){
        assertEquals("(2.0, 3.0)", new Vector2D(2,3).toString());
    }

}
