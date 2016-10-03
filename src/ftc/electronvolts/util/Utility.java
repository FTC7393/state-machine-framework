package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 2/17/16
 */
public class Utility {
    /**
     * limits the value to between min and max
     *
     * @param input the value to be limited
     * @param min   the minimum value
     * @param max   the maximum value
     * @return the input limited to the range from min to max
     */
    public static double limit(double input, double min, double max) {
        if (input > max) return max;
        if (input < min) return min;
        return input;
    }

    /**
     * a limit function where min = -max
     *
     * @param input the value to be limited
     * @param max   the max absolute value
     * @return the input limited to the range from -max to max
     */
    public static double mirrorLimit(double input, double max) {
        return limit(input, -max, max);
    }

    /**
     * Limiting function for motor power
     *
     * @param input the value to be limited
     * @return the input limited to the range from -1 to 1
     */
    public static double motorLimit(double input) {
        return limit(input, -1, 1);
    }

    /**
     * Limiting function for servo positions
     *
     * @param input the value to be limited
     * @return the input limited to the range from 0 to 1
     */
    public static double servoLimit(double input) {
        return limit(input, 0, 1);
    }

}
