package ftc.electronvolts.util;

/**
 * A class that defines a deadzone by returning a boolean as a function of a double
 */
public interface DeadZone {
    boolean isInside(double value);
}
