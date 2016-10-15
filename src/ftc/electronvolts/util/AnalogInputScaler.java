package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * Manages the scaling of an analog input such as a joystick
 */
public class AnalogInputScaler implements InputExtractor<Double> {
    private double value = 0;

    private final InputExtractor<Double> extractor;
    private final Function inputScaler;

    /**
     * @param extractor   the input extractor
     * @param inputScaler the input scaler to use for scaling the input
     */
    public AnalogInputScaler(InputExtractor<Double> extractor, Function inputScaler) {
        this.extractor = extractor;
        this.inputScaler = inputScaler;
    }

    /**
     * updates the output value
     *
     * @return the value
     */
    public double update() {
        value = inputScaler.f(extractor.getValue());
        return value;
    }

    /**
     * @return the value
     */
    @Override
    public Double getValue() {
        return value;
    }
}
