package ftc.electronvolts.test.util.files;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.TeamColor;
import ftc.electronvolts.util.Vector2D;
import ftc.electronvolts.util.Vector3D;
import ftc.electronvolts.util.files.UtilConverters;
import ftc.electronvolts.util.units.Angle;
import ftc.electronvolts.util.units.AngularVelocity;
import ftc.electronvolts.util.units.Distance;
import ftc.electronvolts.util.units.Time;
import ftc.electronvolts.util.units.Velocity;

public class UtilConvertersTest {

    @Test
    public void testUtilConvertersTeamColor() {
        assertEquals(TeamColor.RED, UtilConverters.getInstance().getConverter(TeamColor.class).fromString("r"));
        assertEquals(TeamColor.BLUE, UtilConverters.getInstance().getConverter(TeamColor.class).fromString("b"));
        assertEquals(TeamColor.UNKNOWN, UtilConverters.getInstance().getConverter(TeamColor.class).fromString("u"));
        assertEquals("RED", UtilConverters.getInstance().getConverter(TeamColor.class).toString(TeamColor.RED));
    }

    @Test
    public void testUtilConvertersVector2D() {
        assertEquals(new Vector2D(0, 0), UtilConverters.getInstance().getConverter(Vector2D.class).fromString("(0,0)"));
        assertEquals(new Vector2D(-1.1, -3.4), UtilConverters.getInstance().getConverter(Vector2D.class).fromString("(-1.1,-3.4)"));

        assertEquals(new Vector2D(0, 0), UtilConverters.getInstance().getConverter(Vector2D.class).fromString("(0 , 0)"));
        assertEquals(new Vector2D(-1.1, -3.4), UtilConverters.getInstance().getConverter(Vector2D.class).fromString("(-1.1,   -3.4)"));

        assertEquals(null, UtilConverters.getInstance().getConverter(Vector2D.class).fromString("(-1.1,   -3.4"));
        assertEquals(null, UtilConverters.getInstance().getConverter(Vector2D.class).fromString("12345.0-12143"));
    }

    @Test
    public void testUtilConvertersVector3D() {
        assertEquals(new Vector3D(0, 0, 0), UtilConverters.getInstance().getConverter(Vector3D.class).fromString("(0, 0  ,0)"));
        assertEquals(new Vector3D(-1.1, 5.6, -3.4), UtilConverters.getInstance().getConverter(Vector3D.class).fromString("(-1.1 ,5.6,    -3.4)"));

        assertEquals(new Vector3D(0, 0, 0), UtilConverters.getInstance().getConverter(Vector3D.class).fromString("(0,0, 0)"));
        assertEquals(new Vector3D(-1.1, 5.6, -3.4), UtilConverters.getInstance().getConverter(Vector3D.class).fromString("(-1.1,     5.6,-3.4)"));

        assertEquals(null, UtilConverters.getInstance().getConverter(Vector3D.class).fromString("(-1.1,     5.6 -3.4)"));
        assertEquals(null, UtilConverters.getInstance().getConverter(Vector3D.class).fromString("(-1.1,3.4)"));
    }

    @Test
    public void testUtilConvertersAngle() {
        assertEquals(Angle.fromRadians(5), UtilConverters.getInstance().getConverter(Angle.class).fromString("5"));
        assertEquals(Angle.fromRadians(-1.23), UtilConverters.getInstance().getConverter(Angle.class).fromString("-1.23"));
    }

    @Test
    public void testUtilConvertersAngularVelocity() {
        assertEquals(new AngularVelocity(Angle.fromRadians(5), Time.fromSeconds(1)), UtilConverters.getInstance().getConverter(AngularVelocity.class).fromString("5"));
        assertEquals(new AngularVelocity(Angle.fromRadians(-1.23), Time.fromSeconds(1)), UtilConverters.getInstance().getConverter(AngularVelocity.class).fromString("-1.23"));
    }

    @Test
    public void testUtilConvertersDistance() {
        assertEquals(Distance.fromMeters(5), UtilConverters.getInstance().getConverter(Distance.class).fromString("5"));
        assertEquals(Distance.fromMeters(-1.23), UtilConverters.getInstance().getConverter(Distance.class).fromString("-1.23"));
    }

    @Test
    public void testUtilConvertersTime() {
        assertEquals(Time.fromSeconds(5), UtilConverters.getInstance().getConverter(Time.class).fromString("5"));
        assertEquals(Time.fromSeconds(-1.23), UtilConverters.getInstance().getConverter(Time.class).fromString("-1.23"));
    }

    @Test
    public void testUtilConvertersVelocity() {
        assertEquals(new Velocity(Distance.fromMeters(5), Time.fromSeconds(1)), UtilConverters.getInstance().getConverter(Velocity.class).fromString("5"));
        assertEquals(new Velocity(Distance.fromMeters(-1.23), Time.fromSeconds(1)), UtilConverters.getInstance().getConverter(Velocity.class).fromString("-1.23"));
    }

}
