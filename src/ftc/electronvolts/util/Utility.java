package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 2/17/16
 */
public class Utility {
    /**
     * limits the value to be between a and b
     *
     * @param input the value to be limited
     * @param a     one end of the range
     * @param b     the other end of the range
     * @return the input limited to the range between a and b
     */
    public static double limit(double input, double a, double b) {
    	if(a == b) return a; // if the ends of the range are equal
    	
    	//set min and max to a and b, making sure that min < max
    	double min = a, max = b;
    	if(a > b){
    		min = b;
    		max = a;
    	}
    	
    	//limit the input to be min < input < max
        if (input > max) return max;
        if (input < min) return min;
        return input;
    }

    /**
     * a limit function where min = -max
     *
     * @param input the value to be limited
     * @param max   the max absolute value
     * @return the input limited to be between -max to max
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
        return mirrorLimit(input, 1);
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
