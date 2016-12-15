package ftc.electronvolts.util.files;

/**
 * This file was made by the electronVolts, FTC team 7393
 * 
 * This interface defines a conversion from an object of a certain type to a
 * String and back again
 * 
 * @see Converters
 */
public interface Converter<T> {
    /**
     * Convert an object to a String
     * 
     * @param object the object
     * @return a String representing the object
     */
    String toString(T object);

    /**
     * Convert a String to an object
     * 
     * @param string the String
     * @return the object
     */
    T fromString(String string);
}
