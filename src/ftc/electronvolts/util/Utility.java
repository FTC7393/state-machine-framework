package ftc.electronvolts.util;

import java.util.List;

/**
 * This file was made by the electronVolts, FTC team 7393 Date Created: 2/17/16
 */
public class Utility {
    /**
     * limits the value to be between a and b
     *
     * @param input the value to be limited
     * @param a one end of the range
     * @param b the other end of the range
     * @return the input limited to the range between a and b
     */
    public static double limit(double input, double a, double b) {
        if (a == b) return a; // if the ends of the range are equal

        // set min and max to a and b, making sure that min < max
        double min = a, max = b;
        if (a > b) {
            min = b;
            max = a;
        }

        // limit the input to be min < input < max
        if (input > max) return max;
        if (input < min) return min;
        return input;
    }

    /**
     * a limit function where min = -max
     *
     * @param input the value to be limited
     * @param max the max absolute value
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

    /**
     * Join a list of objects with a separator
     * 
     * @param list the list of values
     * @param separator the String to put between the values
     * @return a String of the joined items
     */
    public static <T> String join(List<T> list, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (T item : list) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Join a list of booleans with a separator
     * 
     * @param list the list of values
     * @param separator the String to put between the values
     * @return a String of the joined items
     */
    public static String join(boolean[] list, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (boolean item : list) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Join a list of bytes with a separator
     * 
     * @param list the list of values
     * @param separator the String to put between the values
     * @return a String of the joined items
     */
    public static String join(byte[] list, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (byte item : list) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Join a list of chars with a separator
     * 
     * @param list the list of values
     * @param separator the String to put between the values
     * @return a String of the joined items
     */
    public static String join(char[] list, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (char item : list) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Join a list of shorts with a separator
     * 
     * @param list the list of values
     * @param separator the String to put between the values
     * @return a String of the joined items
     */
    public static String join(short[] list, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (short item : list) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Join a list of ints with a separator
     * 
     * @param list the list of values
     * @param separator the String to put between the values
     * @return a String of the joined items
     */
    public static String join(int[] list, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int item : list) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Join a list of longs with a separator
     * 
     * @param list the list of values
     * @param separator the String to put between the values
     * @return a String of the joined items
     */
    public static String join(long[] list, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (long item : list) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Join a list of floats with a separator
     * 
     * @param list the list of values
     * @param separator the String to put between the values
     * @return a String of the joined items
     */
    public static String join(float[] list, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (float item : list) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Join a list of doubles with a separator
     * 
     * @param list the list of values
     * @param separator the String to put between the values
     * @return a String of the joined items
     */
    public static String join(double[] list, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (double item : list) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

}
