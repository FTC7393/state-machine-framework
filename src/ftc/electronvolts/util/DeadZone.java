package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * A class that defines a deadzone by returning a boolean as a function of a
 * double
 */
public interface DeadZone {
    boolean isInside(double value);
}
