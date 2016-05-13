package ftc.electronvolts.util;


/**
 * The most basic implementation of ResultReceiver
 */
public class BasicResultReceiver<T> implements ResultReceiver {
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
    public void setValue(Object value) {
        this.value = (T) value;
        ready = true;
    }

    @Override
    public void clear() {
        ready = false;
        value = null;
    }
}
