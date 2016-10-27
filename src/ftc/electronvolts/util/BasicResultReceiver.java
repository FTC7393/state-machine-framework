package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * The most basic implementation of ResultReceiver
 */
public class BasicResultReceiver<T> implements ResultReceiver<T> {
    private T value = null;
    private boolean ready = false;

    @Override
    public boolean isReady() {
        return ready;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
        ready = true;
    }

    @Override
    public void clear() {
        ready = false;
        value = null;
    }
}
