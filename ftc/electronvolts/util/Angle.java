package ftc.electronvolts.util;

/**
 * Created by zhivago on 2/2/16.
 * FTC Team EV 7393
 */
public class Angle {
    private final double radians;
    private Angle(double radians){
        this.radians = radians;
    }

    public static Angle fromRadians(double radians){
        return new Angle(radians);
    }

    public static Angle fromDegrees(double degrees){
        return new Angle(Math.toRadians(degrees));
    }

    public double getValueDegrees() {
        return Math.toDegrees(radians);
    }

    public double getValueRadians() {
        return radians;
    }

    public static Angle sum(Angle angle1, Angle angle2){
        return new Angle(angle1.radians + angle2.radians);
    }
}
