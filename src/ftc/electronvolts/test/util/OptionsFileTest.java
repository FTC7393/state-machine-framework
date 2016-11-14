package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ftc.electronvolts.util.OptionsFile;
import ftc.electronvolts.util.TeamColor;

public class OptionsFileTest {

    @Test
    public void testOptionsFile() {
        new OptionsFile();
    }

    @Test
    public void testOptionsFileMapOfStringString() {
        new OptionsFile(new HashMap<String, String>());
    }

    @Test
    public void testOptionsFileFile() {
        File file = new File("OptionsFile.txt");
        try {
            file.createNewFile();
            new OptionsFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        file.delete();
    }

    @Test
    public void testGetValues() {
        Map<String, String> m = new HashMap<>();
        m.put("A", "1");
        assertEquals(m, new OptionsFile(m).getValues());
    }

    @Test
    public void testAddStringString() {
        OptionsFile o = new OptionsFile();
        o.add("A", "text");
        assertEquals("text", o.getAsString("A"));
    }

    @Test
    public void testAddStringObject() {
        OptionsFile o = new OptionsFile();
        o.add("A", TeamColor.RED);
        assertEquals("RED", o.getAsString("A"));
    }

    @Test
    public void testAddStringInt() {
        OptionsFile o = new OptionsFile();
        o.add("A", 3);
        assertEquals(new Integer(3), o.getAsInteger("A", 0));
    }

    @Test
    public void testAddStringDouble() {
        OptionsFile o = new OptionsFile();
        o.add("A", 5.4);
        assertEquals(5.4, o.getAsDouble("A", 0.0), 0);
    }

    @Test
    public void testAddStringBoolean() {
        OptionsFile o = new OptionsFile();
        o.add("A", true);
        assertEquals(true, o.getAsBoolean("A", false));
    }

    @Test
    public void testGetAsString() {
        OptionsFile o = new OptionsFile();
        o.add("A", "text");
        assertEquals("text", o.getAsString("A"));
    }

    @Test
    public void testGetAsInteger() {
        OptionsFile o = new OptionsFile();
        o.add("A", "1");
        assertEquals(new Integer(1), o.getAsInteger("A", null));
    }

    @Test
    public void testGetAsDouble() {
        OptionsFile o = new OptionsFile();
        o.add("A", "1.2");
        assertEquals(new Double(1.2), o.getAsDouble("A", null));
    }

    @Test
    public void testGetAsBoolean() {
        OptionsFile o = new OptionsFile();
        o.add("A", "true");
        assertEquals(new Boolean(true), o.getAsBoolean("A", null));
    }

    @Test
    public void testWriteToFile() {
        File file = new File("OptionsFile.txt");
        
        OptionsFile o = new OptionsFile();
        o.add("A", "text");

        assertTrue(o.writeToFile(file));
        
        OptionsFile o2 = new OptionsFile(file);
        
        assertEquals("text", o2.getAsString("A"));
        
        file.delete();
    }

}
