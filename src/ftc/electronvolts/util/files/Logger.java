package ftc.electronvolts.util.files;

//import android.os.Environment;
//import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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

    private long logStart;
    private PrintStream fileStream;
    private final String fileName, fileExtension;
    private String titles;
    private String fullFileName;
    private final List<Column> columns;

    /**
     * @param fileName the name of the file
     * @param fileExtension the file's extension (.txt for example)
     * @param columns the columns that will be written to the file
     */
    public Logger(String fileName, String fileExtension, List<Column> columns) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        StringBuilder sb = new StringBuilder("time");
        for (Column column : columns) {
            sb.append("\t").append(column.header);
        }
        titles = sb.append("\n").toString();
        this.columns = columns;
    }

    /**
     * write the column titles to the file
     */
    public boolean start(File dir) {
        logStart = System.currentTimeMillis();

        fullFileName = fileName + logStart + fileExtension;
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
            long now = System.currentTimeMillis();
            StringBuilder line = new StringBuilder(String.valueOf(now - logStart));
            for (Column column : columns) {
                line.append("\t").append(column.input.getValue());
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