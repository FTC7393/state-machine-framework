package ftc.electronvolts.util.files;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ftc.electronvolts.util.TeamColor;
import ftc.electronvolts.util.Vector2D;
import ftc.electronvolts.util.Vector3D;

public class UtilConverters extends BasicConverters {
    private static final String decimalNumber = "([0-9\\.\\-]+)";
    private static final String comma = " *, *";
    static {
        converterMap.put(TeamColor.class, new Converter<TeamColor>() {

            @Override
            public String toString(TeamColor object) {
                return object.name();
            }

            @Override
            public TeamColor fromString(String string) {
                return TeamColor.fromString(string);
            }
        });
        converterMap.put(Vector2D.class, new Converter<Vector2D>() {

            @Override
            public String toString(Vector2D object) {
                return object.toString();
            }

            @Override
            public Vector2D fromString(String string) {
                Pattern pattern = Pattern.compile("\\(" + decimalNumber + comma + decimalNumber + "\\)");
                Matcher matcher = pattern.matcher(string);
                if (matcher.find()) {
                    return new Vector2D(Double.valueOf(matcher.group(1)), Double.valueOf(matcher.group(2)));
                } else {
                    return null;
                }
            }
        });
        converterMap.put(Vector3D.class, new Converter<Vector3D>() {

            @Override
            public String toString(Vector3D object) {
                return object.toString();
            }

            @Override
            public Vector3D fromString(String string) {
                Pattern pattern = Pattern.compile("\\(" + decimalNumber + comma + decimalNumber + comma + decimalNumber + "\\)");
                Matcher matcher = pattern.matcher(string);
                if (matcher.find()) {
                    return new Vector3D(Double.valueOf(matcher.group(1)), Double.valueOf(matcher.group(2)), Double.valueOf(matcher.group(3)));
                } else {
                    return null;
                }
            }
        });

        //        converterMap.put(Double.class, new Converter<Double>() {
        //
        //            @Override
        //            public String toString(Double object) {
        //                return object.toString();
        //            }
        //
        //            @Override
        //            public Double fromString(String string) {
        //                return Double.valueOf(string);
        //            }
        //        });

    }

    private static final Converters INSTANCE = new UtilConverters();

    public static Converters getInstance() {
        return INSTANCE;
    }

    protected UtilConverters() {
        super();
    }
}
