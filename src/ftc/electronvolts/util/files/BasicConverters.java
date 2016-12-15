package ftc.electronvolts.util.files;

import java.util.HashMap;
import java.util.Map;

/**
 * This file was made by the electronVolts, FTC team 7393
 * 
 * A basic implementation of Converters that includes Converter objects
 * for all the class versions of the primitive types, and Strings
 * 
 * @see Converters
 * @see Converter
 */
public class BasicConverters implements Converters {
    protected static final Map<Class<? extends Object>, Converter<?>> converterMap = new HashMap<>();
    static {
        converterMap.put(Boolean.class, new Converter<Boolean>() {

            @Override
            public String toString(Boolean object) {
                return object.toString();
            }

            @Override
            public Boolean fromString(String string) {
                return Boolean.valueOf(string);
            }
        });
        converterMap.put(Byte.class, new Converter<Byte>() {

            @Override
            public String toString(Byte object) {
                return object.toString();
            }

            @Override
            public Byte fromString(String string) {
                return Byte.valueOf(string);
            }
        });
        converterMap.put(Character.class, new Converter<Character>() {

            @Override
            public String toString(Character object) {
                return object.toString();
            }

            @Override
            public Character fromString(String string) {
                return string.charAt(0);
            }
        });
        converterMap.put(Short.class, new Converter<Short>() {

            @Override
            public String toString(Short object) {
                return object.toString();
            }

            @Override
            public Short fromString(String string) {
                return Short.valueOf(string);
            }
        });
        converterMap.put(Integer.class, new Converter<Integer>() {

            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.valueOf(string);
            }
        });
        converterMap.put(Long.class, new Converter<Long>() {

            @Override
            public String toString(Long object) {
                return object.toString();
            }

            @Override
            public Long fromString(String string) {
                return Long.valueOf(string);
            }
        });
        converterMap.put(Float.class, new Converter<Float>() {

            @Override
            public String toString(Float object) {
                return object.toString();
            }

            @Override
            public Float fromString(String string) {
                return Float.valueOf(string);
            }
        });
        converterMap.put(Double.class, new Converter<Double>() {

            @Override
            public String toString(Double object) {
                return object.toString();
            }

            @Override
            public Double fromString(String string) {
                return Double.valueOf(string);
            }
        });

        converterMap.put(String.class, new Converter<String>() {

            @Override
            public String toString(String object) {
                return object;
            }

            @Override
            public String fromString(String string) {
                return string;
            }
        });

    }

    private static Converters INSTANCE = new BasicConverters();

    protected BasicConverters() {
    }

    public static Converters getInstance() {
        return INSTANCE;
    }

    @Override
    public <T> Converter<T> getConverter(Class<T> clazz) {
        return (Converter<T>) converterMap.get(clazz);
    }

}
