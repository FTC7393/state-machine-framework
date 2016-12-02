package ftc.electronvolts.util.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.Map;
import java.util.MissingResourceException;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * This class stores and retrieves values from a file. It should probably be
 * replaced by an XML or JSON interpreter.
 */
public class OptionsFile {
    /**
     * converts objects to and from strings
     */
    private final Converters converters;

    /**
     * stores the strings that are read to and written from files
     */
    private Map<String, String> values;

    /**
     * @param converters the utilities that convert strings to and from objects
     */
    public OptionsFile(Converters converters) {
        this.converters = converters;
        values = new HashMap<>();
    }

    /**
     * @param converters the utilities that convert strings to and from objects
     * @param values the map of values to be loaded
     */
    public OptionsFile(Converters converters, Map<String, String> values) {
        this.converters = converters;
        this.values = values;
    }

    /**
     * retrieve an OptionsFile from a file
     *
     * @param converters the utilities that convert strings to and from objects
     * @param file the file to read from
     * @return the OptionsFile
     */
    public OptionsFile(Converters converters, File file) {
        this.converters = converters;
        values = new HashMap<>();
        try {
            //read each line of the file
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        //split the line at the "="
                        String[] elements = line.split("=");

                        //extract the key and value from the split line
                        String key = elements[0].trim();
                        String value = elements[1].trim();

                        //put the key and value into the map
                        values.put(key, value);
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
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * @return A map of all the values from the file
     */
    public Map<String, String> getValues() {
        return values;
    }

    /**
     * set a value in the map
     *
     * @param tag the name of the value
     * @param value the value (any Object)
     */
    public void set(String tag, Object value) {
        //convert the value to a string and add the key-value pair to the values map
        values.put(tag, value.toString());
    }

    /**
     * set a value in the map
     *
     * @param tag the name of the value
     * @param value a boolean
     */
    public void set(String tag, boolean value) {
        //convert the value to a string and add the key-value pair to the values map
        values.put(tag, Boolean.toString(value));
    }

    /**
     * set a value in the map
     *
     * @param tag the name of the value
     * @param value a byte
     */
    public void set(String tag, byte value) {
        //convert the value to a string and add the key-value pair to the values map
        values.put(tag, Byte.toString(value));
    }

    /**
     * set a value in the map
     *
     * @param tag the name of the value
     * @param value a char
     */
    public void set(String tag, char value) {
        //convert the value to a string and add the key-value pair to the values map
        values.put(tag, Character.toString(value));
    }

    /**
     * set a value in the map
     *
     * @param tag the name of the value
     * @param value a short
     */
    public void set(String tag, short value) {
        //convert the value to a string and add the key-value pair to the values map
        values.put(tag, Short.toString(value));
    }

    /**
     * set a value in the map
     *
     * @param tag the name of the value
     * @param value an int
     */
    public void set(String tag, int value) {
        //convert the value to a string and add the key-value pair to the values map
        values.put(tag, Integer.toString(value));
    }

    /**
     * set a value in the map
     *
     * @param tag the name of the value
     * @param value a long
     */
    public void set(String tag, long value) {
        //convert the value to a string and add the key-value pair to the values map
        values.put(tag, Long.toString(value));
    }

    /**
     * set a value in the map
     *
     * @param tag the name of the value
     * @param value a float
     */
    public void set(String tag, float value) {
        //convert the value to a string and add the key-value pair to the values map
        values.put(tag, Float.toString(value));
    }

    /**
     * set a value in the map
     *
     * @param tag the name of the value
     * @param value a double
     */
    public void set(String tag, double value) {
        //convert the value to a string and add the key-value pair to the values map
        values.put(tag, Double.toString(value));
    }

    /**
     * @param tag the name of the value
     * @param fallback the value to use if none is found
     * @return the value converted to the specified type
     * @throws IllegalArgumentException if there is no converter for the given
     *             type
     */
    public <T> T get(String tag, T fallback) {
        //get the class to convert to
        Class<T> clazz = (Class<T>) fallback.getClass();

        //try to convert, otherwise return the fallback
        try {
            return get(tag, clazz);
        } catch (IllegalArgumentException e) {
            return fallback;
        }
    }

    /**
     * @param tag the name of the value
     * @param class the class to convert to
     * @return the value converted to the specified type
     * @throws MissingResourceException if there is no converter for the given
     *             type
     * @throws IllegalArgumentException if there is no value with the given tag
     * @throws IllegalFormatConversionException if the string could not be
     *             converted to the specified object
     */
    public <T> T get(String tag, Class<T> clazz) {
        //get the converter for the specified class
        Converter<T> converter = converters.getConverter(clazz);

        //throw an error if there is no converter for the class
        if (converter == null) {
            throw new MissingResourceException("No converter given.", converters.getClass().getName(), clazz.getName());
        }

        if (!values.containsKey(tag)) {
            throw new IllegalArgumentException();
        }

        //get the value from the map
        String string = values.get(tag);

        //if the input is null, return null
        if (string == null) return null;

        //convert the string to the object
        T result = converter.fromString(string);

        //if the result is null, throw an exception
        if (result == null) throw new IllegalFormatConversionException((char) 0, clazz);

        return result;
    }

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
            //convert the values map to a string, remove the curly braces, and replace the commas with newlines
            pw.println(values.toString().replaceAll("\\{|\\}", "").replaceAll(", ", "\n"));
            pw.close();
            return true;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return false;
        }
    }
}
