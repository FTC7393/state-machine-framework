package ftc.electronvolts.util;

//import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * This class stores and retrieves values from a file.
 * It should probably be replaced by an XML or JSON interpreter.
 */
public class OptionsFile {
    private Map<String, String> values;

    public OptionsFile() {
        values = new HashMap<>();
    }

    public OptionsFile(Map<String, String> values) {
        this.values = values;
    }

//    /**
//     * retrieve an OptionsFile from a file
//     * @param filename the file to read from
//     * @return the OptionsFile
//     */
//    public static OptionsFile fromFile(String filename){
//        File file = new File(Environment.getExternalStorageDirectory(), filename);
//        return fromFile(file);
//    }

    /**
     * retrieve an OptionsFile from a file
     *
     * @param file the file to read from
     * @return the OptionsFile
     */
    public static OptionsFile fromFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Map<String, String> newvalues = new HashMap<>();
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        String[] elements = line.split("=");
                        String key = elements[0].trim();
                        String value = elements[1].trim();
                        newvalues.put(key, value);
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new OptionsFile(newvalues);
        } catch (FileNotFoundException e1) {
            return new OptionsFile();
        }
    }

    /**
     * @return A map of all the values from the file
     */
    public Map<String, String> getValues() {
        return values;
    }

    /**
     * add a value to the map
     *
     * @param tag   the name of the value
     * @param value the value
     */
    public void add(String tag, String value) {
        values.put(tag, value);
    }

    /**
     * Get the value as a String
     *
     * @param tag the tag to get
     * @return the value associated with the tag
     */
    public String getAsString(String tag) {
        return values.get(tag);
    }

    /**
     * Get the value as an Integer
     *
     * @param tag      the tag to get
     * @param fallback the value to use if it is null
     * @return the value associated with the tag
     */
    public Integer getAsInteger(String tag, Integer fallback) {
        try {
            return Integer.valueOf(values.get(tag));
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    /**
     * Get the value as a Double
     *
     * @param tag      the tag to get
     * @param fallback the value to use if it is null
     * @return the value associated with the tag
     */
    public Double getAsDouble(String tag, Double fallback) {
        try {
            return Double.valueOf(values.get(tag));
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    /**
     * Get the value as a Boolean
     *
     * @param tag      the tag to get
     * @param fallback the value to use if it is null
     * @return the value associated with the tag
     */
    public Boolean getAsBoolean(String tag, Boolean fallback) {
        try {
            return Boolean.valueOf(values.get(tag));
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

//    /**
//     * store the values to a file
//     * @param filename the name of the file
//     * @return whether or not it worked
//     */
//    public boolean writeToFile(String filename){
//        return writeToFile(new File(Environment.getExternalStorageDirectory(), filename));
//    }

    /**
     * store the values to a file
     *
     * @param file the file
     * @return whether or not it worked
     */
    public boolean writeToFile(File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(values.toString().replaceAll("\\{|\\}", "").replaceAll(", ", "\n"));
            pw.close();
            return true;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return false;
        }
    }
}
