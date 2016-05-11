package ftc.electronvolts.util;

/**
 * Manages the scaling of an analog input such as a joystick
 */
public class AnalogInputManager{
    private double value = 0;

    private final InputExtractor<Double> extractor;
    private final InputScaler inputScaler;

    public AnalogInputManager(InputExtractor<Double> extractor, InputScaler inputScaler){
        this.extractor = extractor;
        this.inputScaler = inputScaler;
    }

    public double update() {
        value = inputScaler.scale(extractor.getValue());
        return value;
    }

    public double getValue() {
        return value;
    }
}
