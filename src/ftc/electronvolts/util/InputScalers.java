package ftc.electronvolts.util;

/**
 * Factory class for InputScaler
 * These can be used for joystick scaling
 */
public class InputScalers {
    /**
     *
     * @param deadZone the deadzone to use
     * @return the InputScaler
     */
    public static InputScaler deadzone(final DeadZone deadZone){
        return new InputScaler() {
            @Override
            public double scale(double input) {
                if(deadZone.isInside(input)){
                    return 0;
                } else {
                    return input;
                }
            }
        };
    }

    /**
     * limits the input to between min and max
     * @param min the min value
     * @param max the min value
     * @return the InputScaler
     */
    public static InputScaler limit(final double min, final double max){
        return new InputScaler() {
            @Override
            public double scale(double input) {
                return Utility.limit(input, min, max);
            }
        };
    }

    /**
     * Combines 2 InputScalers like a composite function f(g(x))
     * @param inner g(x)
     * @param outer f(x)
     * @return the InputScaler
     */
    public static InputScaler composite(final InputScaler inner, final InputScaler outer){
        return new InputScaler() {
            @Override
            public double scale(double input) {
                return outer.scale(inner.scale(input));
            }
        };
    }

    /**
     * No modification to the input
     * @return the InputScaler
     */
    public static InputScaler none(){
        return new InputScaler() {
            @Override
            public double scale(double input) {
                return input;
            }
        };
    }

    /**
     * Multiplies the input by a constant
     * @param scalingFactor the constant to multiply by
     * @return the InputScaler
     */
    public static InputScaler linear(final double scalingFactor){
        return new InputScaler() {
            @Override
            public double scale(double input) {
                return input * scalingFactor;
            }
        };
    }

    /**
     * Logarithmic scaling
     * @param logBase the base of the logarithm
     * @return the InputScaler
     */
    public static InputScaler logarithmic(final double logBase){
        return new InputScaler() {
            @Override
            public double scale(double input) {
                if (logBase > 0){
                    //a log function including the points (0,0) and (1,1)
                    return Math.log(logBase * input + 1)/Math.log(logBase + 1);
                } else {
                    return input;
                }
            }
        };
    }

    /**
     * a line from (-1,-1) to (-x,-y) to (0,0) to (x,y) to (1,1)
     * @param pointX x
     * @param pointY y
     * @param maxValue the maximum value of the input
     * @return the InputScaler
     */
    public static InputScaler piecewise(double pointX, double pointY, double maxValue){
        final double x = Utility.motorLimit(Math.abs(pointX));
        final double y = Utility.motorLimit(Math.abs(pointY));
        final double max = Utility.motorLimit(Math.abs(maxValue));
        final double slope1;
        if (x == 0){
            slope1 = 0;
        } else {
            slope1 = y/x;
        }
        final double slope2;
        if (x == 1){
            slope2 = 0;
        } else {
            slope2 = (y-max)/(x-1);
        }
        return new InputScaler() {
            @Override
            public double scale(double input) {
                double output;
                if (Math.abs(input) < x) {
                    output = slope1 * Math.abs(input);
                } else {
                    output = slope2 * (Math.abs(input) - x) + y;
                }

                return Utility.motorLimit(output * Math.signum(input));
            }
        };
    }
}
