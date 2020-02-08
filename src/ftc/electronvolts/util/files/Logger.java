package ftc.electronvolts.util.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ftc.electronvolts.util.InputExtractor;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * A logger that takes a list of Columns, which have a header and an
 * InputExtractor, and logs each of them to a column in a file
 */
public class Logger {
    public static class Column {
        private final String header;
        private final InputExtractor<?> input;

        public Column(String header, InputExtractor<?> input) {
            this.header = header;
            this.input = input;
        }
    }

    private static final DecimalFormat df = new DecimalFormat("#.#####");
    static {
        df.setRoundingMode(RoundingMode.HALF_UP);
    }
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

    private long logStart;
    private PrintStream fileStream;
    private final String beforeTimestamp, afterTimestamp;
    private String titles;
    private String fullFileName;
    private final List<Column> columns;

    /**
     * @param beforeTimestamp the text to put before the timestamp in the filename
     * @param afterTimestamp  the text to put after the timestamp in the filename
     * @param columns the columns that will be written to the file
     */
    public Logger(String beforeTimestamp, String afterTimestamp, List<Column> columns) {
        this.beforeTimestamp = beforeTimestamp;
        this.afterTimestamp = afterTimestamp;
        StringBuilder sb = new StringBuilder("time");
        for (Column column : columns) {
            sb.append(",").append(column.header);
        }
        titles = sb.append("\n").toString();
        this.columns = columns;
    }

    /**
     * write the column titles to the file
     */
    public boolean start(File dir) {
        logStart = System.nanoTime();
        
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        String date = dateFormat.format(now);

        fullFileName = beforeTimestamp + date + afterTimestamp;
        File file = new File(dir, fullFileName);
        try {
            fileStream = new PrintStream(new FileOutputStream(file));

            fileStream.printf(titles);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * write the input columns to the file
     */
    public void act() {
        if (fileStream != null) {
            long now = System.nanoTime();
            StringBuilder line = new StringBuilder(df.format(1e-6 * (now - logStart)));
            for (Column column : columns) {
                line.append(",").append(column.input.getValue());
            }
            fileStream.printf(line.append("\n").toString());
        }
    }

    /**
     * close the file
     */
    public void stop() {
        if (fileStream != null) {
            fileStream.close();
        }
    }

    public String getFileName() {
        return fullFileName;
    }
}