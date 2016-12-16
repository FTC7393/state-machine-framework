package ftc.electronvolts.test.util.files;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.Map;
import java.util.MissingResourceException;

import org.junit.Test;

import ftc.electronvolts.util.Vector2D;
import ftc.electronvolts.util.Vector3D;
import ftc.electronvolts.util.files.OptionsFile;
import ftc.electronvolts.util.files.UtilConverters;

public class OptionsFileTest {

    @Test
    public void testOptionsFileConvertersFile() {
        File file = new File("OptionsFile.txt");
        try {
            file.createNewFile();
            new OptionsFile(UtilConverters.getInstance(), file);
        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

        file.delete();
    }

    @Test
    public void testGetValues() {
        Map<String, String> m = new HashMap<>();
        m.put("A", "1");
        assertEquals(m, new OptionsFile(UtilConverters.getInstance(), m).getValues());
    }

    @Test
    public void testSetStringT() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        o.set("A", new Vector2D(3, 0.5));
        assertEquals(new Vector2D(3, 0.5), o.get("A", Vector2D.class));

        o.set("B", null);
        assertEquals(null, o.get("B", String.class));
    }

    @Test
    public void testSetStringBoolean() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        o.set("A", true);
        assertEquals(true, o.get("A", Boolean.class));

        o.set("B", false);
        assertEquals(false, o.get("B", Boolean.class));
    }

    @Test
    public void testSetStringByte() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());

        for (byte b = -20; b < 20; b += 4) {
            o.set("A", b);
            assertTrue(b == o.get("A", Byte.class));
        }
    }

    @Test
    public void testSetStringChar() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());

        for (int i = -20; i < 20; i += 4) {
            char c = (char) i;
            o.set("A", c);
            assertTrue(c == o.get("A", Character.class));
        }
    }

    @Test
    public void testSetStringShort() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());

        for (short i = -20; i < 20; i += 4) {
            o.set("A", i);
            assertTrue(i == o.get("A", Short.class));
        }
    }

    @Test
    public void testSetStringInt() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());

        for (int i = -20; i < 20; i += 4) {
            o.set("A", i);
            assertTrue(i == o.get("A", Integer.class));
        }
    }

    @Test
    public void testSetStringLong() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());

        for (long i = -20; i < 20; i += 4) {
            o.set("A", i);
            assertTrue(i == o.get("A", Long.class));
        }
    }

    @Test
    public void testSetStringFloat() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());

        for (float i = -20; i < 20; i += 4.3) {
            o.set("A", i);
            assertTrue(i == o.get("A", Float.class));
        }
    }

    @Test
    public void testSetStringDouble() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());

        for (double i = -20; i < 20; i += 4.3) {
            o.set("A", i);
            assertTrue(i == o.get("A", Double.class));
        }
    }

    @Test
    public void testGetTagFallback1() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        o.set("A", 3.0);
        assertEquals(3.0, o.get("A", 0.0), 0);

        o.set("A", true);
        assertEquals(true, o.get("A", false));
    }

    @Test(expected = MissingResourceException.class)
    public void testGetTagFallback2() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        o.get("A", new File(""));
    }

    @Test
    public void testGetTagClass1() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        o.set("A", 3.0);
        assertEquals(3.0, o.get("A", Double.class), 0);

        o.set("A", true);
        assertEquals(true, o.get("A", Boolean.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTagClass2() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());

        o.get("A", Boolean.class);
    }

    @Test(expected = NumberFormatException.class)
    public void testGetTagClass3() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        o.set("A", 0.2);
        o.get("A", Integer.class);
    }

    @Test(expected = IllegalFormatConversionException.class)
    public void testGetTagClass4() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        o.set("A", 0.2);
        o.get("A", Vector2D.class);
    }

    @Test(expected = MissingResourceException.class)
    public void testGetTagClass5() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        o.set("A", 0.2);
        o.get("A", File.class);
    }
    
    @Test
    public void testGetTagClassFallback() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        assertEquals(true, o.get("a", Boolean.class, true));
        assertEquals(false, o.get("a", Boolean.class, false));
        assertEquals(null, o.get("a", Boolean.class, null));
        o.set("a", true);
        assertEquals(true, o.get("a", Boolean.class, null));
        o.set("a", false);
        assertEquals(false, o.get("a", Boolean.class, null));
    }

    @Test
    public void testSetArrayGetArray() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        String s = "|";

        Integer[] array0 = new Integer[] { 1, 2, 3, -5 };
        o.setArray("A", array0, s);
        assertArrayEquals(array0, o.getArray("A", Integer.class, s));
        o.setArray("A", array0);
        assertArrayEquals(array0, o.getArray("A", Integer.class));

        Double[] array1 = new Double[] { 1.0, 2.2, 3.1, -5.6 };
        o.setArray("A", array1, s);
        assertArrayEquals(array1, o.getArray("A", Double.class, s));
        o.setArray("A", array1);
        assertArrayEquals(array1, o.getArray("A", Double.class));

        Vector3D[] array2 = new Vector3D[] { new Vector3D(-1.2, 2, 3.4), new Vector3D(8.3, -1, -1) };
        o.setArray("A", array2, s);
        assertArrayEquals(array2, o.getArray("A", Vector3D.class, s));

        String[] array3 = new String[] { "erg2345gwevf", "ygB7T867b", "8787tb87tbBT87B", "HGI7FGiutyf78" };
        o.setArray("A", array3, s);
        assertArrayEquals(array3, o.getArray("A", String.class, s));
        o.setArray("A", array3);
        assertArrayEquals(array3, o.getArray("A", String.class));
        
        String[] array4 = new String[] { "erg234,5=gwevf", "ygB7T-867b", "87====87tb87tbBT87B", "HGI7FGiu,,,,tyf78" };
        o.setArray("A", array4, s);
        assertArrayEquals(array4, o.getArray("A", String.class, s));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetArrayGetArray1() {
        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        
        String[] array = new String[] { "erg234,5=gwevf", "ygB7T-867b", "87====87tb87tbBT87B", "HGI7FGiu,,,,tyf78" };
        o.setArray("A", array);
    }

    @Test
    public void testWriteToFile() {
        File file = new File("OptionsFile.txt");

        OptionsFile o = new OptionsFile(UtilConverters.getInstance());
        o.set("A", "text");

        assertTrue(o.writeToFile(file));

        OptionsFile o2 = new OptionsFile(UtilConverters.getInstance(), file);

        assertEquals("text", o2.get("A", ""));

        file.delete();
    }

}
