package ftc.electronvolts.test.util;

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
