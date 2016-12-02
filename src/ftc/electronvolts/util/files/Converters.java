package ftc.electronvolts.util.files;

public interface Converters {
    <T> Converter<T> getConverter(Class<T> clazz);
}
