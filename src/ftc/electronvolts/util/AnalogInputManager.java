package ftc.electronvolts.util;

/**
 * Manages the scaling of an analog input such as a joystick
 */
public class AnalogInputManager{
    private double value = 0;

    private final InputExtractor<Double> extractor;
    private final InputScaler inputScaler;

    /**
     *
     * @param extractor the input extractor
     * @param inputScaler the input scaler to use for scaling the input
     */
    public AnalogInputManager(InputExtractor<Double> extractor, InputScaler inputScaler){
        this.extractor = extractor;
        this.inputScaler = inputScaler;
    }

    /**
     * updates the output value
     * @return the value
     */
    public double update() {
        value = inputScaler.scale(extractor.getValue());
        return value;
    }

    /**
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }
}
