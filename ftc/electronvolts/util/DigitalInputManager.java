package ftc.electronvolts.util;

/**
 * Created by nikita on 1/9/16.
 * FTC Team EV 5269
 */
public class DigitalInputManager {
    Boolean currentValue = false;
    Boolean previousValue;
    InputExtractor<Boolean> extractor;

    public DigitalInputManager(InputExtractor<Boolean> extractor) {
        this.extractor = extractor;
    }

    public boolean update() {
        previousValue = currentValue;
        currentValue = extractor.getValue();
        return currentValue;
    }

    public boolean isPressed() {
        return currentValue;
    }

    //edge detection
    public boolean justPressed() {
        return currentValue && !previousValue;
    }

    public boolean justReleased() {
        return !currentValue && previousValue;
    }
}