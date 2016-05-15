package ftc.electronvolts.util;

/**
 * Used to trasmit information between threads
 */
public interface ResultReceiver<T> {
    /**
     *
     * @return whether or not the value been stored yet
     */
    boolean isReady();

    /**
     *
     * @return the value
     */
    T getValue();

    /**
     *
     * @param value the value to be set
     */
    void setValue(T value);

    /**
     * reset the value and mark as not ready
     */
    void clear();
}
