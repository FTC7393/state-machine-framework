package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * Factory class for InputScaler
 * These can be used for joystick scaling
 */
public class Functions {
    /**
     * A constant function y(x) = c
     * 
     * @param c the constant
     * @return the created Function
     */
    public static Function constant(final double c) {
        return new Function() {
            @Override
            public double f(double x) {
                return c;
            }
        };
    }

    /**
     * A linear function y(x) = mx + b
     * 
     * @param m the slope
     * @param b the y-intercept
     * @return the created Function
     */
    public static Function linear(final double m, final double b) {
        return new Function() {
            @Override
            public double f(double x) {
                return m * x + b;
            }
        };
    }

    /**
     * A quadratic function y(x) = ax^2 + bx + c
     * 
     * @param a the squared coefficient
     * @param b the linear coefficient
     * @param c the constant coefficient
     * @return the created Function
     */
    public static Function quadratic(final double a, final double b, final double c) {
        return new Function() {
            @Override
            public double f(double x) {
                return a * x * x + b * x + c;
            }
        };
    }

    /**
     * A cubic function y(x) = ax^3 + bx^2 + cx + d
     * 
     * @param a the cubed coefficient
     * @param b the squared coefficient
     * @param c the linear coefficient
     * @param d the constant coefficient
     * @return the created Function
     */
    public static Function cubic(final double a, final double b, final double c, final double d) {
        return new Function() {
            @Override
            public double f(double x) {
                return a * x * x * x + b * x * x + c * x + d;
            }
        };
    }

    /**
     * A polynomial function:
     * y(x) = a[n]*x^n + a[n-1]*x^(n-1) + ... + a[2]*x^2 + a[1]*x + a[0]
     * note: the first coefficient is the constant term, not the highest term
     * 
     * @param coefficients the constants for each x term
     * @return the created Function
     */
    public static Function polynomial(final double[] coefficients) {
        return new Function() {
            @Override
            public double f(double x) {
                double output = 0;
                double xPart = 1;
                for (int i = 0; i < coefficients.length; i++) {
                    output += coefficients[i] * xPart;
                    xPart *= x;
                }
                return output;
            }
        };
    }

    /**
     * The output = the input squared with the sign retained
     *
     * @return the created Function
     */
    public static Function squared() {
        return new Function() {
            @Override
            public double f(double x) {
                return x * x * Math.signum(x);
            }
        };
    }

    /**
     * The output = the input cubed
     *
     * @return the created Function
     */
    public static Function cubed() {
        return new Function() {
            @Override
            public double f(double x) {
                return x * x * x;
            }
        };
    }

    /**
     * @param deadZone the deadzone to use
     * @return the created Function
     */
    public static Function deadzone(final DeadZone deadZone) {
        return new Function() {
            @Override
            public double f(double x) {
                if (deadZone.isInside(x)) {
                    return 0;
                } else {
                    return x;
                }
            }
        };
    }

    /**
     * limits the input to between min and max
     *
     * @param min the min value
     * @param max the min value
     * @return the created Function
     */
    public static Function limit(final double min, final double max) {
        return new Function() {
            @Override
            public double f(double x) {
                return Utility.limit(x, min, max);
            }
        };
    }

    /**
     * Combines 2 Functions like a composite function b(a(x))
     *
     * @param inner a(x)
     * @param outer b(x)
     * @return the created Function
     */
    public static Function composite(final Function inner, final Function outer) {
        return new Function() {
            @Override
            public double f(double x) {
                return outer.f(inner.f(x));
            }
        };
    }

    /**
     * No modification to the input
     *
     * @return the created Function
     */
    public static Function none() {
        return new Function() {
            @Override
            public double f(double x) {
                return x;
            }
        };
    }

    /**
     * Multiplies the input by a constant
     *
     * @param m the slope
     * @return the created Function
     */
    public static Function linear(final double m) {
        return linear(m, 0);
    }

    /**
     * Logarithmic scaling
     *
     * @param logBase the base of the logarithm
     * @return the created Function
     */
    public static Function logarithmic(final double logBase) {
        return new Function() {
            @Override
            public double f(double x) {
                if (logBase > 0) {
                    double sign = Math.signum(x);
                    x = Math.abs(x);
                    // a log function including the points (0,0) and (1,1)
                    return sign * Math.log(logBase * x + 1) / Math.log(logBase + 1);
                } else {
                    return x;
                }
            }
        };
    }

    /**
     * e^ax based scaling
     * for a != 0
     * y(x) = signum(x) * (e^abs(ax)-1)/(e^a-1)
     * 
     * for a = 0
     * y(x) = x
     * 
     *
     * @param a constant value that determines how curved the function is
     * @return the created Function
     */
    public static Function eBased(final double a) {
        return new Function() {
            @Override
            public double f(double x) {
                if (a == 0) {
                    return x;
                } else {
                    double sign = Math.signum(x);
                    x = Math.abs(x);
                    // a e-based function including the points (0,0) and (1,1)
                    return sign * Math.expm1(a*x)/Math.expm1(a);
                }
            }
        };
    }

    /**
     * a line from (-1,-1) to (-x,-y) to (0,0) to (x,y) to (1,1)
     *
     * @param pointX x
     * @param pointY y
     * @param maxValue the maximum value of the input
     * @return the created Function
     */
    public static Function piecewise(double pointX, double pointY, double maxValue) {
        final double x = Utility.motorLimit(Math.abs(pointX));
        final double y = Utility.motorLimit(Math.abs(pointY));
        final double max = Utility.motorLimit(Math.abs(maxValue));
        final double slope1;
        if (x == 0) {
            slope1 = 0;
        } else {
            slope1 = y / x;
        }
        final double slope2;
        if (x == 1) {
            slope2 = 0;
        } else {
            slope2 = (y - max) / (x - 1);
        }
        return new Function() {
            public double f(double input) {
                if (Math.abs(input) < x) {
                    return slope1 * input;
                } else {
                    return Utility.motorLimit((slope2 * (Math.abs(input) - x) + y) * Math.signum(input));
                }
            }
        };
    }
}
