package ftc.electronvolts.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by vandejd1 on 5/1/16.
 * FTC Team EV 7393
 */
public class Logger {

    private long logStart;
    private File file;
    private PrintStream fileStream;
    private final String directory, fileName, fileExtension;
    private String titles, line;
    private final List<InputExtractor> inputs;

    public Logger(String directory, String fileName, String fileExtension, List<String> inputNames, List<InputExtractor> inputs){
        this.directory = directory;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        titles = "time";
        for(String name : inputNames){
            titles = titles + "\t" + name;
        }
        this.inputs = inputs;
    }

    public void start(){
        logStart = System.currentTimeMillis();

        file = new File(Environment.getExternalStorageDirectory() + "/" + directory, fileName + logStart + fileExtension);
        try {
            fileStream = new PrintStream(new FileOutputStream(file));

            fileStream.printf(titles + "\n");
        } catch (IOException e) {
            Log.e(fileName, "File cannot be opened");
        }
    }

    public void act(){
        long now = System.currentTimeMillis();
        if (fileStream != null){
            line = String.valueOf(now - logStart);
            for(InputExtractor input : inputs){
                line += String.valueOf(input.getValue());
            }
            fileStream.printf(line + "\n");
        }
    }

    public void stop(){
        if (fileStream != null){
            fileStream.close();
        }
    }
}