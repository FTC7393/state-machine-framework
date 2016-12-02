package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.TeamColor;
import ftc.electronvolts.util.Vector2D;
import ftc.electronvolts.util.Vector3D;
import ftc.electronvolts.util.files.UtilConverters;

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

}
