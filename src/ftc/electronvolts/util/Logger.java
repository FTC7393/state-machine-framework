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
 * A logger that takes a list of InputExtractors and logs each on them in a column in a file
 */
public class Logger {

    private long logStart;
    private PrintStream fileStream;
    private final String directory, fileName, fileExtension;
    private String titles;
    private final List<InputExtractor<?>> inputs;

    /**
     * @param directory     the directory where the file will be
     * @param fileName      the name of the file
     * @param fileExtension the file's extension (.txt for example)
     * @param inputNames    the list of the column titles
     * @param inputs        the list of input extractors
     */
    public Logger(String directory, String fileName, String fileExtension, List<String> inputNames, List<InputExtractor<?>> inputs) {
        this.directory = directory;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        titles = "time";
        for (String name : inputNames) {
            titles = titles + "\t" + name;
        }
        this.inputs = inputs;
    }

//    public boolean start(){
//        return start(Environment.getExternalStorageDirectory());
//    }

    /**
     * write the column titles to the file
     */
    public boolean start(File dir) {
        logStart = System.currentTimeMillis();

        File file = new File(dir + "/" + directory, fileName + logStart + fileExtension);
        try {
            fileStream = new PrintStream(new FileOutputStream(file));

            fileStream.printf(titles + "\n");
            return true;
        } catch (IOException e) {
            return false;
//            Log.e(fileName, "File cannot be opened");
        }
    }

    /**
     * write the input columns to the file
     */
    public void act() {
        long now = System.currentTimeMillis();
        if (fileStream != null) {
            String line = String.valueOf(now - logStart);
            for (InputExtractor<?> input : inputs) {
                line += String.valueOf(input.getValue());
            }
            fileStream.printf(line + "\n");
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
}