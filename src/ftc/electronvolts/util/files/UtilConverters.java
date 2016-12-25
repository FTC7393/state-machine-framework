package ftc.electronvolts.util.files;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ftc.electronvolts.util.TeamColor;
import ftc.electronvolts.util.Vector2D;
import ftc.electronvolts.util.Vector3D;
import ftc.electronvolts.util.units.Angle;
import ftc.electronvolts.util.units.AngularVelocity;
import ftc.electronvolts.util.units.Distance;
import ftc.electronvolts.util.units.Time;
import ftc.electronvolts.util.units.Velocity;

/**
 * This file was made by the electronVolts, FTC team 7393
 * 
 * An implementation of Converters that extends BasicConverters and adds
 * Converter objects for some of the utility classes
 * 
 * @see BasicConverters
 */
public class UtilConverters extends BasicConverters {
    private static final String DECIMAL_NUMBER = "([0-9\\.\\-]+)";
    private static final String COMMA = " *, *";
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
                Pattern pattern = Pattern.compile("\\(" + DECIMAL_NUMBER + COMMA + DECIMAL_NUMBER + "\\)");
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
                Pattern pattern = Pattern.compile("\\(" + DECIMAL_NUMBER + COMMA + DECIMAL_NUMBER + COMMA + DECIMAL_NUMBER + "\\)");
                Matcher matcher = pattern.matcher(string);
                if (matcher.find()) {
                    return new Vector3D(Double.valueOf(matcher.group(1)), Double.valueOf(matcher.group(2)), Double.valueOf(matcher.group(3)));
                } else {
                    return null;
                }
            }
        });
        converterMap.put(Angle.class, new Converter<Angle>() {

            @Override
            public String toString(Angle object) {
                return String.valueOf(object.radians());
            }

            @Override
            public Angle fromString(String string) {
                try {
                    return Angle.fromRadians(Double.valueOf(string));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        });
        converterMap.put(AngularVelocity.class, new Converter<AngularVelocity>() {

            @Override
            public String toString(AngularVelocity object) {
                return String.valueOf(object.radiansPerSecond());
            }

            @Override
            public AngularVelocity fromString(String string) {
                try {
                    return new AngularVelocity(Angle.fromRadians(Double.valueOf(string)), Time.fromSeconds(1));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        });
        converterMap.put(Distance.class, new Converter<Distance>() {

            @Override
            public String toString(Distance object) {
                return String.valueOf(object.meters());
            }

            @Override
            public Distance fromString(String string) {
                try {
                    return Distance.fromMeters(Double.valueOf(string));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        });
        converterMap.put(Time.class, new Converter<Time>() {

            @Override
            public String toString(Time object) {
                return String.valueOf(object.seconds());
            }

            @Override
            public Time fromString(String string) {
                try {
                    return Time.fromSeconds(Double.valueOf(string));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        });
        converterMap.put(Velocity.class, new Converter<Velocity>() {

            @Override
            public String toString(Velocity object) {
                return String.valueOf(object.metersPerSecond());
            }

            @Override
            public Velocity fromString(String string) {
                try {
                    return new Velocity(Distance.fromMeters(Double.valueOf(string)), Time.fromSeconds(1));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        });
    }

    private static final Converters INSTANCE = new UtilConverters();

    public static Converters getInstance() {
        return INSTANCE;
    }

    protected UtilConverters() {
        super();
    }
}
