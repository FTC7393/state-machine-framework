package ftc.electronvolts.util;

/**
 * Created by vandejd1 on 2/6/16.
 * FTC Team EV 7393
 */
public class InputScalers {
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

    public static InputScaler limit(final double min, final double max){
        return new InputScaler() {
            @Override
            public double scale(double input) {
                return Utility.limit(input, min, max);
            }
        };
    }

    public static InputScaler composite(final InputScaler inner, final InputScaler outer){
        return new InputScaler() {
            @Override
            public double scale(double input) {
                return outer.scale(inner.scale(input));
            }
        };
    }

    public static InputScaler none(){
        return new InputScaler() {
            @Override
            public double scale(double input) {
                return input;
            }
        };
    }

    public static InputScaler linear(final double scalingFactor){
        return new InputScaler() {
            @Override
            public double scale(double input) {
                return input * scalingFactor;
            }
        };
    }

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

    //(-1,-1) to (-x,-y) to (0,0) to (x,y) to (1,1)
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