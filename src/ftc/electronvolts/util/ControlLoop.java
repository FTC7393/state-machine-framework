package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * An interface that allows for any type of control loop to be used
 * @see PIDController
 */
public interface ControlLoop {
    double computeCorrection(double setPoint, double input);
    void initialize();
}
