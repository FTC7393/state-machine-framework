package ftc.electronvolts.util;

/**
 * A class that is very useful for joystick control.
 * It does edge detection for a digital input.
 */
public class DigitalInputManager {
    Boolean currentValue = false;
    Boolean previousValue;
    InputExtractor<Boolean> extractor;

    /**
     *
     * @param extractor the InputExtractor to do edge detection on
     */
    public DigitalInputManager(InputExtractor<Boolean> extractor) {
        this.extractor = extractor;
    }

    /**
     * update the current and previous value of the input
     * @return the current value of the input
     */
    public boolean update() {
        previousValue = currentValue;
        currentValue = extractor.getValue();
        return currentValue;
    }

    /**
     *
     * @return whether or not the input is true right now
     */
    public boolean isPressed() {
        return currentValue;
    }


    /**
     *
     * @return if the input just turned from false to true
     */
    public boolean justPressed() {
        return currentValue && !previousValue;
    }

    /**
     *
     * @return if the input just turned from true to false
     */
    public boolean justReleased() {
        return !currentValue && previousValue;
    }
}