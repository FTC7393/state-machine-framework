package ftc.electronvolts.util;

//import android.os.Environment;
//import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * A logger that takes a list of InputExtractors and logs each on them in a
 * column in a file
 */
public class Logger {

    private long logStart;
    private PrintStream fileStream;
    private final String directory, fileName, fileExtension;
    private String titles;
    private String fullFileName;
    private final List<InputExtractor<?>> inputs;

    /**
     * @param directory the directory where the file will be
     * @param fileName the name of the file
     * @param fileExtension the file's extension (.txt for example)
     * @param inputNames the list of the column titles
     * @param inputs the list of input extractors
     */
    public Logger(String directory, String fileName, String fileExtension, List<String> inputNames, List<InputExtractor<?>> inputs) {
        this.directory = directory;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        StringBuilder sb = new StringBuilder("time");
        for (String name : inputNames) {
            sb.append("\t").append(name);
        }
        titles = sb.append("\n").toString();
        this.inputs = inputs;
    }

    // public boolean start(){
    // return start(Environment.getExternalStorageDirectory());
    // }

    /**
     * write the column titles to the file
     */
    public boolean start(File dir) {
        logStart = System.currentTimeMillis();

        fullFileName = fileName + logStart + fileExtension;
        File file = new File(dir + "/" + directory, fullFileName);
        try {
            fileStream = new PrintStream(new FileOutputStream(file));

            fileStream.printf(titles);
            return true;
        } catch (IOException e) {
            return false;
            // Log.e(fileName, "File cannot be opened");
        }
    }

    /**
     * write the input columns to the file
     */
    public void act() {
        long now = System.currentTimeMillis();
        if (fileStream != null) {
            StringBuilder line = new StringBuilder(String.valueOf(now
                    - logStart));
            for (InputExtractor<?> input : inputs) {
                line.append("\t").append(input.getValue());
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