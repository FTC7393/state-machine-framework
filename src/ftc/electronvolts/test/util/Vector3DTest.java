package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.Vector2D;
import ftc.electronvolts.util.Vector3D;
import ftc.electronvolts.util.units.Angle;

public class Vector3DTest {

    @Test
    public void testFromPolar2D() {
        Vector3D v;

        v = Vector3D.fromPolar2D(10, Angle.fromDegrees(30));
        assertEquals(5 * Math.sqrt(3), v.getX(), 1e-10);
        assertEquals(5, v.getY(), 1e-10);
        assertEquals(0, v.getZ(), 0);

        v = Vector3D.fromPolar2D(10, Angle.fromDegrees(90));
        assertEquals(0, v.getX(), 1e-10);
        assertEquals(10, v.getY(), 1e-10);
        assertEquals(0, v.getZ(), 0);
    }

    @Test
    public void testFrom2D() {
        Vector3D v;

        v = Vector3D.from2D(new Vector2D(1.1, -2.2));
        assertEquals(1.1, v.getX(), 1e-10);
        assertEquals(-2.2, v.getY(), 1e-10);
        assertEquals(0, v.getZ(), 0);

        v = Vector3D.from2D(new Vector2D(54, -100.08));
        assertEquals(54, v.getX(), 1e-10);
        assertEquals(-100.08, v.getY(), 1e-10);
        assertEquals(0, v.getZ(), 0);
    }

    @Test
    public void testGetX() {
        assertEquals(-21, new Vector3D(-21, 5, 3.2).getX(), 0);
        assertEquals(0, new Vector3D(0, 8.8, 0).getX(), 0);
        assertEquals(15.02, new Vector3D(15.02, -300, 4).getX(), 0);
        assertEquals(4.3, new Vector3D(4.3, 0, 0.5).getX(), 0);
    }

    @Test
    public void testGetY() {
        assertEquals(5, new Vector3D(-21, 5, 3.2).getY(), 0);
        assertEquals(8.8, new Vector3D(0, 8.8, 0).getY(), 0);
        assertEquals(-300, new Vector3D(15.02, -300, 4).getY(), 0);
        assertEquals(0, new Vector3D(4.3, 0, 0.5).getY(), 0);
    }

    @Test
    public void testGetZ() {
        assertEquals(3.2, new Vector3D(-21, 5, 3.2).getZ(), 0);
        assertEquals(0, new Vector3D(0, 8.8, 0).getZ(), 0);
        assertEquals(4, new Vector3D(15.02, -300, 4).getZ(), 0);
        assertEquals(0.5, new Vector3D(4.3, 0, 0.5).getZ(), 0);
    }

    @Test
    public void testGetLength() {
        assertEquals(Math.sqrt(476.24), new Vector3D(-21, 5, 3.2).getLength(), 0);
        assertEquals(8.8, new Vector3D(0, 8.8, 0).getLength(), 0);
        assertEquals(Math.sqrt(90241.6004), new Vector3D(15.02, -300, 4).getLength(), 0);
        assertEquals(Math.sqrt(18.74), new Vector3D(4.3, 0, 0.5).getLength(), 0);
    }

    @Test
    public void testGetPhi() {
        assertEquals(10, new Vector3D(2, Angle.fromDegrees(5), Angle.fromDegrees(10)).getPhi().degrees(), 0);
        assertEquals(90, new Vector3D(2, 3, 0).getPhi().degrees(), 0);
        assertEquals(45, new Vector3D(0, 1, 1).getPhi().degrees(), 1e-10);
    }

    @Test
    public void testGetTheta() {
        assertEquals(5, new Vector3D(2, Angle.fromDegrees(5), Angle.fromDegrees(10)).getTheta().degrees(), 0);
        assertEquals(45, new Vector3D(2, 2, -8.123).getTheta().degrees(), 0);
        assertEquals(90, new Vector3D(0, 1, 5.56).getTheta().degrees(), 1e-10);
    }

    @Test
    public void testNormalized() {
        assertEquals(1, new Vector3D(1, -2, 3).normalized().getLength(), 1e-10);
        assertEquals(1, new Vector3D(-4.3, 5, 18.1).normalized().getLength(), 1e-10);
        assertEquals(1, new Vector3D(6.8, Angle.fromDegrees(10), Angle.fromRadians(1.1)).normalized().getLength(), 1e-10);
    }

    @Test
    public void testCrossProduct() {
        Vector3D v;

        v = Vector3D.crossProduct(new Vector3D(1, 2, 3), new Vector3D(2.2, -3.4, 5.67));
        assertEquals(21.54, v.getX(), 1e-10);
        assertEquals(0.93, v.getY(), 1e-10);
        assertEquals(-7.8, v.getZ(), 1e-10);
        
        assertEquals(22.92763616, v.getLength(), 1e-6);
        assertEquals(2.472237788, v.getTheta().degrees(), 1e-6);
        assertEquals(109.8891079, v.getPhi().degrees(), 1e-6);

        v = Vector3D.crossProduct(new Vector3D(-1.5, 0, 5), new Vector3D(-4, 100, 23));
        assertEquals(-500, v.getX(), 1e-10);
        assertEquals(14.5, v.getY(), 1e-10);
        assertEquals(-150, v.getZ(), 1e-10);
        
        assertEquals(522.2166696, v.getLength(), 1e-6);
        assertEquals(178.338888, v.getTheta().degrees(), 1e-6);
        assertEquals(106.6926171, v.getPhi().degrees(), 1e-6);
    }

    @Test
    public void testDotProduct() {
        assertEquals(12.41, Vector3D.dotProduct(new Vector3D(1, 2, 3), new Vector3D(2.2, -3.4, 5.67)), 1e-10);
        assertEquals(121, Vector3D.dotProduct(new Vector3D(-1.5, 0, 5), new Vector3D(-4, 100, 23)), 1e-10);
    }

    @Test
    public void testAngularSeparation() {
        Vector3D v1;
        Vector3D v2;

        v1 = new Vector3D(1, 2, 3);
        v2 = new Vector3D(1, 2, 3);
        assertEquals(0, Vector3D.angularSeparation(v1, v2).degrees(), 1e-10);

        v1 = new Vector3D(0, 2, 0);
        v2 = new Vector3D(2, 0, 0);
        assertEquals(90, Vector3D.angularSeparation(v1, v2).degrees(), 1e-10);

        v1 = Vector3D.fromPolar2D(1, Angle.zero());
        v2 = Vector3D.fromPolar2D(2, Angle.fromDegrees(50));
        assertEquals(50, Vector3D.angularSeparation(v1, v2).degrees(), 1e-10);

        v1 = Vector3D.fromPolar2D(1, Angle.zero());
        v2 = Vector3D.fromPolar2D(2, Angle.fromRadians(2));
        assertEquals(2, Vector3D.angularSeparation(v1, v2).radians(), 1e-10);
    }


    
    @Test
    public void testToString(){
        assertEquals("(2.0, 3.0, 4.0)", new Vector3D(2,3,4).toString());
    }
}
