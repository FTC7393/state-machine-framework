package ftc.electronvolts.util;

/**
 * Created by vandejd1 on 2/17/16.
 * FTC Team EV 7393
 */
public class Utility {
    public static double limit(double value, double min, double max){
        if (value>max) return max;
        if (value<min) return min;
        return value;
    }

    public static double motorLimit(double value){
        return limit(value, -1, 1);
    }

    public static double servoLimit(double value) {
        return limit(value, 0, 1);
    }

    // Scale joystick values to specific scaling values and apply a deadzone
//    public static double joystickScale(double joyVal, double joyDeadzone, double joyScaling) {
//        if(joyVal <= joyDeadzone && joyVal >= -joyDeadzone) return 0;
//        return (((Math.abs(joyVal) - joyDeadzone) * (joyVal / joyVal)) / (1 - joyDeadzone)) * joyScaling;
//    }
//
//    public static double scaleSonar(double rawValue){
//        return (1.265 * rawValue + 1.146)/2.54;
//    }

}
