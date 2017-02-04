package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 * 
 * This is the factory class for InputExtractor
 */
public class InputExtractors {
    private static final InputExtractor<Double> ZERO = constant(0.0);

    /**
     * @return an InputExtractor that always returns 0
     */
    public static InputExtractor<Double> zero() {
        return ZERO;
    }

    /**
     * @param value the value to return
     * @return an InputExtractor that always returns the specified value
     */
    public static <T> InputExtractor<T> constant(final T value) {
        return new InputExtractor<T>() {
            @Override
            public T getValue() {
                return value;
            }
        };
    }
    
    public static InputExtractor<Double> limit(final double min, final double max, final InputExtractor<Double> inputExtractor) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return Utility.limit(inputExtractor.getValue(), min, max);
            }
        };
    }

    /**
     * Multiply an InputExtractor by a constant
     * 
     * @param inputExtractor the InputExtractor to multiply
     * @param value the constant to multiply by
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> multiply(final InputExtractor<Double> inputExtractor, final double value) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor.getValue() * value;
            }
        };
    }

    /**
     * Multiply an InputExtractor by a constant
     * 
     * @param value the constant to multiply by
     * @param inputExtractor the InputExtractor to multiply
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> multiply(final double value, final InputExtractor<Double> inputExtractor) {
        return multiply(value, inputExtractor);
    }

    /**
     * Multiply an InputExtractor by another InputExtractor
     * 
     * @param inputExtractor1 the first InputExtractor
     * @param inputExtractor2 the second InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> multiply(final InputExtractor<Double> inputExtractor1, final InputExtractor<Double> inputExtractor2) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor1.getValue() * inputExtractor2.getValue();
            }
        };
    }

    /**
     * Divide an InputExtractor by a constant. Equivalent to multiplying by the
     * reciprocal of the constant
     * 
     * @param inputExtractor the InputExtractor
     * @param value the constant
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> divide(final InputExtractor<Double> inputExtractor, final double value) {
        return multiply(inputExtractor, 1 / value);
    }

    /**
     * Divide an InputExtractor by another InputExtractor
     * 
     * @param inputExtractor1 the numerator
     * @param inputExtractor2 the denominator
     * @return
     */
    public static InputExtractor<Double> divide(final InputExtractor<Double> inputExtractor1, final InputExtractor<Double> inputExtractor2) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor1.getValue() / inputExtractor2.getValue();
            }
        };
    }

    /**
     * Divide a constant by an InputExtractor
     * 
     * @param value the constant
     * @param inputExtractor the InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> divide(final double value, final InputExtractor<Double> inputExtractor) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return value / inputExtractor.getValue();
            }
        };
    }

    /**
     * Add a constant to an InputExtractor
     * 
     * @param inputExtractor the InputExtractor
     * @param value the constant
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> add(final InputExtractor<Double> inputExtractor, final double value) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor.getValue() + value;
            }
        };
    }

    /**
     * Add a constant to an InputExtractor
     * 
     * @param value the constant
     * @param inputExtractor the InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> add(final double value, final InputExtractor<Double> inputExtractor) {
        return add(inputExtractor, value);
    }

    /**
     * Add two InputExtractor objects together
     * 
     * @param inputExtractor1 the first InputExtractor
     * @param inputExtractor2 the second InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> add(final InputExtractor<Double> inputExtractor1, final InputExtractor<Double> inputExtractor2) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor1.getValue() + inputExtractor2.getValue();
            }
        };
    }

    /**
     * Subtract a constant from an InputExtractor
     * 
     * @param inputExtractor the InputExtractor
     * @param value the constant to subtract
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> subtract(final InputExtractor<Double> inputExtractor, final double value) {
        return add(inputExtractor, -value);
    }

    /**
     * Subtract one InputExtractor from another InputExtractor
     * 
     * @param inputExtractor1 the first InputExtractor
     * @param inputExtractor2 the second InputExtractor (will be subtracted from
     *            the first)
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> subtract(final InputExtractor<Double> inputExtractor1, final InputExtractor<Double> inputExtractor2) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor1.getValue() - inputExtractor2.getValue();
            }
        };
    }

    /**
     * Subtract an InputExtractor from a constant
     * 
     * @param value the constant
     * @param inputExtractor the InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> subtract(final double value, final InputExtractor<Double> inputExtractor) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return value - inputExtractor.getValue();
            }
        };
    }

    /**
     * Get the absolute value of an InputExtractor
     * 
     * @param inputExtractor the InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> absolute(final InputExtractor<Double> inputExtractor) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return Math.abs(inputExtractor.getValue());
            }
        };
    }

    /**
     * Get the negative of an InputExtractor
     * 
     * @param inputExtractor the InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> negative(final InputExtractor<Double> inputExtractor) {
        return multiply(inputExtractor, -1);
    }

    /**
     * Apply a scaling function to an InputExtractor
     * 
     * @param inputExtractor the InputExtractor
     * @param function the Function to scale by
     * @return the created InputExtractor
     */
    public static InputExtractor<Double> function(final InputExtractor<Double> inputExtractor, final Function function) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return function.f(inputExtractor.getValue());
            }
        };
    }

    /**
     * Get the inverse of an InputExtractor
     * 
     * @param inputExtractor the InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Boolean> not(final InputExtractor<Boolean> inputExtractor) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return !inputExtractor.getValue();
            }
        };
    }

    /**
     * Apply the "and" operator to 2 InputExtractor objects
     * 
     * @param inputExtractor1 the first InputExtractor
     * @param inputExtractor2 the second InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Boolean> and(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return inputExtractor1.getValue() && inputExtractor2.getValue();
            }
        };
    }

    /**
     * Apply the "nand" operator to 2 InputExtractor objects
     * 
     * @param inputExtractor1 the first InputExtractor
     * @param inputExtractor2 the second InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Boolean> nand(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return !(inputExtractor1.getValue() && inputExtractor2.getValue());
            }
        };
    }

    /**
     * Apply the "or" operator to 2 InputExtractor objects
     * 
     * @param inputExtractor1 the first InputExtractor
     * @param inputExtractor2 the second InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Boolean> or(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return inputExtractor1.getValue() || inputExtractor2.getValue();
            }
        };
    }

    /**
     * Apply the "nor" operator to 2 InputExtractor objects
     * 
     * @param inputExtractor1 the first InputExtractor
     * @param inputExtractor2 the second InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Boolean> nor(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return !(inputExtractor1.getValue() || inputExtractor2.getValue());
            }
        };
    }

    /**
     * Apply the "xor" operator to 2 InputExtractor objects
     * 
     * @param inputExtractor1 the first InputExtractor
     * @param inputExtractor2 the second InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Boolean> xor(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return inputExtractor1.getValue() ^ inputExtractor2.getValue();
            }
        };
    }

    /**
     * Apply the "xnor" operator to 2 InputExtractor objects
     * 
     * @param inputExtractor1 the first InputExtractor
     * @param inputExtractor2 the second InputExtractor
     * @return the created InputExtractor
     */
    public static InputExtractor<Boolean> xnor(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return !(inputExtractor1.getValue() ^ inputExtractor2.getValue());
            }
        };
    }

    public static InputExtractor<String> format(final String format, final InputExtractor<Double> inputExtractor) {
        return new InputExtractor<String>() {

            @Override
            public String getValue() {
                return String.format(format, inputExtractor.getValue());
            }
        };
    }

    public static InputExtractor<Integer> booleanToIntIE(final InputExtractor<Boolean> booleanIE) {
        return new InputExtractor<Integer>() {
            @Override
            public Integer getValue() {
                Boolean value = booleanIE.getValue();
                if (value == null) {
                    return null;
                } else if (value) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
    }
}
