package ftc.electronvolts.util.files;

/**
 * This file was made by the electronVolts, FTC team 7393
 * 
 * Implementations of this interface hold a Map of Converter objects of
 * different types
 * 
 * @see Converter
 */
public interface Converters {
    /**
     * Get a Converter for a certain class
     * 
     * @param clazz the class that the converter converts
     * @return the Converter
     */
    <T> Converter<T> getConverter(Class<T> clazz);
}
