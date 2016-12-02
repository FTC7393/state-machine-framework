package ftc.electronvolts.util.files;

public interface Converter<T> {
    String toString(T object);
    T fromString(String string);
}
