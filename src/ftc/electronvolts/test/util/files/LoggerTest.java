package ftc.electronvolts.test.util.files;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import ftc.electronvolts.util.InputExtractor;
import ftc.electronvolts.util.files.Logger;
import ftc.electronvolts.util.files.Logger.Column;

public class LoggerTest {

    @Test
    public void testLogger() {
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("state", new InputExtractor<String>() {
            @Override
            public String getValue() {
                return "SAMPLE_TEXT";
            }
        }));

        columns.add(new Column("sensor", new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return 0.5;
            }
        }));

        Logger l = new Logger("log", ".txt", columns);
        l.start(new File("."));
        l.act();
        l.act();
        l.stop();
        File f = new File(l.getFileName());
        Scanner s = null;
        try {
            s = new Scanner(f);
            assertEquals("time", s.next());
            assertEquals("state", s.next());
            assertEquals("sensor", s.next());

            assertEquals(1, Double.parseDouble(s.next()), 3);
            assertEquals("SAMPLE_TEXT", s.next());
            assertEquals(0.5, Double.parseDouble(s.next()), 0);

            assertEquals(3, Double.parseDouble(s.next()), 3);
            assertEquals("SAMPLE_TEXT", s.next());
            assertEquals(0.5, Double.parseDouble(s.next()), 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("File not found");
        } finally {
            f.delete();
            if (s != null) {
                s.close();
            }
            // System.out.println("file deleted");
        }
    }

}
