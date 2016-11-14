package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 * 
 * This is the factory class for InputExtractor
 */
public class InputExtractors {
    public static InputExtractor<Double> zero() {
        return constant(0.0);
    }

    public static <T> InputExtractor<T> constant(final T value) {
        return new InputExtractor<T>() {
            @Override
            public T getValue() {
                return value;
            }
        };
    }

    public static InputExtractor<Double> multiply(final InputExtractor<Double> inputExtractor, final double value) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor.getValue() * value;
            }
        };
    }

    public static InputExtractor<Double> multiply(final InputExtractor<Double> inputExtractor1, final InputExtractor<Double> inputExtractor2) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor1.getValue() * inputExtractor2.getValue();
            }
        };
    }

    public static InputExtractor<Double> divide(final InputExtractor<Double> inputExtractor, final double value) {
        return multiply(inputExtractor, 1 / value);
    }

    public static InputExtractor<Double> divide(final InputExtractor<Double> inputExtractor1, final InputExtractor<Double> inputExtractor2) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor1.getValue() / inputExtractor2.getValue();
            }
        };
    }

    public static InputExtractor<Double> divide(final double value, final InputExtractor<Double> inputExtractor) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return value / inputExtractor.getValue();
            }
        };
    }

    public static InputExtractor<Double> add(final InputExtractor<Double> inputExtractor, final double value) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor.getValue() + value;
            }
        };
    }

    public static InputExtractor<Double> add(final InputExtractor<Double> inputExtractor1, final InputExtractor<Double> inputExtractor2) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor1.getValue() + inputExtractor2.getValue();
            }
        };
    }

    public static InputExtractor<Double> subtract(final InputExtractor<Double> inputExtractor, final double value) {
        return add(inputExtractor, -value);
    }

    public static InputExtractor<Double> subtract(final InputExtractor<Double> inputExtractor1, final InputExtractor<Double> inputExtractor2) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return inputExtractor1.getValue() - inputExtractor2.getValue();
            }
        };
    }

    public static InputExtractor<Double> subtract(final double value, final InputExtractor<Double> inputExtractor) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return value - inputExtractor.getValue();
            }
        };
    }

    public static InputExtractor<Double> absolute(final InputExtractor<Double> inputExtractor) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return Math.abs(inputExtractor.getValue());
            }
        };
    }

    public static InputExtractor<Double> negative(final InputExtractor<Double> inputExtractor) {
        return multiply(inputExtractor, -1);
    }

    public static InputExtractor<Double> function(final InputExtractor<Double> inputExtractor1, final Function function) {
        return new InputExtractor<Double>() {
            @Override
            public Double getValue() {
                return function.f(inputExtractor1.getValue());
            }
        };
    }

    public static InputExtractor<Boolean> not(final InputExtractor<Boolean> inputExtractor) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return !inputExtractor.getValue();
            }
        };
    }

    public static InputExtractor<Boolean> and(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return inputExtractor1.getValue() && inputExtractor2.getValue();
            }
        };
    }

    public static InputExtractor<Boolean> nand(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return !(inputExtractor1.getValue() && inputExtractor2.getValue());
            }
        };
    }

    public static InputExtractor<Boolean> or(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return inputExtractor1.getValue() || inputExtractor2.getValue();
            }
        };
    }

    public static InputExtractor<Boolean> nor(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return !(inputExtractor1.getValue() || inputExtractor2.getValue());
            }
        };
    }

    public static InputExtractor<Boolean> xor(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return inputExtractor1.getValue() ^ inputExtractor2.getValue();
            }
        };
    }

    public static InputExtractor<Boolean> xnor(final InputExtractor<Boolean> inputExtractor1, final InputExtractor<Boolean> inputExtractor2) {
        return new InputExtractor<Boolean>() {
            @Override
            public Boolean getValue() {
                return !(inputExtractor1.getValue() ^ inputExtractor2.getValue());
            }
        };
    }
}
