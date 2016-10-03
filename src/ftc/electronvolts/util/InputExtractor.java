package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * This class is used to extract a static method and store it in an object
 */
public interface InputExtractor<Type> {
    /**
     * @return the value from wherever the InputExtractor got it
     */
    Type getValue();
}
